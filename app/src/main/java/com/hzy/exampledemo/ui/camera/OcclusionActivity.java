package com.hzy.exampledemo.ui.camera;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.utils.DipUtil;
import com.hzy.exampledemo.widget.OcclusionView;

public class OcclusionActivity extends AppCompatActivity {

    private OcclusionView mOcclusionView;
    private RelativeLayout mRlRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occ);
        mRlRoot = findViewById(R.id.rl_root);
        mOcclusionView = findViewById(R.id.v_occ);
        mOcclusionView.setOcclusionView(mOcclusionView, DipUtil.dip2px(this, 300), DipUtil.dip2px(this, 300), DipUtil.dip2px(this, 200), DipUtil.dip2px(this, 200));
    }
}
