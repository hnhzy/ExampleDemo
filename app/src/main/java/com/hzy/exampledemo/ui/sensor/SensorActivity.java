package com.hzy.exampledemo.ui.sensor;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hzy.exampledemo.R;

import java.util.List;

/**
* Description 传感器测试类
* @author hzy
* Create on 2019/4/30 17:02
*/
public class SensorActivity extends AppCompatActivity {

    private TextView tvSensor;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        tvSensor = findViewById(R.id.tv_sensor);
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorlist= sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s:sensorlist) {
            tvSensor.append(s.getName()+"\n");
        }
    }
}
