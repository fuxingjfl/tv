//package com.starv.tvindex.util.detectNetWork.service;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.util.Log;
//import android.widget.Toast;
//
///**
//     * 监听网络状态的变化情况
//     * */
//    public  class detectNetWorkReceviver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.i("test", "-------- onRecevie -------------");
//            NetworkInfo.State wifiState = null;
//            NetworkInfo.State mobileState = null;
//            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
//            mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
//            if (wifiState != null && mobileState != null
//                    && NetworkInfo.State.CONNECTED != wifiState
//                    && NetworkInfo.State.CONNECTED == mobileState) {
//                // 手机网络连接成功
//                Toast.makeText(context, "手机网络连接成功", Toast.LENGTH_SHORT).show();
//            } else if (wifiState != null && mobileState != null
//                    && NetworkInfo.State.CONNECTED != wifiState
//                    && NetworkInfo.State.CONNECTED != mobileState) {
//                // 手机没有任何的网络
//                Toast.makeText(context,"手机没有任何的网络",Toast.LENGTH_SHORT).show();
//            } else if (wifiState != null && NetworkInfo.State.CONNECTED == wifiState) {
//                // 无线网络连接成功
//                Toast.makeText(context,"无线网络连接成功",Toast.LENGTH_SHORT).show();
//            }
//        }
//
//    }