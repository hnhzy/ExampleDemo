package com.hzy.exampledemo.ui.ndk;

public class HelloWorld {

    static {
        System.loadLibrary("HelloWorld");
    }

    public static native String sayHello();

}
