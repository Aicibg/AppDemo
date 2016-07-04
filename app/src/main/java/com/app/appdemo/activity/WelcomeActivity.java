package com.app.appdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.app.appdemo.MainActivity;
import com.app.appdemo.MyApplication;
import com.app.appdemo.R;
import com.app.appdemo.Utils.Constants;

/**
 * @FileName: com.app.appdemo.activity.WelcomeActivity.java
 * Description:app启动页
 * Created by donghao on 2016/6/29.
 */
public class WelcomeActivity extends BaseActivity {
   private Animation anim;
   private  Handler mHandler=new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= View.inflate(this,R.layout.activity_welcome,null);
        setContentView(view);

        getScreenSize();
        initAnim();
        view.startAnimation(anim);
    }
   //获取频幕尺寸
    private void getScreenSize() {
        DisplayMetrics metric=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        Constants.SCREEN_WIDTH=metric.widthPixels;
        Constants.SCREEN_HEIGHT=metric.heightPixels;
    }

    private void initAnim() {
        anim= AnimationUtils.loadAnimation(this,R.anim.welcome_alpha_in);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                  startMainactivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void startMainactivity() {
        if(MyApplication.isFirstOpen()){
            MyApplication.setFirstOpen();
            startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
            finish();
            overridePendingTransition(R.anim.anim_right_in,R.anim.anim_left_out);
        }else{

                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                            finish();
                            overridePendingTransition(R.anim.welcome_alpha_in,R.anim.close_alpha);
                        }
                    },1000);
                }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
