//package com.starv.tvindex.model.local.module.view.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//
//import com.starv.tvindex.R;
//import com.starv.tvindex.util.Constant;
//import com.starv.tvindex.util.SetHeaderContent;
//import com.starv.tvindex.util.SharePreferenceUtil;
//import com.starv.tvindex.util.detectNetWork.netWorkState;
//import com.starv.tvindex.util.view.ProgressWebView;
//import com.starv.tvindex.util.view.WebViewUtil;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by weizhi on 2016/8/22.
// */
//public class LoginPageActivity_cancle extends FragmentActivity {
//
//
//    private ProgressWebView m_obj_webview;
//
//    //网页加载进度条
//    private ProgressBar m_webview_progress;
//
//    private SwipeRefreshLayout swipeLayout;
//    private Runnable runnable;
//    private Handler handler=new Handler();
//    private LayoutInflater layoutInflater;
//    private View m_obj_view;
//    private WebViewUtil m_obj_webviewUtil;
//
//
//    private RelativeLayout m_obj_loadError_UI;
//    private Button m_obj_refresh;
//    private Map<String,String> map = new HashMap<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        m_obj_view = layoutInflater.from(getApplicationContext()).inflate(R.layout.login_activity,null);
//        setContentView(m_obj_view);
//        map.put(Constant.deviceType,"android");
//        map.put(Constant.token,"");
//        initview();
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        // m_obj_webview.reload();
//
////        String title = this.getIntent().getStringExtra("title");
////        String url = this.getIntent().getStringExtra("loadurl");
////        m_obj_webviewUtil.setView1(m_obj_webview, getApplicationContext(), url, null);
//        // m_obj_webview.reload();
//
////        String title = this.getIntent().getStringExtra("title");
////        String url = this.getIntent().getStringExtra("loadurl");
////        WebViewUtil.getInstancePtr().setView1(m_obj_webview, getApplicationContext(), url, null);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//
//    private void initview(){
//        SetHeaderContent.getInstance().init(m_obj_view);
//        SetHeaderContent.getInstance().setTitle(getResources().getString(R.string.login));
//        SetHeaderContent.getInstance().setLeftIcon(Constant.back_icon, new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                if (m_obj_webview.canGoBack()) {
//                    m_obj_webview.goBack();
//                } else {
//
//                    finish();
//                }
//            }
//        });
//        //SetHeaderContent.getInstance().setRightTitle();
//        m_obj_webview = (ProgressWebView) m_obj_view.findViewById(R.id.webview);
//        m_webview_progress = (ProgressBar) m_obj_view.findViewById(R.id.custom_progressbar);
//
//        m_obj_webviewUtil = new WebViewUtil();
//        m_obj_webview.setProgressbar(m_webview_progress);
//
//
//        initRunnable();
//        initSwipeLayout(m_obj_view);
//
//        m_obj_loadError_UI = (RelativeLayout) m_obj_view.findViewById(R.id.id_load_error_ui);
//        m_obj_refresh = (Button) m_obj_view.findViewById(R.id.id_reload_page);
//        if(!netWorkState.isNetWorkAvailable(this)){
//            m_obj_loadError_UI.setVisibility(View.VISIBLE);
//        }else{
//
//            //m_obj_webviewUtil.setView(m_obj_webview,this,Constant.login_url);
//            m_obj_webviewUtil.initMap(map).setView(m_obj_webview, getApplicationContext(),Constant.login_url);
//        }
//        m_obj_webviewUtil.setGetTokenLister(new WebViewUtil.IgetToken() {
//            @Override
//            public void setToken(String token) {
//                SharePreferenceUtil.getInstance(getApplicationContext()).save(Constant.token,token);
//                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(),LocalXhbTabActivity.class);
//                startActivity(intent);
//                LoginPageActivity_cancle.this.finish();
//            }
//        });
//        m_obj_refresh.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                if (!netWorkState.isNetWorkAvailable(getApplicationContext())) {
//                    m_obj_loadError_UI.setVisibility(View.VISIBLE);
//                } else {
//                    m_obj_loadError_UI.setVisibility(View.GONE);
//                    m_obj_webviewUtil.setView(m_obj_webview,getApplicationContext(), Constant.login_url);
//                }
//            }
//        });
//
//
//
//    }
//
//
//    private void initSwipeLayout(View view){
//        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
//        swipeLayout.setColorSchemeResources(R.color.color_orange2,
//                R.color.color_orange1,
//                R.color.color_orange2,
//                R.color.color_orange3);
//        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//
//            @Override
//            public void onRefresh() {
//                //重新刷新页面
//                handler.postDelayed(runnable, 500);
//            }
//        });
//    }
//    //下拉刷新之后的操作Runnable的初始化
//    private void initRunnable() {
//        //下拉刷新后的操作
//        runnable = new Runnable() {
//            @Override
//            public void run() {
////                swipeLayout.setRefreshing(false);
////                m_obj_webviewUtil.getWebView().reload();
//                swipeLayout.setRefreshing(false);
//                if(!netWorkState.isNetWorkAvailable(getApplicationContext())){
//                    m_obj_loadError_UI.setVisibility(View.VISIBLE);
//                }else{
//                    m_obj_loadError_UI.setVisibility(View.GONE);
//                    m_obj_webviewUtil.initMap(map).setView(m_obj_webview, getApplicationContext(),Constant.login_url);
//                }
////                m_obj_pull2Refresh.onRefreshComplete();
//            }
//        };
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//    }
//}
