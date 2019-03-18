package com.starv.version.entry;

/**
 * Created by sunsiyuan on 2017/10/10.
 */

public class BaseBean<T> {

    private T data;

    private int errCode;
    private String reqMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg;
    }
}
