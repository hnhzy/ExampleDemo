package com.hzy.exampledemo.ui.drawable;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.hzy.exampledemo.R;

import java.lang.ref.WeakReference;

/**
 * Description ClipDrawableActivity
 *
 * @author hzy
 * Create on 2019/7/12 11:27
 */
public class ClipDrawableActivity extends AppCompatActivity {

    private ImageView mIvDmhs;

    /**
     * ClipDrawable图片逐渐显示的Drawable
     */
    private ClipDrawable clipDrawable;
    private Handler mHandler;

    static class MyHandler extends Handler {

        WeakReference<ClipDrawableActivity> mWeakReference;

        public MyHandler(ClipDrawableActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("ClipDrawable-what", msg.what + "");
            ClipDrawableActivity activity = mWeakReference.get();
            if (null != activity) {
                if (msg.what == 0) {
                    Log.d("ClipDrawableActivity", "ClipDrawableActivity1");
                    activity.clipDrawable.setLevel(msg.what);
                    activity.mHandler.sendEmptyMessageDelayed(1000, 60);
                } else {
                    if (activity.clipDrawable.getLevel() < 10000) {
                        Log.d("ClipDrawableActivity", "ClipDrawableActivity2");
                        activity.clipDrawable.setLevel(msg.what);
                        activity.mHandler.sendEmptyMessageDelayed(activity.clipDrawable.getLevel() + 1000, 60);
                    } else {
                        Log.d("ClipDrawableActivity", "ClipDrawableActivity3");
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_drawable);
        mIvDmhs = findViewById(R.id.iv_dmhs);
        mHandler = new MyHandler(this);
        clipDrawable = (ClipDrawable) mIvDmhs.getDrawable();
        clipDrawable.setLevel(0);
        mHandler.sendEmptyMessage(0);
    }
}
