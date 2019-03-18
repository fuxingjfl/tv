package com.starv.version.util;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by sunsiyuan on 2017/9/25.
 * 工具类
 */
public class Tools {

    /**
     * 获取版本号
     *
     * @param context
     * @return
     * @author sunsy 2015年4月1日下午4:31:05
     */
    public static int getVerCode(Context context) {
        int verCode = 0;
        try {
            verCode = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verCode;
    }

}
