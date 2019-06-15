package com.hzy.exampledemo.ui.Thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.utils.DownloadHandlerThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Description HanlderThreadActivity
 *
 * @author hzy
 * Create on 2019/6/15 11:58
 */
public class HanlderThreadActivity extends AppCompatActivity {

    private DownloadHandlerThread mDownloadHandlerThread;
    private ImageModel imageModel = null;

    private List<String> listUrls=new ArrayList<>();

    private ImageView iv1, iv2, iv3, iv4;

    private Handler mUiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("mUiHandler", "次数:" + msg.what);
            ImageModel model = (ImageModel) msg.obj;
            switch (msg.what) {
                case 0:
                    iv1.setImageBitmap(model.getBitmap());
                    break;
                case 1:
                    iv2.setImageBitmap(model.getBitmap());
                    break;
                case 2:
                    iv3.setImageBitmap(model.getBitmap());
                    break;
                case 3:
                    iv4.setImageBitmap(model.getBitmap());
                    break;
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        initData();
        initView();
        initHandlerThread();
    }

    /**
     * 初始化下载数据列表
     */
    private void initData() {
        listUrls.add("http://img5.imgtn.bdimg.com/it/u=891209561,3636218284&fm=26&gp=0.jpg");
        listUrls.add("http://img4.imgtn.bdimg.com/it/u=3405377191,2814141235&fm=26&gp=0.jpg");
        listUrls.add("http://img4.imgtn.bdimg.com/it/u=3354006971,2210896407&fm=26&gp=0.jpg");
        listUrls.add("http://img0.imgtn.bdimg.com/it/u=3693563304,289022646&fm=26&gp=0.jpg");
    }

    /**
     * 初始化View
     */
    private void initView() {
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);
    }

    /**
     * 初始化HandlerThread
     */
    private void initHandlerThread() {
        //创建异步HandlerThread
        mDownloadHandlerThread = new DownloadHandlerThread("mHandlerThread");
        mDownloadHandlerThread.setUiHandler(mUiHandler, listUrls);
        //必须先开启线程
        mDownloadHandlerThread.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDownloadHandlerThread.quit();
        for (int i = 0; i < listUrls.size(); i++) {
            mUiHandler.removeMessages(i, imageModel);
        }

    }
}