package com.hzy.exampledemo.ui.design;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.adapter.DiffAdapter;
import com.hzy.exampledemo.bean.Person;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzy on 2019/3/14
 *
 * @author Administrator
 */
public class FabActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private RecyclerView mRvList;
    private SmartRefreshLayout mRefreshLayout;
    private DiffAdapter mAdapter;
    private List<Person> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("FloatingActionButton");
        setSupportActionBar(toolbar);
        // 给左上角图标的左边加上一个返回的图标 。
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRvList.scrollToPosition(10);
                mRvList.smoothScrollToPosition(0);
            }
        });


        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRvList = findViewById(R.id.rv_list);
        mRvList.setHasFixedSize(true);
        for (int i = 0; i < 20; i++) {
            mList.add(new Person(i, "FloatingActionButton1" + i, i));
        }
        mAdapter = new DiffAdapter(this, mList);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                for (int i = 0; i < 20; i++) {
                    mList.add(new Person(i, "FloatingActionButton2" + i, i));
                }
                mAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mList.clear();
                for (int i = 0; i < 20; i++) {
                    mList.add(new Person(i, "FloatingActionButton1" + i, i));
                }
                mAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();
            }
        });

    }
}
