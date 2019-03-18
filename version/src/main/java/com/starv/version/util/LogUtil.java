package com.starv.version.util;

import android.util.Log;

/**
 * Created by sunsiyuan on 2017/9/25.
 */

public class LogUtil {
    private static boolean debug = true;
    private static String TAG = "LogUtil";

    private LogUtil() {
    }

    public static void i(String msg) {
        if (debug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (debug)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (debug)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (debug)
            Log.v(TAG, msg);
    }

    public static void w(String msg) {
        if (debug)
            Log.w(TAG, msg);
    }
}
