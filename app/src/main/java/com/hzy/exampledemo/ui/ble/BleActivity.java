package com.hzy.exampledemo.ui.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hzy.exampledemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Description 经典蓝牙功能
 *
 * @author hzy
 * Create on 2019/5/8 11:23
 */
public class BleActivity extends AppCompatActivity {

    private ListView mLvBle;
    private ArrayAdapter mArrayAdapter;
    private List<String> mBleList = new ArrayList<>();

    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_list);
        mLvBle = findViewById(R.id.lv_ble);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //打开蓝牙
        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
        }
        setProgressBarIndeterminate(true);
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }
        mBluetoothAdapter.startDiscovery();
        Set<BluetoothDevice> bleSet = mBluetoothAdapter.getBondedDevices();
        if (bleSet.size() > 0) {
            for (BluetoothDevice ble : bleSet) {
                mBleList.add(ble.getName() + ":" + ble.getAddress() + "\n");
            }
        }
        mArrayAdapter = new ArrayAdapter(BleActivity.this, android.R.layout.simple_list_item_1, mBleList);
        mLvBle.setAdapter(mArrayAdapter);

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(receiver, filter);

        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(receiver, filter);
    }


    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    mBleList.add(device.getName() + ":" + device.getAddress() + "\n");
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                Toast.makeText(context, "已搜索完成", Toast.LENGTH_SHORT).show();
            }
            setProgressBarVisibility(false);
            mArrayAdapter.notifyDataSetChanged();
        }
    };

}
