package com.hzy.exampledemo.ui.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.widget.SideBar;

/**
 * @author hzy
 * @description: onDraw
 * @date :2019/3/28 18:24
 */
public class CustomView5Activity extends AppCompatActivity {

    private SideBar sb;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view5);
        sb=findViewById(R.id.sb);
        tv=findViewById(R.id.tv);
        sb.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                sb.setTextView(tv);
            }
        });
    }
}
