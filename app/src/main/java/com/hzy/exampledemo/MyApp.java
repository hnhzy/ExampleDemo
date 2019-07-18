package com.hzy.exampledemo;
 
import android.app.Application;
import android.content.Context;
 
/**
* Description MyApp
* @author hzy
* Create on 2019/7/17 19:20
*/
public class MyApp extends Application {
    public static Context sContext;//全局的Context对象
 
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
}