package com.hzy.exampledemo.ui.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.widget.MoveView;
import com.hzy.exampledemo.widget.MoveView2;

/**
 * @author hzy
 * @description: 可以移动的Button
 * @date :2019/3/28 18:24
 */
public class CustomView1Activity extends AppCompatActivity {

    private MoveView2 mv;
    private RelativeLayout rl_root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view1);
//        mv = findViewById(R.id.mv);
//        rl_root = findViewById(R.id.rl_root);
//        mv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(CustomView1Activity.this, "OnClick-点击了Button", Toast.LENGTH_SHORT).show();
//            }
//        });
//        mv.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(CustomView1Activity.this, "OnLongClick-点击了Button",
//                        Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 300);
//        mv = new MoveView2(this);
//        mv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//        mv.setTop(500);
//        mv.setBottom(600);
//        mv.setLeft(500);
//        mv.setRight(600);
//        mv.setWidth(100);
//        mv.setHeight(100);
//        mv.setLayoutParams(params);
//        rl_root.addView(mv);

    }
}
