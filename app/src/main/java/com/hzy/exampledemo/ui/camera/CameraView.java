package com.hzy.exampledemo.ui.camera;

import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.io.IOException;
import java.util.List;

/**
 * Description CameraView
 * https://www.jianshu.com/p/6c35e79df021
 *
 * @author hzy
 * Create on 2019/7/22 15:58
 */
public class CameraView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder mHolder;
    private Camera mCamera;
    private Context mContext;


    public CameraView(Context context, Camera mCamera) {
        super(context);
        //初始化Camera对象
        this.mCamera = mCamera;
        this.mContext = context;
        //得到SurfaceHolder对象
        this.mHolder = getHolder();
        //添加回调，得到Surface的三个声明周期方法
        mHolder.addCallback(this);
        //已弃用设置，但在3.0之前的Android版本上是必需的
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }


    /**
     * 在surface创建后立即被调用。在开发自定义相机时，
     * 可以通过重载这个函数调用camera.open()、camera.setPreviewDisplay()，
     * 来实现获取相机资源、连接camera和surface等操作。
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {

            //设置预览方向
            mCamera.setDisplayOrientation(90);
            //把这个预览效果展示在SurfaceView上面
            mCamera.setPreviewDisplay(holder);
            //开启预览效果
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在surface发生format或size变化时调用。在开发自定义相机时，
     * 可以通过重载这个函数调用camera.startPreview来开启相机预览，
     * 使得camera预览帧数据可以传递给surface，从而实时显示相机预览图像。
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (holder.getSurface() == null) {
            return;
        }
        //停止预览效果
        mCamera.stopPreview();
        //重新设置预览效果
        try {
            mCamera.setPreviewDisplay(mHolder);
            initParameters(holder);//初始化相机参数
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.startPreview();
    }

    //开始预览
//    private void startPreview() {
//        mCamera.setPreviewDisplay(mSurfaceHolder);
//        setCameraDisplayOrientation(mContext);
//        mCamera.startPreview();
//    }

    /**
     * 在surface销毁之前被调用。在开发自定义相机时，
     * 可以通过重载这个函数调用camera.stopPreview()，camera.release()
     * 来实现停止相机预览及释放相机资源等操作。
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();//释放相机资源
    }

    /**
     * 初始化相机参数
     */
    private void initParameters(SurfaceHolder holder) {
        //得到照相机的参数
        Camera.Parameters parameters = mCamera.getParameters();
        //图片的格式
        parameters.setPictureFormat(ImageFormat.JPEG);
//        //预览的大小是多少
//        parameters.setPreviewSize(1920, 1080);
//        parameters.setPictureSize(1920, 1080);

        //获取与指定宽高相等或最接近的尺寸
        //设置预览尺寸
        Camera.Size bestPreviewSize = getBestSize(720, 990, parameters.getSupportedPreviewSizes());
        parameters.setPreviewSize(bestPreviewSize.width, bestPreviewSize.height);
        //设置保存图片尺寸
        Camera.Size bestPicSize = getBestSize(720, 990, parameters.getSupportedPictureSizes());
        parameters.setPictureSize(bestPicSize.width, bestPicSize.height);

        // 连续对焦模式
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        parameters.setJpegQuality(100);//设置照片的质量
        mCamera.setParameters(parameters);
    }


    public void setCameraDisplayOrientation(Camera.CameraInfo cameraInfo) {
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int rotation = manager.getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        int mDisplayOrientation;
        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            mDisplayOrientation = (cameraInfo.orientation + degrees) % 360;
            mDisplayOrientation = (360 - mDisplayOrientation) % 360;  // compensate the mirror
        } else {  // back-facing
            mDisplayOrientation = (cameraInfo.orientation - degrees + 360) % 360;
        }
        mCamera.setDisplayOrientation(mDisplayOrientation);
    }

    //获取与指定宽高相等或最接近的尺寸
    private Camera.Size getBestSize(int targetWidth, int targetHeight, List<Camera.Size> sizeList) {
        Camera.Size bestSize = null;
        double targetRatio = (targetHeight / targetWidth); //目标大小的宽高比
        double minDiff = targetRatio;

        for (Camera.Size size : sizeList) {
            double supportedRatio = (size.width / size.height);
            Log.d("getBestSize", "系统支持的尺寸 : ${size.width} * ${size.height} ,    比例$supportedRatio");
        }

        for (Camera.Size size : sizeList) {
            if (size.width == targetHeight && size.height == targetWidth) {
                bestSize = size;
                break;
            }
            double supportedRatio = (size.width / size.height);
            if (Math.abs(supportedRatio - targetRatio) < minDiff) {
                minDiff = Math.abs(supportedRatio - targetRatio);
                bestSize = size;
            }
        }
        Log.d("getBestSize", "目标尺寸 ：$targetWidth * $targetHeight ，   比例  $targetRatio");
        Log.d("getBestSize", "最优尺寸 ：${bestSize?.height} * ${bestSize?.width}");
        return bestSize;
    }

    /**
     * 释放相机资源
     */
    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }
}
