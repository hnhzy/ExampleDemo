package com.hzy.exampledemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.utils.DipUtil;

/**
 * @author hzy
 * @description:
 * @date :2019/3/28 10:20
 */
public class CustomTextView extends AppCompatTextView {

    private static final String TAG = "CustomTextView";
    private int mTextColor;
    private float mTextSize;
    private int mTextMaxLength;
    private String mTextStr;

    /**
     * 绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        mTextColor = ta.getColor(R.styleable.CustomTextView_CustomTextColor,
                getResources().getColor(R.color.c_333333));
        mTextSize = ta.getDimension(R.styleable.CustomTextView_CustomTextSize,
                DipUtil.sp2px(context, 14));
        mTextMaxLength = ta.getInt(R.styleable.CustomTextView_CustomTextMaxLength,
                10);
        mTextStr = ta.getString(R.styleable.CustomTextView_CustomText);
        ta.recycle();
        initTextView();
    }

    /**
     * 初始化数据
     */
    private void initTextView() {
        //初始化Paint数据
        mPaint = new Paint();
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);

        //获取绘制的宽高
        mBound = new Rect();
        mPaint.getTextBounds(mTextStr, 0, mTextStr.length(), mBound);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制文字
        canvas.drawText(mTextStr, getWidth() / 2 - mBound.width() / 2,
                getHeight() / 2 + mBound.height() / 2, mPaint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        Log.e("CustomTextViewWidth", "---speSize = " + specSize + "");

        switch (specMode) {
            case MeasureSpec.AT_MOST:
                result = (int) mPaint.measureText(mTextStr) + getPaddingLeft() + getPaddingRight();

                Log.e("CustomTextViewWidth", "---speMode = AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                Log.e("CustomTextViewWidth", "---speMode = EXACTLY");
                result = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                Log.e("CustomTextViewWidth", "---speMode = UNSPECIFIED");
                result = Math.max(result, specSize);
        }
        Log.e("CustomTextViewWidth", "---result = "+result);
        return result;
    }


    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        Log.e("CustomTextViewHeight", "---speSize = " + specSize + "");

        switch (specMode) {
            case MeasureSpec.AT_MOST:
                result =
                        (int) (-mPaint.ascent() + mPaint.descent()) + getPaddingTop() + getPaddingBottom();
                Log.e("CustomTextViewHeight", "---speMode = AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                Log.e("CustomTextViewHeight", "---speSize = EXACTLY");
                break;
            case MeasureSpec.UNSPECIFIED:
                result = Math.max(result, specSize);
                Log.e("CustomTextViewHeight", "---speSize = UNSPECIFIED");
                break;
        }
        Log.e("CustomTextViewHeight", "---result = "+result);
        return result;
    }
}
