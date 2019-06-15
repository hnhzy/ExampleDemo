package com.hzy.exampledemo.ui.customview;

import android.app.Activity;
import android.os.Bundle;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.widget.radarview.AbilityBean;
import com.hzy.exampledemo.widget.radarview.AbilityView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzy
 * @description: 绘制雷达图
 * @date :2019/3/28 18:24
 */
public class CustomView12Activity extends Activity {

//    AbilityView mAbilityView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view12);
//        mAbilityView=findViewById(R.id.ability_view);
//        List<AbilityBean> abList = new ArrayList<>();
//        abList.add(new AbilityBean("干纹",60));
//        abList.add(new AbilityBean("粉刺",80));
//        abList.add(new AbilityBean("毛孔",40));
//        abList.add(new AbilityBean("敏感",50));
//        abList.add(new AbilityBean("色素",77));
//        mAbilityView.setData(abList);
    }
}