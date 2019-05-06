package com.hzy.exampledemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.FlowLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.adapter.MainListAdapter;
import com.hzy.exampledemo.ui.customview.CustomView10Activity;
import com.hzy.exampledemo.ui.customview.CustomView11Activity;
import com.hzy.exampledemo.ui.customview.CustomView1Activity;
import com.hzy.exampledemo.ui.customview.CustomView2Activity;
import com.hzy.exampledemo.ui.customview.CustomView3Activity;
import com.hzy.exampledemo.ui.customview.CustomView4Activity;
import com.hzy.exampledemo.ui.customview.CustomView5Activity;
import com.hzy.exampledemo.ui.customview.CustomView6Activity;
import com.hzy.exampledemo.ui.customview.CustomView7Activity;
import com.hzy.exampledemo.ui.customview.CustomView8Activity;
import com.hzy.exampledemo.ui.customview.CustomView9Activity;
import com.hzy.exampledemo.ui.design.CollapsActivity;
import com.hzy.exampledemo.ui.design.DrawerActivity;
import com.hzy.exampledemo.ui.design.FabActivity;
import com.hzy.exampledemo.ui.design.MenuActivity;
import com.hzy.exampledemo.ui.design.SceneTransitionActivity;
import com.hzy.exampledemo.ui.design.ScrollingActivity;
import com.hzy.exampledemo.ui.nfc.AutoOpenPackageActivity;
import com.hzy.exampledemo.ui.nfc.AutoOpenUrlActivity;
import com.hzy.exampledemo.ui.sensor.MotionSensorActivity;
import com.hzy.exampledemo.ui.sensor.SensorActivity;
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
        mList.add("CustomView1Activity");
        mList.add("CustomView2Activity");
        mList.add("CustomView3Activity");
        mList.add("CustomView4Activity");
        mList.add("CustomView5Activity");
        mList.add("CustomView6Activity");
        mList.add("CustomView7Activity");
        mList.add("CustomView8Activity");
        mList.add("CustomView9Activity");
        mList.add("CustomView10Activity");
        mList.add("CustomView11Activity");
        mList.add("SensorActivity");
        mList.add("MotionSensorActivity");
        mList.add("AutoOpenUrlActivity");
        mList.add("AutoOpenPackageActivity");
        mAdapter = new MainListAdapter(this, mList);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                switch (position) {
                    //design
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
                        /**
                         * R.anim.slide_in_right:新的Activity进入时的动画，这里是指OtherActivity进入时的动画
                         * R.anim.slide_out_left：旧的Activity出去时的动画，这里是指this进入时的动画
                         */
                        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
                        break;
                    //自定义View
                    case 6:
                        //可以移动的view onTouch
                        startActivity(new Intent(MainActivity.this, CustomView1Activity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, CustomView2Activity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, CustomView3Activity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, CustomView4Activity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, CustomView5Activity.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, CustomView6Activity.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainActivity.this, CustomView7Activity.class));
                        break;
                    case 13:
                        startActivity(new Intent(MainActivity.this, CustomView8Activity.class));
                        break;
                    case 14:
                        startActivity(new Intent(MainActivity.this, CustomView9Activity.class));
                        break;
                    case 15:
                        startActivity(new Intent(MainActivity.this, CustomView10Activity.class));
                        break;
                    case 16:
                        startActivity(new Intent(MainActivity.this, CustomView11Activity.class));
                        break;
                    case 17:
                        startActivity(new Intent(MainActivity.this, SensorActivity.class));
                        break;
                    case 18:
                        startActivity(new Intent(MainActivity.this, MotionSensorActivity.class));
                        break;
                    case 19:
                        startActivity(new Intent(MainActivity.this, AutoOpenUrlActivity.class));
                        break;
                    case 20:
                        startActivity(new Intent(MainActivity.this, AutoOpenPackageActivity.class));
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
