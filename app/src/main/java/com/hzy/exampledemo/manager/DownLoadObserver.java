package com.hzy.exampledemo.manager;
 
import com.hzy.exampledemo.bean.DownloadInfo;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
 
/**
* Description DownLoadObserver
* @author hzy
* Create on 2019/7/17 19:24
*/
public  abstract class DownLoadObserver implements Observer<DownloadInfo> {
    protected Disposable d;//可以用于取消注册的监听者
    protected DownloadInfo downloadInfo;
    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
    }
 
    @Override
    public void onNext(DownloadInfo downloadInfo) {
        this.downloadInfo = downloadInfo;
    }
 
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }
 
 
}