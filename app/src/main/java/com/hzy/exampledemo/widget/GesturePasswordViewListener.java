package com.hzy.exampledemo.widget;

/**
* Description GesturePasswordViewListener
* @author hzy
* Create on 2019/7/18 16:31
*/
public interface GesturePasswordViewListener {
    /**
     * 解锁成功调用
     */
    void onSuccess();

    /**
     * 错误调用
     */
    void onError();

    /**
     * 解锁失败调用
     */
    void onFailure();
}