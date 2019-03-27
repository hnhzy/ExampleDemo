package com.hzy.exampledemo.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hzy.exampledemo.utils.DipUtil;

/**
 * @author hzy
 * @description:
 * @date :2019/3/27 16:17
 */
public class MoveView extends AppCompatButton implements View.OnTouchListener {

    /**
     * //可拖拽按钮-触摸开始时间
     */
    private long startTime = 0;

    /**
     * //可拖拽按钮-是否是要点击效果
     */
    private boolean isClick = false;

    /**
     * 按下时View的位置
     */
    private int downX;
    private int downY;
    /**
     * 状态栏的高度
     */
    private int statusHeight;
    /**
     * 屏幕的宽高
     */
    private int screenWidth;
    private int screenHeight;

    public MoveView(Context context) {
        super(context);
        init();
    }

    public MoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        statusHeight = DipUtil.getStatusHeight(getContext());
        screenWidth = DipUtil.getScreenWidth(getContext());
        screenHeight = DipUtil.getScreenHeight(getContext());
        setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x = (int) motionEvent.getRawX();
        int y = (int) motionEvent.getRawY();
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTime = System.currentTimeMillis();
                downX = (int) motionEvent.getRawX();
                downY = (int) motionEvent.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                //手指有移动才更新位置
                if (Math.abs(x - downX) > 10 || Math.abs(y - downY) > 10) {
                    moveViewByLayout(this, x, y);
                }
                break;
            case MotionEvent.ACTION_UP:
                boolean upTime = System.currentTimeMillis() - startTime < 100;
                isClick = !upTime;
                break;
            default:
                break;
        }
        return isClick;
    }

    /**
     * 通过layout方法，移动view
     * 优点：对view所在的布局，要求不苛刻，不要是RelativeLayout，而且可以修改view的大小
     *
     * @param view
     * @param rawX
     * @param rawY
     */
    private void moveViewByLayout(View view, int rawX, int rawY) {
        int left = rawX - view.getWidth() / 2;
        int top = rawY - statusHeight - view.getHeight() / 2;
        //边距检测
        if (left < 0) {
            left = 0;
        } else if (left > screenWidth - view.getWidth()) {
            left = screenWidth - view.getWidth();
        }
        if (top < 0) {
            top = 0;
        } else if (top > screenHeight - statusHeight - view.getHeight()) {
            top = screenHeight - statusHeight - view.getHeight();
        }
        int width = left + view.getWidth();
        int height = top + view.getHeight();
        view.layout(left, top, width, height);
    }
}
