package com.hzy.exampledemo.ui.customview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.widget.radarview.RadarCharView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzy
 * @description: 时钟绘制
 * @date :2019/3/28 18:24
 */
public class CustomView9Activity extends Activity {

    RadarCharView radarCharView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view9);
        radarCharView=findViewById(R.id.radarview);
        List<Float> radarCharlist = new ArrayList<>();
        radarCharlist.add(50f);
        radarCharlist.add(60f);
        radarCharlist.add(70f);
        radarCharlist.add(80f);
        radarCharlist.add(90f);
        radarCharView.setData(radarCharlist);
    }
}