package com.hzy.exampledemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.hzy.exampledemo.utils.DipUtil;

/**
 * @author hzy
 * @description:
 * @date :2019/4/3 17:47
 */
public class ClockView extends View {

    private Paint mPaint;

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        //将位置移动画纸的坐标点:canvas.getWidth()/2, 400
        canvas.translate(canvas.getWidth() / 2, 400);
        //画圆圈 以200为半径画外圈
        canvas.drawCircle(0, 0, 200, mPaint);

        //使用path绘制路径文字
        canvas.save();
        canvas.translate(-150, -150);
        Path path = new Path();
        path.addArc(new RectF(0, 0, 300, 300), -180, 180);
        Paint citePaint = new Paint(mPaint);
        citePaint.setTextSize(DipUtil.sp2px(getContext(), 12));
        citePaint.setStrokeWidth(1);
        canvas.drawTextOnPath("www.wanandroid.com", path, 28, 0, citePaint);
        canvas.restore();

        //小刻度画笔对象
        Paint tmpPaint = new Paint(mPaint);
        tmpPaint.setStrokeWidth(1);

        float y = 200;
        //总刻度数
        int count = 60;
        tmpPaint.setTextSize(DipUtil.sp2px(getContext(), 12));
        for (int i = 0; i < count; i++) {
            if (i % 5 == 0) {
                canvas.drawLine(0f, y, 0, y + 12f, mPaint);
                canvas.drawText(String.valueOf(i / 5 + 1), -12f, y + 60f, tmpPaint);
            } else {
                canvas.drawLine(0f, y, 0f, y + 5f, tmpPaint);
            }
            //旋转画纸
            canvas.rotate(360 / count, 0f, 0f);
        }
        //绘制指针
        tmpPaint.setColor(Color.GRAY);
        tmpPaint.setStrokeWidth(4);
        canvas.drawCircle(0, 0, 7, tmpPaint);
        tmpPaint.setStyle(Paint.Style.FILL);
        tmpPaint.setColor(Color.YELLOW);
        canvas.drawCircle(0, 0, 5, tmpPaint);
        canvas.drawLine(0, 10, 0, -65, mPaint);

    }
}
