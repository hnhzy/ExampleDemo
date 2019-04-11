package com.hzy.exampledemo.widget.radarview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


import com.hzy.exampledemo.R;
import com.hzy.exampledemo.utils.DipUtil;

import java.util.List;

/**
 * @author hzy
 * @description:
 * @date :2019/4/1 15:43
 */
public class RadarCharView extends View {
    /**
     * 布局的宽度
     */
    private int mWidth;

    /**
     * 布局的高度
     */
    private int mHeight;

    /**
     * 最大半径
     */
    private float mRadius;

    /**
     * 默认个数
     */
    private int mCount = 0;

    /**
     * 弧度
     */
    private double radian;

    /**
     * 连接线路径
     */
    private Path mLinePath = new Path();

    /**
     * 覆盖层路径
     */
    private Path mLayerPath = new Path();

    /**
     * 雷达画笔
     */
    private Paint mRadarPaint;

    /**
     * 文本画笔
     */
    private Paint mTextPaint;

    /**
     * 覆盖层画笔
     */
    private Paint mLayerPaint;

    /**
     * 连接线画笔
     */
    private Paint mLinePaint;

    /**
     * 雷达网数
     */
    private int mNum = 6;

    /**
     * 数据源
     */
    private List<Float> mData;

    /**
     * 单项检测雷达点
     */
    Bitmap singleDetectionBp;
    private String[] dimension = {"语文", "数学", "化学", "物理", "外语"};

    public RadarCharView(Context context) {
        this(context, null);
    }

    public RadarCharView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarCharView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        mRadarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRadarPaint.setStyle(Paint.Style.STROKE);
        mRadarPaint.setColor(getResources().getColor(R.color.c_2deed9));

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(getResources().getColor(R.color.c_ffffff));

        mLayerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLayerPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mLayerPaint.setColor(getResources().getColor(R.color.c_2ff5c9));
        mLayerPaint.setAlpha(217);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(getResources().getColor(R.color.c_2bebe2));
        mTextPaint.setTextSize(DipUtil.sp2px(getContext(), 16));
        singleDetectionBp = BitmapFactory.decodeResource(getResources(),
                R.drawable.single_detection_bj);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadius = 172.5f;
    }

    /**
     * 绑定数据
     *
     * @param data
     */
    public void setData(List<Float> data) {
        if (data != null) {
            this.mData = data;
            this.mCount = 5;
            this.radian = Math.PI * 2 / mCount;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2 + 10);
        drawRadarBg(canvas);
        drawLayer(canvas);
        drawText(canvas);
    }

    /**
     * 绘制文本内容
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < mCount; i++) {
            float x = (float) (mRadius * Math.sin(radian / 2 - radian * (i + 3)));
            float y = (float) (mRadius * Math.cos(radian / 2 - radian * (i + 3)));
            String text = dimension[i];
            float fontWidth = mTextPaint.measureText(text);
            switch (i) {
                case 0:
                    //语文
                    canvas.drawText(text, x - fontWidth / 2, y - 8, mTextPaint);
                    break;
                case 1:
                    //数学
                    canvas.drawText(text, x + 5, y + fontHeight / 4, mTextPaint);
                    break;
                case 2:
                    //化学
                    canvas.drawText(text, x, y + fontHeight / 2 + 10, mTextPaint);
                    break;
                case 3:
                    //物理
                    canvas.drawText(text, x - fontWidth + 3, y + fontHeight / 2 + 10, mTextPaint);
                    break;
                case 4:
                    //外语
                    canvas.drawText(text, x - fontWidth - 8, y + fontHeight / 4, mTextPaint);
                    break;
            }
        }
    }


    /**
     * 绘制覆盖图层
     *
     * @param canvas
     */
    private void drawLayer(Canvas canvas) {
        mLayerPath.moveTo(0, 0);
        for (int i = 0; i < mCount; i++) {
            float percent = mData.get(i) / 100F;
            float x = (float) (mRadius * Math.sin(radian / 2 - radian * (i + 3)) * percent);
            float y = (float) (mRadius * Math.cos(radian / 2 - radian * (i + 3)) * percent);


            if (i == 0) {
                mLayerPath.moveTo(x, y);
            } else {
                mLayerPath.lineTo(x, y);
            }
            if (percent != 0) {
                // 指定图片绘制区域
                int halfWidth = singleDetectionBp.getWidth() / 2;
                int halfHeight = singleDetectionBp.getHeight() / 2;
                Rect src = new Rect((int) (x - halfWidth), (int) (y - halfHeight),
                        (int) (x + halfWidth), (int) (y + halfHeight));
                canvas.drawBitmap(singleDetectionBp, null, src, mLayerPaint);
            }
        }
        mLayerPath.close();
        canvas.drawPath(mLayerPath, mLayerPaint);
    }

    /**
     * 绘制雷达网格
     *
     * @param canvas
     */
    private void drawRadarBg(Canvas canvas) {
        //雷达网格间距
        float mSpc = mRadius / (mNum - 1);
        for (int i = 1; i < mNum; i++) {
            //计算当前半径
            float curRadius = mSpc * i;
            for (int j = 0; j < mCount; j++) {
                //根据半径，计算出每个点的坐标
                float x = (float) (curRadius * Math.sin(radian / 2 + radian * j));
                float y = (float) (curRadius * Math.cos(radian / 2 + radian * j));
                canvas.drawCircle(0, 0, curRadius, mRadarPaint);
                drawLine(canvas, i, x, y);
            }
        }
    }

    /**
     * 绘制连接线
     *
     * @param canvas
     * @param x
     * @param y
     */
    private void drawLine(Canvas canvas, int i, float x, float y) {
        if (i == mNum - 1) {
            mLinePath.reset();
            mLinePath.moveTo(0, 0);
            mLinePath.lineTo(x, y);
            canvas.drawPath(mLinePath, mLinePaint);
        }
    }
}
