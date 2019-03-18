package com.starv.version.util.http;

import android.os.Handler;
import android.util.TimeFormatException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by sunsiyuan on 2017/9/25.
 * <p>
 * 网络请求
 */
public class Http {
    public static final int TIME_OUT = 5000;
    private static Handler mHandler = new Handler();

    /**
     * Get请求
     *
     * @param url
     * @param params      参数
     * @param httpHandler
     */
    public static void asyncGet(final String url,
                                final HashMap<String, String> params, final HttpHandler httpHandler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final HttpResult result = connect(url, params, "GET");
                // Files.getContentUri(volumeName);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpHandler.handleResponse(result);
                    }
                });
            }
        }).start();
    }


    /**
     * 同步Get请求
     *
     * @param url
     * @param params 参数
     */
    public static HttpResult syncGet(final String url,
                                     final HashMap<String, String> params) {
        return connect(url, params, "GET");
    }

    /**
     * Post请求读取字符串
     *
     * @param url
     * @param params      参数
     * @param httpHandler
     */
    public static void asyncPost(final String url,
                                 final HashMap<String, String> params,
                                 final HttpStringHandler httpHandler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final HttpStringResult result = postString(url, params);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpHandler.handleResponse(result);
                    }
                });
            }
        }).start();
    }

    /**
     * POST请求
     *
     * @param url
     * @param params
     * @param httpHandler
     */
    public static void asyncPost(final String url,
                                 final HashMap<String, String> params, final HttpHandler httpHandler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final HttpResult result = connect(url, params, "POST");
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpHandler.handleResponse(result);
                    }
                });
            }
        }).start();
    }


    public interface HttpHandler {
        void handleResponse(HttpResult result);
    }

    public interface HttpStringHandler {
        void handleResponse(HttpStringResult result);
    }

    /**
     * 发送请求
     *
     * @param sourceUrl
     * @param params
     * @param httpMethod
     * @return
     */
    private static HttpResult connect(String sourceUrl,
                                      Map<String, String> params, String httpMethod) {
        HttpResult result = new HttpResult(0);
        try {
            String urlStr = new String(sourceUrl);
            if (httpMethod.equals("GET")) {
                urlStr += "?";
                if (params != null) {
                    Iterator<Map.Entry<String, String>> it = params.entrySet()
                            .iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, String> entry = it.next();
                        System.out.println(">>key:" + entry.getKey()
                                + " value=" + entry.getValue());
                        urlStr += entry.getKey() + "="
                                + URLEncoder.encode(entry.getValue(), "utf-8")
                                + "&";
                    }
                    params = null;
                }
            }
            result.url = urlStr;
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(httpMethod);
            connection.setDoInput(true);
            connection.setConnectTimeout(TIME_OUT);
            if (params != null) {
                Iterator<Map.Entry<String, String>> it = params.entrySet()
                        .iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> entry = it.next();
                    connection.setRequestProperty(entry.getKey(),
                            entry.getValue());
                }
            }
            connection.connect();
            int resCode = connection.getResponseCode();
            result.resCode=resCode;
            // 读数据
            InputStream in = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = (in.read(buffer))) != -1) {
                baos.write(buffer, 0, len);
            }
            String retStr = new String(baos.toByteArray());
            in.close();
            result.result = retStr;
            // getResult(result, retStr, resCode);
        } catch (MalformedURLException e) {
            result.msg = e.getClass().getSimpleName() + " " + e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            if (e instanceof SocketTimeoutException) {
                result.msg = "服务器连接超时";
            } else if (e instanceof ConnectException
                    || e instanceof SocketException) {
                result.msg = "网络连接失败";// App.getInstance().getString(R.string.connect_failed);
            } else if (e instanceof FileNotFoundException) {
                result.msg = "服务器连接失败";// App.getInstance().getString(R.string.connect_server_failed);
            } else {
                result.msg = e.getClass().getSimpleName() + " "
                        + e.getMessage();
            }
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Post请求字符串
     *
     * @param urlStr
     * @param params
     * @return
     */
    public static HttpStringResult postString(String urlStr,
                                              Map<String, String> params) {
        HttpStringResult ret = new HttpStringResult();
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(urlStr);
            HttpEntity entity;

            ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
            if (params != null) {
                Set<String> keys = params.keySet();
                for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                    String key = (String) i.next();
                    pairs.add(new BasicNameValuePair(key, params.get(key)));
                }
            }
            entity = new UrlEncodedFormEntity(pairs, "utf-8");

            httpPost.setEntity(entity);
            HttpResponse response = client.execute(httpPost);
            // 获取状态码
            int res = response.getStatusLine().getStatusCode();
            ret.resCode = res;
            HttpEntity responseEntity = response.getEntity();

            InputStream content = responseEntity.getContent();
            ret.setResult(StringUtils.readString(content));
        } catch (TimeFormatException e) {
            e.printStackTrace();
            ret.setErrorMsg(e.getLocalizedMessage());
        } catch (IOException e) {
            e.printStackTrace();
            if (e instanceof SocketTimeoutException) {
                ret.setErrorMsg("网络链接超时");
            } else if (e instanceof ConnectException
                    || e instanceof SocketException) {
                ret.setErrorMsg("网络链接失败");
            } else if (e instanceof FileNotFoundException) {
                ret.setErrorMsg("服务器链接失败");
            } else {
                ret.setErrorMsg(e.getLocalizedMessage());
            }
        }
        return ret;
    }
}
