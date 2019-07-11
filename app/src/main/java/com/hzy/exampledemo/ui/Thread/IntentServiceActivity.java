package com.hzy.exampledemo.ui.Thread;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.utils.DownloadFileService;

import java.io.File;

public class IntentServiceActivity extends AppCompatActivity {

    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
    }

    public void onClick(View v) {
        // new CheckedUpdate().execute();
        //https://www.apk3.com/app/275283.html
        String url = "http://a3gyxz.syzjxz2018.cn/a31/rj_xgd1/chachayunyou.apk";
        Intent downloadIntent = new Intent(this, DownloadFileService.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        downloadIntent.putExtras(bundle);
        startService(downloadIntent);
        // 设置广播接收器，当新版本的apk下载完成后自动弹出安装界面
        IntentFilter intentFilter = new IntentFilter("com.test.downloadComplete");
        receiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent) {
                Intent install = new Intent(Intent.ACTION_VIEW);
                String pathString = intent.getStringExtra("downloadFile");
                install.setDataAndType(Uri.fromFile(new File(pathString)), "application/vnd.android.package-archive");
                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(install);
            }
        };
        registerReceiver(receiver, intentFilter);
    }

    protected void onDestroy() {
        // 移除广播接收器
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        super.onDestroy();
    }
}
