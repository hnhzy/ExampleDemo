package com.hzy.exampledemo.ui.anim;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.hzy.exampledemo.R;

public class ValueAnimatorActivity extends Activity {


    ImageView iv;
    ImageView iv2;
    private float[] mCurrentPosition = new float[2];
    private PathMeasure pathMeasure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_value_anim);
        iv = findViewById(R.id.iv);
        iv2 = findViewById(R.id.iv2);
        //添加视图加载完成监听，getLocationInWindow需等到视图加载完成后才能返回正确值，否则为0
        iv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                startRotate();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void startRotate() {

        //获取控件当前位置
        int[] startLoc = new int[2];
        iv2.getLocationInWindow(startLoc);

        //获取被围绕控件的起始点
        int[] parentStart = new int[2];
        iv.getLocationInWindow(parentStart);

        //获取被围绕坐标的终点
        int[] parentEnd = new int[2];
        parentEnd[0] = parentStart[0] + iv.getWidth();
        parentEnd[1] = parentStart[1] + iv.getHeight();

        //构建椭圆
//        KLog.i((parentStart[0]-deviation)+"   " +(parentStart[1]-deviation)+"   "+(parentEnd[0]+deviation)+"   "+(parentEnd[1]+deviation));
        Path path = new Path();

        RectF rectF = new RectF(parentStart[0] - 120, parentStart[1] - 220, parentEnd[0] + 100, parentEnd[1]);//椭圆大小需自己调整
        path.addArc(rectF, 0, 360);

        //设置椭圆倾斜度数
        Matrix matrix = new Matrix();
        matrix.setRotate(-14, (parentStart[0] + parentEnd[0]) / 2, (parentStart[1] + parentEnd[1]) / 2);
        path.transform(matrix);

        //pathMeasure用来计算显示坐标
        pathMeasure = new PathMeasure(path, true);

        //属性动画加载
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());

        //设置动画时长
        valueAnimator.setDuration(10000);

        //加入差值器
        valueAnimator.setInterpolator(new LinearInterpolator());

        //设置无限次循环
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);

        //添加监听
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取当前位置
                float value = (float) animation.getAnimatedValue();
                //boolean getPosTan(float distance, float[] pos, float[] tan) ：
                //传入一个距离distance(0<=distance<=getLength())，然后会计算当前距
                // 离的坐标点和切线，pos会自动填充上坐标
                pathMeasure.getPosTan(value, mCurrentPosition, null);
                //打印当前坐标
//                KLog.i(mCurrentPosition[0]+"    "+mCurrentPosition[1]);
                //设置视图坐标
                iv2.setX(mCurrentPosition[0]);
                iv2.setY(mCurrentPosition[1]);
            }
        });

        valueAnimator.start();
    }
}