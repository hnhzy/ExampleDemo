package com.hzy.exampledemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.adapter.MainListAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzy on 2019/3/14
 *
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private RecyclerView mRvList;
    private MainListAdapter mAdapter;
    private List<String> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Design");
        setSupportActionBar(toolbar);

        mRvList = findViewById(R.id.rv_list);
        mRvList.setHasFixedSize(true);
        mList.add("FabActivity");
        mList.add("DrawerActivity");
        mList.add("MenuActivity");
        mList.add("CollapsingToolbarLayout");
        mList.add("ScrollingActivity");
        mList.add("SceneTransitionActivity");
        mAdapter = new MainListAdapter(this, mList);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, FabActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, DrawerActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, MenuActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, CollapsActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, ScrollingActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, SceneTransitionActivity.class));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder,
                                           int position) {
                return false;
            }
        });

    }

    /**
     * 再按一次退出程序
     */
    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - exitTime) < 2000) {
            super.onBackPressed();
            System.exit(0);
        } else {
            Toast.makeText(this, R.string.double_click_exit, Toast.LENGTH_SHORT).show();
            exitTime = currentTime;
        }
    }
}
