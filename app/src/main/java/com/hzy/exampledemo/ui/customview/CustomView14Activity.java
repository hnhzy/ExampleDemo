package com.hzy.exampledemo.ui.customview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.listener.MoveViewListener;
import com.hzy.exampledemo.ui.MainActivity;
import com.hzy.exampledemo.widget.GesturePasswordView;
import com.hzy.exampledemo.widget.GesturePasswordViewListener;
import com.hzy.exampledemo.widget.MoveView2;
import com.hzy.exampledemo.widget.radarview.RadarView;

/**
* Description 指纹解锁
* @author hzy
* Create on 2019/7/18 16:26
*/
public class CustomView14Activity extends Activity {

    private GesturePasswordView mGesturePasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view14);
        mGesturePasswordView = findViewById(R.id.gestureView);
        mGesturePasswordView.setGesturePasswordViewListener(new GesturePasswordViewListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(CustomView14Activity.this, "解锁成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError() {
                Toast.makeText(CustomView14Activity.this, "至少四个点", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure() {
                Toast.makeText(CustomView14Activity.this, "解锁失败", Toast.LENGTH_SHORT).show();

            }
        });

    }
}