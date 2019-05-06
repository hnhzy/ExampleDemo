package com.hzy.exampledemo.ui.sensor;

import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.hzy.exampledemo.R;

import java.text.SimpleDateFormat;

/**
* Description 动作传感器
* @author hzy
* Create on 2019/5/5 10:33
*/
public class MotionSensorActivity extends AppCompatActivity {
    private TextView tv1;//是否支持StepDetector
    private TextView tv2;//是否支持StepCounter
    private TextView tv4;//当前步伐时间
    private TextView tv3;//当前步伐数
    private TextView tv5;//总步伐计数

    private SensorManager sensorManager;
    private Sensor stepCounter;//步伐总数传感器
    private Sensor stepDetector;//单次步伐传感器
    private SensorEventListener stepCounterListener;//步伐总数传感器事件监听器
    private SensorEventListener stepDetectorListener;//单次步伐传感器事件监听器

    private SimpleDateFormat simpleDateFormat;//时间格式化

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_sensor);
        initView();
        initData();
        initListener();
    }

    protected void initView() {
        tv3= (TextView) findViewById(R.id.tv3);
        tv5= (TextView) findViewById(R.id.tv5);
        tv4= (TextView) findViewById(R.id.tv4);
        tv1= (TextView) findViewById(R.id.tv1);
        tv2= (TextView) findViewById(R.id.tv2);
    }

    protected void initData() {
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器系统服务
        stepCounter=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);//获取计步总数传感器
        stepDetector=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);//获取单次计步传感器

        tv2.setText("是否支持StepCounter:"+getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER));
        tv1.setText("是否支持StepDetector:"+getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR));

        simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    }

    protected void initListener() {
        stepCounterListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Log.e("Counter-SensorChanged",event.values[0]+"---"+event.accuracy+"---"+event.timestamp);
                tv5.setText("总步伐计数:"+event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                Log.e("Counter-Accuracy",sensor.getName()+"---"+accuracy);

            }
        };

        stepDetectorListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Log.e("Detector-SensorChanged",event.values[0]+"---"+event.accuracy+"---"+event.timestamp);
                tv3.setText("当前步伐计数:"+event.values[0]);
                tv4.setText("当前步伐时间:"+simpleDateFormat.format(event.timestamp/1000000));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                Log.e("Detector-Accuracy",sensor.getName()+"---"+accuracy);

            }
        };
    }

    private void registerSensor(){
        //注册传感器事件监听器
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER)&&
                getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)){
            sensorManager.registerListener(stepDetectorListener,stepDetector, SensorManager.SENSOR_DELAY_FASTEST);
            sensorManager.registerListener(stepCounterListener,stepCounter,SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    private void unregisterSensor(){
        //解注册传感器事件监听器
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER)&&
                getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)){
            sensorManager.unregisterListener(stepCounterListener);
            sensorManager.unregisterListener(stepDetectorListener);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        unregisterSensor();
    }

    @Override
    public void onResume(){
        super.onResume();
        registerSensor();
    }

}
