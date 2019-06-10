package com.hzy.exampledemo.ui.ndk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hzy.exampledemo.R;

public class NdkActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);
        tv = findViewById(R.id.tv);
        tv.setText(HelloWorld.sayHello());
    }
}
