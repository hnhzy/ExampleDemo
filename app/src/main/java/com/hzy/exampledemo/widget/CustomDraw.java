package com.hzy.exampledemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.utils.DipUtil;

/**
 * @author hzy
 * @description:
 * @date :2019/4/1 15:43
 */
public class CustomDraw extends View {

    private Context mContext;
    /**
     * 定义一个paint
     */
    private Paint mPaint;

    public CustomDraw(Context context) {
        this(context, null);
    }

    public CustomDraw(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawNomal(canvas);
        drawTest(canvas);
    }

    /**
     * 常规绘制  以(0,0)作为坐标原点参考点
     *
     * @param canvas
     */
    private void drawNomal(Canvas canvas) {
        mPaint = new Paint();
        // 绘制画布背景
        canvas.drawColor(getResources().getColor(R.color.c_f2f2f2));
        //设置画笔颜色
        mPaint.setColor(Color.BLUE);
        //设置画笔为空心
        mPaint.setStyle(Paint.Style.STROKE);
        //绘制直线
        canvas.drawLine(50, 50, 450, 50, mPaint);
        //绘制矩形
        canvas.drawRect(100, 100, 200, 300, mPaint);
        //绘制矩形
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(300, 100, 400, 400, mPaint);
        mPaint.setColor(Color.YELLOW);
        RectF r = new RectF(150, 500, 270, 600);
        // 画矩形
        canvas.drawRect(r, mPaint);
        // 画圆
        canvas.drawCircle(50, 500, 50, mPaint);
        RectF oval = new RectF(350, 500, 450, 700);
        // 画椭圆
        canvas.drawOval(oval, mPaint);
        RectF rect = new RectF(100, 700, 170, 800);
        // 画圆角矩形
        canvas.drawRoundRect(rect, 30, 20, mPaint);
        //绘制圆弧 绘制弧形
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        RectF re1 = new RectF(880, 0, 1000, 120);
        canvas.drawArc(re1, 0, 90, false, mPaint);
        RectF re2 = new RectF(880, 300, 1000, 420);
        canvas.drawArc(re2, 0, 90, true, mPaint);
        //设置Path路径
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(3);
        Path path = new Path();
        path.moveTo(500, 100);
        path.lineTo(820, 80);
        path.lineTo(700, 200);
        path.lineTo(580, 400);
        path.close();
        mPaint.setTextSize(DipUtil.sp2px(getContext(), 14));
        canvas.drawPath(path, mPaint);
        canvas.drawTextOnPath("gjalgjdlsagjdlsajgldsjglgjdslajgdlsj", path, -20, -20, mPaint);
        //三角形
        path.moveTo(10, 330);
        path.lineTo(70, 330);
        path.lineTo(40, 270);
        path.close();
        canvas.drawPath(path, mPaint);
        //把开始的点和最后的点连接在一起，构成一个封闭梯形
        path.moveTo(10, 410);//绘画基点
        path.lineTo(70, 410);
        path.lineTo(55, 350);
        path.lineTo(25, 350);
        //如果是Style.FILL的话，不设置close,也没有区别，可是如果是STROKE模式， 如果不设置close,
        // 图形不封闭。当然，你也可以不设置close，再添加一条线，效果一样。
        path.close();
        canvas.drawPath(path, mPaint);
        //参数一为渐变起初点坐标x位置，参数二为y轴位置，参数三和四分辨对应渐变终点,其中参数new int[]{startColor, midleColor,
        // endColor}是参与渐变效果的颜色集合，
        // 其中参数new float[]{0 , 0.5f, 1.0f}是定义每个颜色处于的渐变相对位置， 这个参数可以为null，如果为null表示所有的颜色按顺序均匀的分布
        // Shader.TileMode三种模式
        // REPEAT:沿着渐变方向循环重复
        // CLAMP:如果在预先定义的范围外画的话，就重复边界的颜色
        // MIRROR:与REPEAT一样都是循环重复，但这个会对称重复
        Shader mShader = new LinearGradient(0, 0, 100, 100,
                new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW},
                null, Shader.TileMode.REPEAT);
        mPaint.setShader(mShader);// 用Shader中定义定义的颜色来话
        mPaint.setStyle(Paint.Style.FILL);
        Path path1 = new Path();
        path1.moveTo(170, 410);
        path1.lineTo(230, 410);
        path1.lineTo(215, 350);
        path1.lineTo(185, 350);
        path1.close();
        canvas.drawPath(path1, mPaint);
        canvas.save();
    }

    /**
     * 绘制方法练习
     *
     * @param canvas
     */
    private void drawTest(Canvas canvas) {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        //平移测试
        canvas.translate(50, 1000);
        canvas.drawRect(new Rect(0, 0, 100, 100), mPaint);
        canvas.translate(0, 180);
        canvas.drawRect(new Rect(0, 0, 100, 100), mPaint);
        //缩放测试
        canvas.translate(150, -50);
        canvas.drawRect(new Rect(0, 0, 200, 200), mPaint);
        // 保存画布状态
        canvas.save();
        canvas.scale(0.5f, 0.5f);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(new Rect(0, 0, 200, 200), mPaint);
        // 画布状态回滚
        canvas.restore();
        // 先将画布平移到矩形的中心
        canvas.translate(300, -650);
        canvas.drawRect(new Rect(0, 0, 200, 200), mPaint);
        //旋转测试
        canvas.save();
        canvas.translate(250, 0);
        canvas.drawRect(new Rect(0, 0, 200, 200), mPaint);
        canvas.translate(-350, 350);
        mPaint.setColor(Color.RED);
        canvas.rotate(45, 200, 200);
        canvas.drawRect(new Rect(0, 0, 200, 200), mPaint);
        canvas.restore();
        //画布错切 三角函数tan的值
        canvas.translate(300, 300);
        canvas.drawRect(new Rect(0, 0, 200, 200), mPaint);
        canvas.translate(0, 300);
        // y 方向上倾斜45 度
        canvas.skew(0, 1);
        mPaint.setColor(0x8800ff00);
        canvas.drawRect(new Rect(0, 0, 200, 200), mPaint);

        canvas.translate(-300, 300);
        mPaint.setColor(Color.BLACK);
        //实例化路径
        Path path = new Path();
        // 此点为多边形的起点
        path.moveTo(0, 0);
        path.lineTo(120, 0);
        path.lineTo(0, 120);
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, mPaint);
    }
}
