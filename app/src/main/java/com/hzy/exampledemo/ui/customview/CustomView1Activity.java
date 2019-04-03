package com.hzy.exampledemo.ui.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.widget.MoveView;

/**
 * @author hzy
 * @description: 可以移动的Button
 * @date :2019/3/28 18:24
 */
public class CustomView1Activity extends AppCompatActivity {

    private MoveView mv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view1);
        mv = findViewById(R.id.mv);
        mv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomView1Activity.this, "OnClick-点击了Button", Toast.LENGTH_SHORT).show();
            }
        });
        mv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(CustomView1Activity.this, "OnLongClick-点击了Button",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
