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
public class CustomView10Activity extends Activity {

    AbilityView mAbilityView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view10);
        mAbilityView=findViewById(R.id.ability_view);
        List<AbilityBean> abList = new ArrayList<>();
        abList.add(new AbilityBean("击杀",90));
        abList.add(new AbilityBean("生存",60));
        abList.add(new AbilityBean("助攻",100));
        abList.add(new AbilityBean("物理",70));
        abList.add(new AbilityBean("魔法",100));
        abList.add(new AbilityBean("防御",30));
        abList.add(new AbilityBean("金钱",80));
        mAbilityView.setData(abList);
    }
}