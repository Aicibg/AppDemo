package com.app.appdemo;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * @FileName: com.app.appdemo.MyApplication.java
 * Description:
 * Created by donghao on 2016/6/29.
 */
public class MyApplication extends Application {
    public static MyApplication instance;
    private static String STORE_NAME="openseting";
    private static SharedPreferences preference;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        //记录是否首次启动APP
        preference=getSharedPreferences(STORE_NAME,MODE_PRIVATE);
        if(preference==null){
            SharedPreferences.Editor edit=preference.edit();
            edit.putBoolean("isFirstOPen",true);
            edit.commit();
        }
    }

    public static boolean isFirstOpen() {
      return preference.getBoolean("isFirstOPen",true);
    }

    public static void setFirstOpen(){
        SharedPreferences.Editor edit=preference.edit();
        edit.putBoolean("isFirstOPen",false);
        edit.commit();
    }
}
