package com.hzy.exampledemo.widget.radarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.utils.DipUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hzy
 * @description:
 * @date :2019/4/1 15:43
 */
public class AbilityView extends View {

    /**
     * 名字和分数列表数据
     */
    private List<AbilityBean> abList;

    /**
     * 角的个数
     */
    private int n;

    /**
     * 顶点到中心点的距离
     */
    private float radius;

    /**
     * 半径分为几段
     */
    private int intervalCount;

    /**
     * 间隔的角度
     */
    private float angle;

    /**
     * 画线的笔
     */
    private Paint linePaint;

    /**
     * 画文字的笔
     */
    private Paint textPaint;

    /**
     * 存储多边形顶点数组的数组
     */
    private List<List<PointF>> pointsArrayList;

    /**
     * 存储能力点的数组
     */
    private ArrayList<PointF> abilityPoints;

    /**
     * 控制变化的参数
     */
    private static int count = 0;
    private int times = 40;
    private int intervalTime = 25;

    /**
     * 计时线程池
     */
    private ScheduledExecutorService scheduledExecutorService;
    private MyHandler myHandler;

    public AbilityView(Context context) {
        this(context, null);
    }

    public AbilityView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbilityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // 获取自定义的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AbilityView);
        n = typedArray.getInteger(R.styleable.AbilityView_corner, 7);
        radius = typedArray.getDimension(R.styleable.AbilityView_radius, DipUtil.dip2px(context,
                100));
        intervalCount = typedArray.getInteger(R.styleable.AbilityView_intervalCount, 4);
        typedArray.recycle();
        // 算出间隔角度
        angle = (float) ((2 * Math.PI) / n);
        // 初始化集合存储各个顶点
        pointsArrayList = new ArrayList<>();
        float x;
        float y;
        for (int i = 0; i < intervalCount; i++) {
            List<PointF> pointFList = new ArrayList<>();
            float r = radius * ((float) (intervalCount - i) / intervalCount);
            for (int j = 0; j < n; j++) {
                // 此处减去π／2是为了让点逆时针旋转90度（为了让图是立着的 更加美观）
                x = (float) (r * Math.cos(j * angle - Math.PI / 2));
                y = (float) (r * Math.sin(j * angle - Math.PI / 2));
                pointFList.add(new PointF(x, y));
            }
            pointsArrayList.add(pointFList);
        }
        abilityPoints = new ArrayList<>();
        // 初始化画笔
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStrokeWidth(DipUtil.dip2px(context, 1f));
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(DipUtil.sp2px(context, 14));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.BLACK);
        // 初始化线程池 用于动画变动
        scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        myHandler = new MyHandler(this);
    }

    /**
     * 使用静态的Handler防止内存泄露
     */
    private static class MyHandler extends Handler {

        WeakReference<View> weakReference;

        public MyHandler(View view) {
            weakReference = new WeakReference<>(view);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            count++;
            weakReference.get().invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 将画布移动到中心
        canvas.translate(getWidth() / 2, getHeight() / 2);
        // 画每个面
        drawPolygon(canvas);
        // 勾勒外围轮廓线
        drawOutLine(canvas);
        // 绘制文本
        drawText(canvas);
        // 画出能力值的线
        drawAbility(canvas);
    }

    private void drawPolygon(Canvas canvas) {
        canvas.save();
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Path path = new Path();
        for (int i = 0; i < intervalCount; i++) {
            switch (i) {
                case 0:
                    linePaint.setColor(Color.parseColor("#D4F0F3"));
                    break;
                case 1:
                    linePaint.setColor(Color.parseColor("#99DCE2"));
                    break;
                case 2:
                    linePaint.setColor(Color.parseColor("#56C1C7"));
                    break;
                case 3:
                    linePaint.setColor(Color.parseColor("#278891"));
                    break;
                default:
            }
            for (int j = 0; j < n; j++) {
                float x = pointsArrayList.get(i).get(j).x;
                float y = pointsArrayList.get(i).get(j).y;
                if (j == 0) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, linePaint);
            path.reset();
        }
        canvas.restore();
    }

    private void drawOutLine(Canvas canvas) {
        canvas.save();
        Path path = new Path();
        linePaint.setColor(Color.parseColor("#99DCE2"));
        linePaint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < n; i++) {
            float x = pointsArrayList.get(0).get(i).x;
            float y = pointsArrayList.get(0).get(i).y;
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
            canvas.drawLine(0, 0, x, y, linePaint);
        }
        path.close();
        canvas.drawPath(path, linePaint);
        canvas.restore();
    }

    private void drawText(Canvas canvas) {
        canvas.save();
        Paint pointPaint = new Paint();
        pointPaint.setStrokeWidth(DipUtil.dip2px(getContext(), 2f));
        pointPaint.setStyle(Paint.Style.STROKE);
        float r = radius + DipUtil.dip2px(getContext(), 15f);
        Paint.FontMetrics metrics = textPaint.getFontMetrics();
        float textFont = (metrics.descent + metrics.ascent) / 2f;
        for (int i = 0; i < n; i++) {
            float x = (float) (r * Math.cos(angle * i - Math.PI / 2));
            float y = (float) (r * Math.sin(angle * i - Math.PI / 2)) - textFont;
            canvas.drawText(abList.get(i).getAbName(), x, y, textPaint);
        }
        canvas.restore();
    }

    private void drawAbility(final Canvas canvas) {
        if (abList == null) {
            return;
        }
        linePaint.setColor(Color.parseColor("#E96153"));
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(DipUtil.dip2px(getContext(), 1f));
        // 获取数据的点
        for (int j = 0; j < n; j++) {
            float percent = abList.get(j).getAbScore() / 100f;
            float x = pointsArrayList.get(0).get(j).x * percent;
            float y = pointsArrayList.get(0).get(j).y * percent;
            abilityPoints.add(new PointF(x, y));
        }
        // 画本次的一圈
        Path path = new Path();
        for (int j = 0; j < n; j++) {
            float x = (count * abilityPoints.get(j).x) / times;
            float y = (count * abilityPoints.get(j).y) / times;
            if (j == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        path.close();
        canvas.drawPath(path, linePaint);
        path.reset();
    }

    public void setData(List<AbilityBean> abList) {
        if (abList == null) {
            return;
        }
        // count 未重置之前 跳过本次调用
        if (count == 0 && !abList.equals(this.abList)) {
            this.abList = abList;
            if (scheduledExecutorService.isShutdown()) {
                scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
            }
            count = 0;
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    if (count < times) {
                        Message message = new Message();
                        myHandler.sendMessage(message);
                    } else {
                        scheduledExecutorService.shutdown();
                        count = 0;
                        abilityPoints.clear();
                    }
                }
            }, intervalTime, intervalTime, TimeUnit.MILLISECONDS);
        }

    }

}
