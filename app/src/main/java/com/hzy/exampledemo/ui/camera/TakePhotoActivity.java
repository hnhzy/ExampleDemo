package com.hzy.exampledemo.ui.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.hzy.exampledemo.R;

/**
 * Description 拍照/拍视频
 *
 * @author hzy
 * Create on 2019/7/19 14:39
 */
public class TakePhotoActivity extends AppCompatActivity {

    public static final int ACTION_IMAGE_CAPTURE = 0;
    public static final int ACTION_VIDEO_CAPTURE = 1;

    private ImageView mIvPhoto;
    private VideoView mVvRecored;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        initView();
        initPermission();
    }

    private void initPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION},
                0);//自定义的code

    }

    private void initView() {
        mIvPhoto = findViewById(R.id.iv_photo);
        mVvRecored = findViewById(R.id.vv_recored);
    }

    /**
     * 拍照
     *
     * @param view
     */
    public void onTackePhotoClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, ACTION_IMAGE_CAPTURE);

    }

    /**
     * 拍视频
     *
     * @param view
     */
    public void onRecordClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, ACTION_VIDEO_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case ACTION_IMAGE_CAPTURE:
                mVvRecored.setVisibility(View.GONE);
                mIvPhoto.setVisibility(View.VISIBLE);
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                mIvPhoto.setImageBitmap(bitmap);
                break;
            case ACTION_VIDEO_CAPTURE:
                mIvPhoto.setVisibility(View.GONE);
                mVvRecored.setVisibility(View.VISIBLE);
                Uri uri = data.getData();
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor.moveToFirst()) {
                    String videoPath = cursor.getString(cursor.getColumnIndex("_data"));
                    Log.d("videoPath", videoPath);
                    mVvRecored.setVideoURI(Uri.parse(videoPath));
                    mVvRecored.setMediaController(new MediaController(this));
                    mVvRecored.start();
                }
                break;
        }
    }
}
