package com.hzy.exampledemo.ui.customview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.listener.MoveViewListener;
import com.hzy.exampledemo.widget.MoveView2;
import com.hzy.exampledemo.widget.radarview.RadarView;

/**
 * @author hzy
 * @description: 绘制雷达图
 * @date :2019/3/28 18:24
 */
public class CustomView13Activity extends Activity implements MoveViewListener {

    RadarView mRadarView;
    MoveView2 mv_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view13);
        mRadarView = findViewById(R.id.ability_view);
        mv_view = findViewById(R.id.mv_view);
        mv_view.setmMoveViewListener(this);
    }

    @Override
    public void downAction(int RawX, int RawY) {
        Log.d("downAction", "RawX:" + RawX + "\n" + "RawY:" + RawY);
    }

    @Override
    public void moveAction(int l, int t, int r, int b) {
        //获取mLlLeft相对屏幕的绝对位置，再根据mMoveView的中心点进行判断中心点是否超过
        int[] location = new int[2];
        mRadarView.getLocationInWindow(location);
        int xCenter = (l + r) / 2;
        int yCenter = (t + b) / 2;

        //判断移动的view是否在矩形范围内
        if (location[0] < xCenter && xCenter < (location[0] + mRadarView.getWidth()) && location[1] < yCenter && yCenter < (location[1] + mRadarView.getHeight())) {
            Log.d("moveAction", "在矩形范围内");
        } else {
            Log.d("moveAction", "不在矩形范围内");
        }
    }
}