//package com.starv.tvindex.util.net;
//
//import android.content.Context;
//import android.util.Log;
//
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.Volley;
//
//import java.io.ByteArrayOutputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 网络数据请求
// * http://blog.csdn.net/onlysnail/article/details/47905375
// */
//public class volleyUtil {
//    private Context m_obj_Context;
//    private RequestQueue mRequestQueue;
//    //访问连接
//    private String m_str_url;
//    //携带的参数
//    Map<String,String> m_map_header = null;
//    //请求方式
//    int m_i_method = Request.Method.GET;
//
//    //网络请求后的响应
//    private IResponse m_callback;
//
//    //请求参数
//    private String m_str_requestJson;
//
//    private static volleyUtil instance;
//
//    public static volleyUtil getInstance(){
//        if(null == instance){
//            instance = new volleyUtil();
//        }
//        return instance;
//    }
//
//
//    public enum EMethod {
//        EMethod_Unknown, EMethod_Get, EMethod_Post
//    }
//
//    public volleyUtil init(Context context,String url,EMethod method,HashMap<String,String> value,IResponse lister){
//        m_obj_Context = context;
//        m_str_url = url;
//
//        if(method == EMethod.EMethod_Get){
//            m_i_method = Request.Method.GET;
//        }else if(method == EMethod.EMethod_Post){
//            m_i_method = Request.Method.POST;
//        }
//        m_callback = lister;
//        return this;
//    }
//    public volleyUtil init(Context context,String url,EMethod method,Map<String,String> header,String request_json,IResponse lister){
//        m_obj_Context = context;
//        m_str_url = url;
//        m_map_header = header;
//        m_str_requestJson = request_json;
//        if(method == EMethod.EMethod_Get){
//            m_i_method = Request.Method.GET;
//        }else if(method == EMethod.EMethod_Post){
//            m_i_method = Request.Method.POST;
//        }
//        m_callback = lister;
//        return this;
//    }
//
//    public void volley_str_update(){
//
//        RequestQueue mQueue = Volley.newRequestQueue(m_obj_Context) ;
//        //String url = "http://122.144.168.27:8088/sdiptv/sdiptv.json";
//        //String params = "cityname=朝阳";
//        // url = url + "?" + params;
//        myStringRequest stringRequest = new myStringRequest(m_i_method, m_str_url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                //Log.i("choiceaccouttwo","data ----" + response);
//
//                m_callback.success(response);
//
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("error","data ----" + error.toString());
//                m_callback.error(error.toString());
//            }
//        }){
////            @Override
////            public Map<String, String> getHeaders() throws AuthFailureError {
////                Map<String,String> map = new HashMap<>();
////                map.put("apikey","f71e5f1e08cd5a7e42a7e9aa70d22458");
////                return map;
////            }
//
//            @Override
//            public String getBodyContentType() {
//                return "application/x-www-form-urlencoded; charset=" + this.getParamsEncoding();
//            }
//
////            @Override
////            public RetryPolicy getRetryPolicy() {
////                RetryPolicy retryPolicy = new DefaultRetryPolicy(30*1000, /*DefaultRetryPolicy.DEFAULT_MAX_RETRIES*/0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
////                return retryPolicy;
////            }
//        };
//        DefaultRetryPolicy defaultRetryPolicy = new DefaultRetryPolicy(15*1000,
//                /*DefaultRetryPolicy.DEFAULT_MAX_RETRIES*/0,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(defaultRetryPolicy);
//        mQueue.add(stringRequest);
//    }
//    //choiceaccouttwo
//    public void volley_str(){
//
//        RequestQueue mQueue = Volley.newRequestQueue(m_obj_Context) ;
//        myStringRequest stringRequest = new myStringRequest(m_i_method, m_str_url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                m_callback.success(response);
//
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("error","data ----" + error.toString());
//                m_callback.error(error.toString());
//            }
//        }){
//
//
//            @Override
//            public byte[] getBody() throws AuthFailureError {
//
////                Map<String,String> jsonrpc = new HashMap<String, String>();
////                jsonrpc.put("jsonrpc","2.0");
////
////                Map<String,String> method = new HashMap<String, String>();
////                method.put("method","login");
////
////                Map<String,String> id = new HashMap<String, String>();
////                id.put("id","1");
////
////                Map<String,String> params = new HashMap<String, String>();
////                params.put("phone", "111111");
////                params.put("password", "111111");
//                //String json1 = null;
//                ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
//                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayInputStream);
//                try {
//                    //json1 = gsonUtil.getJsonData1(jsonrpc, method, params, id);
//                    dataOutputStream.writeInt(m_str_requestJson.getBytes().length);
//                    dataOutputStream.write(m_str_requestJson.getBytes());
//                    dataOutputStream.flush();
//                    dataOutputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return byteArrayInputStream.toByteArray();
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
////                Map<String,String> map = new HashMap<String,String>();
////                //时间戳
////                long timestamp = System.currentTimeMillis();
////                map.put("timestamp",Long.toString(timestamp));
////                String encryption = md5.getJiaMi("3C9F17FD-CA6F-4204-B6F7-B5BC6338165E", Long.toString(timestamp));
////                map.put("token",encryption);
//                return m_map_header;
//            }
//
////            @Override
////            public RetryPolicy getRetryPolicy() {
////                RetryPolicy retryPolicy = new DefaultRetryPolicy(30*1000, /*DefaultRetryPolicy.DEFAULT_MAX_RETRIES*/0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
////                return retryPolicy;
////            }
//        };
////        请求超时时间设置
//        DefaultRetryPolicy defaultRetryPolicy = new DefaultRetryPolicy(15*1000,
//                /*DefaultRetryPolicy.DEFAULT_MAX_RETRIES*/0,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(defaultRetryPolicy);
//        Log.i("test", "request_timeout = " + stringRequest.getTimeoutMs());
//
//        mQueue.add(stringRequest);
//    }
//
//
//
//    //请求响应后的回调接口
//    public interface IResponse{
//        void success(String json);
//        void error(String error);
//    }
//}
