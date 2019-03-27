package com.hzy.exampledemo.ui.design;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.adapter.BaseFragmentAdapter;
import com.hzy.exampledemo.fragment.ListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzy on 2019/3/15
 *
 * @author Administrator
 */
public class CollapsActivity extends AppCompatActivity {

    ViewPager mViewPager;
    List<Fragment> mFragments;
    Toolbar mToolbar;

    String[] mTitles = new String[]{
            "主页", "微博", "相册"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaps);
        mViewPager = findViewById(R.id.viewpager);
        mToolbar = findViewById(R.id.toolbar);

        mToolbar.setTitle("唐嫣");
        setSupportActionBar(mToolbar);
        // 给左上角图标的左边加上一个返回的图标 。
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        setupViewPager();
    }

    private void setupViewPager() {
        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            ListFragment listFragment = ListFragment.newInstance(mTitles[i]);
            mFragments.add(listFragment);
        }
        BaseFragmentAdapter adapter =
                new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);


        viewPager.setAdapter(adapter);
    }
}
