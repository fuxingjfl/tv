package com.starv.tvindex.model.local.base.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.starv.tvindex.R;

//import butterknife.ButterKnife;

/**
 * Created by weizhi on 2016/8/22.
 * Activity基类
 */
public abstract class BaseActivity extends FragmentActivity {


    LayoutInflater layoutInflater = null;
    private View m_self = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
        layoutInflater = LayoutInflater.from(this);
        m_self = layoutInflater.inflate(getLayoutId(), null);
        setContentView(m_self);
//        ButterKnife.bind(this);
        initViews(m_self,savedInstanceState);
        initData();
        initListeners();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 123) {
//            boolean isPermission = true;
//            for (int grant : grantResults) {
//                // 判断是否所有的权限都已经授予了
//                if (grant != PackageManager.PERMISSION_GRANTED) {
//                    isPermission = false;
//                    break;
//                }
//            }
//            if (isPermission) {
////                Toast.makeText(BaseActivity.this, "我看看", Toast.LENGTH_SHORT).show();
//            } else {
//                // 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setMessage("备份通讯录需要访问")
//                        .setPositiveButton("设置", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Intent intent=new Intent();
//                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                intent.addCategory(Intent.CATEGORY_DEFAULT);
//                                intent.setData(Uri.parse("package:" + getPackageName()));
//                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//                                startActivity(intent);
//                            }
//                        })
//                        .setNegativeButton("退出",null);
//                builder.show();
//            }
//        }
    }

    /**
     * get layout id
     *
     * @return layout id
     */
    protected abstract int getLayoutId();


    /**
     * Initialize the view in the layout
     *
     * @param self self
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void initViews(View self, Bundle savedInstanceState);

    /**
     * Initialize the View of the listener
     */
    protected abstract void initListeners();

    /**
     * Initialize the Activity data
     */
    protected abstract void initData();


    /**
     * Find the view by id
     *
     * @param id id
     * @param <V> V
     * @return V
     */
    @SuppressWarnings("unchecked") protected <V extends View> V findView(int id) {
        return (V) this.m_self.findViewById(id);
    }


}
