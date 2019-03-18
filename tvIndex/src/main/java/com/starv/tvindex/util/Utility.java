package com.starv.tvindex.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by weizhi on 2016/5/27.
 */
public class Utility {

//    获取手机的屏幕密度
    public static int getScreenDpi(Activity activity){
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕宽度（像素）
        int height = metric.heightPixels;  // 屏幕高度（像素）
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
//        Log.i("wz","width = " + width);
//        Log.i("wz","height = " + height);
//        Log.i("wz","densityDpi = " + densityDpi);
        return densityDpi;
    }
}
