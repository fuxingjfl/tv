//package com.starv.tvindex.util.net;
//
//import com.android.volley.NetworkResponse;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.toolbox.HttpHeaderParser;
//
//import java.io.UnsupportedEncodingException;
//
///**
// * Created by weizhi on 2016/3/1.
// */
//public class myStringRequest extends Request<String> {
//
//    private final Response.Listener<String> mListener;
//
//    public myStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
//        super(method, url, errorListener);
//        this.mListener = listener;
//    }
//
//    public myStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
//        this(0, url, listener, errorListener);
//    }
//
//    protected void deliverResponse(String response) {
//        this.mListener.onResponse(response);
//    }
//
//    protected Response<String> parseNetworkResponse(NetworkResponse response) {
//        String parsed;
//        try {
//            //parsed = new String(response.data, "utf-8");
//            parsed = new String(response.data, "utf-8");
//        } catch (UnsupportedEncodingException var4) {
//            parsed = new String(response.data);
//        }
//
//        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
//    }
//
////    @Override
////    protected Map<String, String> getParams() throws AuthFailureError {
////        Map<String, String> map = new HashMap<String, String>();
////        map.put("name", "weizhi");
////        map.put("code", "123");
////
////        return map;
////    }
//
//
//
//    @Override
//    public String getBodyContentType() {
//        return "text/*; charset=" + this.getParamsEncoding();
//        //return super.getBodyContentType();
//    }
//}
