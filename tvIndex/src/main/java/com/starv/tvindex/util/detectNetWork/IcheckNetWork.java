package com.starv.tvindex.util.detectNetWork;

/**
 * Created by weizhi on 2015/12/24.
 */
public interface IcheckNetWork {

    //页面在刷新的时候出现刷新数据错误
    interface IcheckNetWork_refreshDataError{
        void refreshDataError();
    }

    //是否连接网络
    void netWorkState();

    //程序在运行的过程当中检测到网络的状态
    void netAbnormal();

}
