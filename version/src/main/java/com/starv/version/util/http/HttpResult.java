package com.starv.version.util.http;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sunsiyuan on 2017/9/25.
 * <p>
 * 网络接口返回值
 */
public class HttpResult implements Parcelable {

    public String result;
    public int resCode = -1;
    public String url;
    public String msg;

    public HttpResult(int resCode) {
        this.resCode = resCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.result);
        dest.writeInt(this.resCode);
        dest.writeString(this.url);
        dest.writeString(this.msg);
    }

    protected HttpResult(Parcel in) {
        this.result = in.readString();
        this.resCode = in.readInt();
        this.url = in.readString();
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<HttpResult> CREATOR = new Parcelable.Creator<HttpResult>() {
        @Override
        public HttpResult createFromParcel(Parcel source) {
            return new HttpResult(source);
        }

        @Override
        public HttpResult[] newArray(int size) {
            return new HttpResult[size];
        }
    };
}
