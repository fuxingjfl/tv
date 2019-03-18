package com.starv.tvindex.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by ysq on 2019/3/13.
 */
@SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
public class JsInterface {

    private Context mContext;
    private IndexListener inlis;
    public JsInterface(Context context,IndexListener inlis) {
        this.mContext = context;
        this.inlis=inlis;
    }


    @JavascriptInterface
    public int getPass(int pos){
        Log.e("TAG","第一次"+pos);
        inlis.onIndexListener(pos);

        return pos;
    }



}
