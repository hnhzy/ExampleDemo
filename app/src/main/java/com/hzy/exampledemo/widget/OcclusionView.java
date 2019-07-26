package com.hzy.exampledemo.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hzy.exampledemo.R;


/**
 * Description 遮挡的View
 *
 * @author hzy
 * Create on 2019/7/23 10:13
 */
public class OcclusionView extends View {

    private Paint mPaint;
    private int mMaskColor; // 取景框外的背景颜色
    private int mCameraWidth;
    private int mCameraHeight;
    private int mPicWidth;
    private int mPicHeight;
    private OcclusionView mOcclusionView;

    public OcclusionView(Context context) {
        super(context);
        initView();
    }

    public OcclusionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        Resources resources = getResources();
        mMaskColor = resources.getColor(R.color.black_back);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 绘制取景框外的暗灰色的表面，分四个矩形绘制
        mPaint.setColor(mMaskColor);
    }

    public void setOcclusionView(OcclusionView mOcclusionView, int mCameraWidth, int mCameraHeight, int mPicWidth, int mPicHeight) {
        this.mOcclusionView = mOcclusionView;
        this.mCameraWidth = mCameraWidth;
        this.mCameraHeight = mCameraHeight;
        this.mPicWidth = mPicWidth;
        this.mPicHeight = mPicHeight;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (mOcclusionView == null) {
            return; // not ready yet, early draw before done configuring
        }

//        canvas.drawRect(0, 0, mCameraWidth, mCameraHeight, paint);// 黑色区域
//        int tranColor = Resources.getSystem().getColor(android.R.color.transparent);
//        paint.setColor(tranColor);
//        canvas.drawRect((mCameraWidth - mPicWidth) / 2, (mCameraHeight - mPicHeight) / 2, (mCameraWidth + mPicWidth) / 2, (mCameraHeight + mPicHeight) / 2, paint); //透明区域

        Path path1 = new Path();
        path1.addRect(new RectF(0, 0, mCameraWidth, mCameraHeight), Path.Direction.CW);

        Path path2 = new Path();
        path2.addRect(new RectF((mCameraWidth - mPicWidth) / 2, (mCameraHeight - mPicHeight) / 2, (mCameraWidth + mPicWidth) / 2, (mCameraHeight + mPicHeight) / 2), Path.Direction.CW);

        path1.op(path2, Path.Op.DIFFERENCE);
        canvas.drawPath(path1, mPaint);

    }
}
