//package com.starv.tvindex.model.local.base.activity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//
//import com.starv.tvindex.R;
//import com.starv.tvindex.util.detectNetWork.netWorkState;
//import com.starv.tvindex.util.view.ProgressWebView;
//import com.starv.tvindex.util.view.WebViewUtil;
//
//import java.util.Map;
//
//import butterknife.Bind;
//import butterknife.OnClick;
//
///**
// * Created by weizhi on 2016/8/22.
// */
//public abstract class BaseWebViewActivity extends BaseActivity {
//
//    //webview
//    @Bind(R.id.webview)
//    ProgressWebView m_obj_webview;
//
//
//    //进度条
//    @Bind(R.id.custom_progressbar)
//    ProgressBar m_webview_progress;
//
//    //加载失败显示页面
//    @Bind(R.id.id_load_error_ui)
//    RelativeLayout m_obj_loadError_UI;
//
//
//    protected WebViewUtil m_obj_webviewUtil;
//
//    /**
//     * 获取需要加载的url
//     * */
//    protected abstract String getLoadUrl();
//
//    /**
//     * 需要传递的map参数
//     * */
//    protected abstract Map<String,String> getMap();
//
//    @Override
//    protected void initListeners() {
//
//    }
//
//    @Override
//    protected void initViews(View self, Bundle savedInstanceState) {
//
//    }
//
//    @Override
//    protected void initData() {
//
//        m_obj_webviewUtil = new WebViewUtil();
//        m_obj_webview.setProgressbar(m_webview_progress);
//        init();
//    }
//
//    private void init(){
//        loadPage();
//    }
//
//
//
//    public void loadPage(){
//        if(!netWorkState.isNetWorkAvailable(getApplicationContext())){
//            m_obj_loadError_UI.setVisibility(View.VISIBLE);
//        }else{
//            m_obj_webviewUtil.initMap(getMap()).setView(m_obj_webview,getApplicationContext(), getLoadUrl());
//
//        }
//    }
//
//
//    /**
//     * 重新加载响应
//     * */
//    @OnClick(R.id.id_reload_page)
//    public void reloadPage(){
//        if (!netWorkState.isNetWorkAvailable(getApplicationContext())) {
//            m_obj_loadError_UI.setVisibility(View.VISIBLE);
//        } else {
//            m_obj_loadError_UI.setVisibility(View.GONE);
//            m_obj_webviewUtil.initMap(getMap()).setView(m_obj_webview, getApplicationContext(), getLoadUrl());
//        }
//    }
//
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//}
