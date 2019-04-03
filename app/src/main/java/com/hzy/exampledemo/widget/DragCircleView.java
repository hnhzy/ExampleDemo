package com.hzy.exampledemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author hzy
 * @description: 会动的小圆，绘制一个红色的小圆，随着你手指的移动而移动位置。
 * 要监听手指和屏幕之间的关系，然后确定小圆绘制的位置
 * @date :2019/4/3 14:56
 */
public class DragCircleView  extends View {
    private Paint paint;
    private int currentX = 60;
    private int currentY = 60;
    public DragCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆的过程
        canvas.drawCircle(currentX,currentY,30,paint);
    }

    /**
     * 触摸事件：手指和屏幕的触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //手指在x轴的位置
        currentX = (int) event.getX();
        //手指在y轴的位置
        currentY = (int) event.getY();
        invalidate();   //重绘当前界面的方法
        return true;
    }
}
