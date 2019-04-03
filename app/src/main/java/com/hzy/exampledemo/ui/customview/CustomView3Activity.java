package com.hzy.exampledemo.ui.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.widget.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzy
 * @description:流线布局
 * @date :2019/3/28 18:24
 */
public class CustomView3Activity extends AppCompatActivity {

    List<String> mFlowList = new ArrayList<>();
    private FlowLayout mFlv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view3);
        initView();

    }

    private void initView() {
        mFlv = findViewById(R.id.flv);
        for (int i = 0; i < 10; i++) {
            mFlowList.add("mFlowList" + i);
        }
        for (int i = 0; i < mFlowList.size(); i++) {
            TextView view = getView(mFlowList.get(i));
            mFlv.addView(view);
        }
    }

    private TextView getView(String msg) {
        final TextView tv = new TextView(this);
        ViewGroup.MarginLayoutParams lp =
                new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10, 10, 10, 10);
        tv.setPadding(30, 10, 30, 10);
        tv.setLayoutParams(lp);
        tv.setTextSize(14);
        tv.setText(msg);
        tv.setBackgroundResource(R.drawable.shape_solid_blue_corner555);
        tv.setTextColor(getResources().getColor(R.color.white));
        return tv;
    }
}
