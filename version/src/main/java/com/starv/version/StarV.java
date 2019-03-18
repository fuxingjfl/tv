package com.starv.version;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.starv.version.config.Config;
import com.starv.version.entry.AppVersionInfo;
import com.starv.version.util.LogUtil;
import com.starv.version.util.NetworkStateUtil;
import com.starv.version.util.Tools;
import com.starv.version.util.http.Http;
import com.starv.version.util.http.HttpResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sunsiyuan on 2017/9/25.
 * <p>
 * 版本控制主类
 */
public class StarV {

    public static final int CHECK_VRESION = 0x002;
    public static final int HANDLE_DOWNLOAD = 0x001;
    private DownloadManager dm;
    private long downloadId = 0L;
    private OnProgressListener onProgressListener;
    private Timer timer = new Timer();
    private boolean az;//控制安装界面包括取消键一次,否则重复跳转界面
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            updateProgress();
        }
    };

    //关闭实时获取的资源
    public void stopData() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }
        iswz=false;
        az=false;
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            boolean is = false;
            switch (msg.what) {
                case CHECK_VRESION:

                    break;
                case HANDLE_DOWNLOAD:
                    //被除数可以为0，除数必须大于0
                    if (msg.arg1 >= 0 && msg.arg2 > 0) {
                        if (onProgressListener != null)
                            onProgressListener.onProgress(msg.arg1 / (float) msg.arg2);
                        int state = (int) msg.obj;
                        if (state == DownloadManager.STATUS_SUCCESSFUL) {
                            if (!az){
                                installApk();
                            }else{//安装界面的显示
                                stopData();
                            }
                        }
                    }
                    break;
            }
            return false;
        }
    });

    private Context context;

    private static StarV instance;

    /**
     * 获取单例模式
     *
     * @return
     */
    public static StarV getInstance() {
        if (instance == null) {
            synchronized (StarV.class) {
                instance = new StarV();
            }
        }
        return instance;
    }

    /**
     * 私有化构造函数，只能通过单例获取
     */
    private StarV() {

    }

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }

    public void init(Context context) {
        this.context = context;
    }

    public void checkVersion(final CheckVersionCallBack callBack) {
        if (context == null) {
            throw new NullPointerException("context is null，please init first");
        }
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            String versionKey = appInfo.metaData.getString("VersionKey");
            HashMap<String, String> params = new HashMap<>();
            params.put("appkey", versionKey);
            params.put("version", String.valueOf(Tools.getVerCode(context)));
            params.put("packageName", context.getPackageName());

            Log.e("TAG","version==="+Config.checkVersion+",appkey=="+versionKey+",version=="+String.valueOf(Tools.getVerCode(context))+",packageName="+context.getPackageName());

            Http.asyncGet(Config.checkVersion, params, new Http.HttpHandler() {
                @Override
                public void handleResponse(HttpResult result) {
                    LogUtil.e("result.resCode=" + result.resCode);
                    if (result.resCode == 200) {
                        try {
                            JSONObject jsonObject = new JSONObject(result.result);
                            if (jsonObject.getInt("errCode") == 0) {
                                JSONObject data = jsonObject.getJSONObject("data");
                                AppVersionInfo info = new AppVersionInfo();
                                info.setDownloadUrl(data.getString("downloadUrl"));
                                info.setRemark(data.getString("remark"));
                                info.setVersionCode(data.getInt("versionCode"));
                                info.setVersionName(data.getString("versionName"));
                                info.setIs_force(data.getInt("is_force"));
                                callBack.callBack(info);
                            } else {
                                callBack.callBack(null);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        callBack.callBack(null);
                    }
                }
            });
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    public long downLoadApk(String path, String title) {
        if (!path.startsWith("http://")) {
            path = Config.DOWNLOAD_SITE + path;
        }
        downloadId = startDownload(path, title, "更新apk");

        if (timer!=null||task!=null){
            stopData();
        }
        if (timer==null){
            timer = new Timer();
        }
        if (task==null){
            task = new TimerTask() {
                @Override
                public void run() {
                    updateProgress();
                }
            };
        }


        timer.schedule(task, 2000, 2000);
        return downloadId;
    }

    private long startDownload(String uri, String title, String description) {
        dm = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
        DownloadManager.Request req = new DownloadManager.Request(Uri.parse(uri));

        if (NetworkStateUtil.isWifiConnected(context)){
            req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        }else if (NetworkStateUtil.isMobileConnected(context)){
            req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE);
        }else{
            req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        }
        req.setAllowedOverRoaming(true);

        req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        //设置文件的保存的位置[三种方式]
        //第一种
        //file:///storage/emulated/0/Android/data/your-package/files/Download/update.apk
//        req.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "update.apk");
        //第二种
        //file:///storage/emulated/0/Download/update.apk
        req.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,   "xha.apk");
//        req.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, context.getPackageName() + ".apk");
        //第三种 自定义文件路径
//        req.setDestinationUri()
        // 设置一些基本显示信息
        req.setTitle(title);
        req.setDescription(description);
        req.setMimeType("application/vnd.android.package-archive");
        //加入下载队列
        return dm.enqueue(req);

        //long downloadId = dm.enqueue(req);
        //Log.d("DownloadManager", downloadId + "");
        //dm.openDownloadedFile()
    }

    /**
     * 发送Handler消息更新进度和状态
     */
    private void updateProgress() {
        int[] bytesAndStatus = getBytesAndStatus(downloadId);
        handler.sendMessage(handler.obtainMessage(HANDLE_DOWNLOAD, bytesAndStatus[0], bytesAndStatus[1], bytesAndStatus[2]));
    }

    /**
     * 通过query查询下载状态，包括已下载数据大小，总大小，下载状态
     *
     * @param downloadId
     * @return
     */
    private int[] getBytesAndStatus(long downloadId) {
        int[] bytesAndStatus = new int[]{
                -1, -1, 0
        };
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor cursor = null;
        try {
            cursor = dm.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                //已经下载文件大小
                bytesAndStatus[0] = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                //下载文件的总大小
                bytesAndStatus[1] = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                //下载状态
                bytesAndStatus[2] = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return bytesAndStatus;
    }

    private boolean iswz;//控制跳转未知安装应用界面一次.解决重复跳转

    private void installApk() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//开始下载
//        DownloadManager systemService = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
//        DownloadManager.Query query = new DownloadManager.Query() ;
//        query.setFilterById( downloadId );
//        Cursor cursor = systemService.query( query ) ;
//        if ( !cursor.moveToFirst() ) {// 没有记录
//
//        } else {
//            //有记录
//
//        }
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),   "xha.apk");
        if (file.exists()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri uri = getUriForFile(file);
                if (uri == null) {
                    return;
                }
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
                //兼容8.0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    boolean hasInstallPermission = context.getPackageManager().canRequestPackageInstalls();
                    if (!hasInstallPermission) {
//                        ToastUtil.makeText(MyApplication.getContext(), MyApplication.getContext().getString(R.string.string_install_unknow_apk_note), false);

                        startInstallPermissionSettingActivity();
                        return;
                    }else{
                        //真正安装
                        az=true;
                    }
                }else{
                    az=true;
                }
            } else {
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                az=true;
            }
        }
        context.startActivity(intent);


//        android.os.Process.killProcess(Process.myPid());
    }

    /**
     * 跳转到设置-允许安装未知来源-页面
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() {
        Log.e("TAG","外面iswz==="+iswz);
        if (!iswz){
            //注意这个是8.0新API
            Intent intent = new Intent();
            //获取当前apk包URI，并设置到intent中（这一步设置，可让“未知应用权限设置界面”只显示当前应用的设置项）
            Uri packageURI = Uri.parse("package:"+context.getPackageName());
            intent.setData(packageURI);
            //设置不同版本跳转未知应用的动作
            if (Build.VERSION.SDK_INT >= 26) {
                //intent = new Intent(android.provider.Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,packageURI);
                intent.setAction(android.provider.Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
            }else {
                intent.setAction(android.provider.Settings.ACTION_SECURITY_SETTINGS);
            }
            context.startActivity(intent);
            Log.e("TAG","里面iswz==="+iswz);
            iswz=true;
        }
    }


    public Uri getUriForFile(File file) {
        if (file == null) {
            return null;
        }
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context, "com.starv.tvindex.fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        LogUtil.e("uri=" + uri.toString());
        return uri;
    }

}
