package com.starv.tvindex.model.activity;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.starv.tvindex.R;
import com.starv.tvindex.util.Constant;
import com.starv.tvindex.util.SetHeaderContent;

/**
 * Created by weizhi on 2016/6/16.
 */
public class AboutActivity extends FragmentActivity {
    private TextView m_obj_version;
    private LayoutInflater layoutInflater;
    private View m_obj_view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        m_obj_view = layoutInflater.from(getApplicationContext()).inflate(R.layout.about,null);
        setContentView(m_obj_view);
        //setContentView(R.layout.about);
        m_obj_version = (TextView) this.findViewById(R.id.id_about_version);
        m_obj_version.setText(getVersion());


        final AboutActivity aa = this;
        SetHeaderContent.getInstance().init(m_obj_view);
        SetHeaderContent.getInstance().setTitle(getResources().getString(R.string.about));
        SetHeaderContent.getInstance().setLeftIcon(getResources().getString(R.string.back_icon), new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                aa.finish();
            }
        });

    }

    private String getVersion(){
        String versionName = "";
        int versioncode = 0;
        try {
            // ---get the package info---
            PackageManager pm = AboutActivity.this.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(AboutActivity.this.getPackageName(), 0);
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
