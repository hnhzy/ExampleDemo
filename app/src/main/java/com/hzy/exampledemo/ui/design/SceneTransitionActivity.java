package com.hzy.exampledemo.ui.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.adapter.SceneListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzy on 2019/3/23
 *
 * @author Administrator
 */
public class SceneTransitionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView mRvList;
    private SceneListAdapter mAdapter;
    private List<Integer> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_transition);
        initView();
    }

    private void initView() {
        mList = new ArrayList<>();
        mList.clear();
        mList.add(R.drawable.anim1);
        mList.add(R.drawable.anim2);
        mList.add(R.drawable.anim3);
        mList.add(R.drawable.anim4);
        mList.add(R.drawable.anim5);
        mList.add(R.drawable.anim6);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("SceneTransitionActivity");
        setSupportActionBar(toolbar);
        // 给左上角图标的左边加上一个返回的图标 。
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRvList = findViewById(R.id.rv_list);
        mRvList.setHasFixedSize(true);
        mAdapter = new SceneListAdapter(this, mList);
        mRvList.setLayoutManager(new GridLayoutManager(this, 2));
        mRvList.setAdapter(mAdapter);
    }
}
