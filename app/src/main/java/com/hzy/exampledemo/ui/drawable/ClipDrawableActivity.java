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
    private Thread mThread;
    private Handler mHandler;

    static class MyHandler extends Handler {

        WeakReference<ClipDrawableActivity> mWeakReference;

        public MyHandler(ClipDrawableActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ClipDrawableActivity activity = mWeakReference.get();
            if (null != activity) {
                if (msg.what == 0) {
                    Log.d("EasyMomentFragment", "EasyMomentFragment1");
                    activity.clipDrawable.setLevel(msg.what);
                    activity.mHandler.sendEmptyMessageDelayed(1000, 60);
                } else {
                    Log.d("EasyMomentFragment", "EasyMomentFragment2");
                    if (activity.clipDrawable.getLevel() <= 10000) {
                        activity.clipDrawable.setLevel(msg.what);
                        activity.mHandler.sendEmptyMessageDelayed(activity.clipDrawable.getLevel() + 1000, 60);
                    } else {
                        //皮肤属性动画
                        activity.mHandler.removeMessages(msg.what);
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
        mThread = new MyThread(mHandler);
        clipDrawable = (ClipDrawable) mIvDmhs.getDrawable();
        clipDrawable.setLevel(0);
        mThread.start();
    }


    /**
     * 使用静态的线程防止内存泄露
     */
    public static class MyThread extends Thread {

        private Handler handler;

        public MyThread(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            super.run();
            Message msg = Message.obtain();
            msg.what = 0;
            handler.sendMessage(msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
