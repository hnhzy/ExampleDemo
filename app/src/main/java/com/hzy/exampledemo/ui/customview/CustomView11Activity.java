package com.hzy.exampledemo.ui.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.widget.GomokuPanel;

/**
 * @author hzy
 * @description: 五子棋
 * @date :2019/3/28 18:24
 */
public class CustomView11Activity extends AppCompatActivity {
    private GomokuPanel mFIRPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view11);

        initView();
    }

    private void initView() {
        mFIRPanel = (GomokuPanel) findViewById(R.id.id_fir_panel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_restart:
                mFIRPanel.restart();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}