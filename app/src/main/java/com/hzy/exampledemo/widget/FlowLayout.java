package com.hzy.exampledemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzy
 * @description:
 * @date :2019/3/28 18:24
 */
public class FlowLayout extends ViewGroup {

    /**
     * 每一行view的集合
     */
    private List<List<View>> mAllViews = new ArrayList<>();
    /**
     * 每一行的高度
     */
    private List<Integer> mLineHeight = new ArrayList<>();

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);

        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        // 如果是wrap_content,定义width，height统计FlowLayout的宽高
        int width = 0;
        int height = 0;
        // 记录每一行的宽度与高度
        int lineWidth = 0;
        int lineHeight = 0;
        /**
         * 1、通过getChildCount，获取子View的个数view个数
         */
        int childCount = getChildCount();
        /**
         * 2、遍历childCount，通过getChildAt获取到对应的view
         */
        for (int i = 0; i < childCount; i++) {
            //获取i对应的子View，通过获取他的宽高，确定
            View childView = getChildAt(i);
            /**
             * 3、对childView进行测量
             */
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            // 判断是否换行，如果换行则高度累加，如果不换行则宽度累加
            if (lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight()) {
                // 对比得到最大的宽度
                width = Math.max(width, lineWidth);
                // 重置lineWidth
                lineWidth = childWidth;
                // 记录行高
                height += lineHeight;
                // 重置lineHeight
                lineHeight = childHeight;
            } else {
                // 宽度累加
                lineWidth += childWidth;
                // 得到当前行最大的高度
                lineHeight = Math.max(lineHeight, childHeight);
            }
            // 最后一个的时候，不管是换行，还是未换行，前面都没有处理
            if (i == childCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
            /**
             * 4、确定父布局（FlowLayout）的宽高
             */
            setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth :
                            width + getPaddingLeft() + getPaddingRight(),
                    modeHeight == MeasureSpec.EXACTLY ? sizeHeight :
                            height + getPaddingTop() + getPaddingBottom());
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        mLineHeight.clear();

        // 当前ViewGroup的宽度
        int width = getWidth();
        Log.d("FlowLayout","getWidth="+getWidth());
        Log.d("FlowLayout","getMeasuredWidth="+getMeasuredWidth());

        int lineWidth = 0;
        int lineHeight = 0;

        // 获取每一行的view集合
        List<View> viewList = new ArrayList<>();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            // 换行
            if (lineWidth + childWidth > width - getPaddingLeft() - getPaddingRight()) {
                // 记录当前行的Views
                mAllViews.add(viewList);
                // 记录LineHeight
                mLineHeight.add(lineHeight);
                // 重置当前行的Views
                viewList = new ArrayList<>();
                // 重置宽
                lineWidth = childWidth;
                // 重置高
                lineHeight = childHeight;
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            viewList.add(childView);
        }
        // 处理最后一行
        mAllViews.add(viewList);
        mLineHeight.add(lineHeight);

        // 行的个数
        int lineNum = mAllViews.size();
        // view的初始位置
        int left = getPaddingLeft();
        int top = getPaddingTop();
        for (int i = 0; i < lineNum; i++) {
            viewList = mAllViews.get(i);
            lineHeight = mLineHeight.get(i);

            for (View view : viewList) {
                if (view.getVisibility() == GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + view.getMeasuredWidth();
                int bc = tc + view.getMeasuredHeight();
                view.layout(lc, tc, rc, bc);
                left += view.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return super.onInterceptHoverEvent(event);
    }
}