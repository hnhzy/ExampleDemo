package com.hzy.exampledemo.ui.Thread;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hzy.exampledemo.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description 线程池
 *
 * @author hzy
 * Create on 2019/7/17 16:49
 * https://blog.csdn.net/liqianwei1230/article/details/78239281
 */
public class ThreadPoolActivity extends AppCompatActivity {

    public static final String TAG = "ThreadPoolActivity-";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);
//        threadPoolExecutor();
//        newFixedThreadPool();//只有核心线程，并且数量是固定的,不会被回收
//        SingleThreadPool();//只有一个核心线程
//        CachedThreadPool();//只有非核心线程
//        ScheduledThreadPool();//核心线程数固定，非核心线程数没有限制
    }

    private void threadPoolExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 30, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(128));
        for (int i = 0; i < 30; i++) {
            final int index = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        String threadName = Thread.currentThread().getName();
                        Log.v(TAG, "线程：" + threadName + ",正在执行第" + index + "个任务");
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executor.execute(runnable);
        }
    }

    /**
     * FixedThreadPool 只有核心线程，并且数量是固定的，
     * 也不会被回收，能更快地响应外界请求。
     */
    private void newFixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    Log.v(TAG, "线程：" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * SingleThreadPool 只有一个核心线程，
     * 确保所有任务都在同一线程中按顺序完成。
     * 因此不需要处理线程同步的问题。类似HandlerThread
     */
    private void SingleThreadPool() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    Log.v(TAG, "线程：" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    /**
     * CachedThreadPool 只有非核心线程，
     * 最大线程数非常大，所有线程都活动时，会为新任务创建新线程，
     * 否则利用空闲线程处理任务，任何任务都会被立即执行。
     */
    private void CachedThreadPool() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    Log.v(TAG, "线程：" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        long time = index * 500;
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    /**
     * ScheduledThreadPool 核心线程数固定，非核心线程数没有限制，
     * 主要用于执行定时任务以及有固定周期的重复任务。
     */
    private void ScheduledThreadPool() {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(3);
        threadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                Log.v(TAG, "线程：" + threadName + ",正在执行");
            }
        }, 2, 1, TimeUnit.SECONDS);
    }
}
