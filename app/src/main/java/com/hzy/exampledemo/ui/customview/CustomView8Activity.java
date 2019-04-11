package com.hzy.exampledemo.ui.customview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.hzy.exampledemo.R;

import java.util.ArrayList;

/**
 * @author hzy
 * @description: 时钟绘制
 * @date :2019/3/28 18:24
 */
public class CustomView8Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new CustomView1(this));

    }

    /**
     * 使用内部类 自定义一个简单的View
     * @author Administrator
     *
     */
    class CustomView1 extends View{

        Paint paint;
        private ArrayList<PointF> graphics = new ArrayList<PointF>();
        PointF point;

        public CustomView1(Context context) {
            super(context);
            //设置一个笔刷大小是3的黄色的画笔
            paint = new Paint();
            paint.setColor(Color.YELLOW);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(3);

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            graphics.add(new PointF(event.getX(),event.getY()));

            invalidate(); //重新绘制区域

            return true;
        }

        //在这里我们将测试canvas提供的绘制图形方法
        @Override
        protected void onDraw(Canvas canvas) {
            for (PointF point : graphics) {
                canvas.drawPoint(point.x, point.y, paint);
            }
//          super.onDraw(canvas);

        }
    }

}