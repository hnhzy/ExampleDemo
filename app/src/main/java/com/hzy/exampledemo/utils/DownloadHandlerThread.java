package com.hzy.exampledemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.hzy.exampledemo.ui.Thread.ImageModel;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Description HandlerThread下载测试
 *
 * @author hzy
 * Create on 2019/6/15 11:55
 */
public class DownloadHandlerThread extends HandlerThread {

    private Handler mDownloadHandler;//下载文件的Handler
    private Handler mUiHandler; //处理UI刷新的Handler
    private List<String> listUrls = new ArrayList();

    public DownloadHandlerThread(String name) {
        super(name);
    }

    public DownloadHandlerThread(String name, int priority) {
        super(name, priority);
    }

    public void setUiHandler(Handler mUiHandler, List listUrls) {
        this.mUiHandler = mUiHandler;
        this.listUrls = listUrls;
    }


    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        //初始化
        mDownloadHandler = new Handler(getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //在子线程中进行网络请求
                Bitmap bitmap = downloadUrlBitmap(listUrls.get(msg.what));
                ImageModel imageModel = new ImageModel();
                imageModel.setBitmap(bitmap);
                imageModel.setUrl(listUrls.get(msg.what));
                Message msg1 = new Message();
                msg1.what = msg.what;
                msg1.obj = imageModel;
                //通知主线程去更新UI
                mUiHandler.sendMessage(msg1);
            }
        };
        if (mUiHandler == null) {
            throw new NullPointerException("uiHandler is not null");
        }
        for (int i = 0; i < listUrls.size(); i++) {
            //每个1秒去更新图片
            mDownloadHandler.sendEmptyMessage(i);
        }
    }

    private Bitmap downloadUrlBitmap(String urlString) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        Bitmap bitmap = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

}