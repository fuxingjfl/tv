package com.starv.tvindex;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.starv.tvindex.activity.ApplicationScenarioActivity;
import com.starv.tvindex.activity.DiagramActivity;
import com.starv.tvindex.activity.MainActivity;
import com.starv.tvindex.activity.RealTimeDataActivity;
import com.starv.tvindex.activity.ShareActivity;
import com.starv.tvindex.activity.VarietyDetailsActivity;

import com.starv.tvindex.util.Constant;
import com.starv.tvindex.util.SharePreferenceUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

//import butterknife.ButterKnife;


/**
 * Created by weizhi on 2016/4/28.
 */
public class WelcomeActivity extends FragmentActivity {
    private Handler handler=new Handler();
    private Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()) {
            final Intent intent = getIntent();
            final String intentAction = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null && intentAction.equals(Intent
                    .ACTION_MAIN)) {
                finish();
                return;
            }
        }
        setContentView(R.layout.welcome);
        runnable = new Runnable() {
            @Override
            public void run() {


//                判断app是否更新了(根据版本号来判断)
                if(!SharePreferenceUtil.getInstance(getApplicationContext()).get(Constant.app_version).equals(getVersion())){
                    //版本更新了
                    //设置为第一次登陆
                    SharePreferenceUtil.getInstance(getApplicationContext()).save(Constant.first_flag,Constant.first_yes);
                    if(!SharePreferenceUtil.getInstance(getApplicationContext()).get(Constant.token).equals("")){
                        SharePreferenceUtil.getInstance(getApplicationContext()).save(Constant.token,"");
                    }
                }

                //存储当前的app版本号
                SharePreferenceUtil.getInstance(getApplicationContext()).save(Constant.app_version,getVersion());

                Intent intent =null;
//                if(SharePreferenceUtil.getInstance(getApplicationContext()).get(Constant.first_flag).equals(Constant.first_no)){
//                    intent= new Intent(WelcomeActivity.this,MainActivity.class);
//                }else{
//                    intent= new Intent(WelcomeActivity.this,GuideActivity.class);
//                    //第一次登陆
////                    intent.setClass(getApplicationContext(),GuideActivity.class);
//                }
                intent= new Intent(WelcomeActivity.this,MainActivity.class);

                startActivity(intent);
                overridePendingTransition(R.anim.fade, R.anim.hold);
                WelcomeActivity.this.finish();
            }
        };
        handler.postDelayed(runnable, 3 * 1000);//
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        handler.removeCallbacks(runnable);
    }

    @Override
    public void onBackPressed() {
        return ;
    }

    private String getVersion(){
        String versionName = "";
        int versioncode = 0;
        try {
            // ---get the package info---
            PackageManager pm = WelcomeActivity.this.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(WelcomeActivity.this.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
//            if (versionName == null || versionName.length() <= 0) {
//                return "";
//            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        Log.i("choiceaccouttwo","versionName = " + versionName);
        return versionName;
    }


}
