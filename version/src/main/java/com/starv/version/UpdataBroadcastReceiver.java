package com.starv.version;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by ysq on 2019/3/13.
 */

public class UpdataBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        long myDwonloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
//        long refernece = SharePreHelper.getIns().getLongData("refernece", 0);
//        if (refernece != myDwonloadID) {
//            return;
//        }
//
//        DownloadManager dManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//        Uri downloadFileUri = dManager.getUriForDownloadedFile(myDwonloadID);
//        installAPK(context,downloadFileUri);
//        installApk(context);


    }


    private void installApk(Context context) {
        File file;
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), context.getPackageName() + ".apk");
        if (file == null) {
            return ;
        }
        Uri uri = null;
//        if (Build.VERSION.SDK_INT >= 24) {
//            uri = FileProvider.getUriForFile(context, "com.starv.tvindex.fileprovider", file);
//        } else {
//            uri = Uri.fromFile(file);
//        }
//        if (Build.VERSION.SDK_INT < 23) {
//            Intent intents = new Intent();
//            intents.setAction("android.intent.action.VIEW");
//            intents.addCategory("android.intent.category.DEFAULT");
//            intents.setType("application/vnd.android.package-archive");
//            intents.setData(uri);
//            intents.setDataAndType(uri, "application/vnd.android.package-archive");
//            intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intents);
//        } else {
//            file = queryDownloadedApk(context);
//            if (file.exists()) {
//                openFile(file, context);
//            }
//
//        }

        Intent intent = new Intent(Intent.ACTION_VIEW); if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, "com.starv.tvindex.fileprovider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else { intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }


    }

    /**
     * 通过downLoadId查询下载的apk，解决6.0以后安装的问题
     * @param context
     * @return
     */
//    public static File queryDownloadedApk(Context context) {
//        File targetApkFile = null;
//        DownloadManager downloader = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//        long downloadId = SharePreHelper.getIns().getLongData("refernece", -1);
//        if (downloadId != -1) {
//            DownloadManager.Query query = new DownloadManager.Query();
//            query.setFilterById(downloadId);
//            query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);
//            Cursor cur = downloader.query(query);
//            if (cur != null) {
//                if (cur.moveToFirst()) {
//                    String uriString = cur.getString(cur.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
//                    if (!TextUtils.isEmpty(uriString)) {
//                        targetApkFile = new File(Uri.parse(uriString).getPath());
//                    }
//                }
//                cur.close();
//            }
//        }
//        return targetApkFile;
//
//    }


}
