package com.app.appdemo.Utils;

import android.util.Log;

/**
 * @FileName: com.app.appdemo.Utils.LogUtil.java
 * Description:debug调试工具类
 * Created by donghao on 2016/6/29.
 */
public class LogUtil {

    //是否是开发调试阶段
    private static boolean isDebug=true;

    private static String TAG="test";

    public static void e(String tag,String str){
        if(isDebug){
            Log.e(TAG,tag+"="+str);
        }
    }

    public static void w(String tag,String str){
        if(isDebug){
            Log.w(TAG,tag+"="+str);
        }
    }

    public static void i(String tag,String str){
        if(isDebug){
            Log.i(TAG,tag+"="+str);
        }
    }
}
