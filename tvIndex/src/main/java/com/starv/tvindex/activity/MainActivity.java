package com.starv.tvindex.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.starv.tvindex.R;
import com.starv.tvindex.base.BaseActivity;
import com.starv.tvindex.util.Constant;
import com.starv.tvindex.util.ShareSdkUtil;
import com.starv.tvindex.util.dialog.ShareDialog;
import com.starv.tvindex.util.dialog.UpdataDialog;
import com.starv.tvindex.util.view.SharePop;
import com.starv.version.CheckVersionCallBack;
import com.starv.version.StarV;
import com.starv.version.entry.AppVersionInfo;


/**
 * Created by ysq on 2019/2/25.
 */

public class MainActivity extends BaseActivity implements CheckVersionCallBack {


    private LinearLayout ll_qtss, ll_sssj, ll_wjpm, ll_rbzy, ll_qtyycj, ll_lxfs;
    private SharePop sharePop;
    private ImageView iv_right_btn;
    private String url;
    private long preTime;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainac);

//        boolean isPermission = checkSelfPermissionAll(new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES, Manifest.permission.WRITE_EXTERNAL_STORAGE});
//        if (isPermission) {
////            Toast.makeText(MainActivity.this, "正在查看!", Toast.LENGTH_SHORT).show();
//            return;
//        }
////        请求权限
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);


        ll_qtss = (LinearLayout) findViewById(R.id.ll_qtss);
        ll_sssj = (LinearLayout) findViewById(R.id.ll_sssj);
        ll_wjpm = (LinearLayout) findViewById(R.id.ll_wjpm);
        ll_rbzy = (LinearLayout) findViewById(R.id.ll_rbzy);
        ll_qtyycj = (LinearLayout) findViewById(R.id.ll_qtyycj);
        ll_lxfs = (LinearLayout) findViewById(R.id.ll_lxfs);
        iv_right_btn = (ImageView) findViewById(R.id.iv_right_btn);
        StarV.getInstance().checkVersion(MainActivity.this);
        sharePop = new SharePop(MainActivity.this, R.layout.pop_share, shareSelectorListener);
        ll_qtss.setOnClickListener(onClickListener);
        ll_sssj.setOnClickListener(onClickListener);
        ll_wjpm.setOnClickListener(onClickListener);
        ll_rbzy.setOnClickListener(onClickListener);
        ll_qtyycj.setOnClickListener(onClickListener);
        ll_lxfs.setOnClickListener(onClickListener);
        iv_right_btn.setOnClickListener(onClickListener);
        sharePop.setOnDismissListener(onDismissListener);
//        Toast.makeText(MainActivity.this,"版本更新的!!!",Toast.LENGTH_SHORT).show();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //    检查是否拥有指定的所有权限
    private boolean checkSelfPermissionAll(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                case R.id.ll_qtss:
                    intent = new Intent(MainActivity.this, VarietyDetailsActivity.class);
                    intent.putExtra("time_range", "ALL");
                    intent.putExtra("tiaomu", "24小时全天历史数据排行");
                    startActivity(intent);
                    break;
                case R.id.ll_sssj:
                    intent = new Intent(MainActivity.this, RealTimeDataActivity.class);
                    startActivity(intent);
                    break;
                case R.id.ll_wjpm:
                    intent = new Intent(MainActivity.this, VarietyDetailsActivity.class);
                    intent.putExtra("time_range", "TELEPLAY");
                    intent.putExtra("tiaomu", "卫视黄金档电视剧排行榜单");
                    startActivity(intent);
                    break;
                case R.id.ll_rbzy:
                    intent = new Intent(MainActivity.this, VarietyDetailsActivity.class);
                    intent.putExtra("time_range", "VARIETY");
                    intent.putExtra("tiaomu", "卫视晚间档综艺排行榜单");
                    startActivity(intent);
                    break;
                case R.id.ll_qtyycj:
                    intent = new Intent(MainActivity.this, ApplicationScenarioActivity.class);
                    startActivity(intent);
                    break;
                case R.id.ll_lxfs:
                    intent = new Intent(MainActivity.this, ShareActivity.class);
                    startActivity(intent);
                    break;
                case R.id.iv_right_btn:
//                    if (sharePop != null) {服
//                        if (sharePop.isShowing()) {
//                            sharePop.dismiss();
//                            // StateChanged();
//                        } else {
//                            setWindowTranslucence(0.3);
//                            sharePop.showAtLocation(ll_qtyycj, Gravity.BOTTOM, 0, 0);
//                        }
//                    }
                    ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO,shareListener).setValues(getResources().getString(R.string.app_name), BitmapFactory.decodeResource(getResources(), R.mipmap.logo), Constant.apkUrl, "电视媒介星红榜！网罗全国收视大数据，洞察传媒纵深新价值！");
                    ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO,shareListener).showShare();
