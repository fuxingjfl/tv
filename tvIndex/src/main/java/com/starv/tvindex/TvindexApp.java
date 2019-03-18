package com.starv.tvindex;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import com.starv.tvindex.model.local.Ipml.ConcreteWatched;
import com.starv.tvindex.util.PreUtil;
import com.starv.tvindex.util.file.writeLogToSD;
import com.starv.version.StarV;

import java.lang.reflect.Field;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by weizhi on 2016/6/15.
 */
public class TvindexApp extends Application {

    public static ConcreteWatched watched;
    public static Context context;
    public static writeLogToSD writelog;

    @Override
    public void onCreate() {
        super.onCreate();
        watched = new ConcreteWatched();
//        CrashReport.initCrashReport(getApplicationContext(), "900034694", true);
        //CrashReport.testJavaCrash();
        instance = this;
        iconTypeFace_local = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        iconTypeFace = Typeface.createFromAsset(getAssets(), "iconfont.ttf");


        writelog = new writeLogToSD("tvindexLog.txt");
        context = this;

        PreUtil.getInstance().init(TvindexApp.this);
        StarV.getInstance().init(TvindexApp.this);

    }

    private static TvindexApp instance;
    public static synchronized TvindexApp getInstance() {
        return instance;
    }
    //字体图标
    private Typeface iconTypeFace_local;
    private Typeface iconTypeFace;


    /**
     * type:
     *  0:表示总榜
     *  1:表示地方版
     * */
    public Typeface getIconTypeFace(int type) {
        if(1 == type){
            return iconTypeFace_local;
        }else{
            return iconTypeFace;
        }
    }

}
