//package com.starv.tvindex.util.view;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.net.http.SslError;
//import android.os.AsyncTask;
//import android.os.Build;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.MotionEvent;
//import android.view.View;
//import android.webkit.CookieManager;
//import android.webkit.JavascriptInterface;
//import android.webkit.SslErrorHandler;
//import android.webkit.WebResourceRequest;
//import android.webkit.WebResourceResponse;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.Toast;
//
//import com.starv.tvindex.TvindexApp;
//import com.starv.tvindex.model.local.event.WebViewFlagEvent;
//import com.starv.tvindex.util.Constant;
//import com.starv.tvindex.util.RxBus;
//import com.starv.tvindex.util.SetCommonHeader;
//import com.starv.tvindex.util.SharePreferenceUtil;
//import com.starv.tvindex.util.timer.TimerUtil;
//
//import org.apache.http.HttpClientConnection;
//import org.apache.http.HttpRequest;
//import org.apache.http.HttpResponse;
//import org.apache.http.protocol.HTTP;
//
//import java.io.ByteArrayOutputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.text.SimpleDateFormat;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Timer;
//import java.util.TimerTask;
//
//
//public class WebViewUtil {
//
//	private WebViewUtil instance = null;
//	private View m_obj_view = null;
//	private WebView m_obj_webView = null;
//	private WebSettings settings = null;
//	private Context m_obj_context = null;
//	private Map<String,String> m_obj_map = new HashMap<String,String>();
//	private IgetJsonData m_obj_lister;
//	private IgetShareData m_obj_getshareDataLister;
//	//获取token的监听
//	private IgetToken m_obj_getTokenLister;
//	//获取area的监听
//	private IgetArea m_obj_getAreaLister;
//
//
//
//	//获取链接response头的信息
//	private ResponseHeaderTask m_obj_responseHeaderTask;
//
//
//	/****************   超时时间设置  *******************/
//	private long timeout =25 * 1000;
//	private IDoTimeOut lister_timeOut = null;
//	Handler timeoutHandler = new Handler(){
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//
////			Log.i("test","timeoutHandler message");
////			event.setFlag(WebViewFlagEvent.Flag.FLAG_loadTimeOut);
////			RxBus.getInstance().post(event);
//			if(null != lister_timeOut){
//				lister_timeOut.ReloadPage();
//			}
//			timeoutHandler.removeMessages(0);
//		}
//	};
//	//设置超时时间  默认25s
//	public void setTimeOut(long _timeout){
//		this.timeout = _timeout;
//	}
//	public interface IDoTimeOut{
//		void ReloadPage();
//	}
//	public void setTimeOutLister(IDoTimeOut lister){
//		this.lister_timeOut = lister;
//	}
//	/*******************************************************/
//
//	/**********************页面访问错误回调***********************/
//	private IShowLoadPage m_obj_load_lister;
//	public interface IShowLoadPage{
//		void ReloadPage();
//	}
//	public void setShowLoadPage(IShowLoadPage lister){
//		m_obj_load_lister = lister;
//	}
//	/***********************************************/
//
//	/**
//	 * 取消handler
//	 * */
//	public void cancleHander(){
//
//		if(null == timeoutHandler){
//			return;
//		}
//		timeoutHandler.removeMessages(0);
//	}
//
//
//	public WebView getWebView() {
//		if (null == m_obj_webView) {
//			return null;
//		}
//		return m_obj_webView;
//	}
//
//
//	public WebViewUtil initMap(Map map){
//		m_obj_map = map;
//		return this;
//	}
//	public WebViewUtil setView(WebView view, Context context, String url) {
//		m_obj_webView = view;
//		m_obj_context = context;
//
////		webview设置焦点为false 是因为在区域版的标题栏上有一个区域的跑马灯显示 当webview获取焦点的时候 TextView失去
////		焦点 则跑马灯效果结束  所以将webview的获取焦点禁止
////		m_obj_webView.setFocusable(false);
//
//
//
////		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
////			m_obj_webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
////		}
//		initData();
//		loadPage(url);
//		return this;
//	}
//
//	public WebViewUtil setView(WebView view, Context context) {
//		m_obj_webView = view;
//		m_obj_context = context;
////		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
////			m_obj_webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
////		}
//		initData();
//		return this;
//	}
//
//
//
//	private WebViewUtil loadPage(String url) {
//	//	m_obj_webView.loadUrl(url,m_obj_map);
//
//		//Log.i("test","m_obj_webView"+m_obj_webView);
//		m_obj_map.put("deviceType","android");
//
//		if(null != m_obj_map && 0 != m_obj_map.size() ){
//			m_obj_webView.loadUrl(url,m_obj_map);
//		}else{
//			m_obj_webView.loadUrl(url);
//		}
//
//		return this;
//	}
//
//
//
//	private void initData() {
//		//m_obj_webView = (WebView) m_obj_view.findViewById(R.id.id_webview);
//		settings = m_obj_webView.getSettings();
//		setWebViewSetting();
//		addJsInterface();
//
////		m_obj_webView.setOnKeyListener(new View.OnKeyListener() {
////			@Override
////			public boolean onKey(View v, int keyCode, KeyEvent event) {
////				if (event.getAction() == KeyEvent.ACTION_DOWN) {
////					if (keyCode == KeyEvent.KEYCODE_BACK && m_obj_webView.canGoBack()) {  //表示按返回键时的操作
////						m_obj_webView.goBack();   //后退
////
////						//webview.goForward();//前进
////						return true;    //已处理
////					}
////				}
////				return false;
////			}
////		});
//
//	}
//
//	private void addJsInterface(){
//		m_obj_webView.addJavascriptInterface(new tvIndexJavaScriptInterface(), "Js2JavaInterface");
//	}
//	/**
//	 * js中调用
//	 * <a onClick="window.gzgd.startSubmitActivity()">
//	 * */
//	public final class tvIndexJavaScriptInterface {
//		tvIndexJavaScriptInterface() {
//
//		}
//		/**
//		 * This is not called on the UI thread. Post a runnable to invoke
//		 * loadUrl on the UI thread.
//		 */
//		@JavascriptInterface
//		public void openView(String url,String title) {
//			if(null != m_obj_lister){
//				m_obj_lister.responseJson(url,title);
//			}
////            Intent intent = new Intent();
////            intent.putExtra("dicountId",id);
////            intent.setClass(getApplicationContext(),submitCheckActivity.class);
////            startActivity(intent);
//		}
//		@JavascriptInterface
//		public void getContent(String icon,String url,String content){
//
//			if(null != m_obj_getshareDataLister){
//				m_obj_getshareDataLister.getShareData(icon,url,content);
//			}
//		}
//
//		@JavascriptInterface
//		public void setToken(String token){
//			if(null != m_obj_getTokenLister){
//				m_obj_getTokenLister.setToken(token);
//			}
//		}
//
//
//		@JavascriptInterface
//		public void setRegion(String areaid,String areaname){
////			Log.i("test","get region");
//			if(null != m_obj_getAreaLister){
//				m_obj_getAreaLister.setRegion(areaid,areaname);
//			}
//		}
//	}
//	private void setWebViewSetting() {
//		settings.setJavaScriptEnabled(true);
//		// 设置自适应屏幕
//		settings.setUseWideViewPort(true);
//		settings.setLoadWithOverviewMode(true);
//
//		// 设置WebView支持JS
//		settings.setJavaScriptEnabled(true);
//
//		// 设置当前WebView的编码
//		//settings.setDefaultTextEncodingName("utf-8");
//
//		m_obj_webView.setBackgroundColor(Color.argb(0, 0, 0, 0));
//
//		m_obj_webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//		//不使用缓存：
//		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//
//		// 开启DOM 存储
//		settings.setDomStorageEnabled(true);
//
//
//		settings.setSupportZoom(true);
//		settings.setJavaScriptCanOpenWindowsAutomatically(true);
//		settings.setBuiltInZoomControls(true);//support zoom
//		settings.setUseWideViewPort(true);
//		settings.setLoadWithOverviewMode(true);
//
//
//
////		settings.setDefaultTextEncodingName("utf-8");//设置编码
//		// WebView内链接跳转
//		urlJumpInner();
//
//	}
//
//	private void urlJumpInner() {
//		// TODO Auto-generated method stub
//
//
//
//		m_obj_webView.setWebViewClient(new WebViewClient() {
//
//
////			@Override
////			public boolean shouldOverrideUrlLoading(WebView view, String url) {
////
////
////				view.loadUrl(url); //load url in current WebView
////				Log.i("test","jump url = " + url);
////				return true;
////				// 返回true表示停留在本WebView（不跳转到系统的浏览器）
////			}
//
//
//			@Override
//			public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//
//				SetCommonHeader.getInstance().getTextViewFoucs();
////				Log.i("test","url inner jump");
//				view.loadUrl(url,m_obj_map);
//
//				return true;
////				Log.i("test","inner jump");
////				return super.shouldOverrideUrlLoading(view, url);
//			}
//
//			@Override
//			public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
//				super.onReceivedHttpError(view, request, errorResponse);
////				Log.i("test", "error = " + errorResponse.toString());
////				Toast.makeText(m_obj_context, "加载失败", Toast.LENGTH_SHORT).show();
//			}
//
//			@Override
//			public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//				super.onReceivedSslError(view, handler, error);
////				Toast.makeText(m_obj_context, "onReceivedSslError", Toast.LENGTH_SHORT).show();
//			}
//
//			@Override
//			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//				super.onReceivedError(view, errorCode, description, failingUrl);
////				Toast.makeText(m_obj_context, "加载失败" + errorCode, Toast.LENGTH_SHORT).show();
//				if(null != m_obj_load_lister){
//					m_obj_load_lister.ReloadPage();
//				}
//
//			}
//
//
//			@Override
//			public void onPageFinished(WebView view, String url) {
//				super.onPageFinished(view, url);
//
//				timeoutHandler.removeMessages(0);
//				//m_obj_webView.loadUrl("javascript:(function(){window.Js2JavaInterface.getContent(document.getElementById('starv_icon').innerText);})()");
//				m_obj_webView.loadUrl("javascript:(function(){window.Js2JavaInterface.getContent(document.getElementById('starv_icon').innerText,document.getElementById('starv_url').innerText,document.getElementById('starv_content').innerText);})()");
//
//				//m_obj_webView.setScrollY(160);
////				m_obj_timer.cancel();
////				m_obj_timer.purge();
////				m_obj_timetask.cancel();
//
////				Log.i("test","------------ onPageFinished --------------");
//
//			}
//
//			@Override
//			public void onPageStarted(WebView view, final String url, Bitmap favicon) {
//				super.onPageStarted(view, url, favicon);
//
////				if(null != m_obj_responseHeaderTask){
////					m_obj_responseHeaderTask.execute(url,"Server");
////				}
//
////				Log.i("test1",view.getUrl() + "timeout = " + timeout);
//				timeoutHandler.removeMessages(0);
//				timeoutHandler.sendEmptyMessageDelayed(0,timeout);
////				m_obj_timer = new Timer();
////				m_obj_timetask = new TimerTask() {
////					@Override
////					public void run() {
////						if(m_obj_webView.getProgress() < 100){
////							m_obj_timer.cancel();
////							m_obj_timer.purge();
////							m_obj_timetask.cancel();
////						}
////					}
////				};
////
////				m_obj_timer.schedule(m_obj_timetask, 5000, 1);
////				Log.i("test","------------ onPageStarted --------------");
//			}
//		});
//	}
//
//	private WebViewFlagEvent event;
//	public WebViewUtil() {
//
//
//		m_obj_responseHeaderTask = new ResponseHeaderTask();
//		event = new WebViewFlagEvent();
//
//	}
//
//	//js调用java方法的回调
//	public void setLister(IgetJsonData lister){
//		this.m_obj_lister = lister;
//
//	}
//	public interface IgetJsonData{
//		void responseJson(String url,String title);
//	}
//
//	/***************** 注册获取分享数据 ***********************/
//	public void setGetShareDataLister(IgetShareData lister){
//
//		m_obj_getshareDataLister = lister;
//	}
//	public interface IgetShareData{
//		void getShareData(String icon,String url,String content);
//	}
//	/********************************************************/
//
//
//	/*********************** 获取token *******************************/
//
//	public void setGetTokenLister(IgetToken lister){
//		m_obj_getTokenLister = lister;
//	}
//	public interface IgetToken{
//		void setToken(String token);
//	}
//	/***************************************************/
//
//
//	/*********************** 获取区域城市 *******************************/
//
//	public void setGetAreaLister(IgetArea lister){
//		m_obj_getAreaLister = lister;
//	}
//	public interface IgetArea{
//		void setRegion(String regionId,String regionName);
//	}
//	/***************************************************/
//
//
//
//	/***************************获取response的header的信息***************************/
//	public String geturlResponseHeader(String url1,String headKey){
//
//
//		String read = "";
//		URL url;
//		URLConnection conexion;
//		try {
//			url = new URL(url1);
//			conexion = url.openConnection();
//			conexion.setConnectTimeout(3000);
//			conexion.connect();
//			read = conexion.getHeaderField(headKey);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return read;
//	}
//
//
//	/**
//	 * params[0]是访问的链接  arams[1]是需要获取header的key
//	 * */
//	private class ResponseHeaderTask extends AsyncTask<String,Void,String> {
//
//
//		@Override
//		protected String doInBackground(String... params) {
//			return geturlResponseHeader(params[0],params[1]);
//		}
//
//		@Override
//		protected void onPostExecute(String s) {
//			Log.i("test","header = " + s);
//			super.onPostExecute(s);
//		}
//	}
//
//
//}
