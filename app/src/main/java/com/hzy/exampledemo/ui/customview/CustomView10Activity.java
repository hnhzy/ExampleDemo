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
 * @description: 时钟绘制
 * @date :2019/3/28 18:24
 */
public class CustomView10Activity extends Activity {

    AbilityView mAbilityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view10);
        mAbilityView=findViewById(R.id.ability_view);
        List<AbilityBean> radarCharlist = new ArrayList<>();
        radarCharlist.add(new AbilityBean("击杀",90));
        radarCharlist.add(new AbilityBean("生存",60));
        radarCharlist.add(new AbilityBean("助攻",100));
        radarCharlist.add(new AbilityBean("物理",70));
        radarCharlist.add(new AbilityBean("魔法",100));
        radarCharlist.add(new AbilityBean("防御",30));
        radarCharlist.add(new AbilityBean("金钱",80));
        mAbilityView.setData(radarCharlist);
    }
}