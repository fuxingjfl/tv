//package com.starv.tvindex.util.detectNetWork;
//
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Build;
//
///**
// * Created by weizhi on 2015/12/25.
// * 网络状态的判断
// */
//public class netWorkState {
//
//    //定义了网络类型
//    public enum ENetType
//    {
//        WIFI,//wifi网络
//        MOBILE,//手机网络
//        NONET//没有网络
//    }
//
//    /**
//     * 判断当前是否有网络可以使用
//     * true 有网络
//     * false 无网络
//     * */
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public static boolean  isNetWorkAvailable(Context context){
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
//
//
//        for(NetworkInfo info : networkInfos){
//            if(NetworkInfo.State.CONNECTED == info.getState()){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 判断当前网络是否连接
//     * true:连接
//     * false:没有连接
//     * */
//    public static boolean isNetWorkConnect(Context context){
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        if(null != networkInfo){
//            return networkInfo.isAvailable();
//        }
//        return false;
//    }
//
//    /**
//     * 判断WiFi是否连接
//     * true:连接
//     * false:没有连接
//     * */
//    public static boolean isWifiConnect(Context context){
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        if(null != networkInfo){
//            return networkInfo.isConnected();
//        }
//        return false;
//    }
//
//    /**
//     * 判断手机网络是否连接
//     * true:连接
//     * false:没有连接
//     * */
//    public static boolean isMobileConnect(Context context){
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//        if(null != networkInfo){
//            return networkInfo.isConnected();
//        }
//        return false;
//    }
//
//    /**
//     * 判断当前网络类型
//     * */
//    public static ENetType getNetWorkStyle(Context context){
//        if(!isNetWorkAvailable(context)){
//            return ENetType.NONET;
//        }
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        if(ConnectivityManager.TYPE_WIFI == networkInfo.getType()){
//            return ENetType.WIFI;
//        }else if(ConnectivityManager.TYPE_MOBILE == networkInfo.getType()){
//            return ENetType.MOBILE;
//        }
//        return ENetType.NONET;
//    }
//
//
//
//}
