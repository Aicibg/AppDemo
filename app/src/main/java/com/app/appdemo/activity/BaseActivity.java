package com.app.appdemo.activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.app.appdemo.MyApplication;
import com.app.appdemo.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;

/**
 * @FileName: com.app.appdemo.activity.BaseActivity.java
 * Description:activity模板
 * Created by donghao on 2016/6/29.
 */
public class BaseActivity extends AppCompatActivity {
   protected Toolbar toolbar;
   protected SystemBarTintManager mTintmanger;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        initToolBar();
    }

    protected void initToolBar() {
        this.toolbar= ButterKnife.findById(this, R.id.toolbar);
        if(toolbar!=null){
            this.setSupportActionBar(toolbar);
            this.toolbar.setNavigationIcon(R.mipmap.navigation_return);
            this.toolbar.setTitleTextColor(Color.WHITE);
            this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        BaseActivity.this.finish();
                    overridePendingTransition(R.anim.close_scale,R.anim.close_scale);
                }
            });
        }
    }


    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        //沉浸式状态栏
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT){
            this.setTranslucentStatus(true);
            this.mTintmanger=new SystemBarTintManager(this);
            this.mTintmanger.setStatusBarTintEnabled(true);
            this.mTintmanger.setTintColor(Color.parseColor("#3F51B5"));
            this.mTintmanger.setStatusBarTintResource(R.color.colorPrimaryDark);
           // this.mTintmanger.setNavigationBarTintColor(Color.BLACK);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setTranslucentStatus(boolean b) {
        Window w=this.getWindow();
        WindowManager.LayoutParams params=w.getAttributes();
        final int bits=WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if(b){
            params.flags |=bits;
        }else{
            params.flags &=~bits;
        }
        w.setAttributes(params);
    }

    protected void setTitle(String title){
        ActionBar actionbar=this.getSupportActionBar();
        if(actionbar!=null){
            actionbar.setTitle(title);
        }
    }

    public void seTtitle(int resid){
        this.setTitle(this.getString(resid));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
