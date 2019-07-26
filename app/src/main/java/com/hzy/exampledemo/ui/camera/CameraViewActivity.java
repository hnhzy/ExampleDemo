package com.hzy.exampledemo.ui.camera;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hzy.exampledemo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Description 自定义相机
 *
 * @author hzy
 * Create on 2019/7/19 15:08
 */
public class CameraViewActivity extends AppCompatActivity {

    private FrameLayout camera_preview;
    private CameraView mPreview;
    private Camera mCamera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera_view);
        initPermission();
        initCamera();
    }

    private void initCamera() {
        //检测设备是否支持相机
        if (checkCameraHardware(this)) {
            //初始化 Camera对象
            mCamera = Camera.open();
            mPreview = new CameraView(this, mCamera);
            camera_preview = findViewById(R.id.camera_preview);
            camera_preview.addView(mPreview);
        }
    }

    /**
     * 检测设备是否支持相机
     *
     * @param context
     * @return
     */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        }
        return false;
    }

    /**
     * 申请权限
     */
    private void initPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    0);//自定义的code
        }
    }



    public void onPzClick(View view) {
        //得到照相机的参数
        Camera.Parameters parameters = mCamera.getParameters();
        List<Camera.Size> mPreviewSizes = parameters.getSupportedPreviewSizes();
        List<Camera.Size> mPictureSizes = parameters.getSupportedPictureSizes();

        for (int i = 0; i < mPreviewSizes.size(); i++) {
            Log.d("mPreviewSizes-" + i, "width:" + mPreviewSizes.get(i).width + "--height:" + mPreviewSizes.get(i).height);
        }

        for (int i = 0; i < mPictureSizes.size(); i++) {
            Log.d("mPictureSizes-" + i, "width:" + mPictureSizes.get(i).width + "--height:" + mPictureSizes.get(i).height);
        }
//        Log.d("mPictureSizes", mPictureSizes.toString());
        //图片的格式
        parameters.setPictureFormat(ImageFormat.JPEG);
        //预览的大小是多少
        parameters.setPreviewSize(1920, 1080);
        parameters.setPictureSize(1920, 1080);
        // 连续对焦模式
//        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        //设置对焦模式，自动对焦
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        parameters.setJpegQuality(100);//设置照片的质量
        mCamera.setParameters(parameters);
        //对焦成功后，自动拍照
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    //获取照片
                    mCamera.takePicture(() -> {
                    }, null, mPictureCallback);
                }
            }
        });
    }

    //获取照片中的接口回调
    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Log.d("onPictureTaken--1", "width:" + bitmap.getWidth() + "--height:" + bitmap.getHeight());
            Bitmap cropBitmap = cropBitmap(bitmap);
            Log.d("onPictureTaken--2", "width:" + cropBitmap.getWidth() + "--height:" + cropBitmap.getHeight());
            FileOutputStream fos = null;
            String mFilePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "tt001.png";
            //文件
            File tempFile = new File(mFilePath);
            try {
                //
                fos = new FileOutputStream(tempFile);
                fos.write(data);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //实现连续拍多张的效果
//                mCamera.startPreview();
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    };

    /**
     * 裁剪
     * https://blog.csdn.net/qunqunstyle99/article/details/87869018
     *
     * @param bitmap 原图
     * @return 裁剪后的图像
     */
    private Bitmap cropBitmap(Bitmap bitmap) {
        //width:1920--height:1080
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int x = 0;
        int y = 0;
        if (bitmap.getWidth() < 720) {
            Toast.makeText(this, "x + width must be <= bitmap.width()", Toast.LENGTH_SHORT).show();
            return bitmap;
        }
        if (bitmap.getHeight() < 990) {
            Toast.makeText(this, "y + height must be <= bitmap.height()", Toast.LENGTH_SHORT).show();
            return bitmap;
        }
        x = (w - 720) / 2;
        y = (h - 990) / 2;
        return Bitmap.createBitmap(bitmap, x, y, 720, 990, null, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

}
