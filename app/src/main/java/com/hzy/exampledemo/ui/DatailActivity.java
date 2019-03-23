package com.hzy.exampledemo.ui;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;

import com.hzy.exampledemo.R;

/**
 * Created by hzy on 2019/3/23
 *
 * @author Administrator
 */
public class DatailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView mImageView;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("DatailActivity");
        setSupportActionBar(toolbar);
        // 给左上角图标的左边加上一个返回的图标 。
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mImageView = findViewById(R.id.image);
        mFloatingActionButton = findViewById(R.id.fabbtn);
        mImageView.setBackgroundResource(getIntent().getIntExtra("id", 0));

        if (savedInstanceState == null) {
            mFloatingActionButton.setScaleX(0);
            mFloatingActionButton.setScaleY(0);
            getWindow().getEnterTransition().addListener(new CustomTransitionListener() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    getWindow().getEnterTransition().removeListener(this);
                    mFloatingActionButton.animate().scaleX(1).scaleY(1);
                }
            });
        }
    }

    /**
     * @param context
     * @param id
     * @param imageView
     */
    public static void start(Activity context, Integer id, ImageView imageView) {
        Intent intent = new Intent(context, DatailActivity.class);
        intent.putExtra("id", id);
        //R.string.transition_str 与xml文件对应
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                        imageView,
                        context.getResources().getString(R.string.transition_str));
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }

    /**
     * 自定义的一个监听器。
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    class CustomTransitionListener implements Transition.TransitionListener,
            Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }

        @Override
        public void onTransitionStart(Transition transition) {

        }

        @Override
        public void onTransitionEnd(Transition transition) {

        }

        @Override
        public void onTransitionCancel(Transition transition) {

        }

        @Override
        public void onTransitionPause(Transition transition) {

        }

        @Override
        public void onTransitionResume(Transition transition) {

        }
    }
}
