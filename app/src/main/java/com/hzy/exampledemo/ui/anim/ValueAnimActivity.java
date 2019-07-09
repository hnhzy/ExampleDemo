package com.hzy.exampledemo.ui.anim;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.hzy.exampledemo.R;

public class ValueAnimActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_value_anim2);
    }
}