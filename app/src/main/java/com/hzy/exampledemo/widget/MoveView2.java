package com.hzy.exampledemo.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.hzy.exampledemo.listener.MoveViewListener;
import com.hzy.exampledemo.utils.DipUtil;

/**
 * @author hzy
 * @description:
 * @date :2019/3/27 16:17
 */
public class MoveView2 extends FrameLayout {

    /**
     * 开始的位置
     */
    private int startLacation = 10;

    /**
     * 可拖拽按钮-触摸开始时间
     */
    private long startTime = 0;

    /**
     * 判断是点击还是移动
     */
    private boolean isClick = true;

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

    private MoveViewListener mMoveViewListener;

    public MoveViewListener getmMoveViewListener() {
        return mMoveViewListener;
    }

    public void setmMoveViewListener(MoveViewListener mMoveViewListener) {
        this.mMoveViewListener = mMoveViewListener;
    }

    public MoveView2(Context context) {
        super(context);
        init();
    }

    public MoveView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MoveView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 在自定义控件中重写onInterceptTouchEvent就告诉所有父View：不要拦截事件，让我消费！！
     * 否则滑动事件让NestedScrollView直接消费掉了
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(ev);
    }

    private void init() {
        statusHeight = DipUtil.getStatusHeight(getContext());
        screenWidth = DipUtil.getScreenWidth(getContext());
        screenHeight = DipUtil.getScreenHeight(getContext());
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         *         event.getX();
         *         event.getY();
         * 控件点击的位置相对于自身X,Y的值（以左上角为基础）
         */

        /**
         *         event.getRawX();
         *         event.getRawY();
         * 控件相对于于屏幕X,Y的值（以左上角为基础）
         */
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isClick = true;
                startTime = System.currentTimeMillis();
                downX = (int) event.getRawX();
                downY = (int) event.getRawY();
                mMoveViewListener.downAction(downX,downY);
                break;
            case MotionEvent.ACTION_MOVE:
                isClick = false;
                //手指有移动才更新位置
                if (Math.abs(x - downX) > startLacation || Math.abs(y - downY) > startLacation) {
                    moveViewByLayout(this, x, y);
                }
                break;
            case MotionEvent.ACTION_UP:
                boolean upTime = System.currentTimeMillis() - startTime < 100;
                if (isClick) {
                    if (upTime) {
                        performClick();
                    } else {
                        performLongClick();
                    }
                }
                break;
            default:
                break;
        }
        return true;
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
        //记录左上点的位置
        int l = rawX - view.getWidth() / 2;
        int t = rawY - statusHeight - view.getHeight() / 2;

        //检测左右边界
        if (l < 0) {
            l = 0;
        } else if (l > screenWidth - view.getWidth()) {
            l = screenWidth - view.getWidth();
        }

        //检测上下边界
        if (t < 0) {
            t = 0;
        } else if (t > screenHeight - statusHeight - view.getHeight()) {
            t = screenHeight - statusHeight - view.getHeight();
        }

        //记录右下点的位置
        int r = l + view.getWidth();
        int b = t + view.getHeight();

        view.layout(l, t, r, b);
        mMoveViewListener.moveAction(l,t,r,b);
    }
}