//                    ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO).setValues(getResources().getString(R.string.app_name), R.mipmap.logo+"",Constant.apkUrl,"电视媒介星红榜！网罗全国收视大数据，洞察传媒纵深新价值！");
//                    ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO).showShare();
                    break;
            }
        }
    };

    private ShareSdkUtil.ShareListener shareListener = new ShareSdkUtil.ShareListener() {
        @Override
        public void onCompleteShareListener() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this,"分享成功!",Toast.LENGTH_LONG).show();
                    show("分享成功");

                }
            });
        }

        @Override
        public void ononCancelShareListener() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    Toast.makeText(MainActivity.this,"分享取消!",Toast.LENGTH_LONG).show();
                    show("分享取消");
                }
            });
        }
    };

    public void show(String text){
        ShareDialog dialog = new ShareDialog(MainActivity.this).builder()
                .setTitle(text)
                .setNegativeButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
        Log.e("TAG","分享弹框:::");
        dialog.show();
    }



//    @Override
//    public void onBackPressed() {
//        boolean isReturn = showConfirmToast();
//        if (isReturn)
//            return;
//
//
//        // SharePreferenceUtils.getInstance(getApplicationContext()).clearUser();
//        super.onBackPressed();
//    }

    /*
       返回键退出程序
     */
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > preTime + 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            preTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();//相当于finish()
//            BaseActivity.realBack();//删除所有引用
        }

    }



    private long mExitTime;

    public boolean showConfirmToast() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - mExitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            mExitTime = secondTime;

            return true;

        }
        return false;
    }

    private SharePop.ShareSelectorListener shareSelectorListener = new SharePop.ShareSelectorListener() {
        @Override
        public void onCancelListener() {
            sharePop.dismiss();
        }

        @Override
        public void onShareSelectorListener(String data) {

            if ("WX".equals(data)) {

            } else if ("WX_PY".equals(data)) {

            } else if ("QQ".equals(data)) {

            } else if ("QQ_PY".equals(data)) {

            } else if ("WB".equals(data)) {

            }
            sharePop.dismiss();
        }
    };

    private PopupWindow.OnDismissListener onDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            setWindowTranslucence(1.0f);
        }
    };

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
//        client2.connect();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        AppIndex.AppIndexApi.start(client, getIndexApiAction());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        AppIndex.AppIndexApi.start(client2, getIndexApiAction0());
    }

    @Override
    public void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
//        AppIndex.AppIndexApi.end(client2, getIndexApiAction0());

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        AppIndex.AppIndexApi.end(client, getIndexApiAction());
//        client.disconnect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client2.disconnect();
    }

    @Override
    public void callBack(final AppVersionInfo appVersionInfo) {
        if (appVersionInfo != null) {
//            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this,R.style.Dialog_FS)

//            appVersionInfo.getRemark()
            UpdataDialog dialog = new UpdataDialog(MainActivity.this).builder()
                    .setTitle("版本：" + appVersionInfo.getVersionName())
                    .setMsg("本次更新：\n" + appVersionInfo.getRemark())
                    .setNegativeButton("下载更新", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (Build.VERSION.SDK_INT >= 23) {
                                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                                    StarV.getInstance().downLoadApk(appVersionInfo.getDownloadUrl(), getString(R.string.app_name));
                                } else {
                                    url = appVersionInfo.getDownloadUrl();
                                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x100);
                                }
                            } else {
                                StarV.getInstance().downLoadApk(appVersionInfo.getDownloadUrl(), getString(R.string.app_name));
                            }
                        }
                    });
            Log.e("TAG", "appVersionInfo.getVersionCode()==" + appVersionInfo.getVersionCode());
//            if (appVersionInfo.getVersionCode() % 2 == 0) {//最新版偶数 必须更新
//                dialog.setCancelable(false);
//            } else {
                dialog.setPositiveButton("稍后更新", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
//            }
            dialog.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0x100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                StarV.getInstance().downLoadApk(url, getResources().getString(R.string.app_name));
            }
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction0() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
}
