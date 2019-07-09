//package com.hzy.exampledemo.ui.Thread;
//
//import android.media.AudioAttributes;
//import android.media.AudioManager;
//import android.media.SoundPool;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//
//import com.hzy.exampledemo.R;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class IntentServiceActivity extends AppCompatActivity implements View.OnClickListener {
//
//    /**
//     * 播放
//     */
//    private Button mBtBf;
//    /**
//     * 暂停
//     */
//    private Button mBtZt;
//
//    public SoundPool mSoundPlayer = null;
//    public Map<Integer, Integer> musicId = new HashMap<>();
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_intent_service);
//        initView();
//    }
//
//    private void initView() {
//        mBtBf = (Button) findViewById(R.id.bt_bf);
//        mBtBf.setOnClickListener(this);
//        mBtZt = (Button) findViewById(R.id.bt_zt);
//        mBtZt.setOnClickListener(this);
//
//        if (Build.VERSION.SDK_INT >= 21) {
//            SoundPool.Builder builder = new SoundPool.Builder();
//            //传入音频的数量
//            builder.setMaxStreams(1);
//            //AudioAttributes是一个封装音频各种属性的类
//            AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
//            //设置音频流的合适属性
//            attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
//            builder.setAudioAttributes(attrBuilder.build());
//            mSoundPlayer = builder.build();
//        } else {
//            //第一个参数是可以支持的声音数量，第二个是声音类型，第三个是声音品质
//            mSoundPlayer = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
//        }
//
//        if (musicId == null) {
//            musicId = new HashMap<>();
//        }
//        musicId.put(1, mSoundPlayer.load(this, R.raw.a001, 1));// 1
//        musicId.put(2, mSoundPlayer.load(this, R.raw.a002, 1));
//        musicId.put(3, mSoundPlayer.load(this, R.raw.a003, 1));
//        musicId.put(4, mSoundPlayer.load(this, R.raw.a004, 1));
//        musicId.put(5, mSoundPlayer.load(this, R.raw.a005, 1));
//        musicId.put(6, mSoundPlayer.load(this, R.raw.a006, 1));
//        musicId.put(7, mSoundPlayer.load(this, R.raw.a007, 1));
//        musicId.put(8, mSoundPlayer.load(this, R.raw.a008, 1));
//        musicId.put(9, mSoundPlayer.load(this, R.raw.a009, 1));
//        musicId.put(10, mSoundPlayer.load(this, R.raw.a010, 1));
//        musicId.put(11, mSoundPlayer.load(this, R.raw.a011, 1));
//        musicId.put(12, mSoundPlayer.load(this, R.raw.a012, 1));
//        musicId.put(13, mSoundPlayer.load(this, R.raw.a013, 1));
//        musicId.put(14, mSoundPlayer.load(this, R.raw.a014, 1));
//        musicId.put(15, mSoundPlayer.load(this, R.raw.a015, 1));
//        musicId.put(16, mSoundPlayer.load(this, R.raw.a016, 1));
//        musicId.put(17, mSoundPlayer.load(this, R.raw.a017, 1));
//        musicId.put(18, mSoundPlayer.load(this, R.raw.a018, 1));
//
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            default:
//                break;
//            case R.id.bt_bf:
//                for (int i = 0; i < musicId.size(); i++) {
//                    mSoundPlayer.play(musicId.get(i), 1, 1, 0, 0, 1);
//                }
//                break;
//            case R.id.bt_zt:
//
//                break;
//        }
//    }
//
//    /**
//     * 停止播放
//     */
//    public static void stop() {
//        if (mSoundPlayer != null) {
//            mSoundPlayer.stop(autoId);
//        }
//    }
//
//    /**
//     * 释放资源
//     */
//    public static void release() {
//        if (mSoundPlayer != null) {
//            mSoundPlayer.release();
//            mSoundPlayer = null;
//            musicId = null;
//        }
//    }
//}
