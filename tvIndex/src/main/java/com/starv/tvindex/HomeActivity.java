//package com.starv.tvindex;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//
//import com.starv.tvindex.base.CommonActivity;
//import com.starv.tvindex.util.Constant;
//import com.starv.tvindex.util.Utility;
//import com.starv.tvindex.util.detectNetWork.netWorkState;
//import com.starv.tvindex.util.view.ProgressWebView;
//import com.starv.tvindex.util.view.WebViewUtil;
//
//
///**
// * Created by weizhi on 2016/4/25.
// */
//public class HomeActivity extends CommonActivity {
//    LayoutInflater layoutInflater = null;
//    private View m_obj_mydiscountActivity_view;
//
//    private ProgressWebView m_obj_webview;
//
//    //左图标
//
//    //标题
//    private TextView m_obj_title;
//    //激活
//    private TextView m_obj_active;
//
//    //网页加载进度条
//    private ProgressBar m_webview_progress;
//
//    private WebViewUtil m_obj_webviewUtil;
//    //下拉刷新控件
//    //private PullToRefreshScrollView m_obj_pull2Refresh;
//
//    //private SwipeRefreshLayout swipeLayout;
//    private Runnable runnable;
//    private Handler handler=new Handler();
//
//    private RelativeLayout m_obj_loadError_UI;
//    private Button m_obj_refresh;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        layoutInflater = LayoutInflater.from(this);
//        m_obj_mydiscountActivity_view = layoutInflater.inflate(R.layout.homeactivity, null);
//        setContentView(m_obj_mydiscountActivity_view);
//        initView(m_obj_mydiscountActivity_view);
//        Utility.getScreenDpi(this);
//
//
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        //m_obj_webview.reload();
//    }
//
//    private void initView(View view){
//
//
//
//
//
//        m_obj_webview = (ProgressWebView) view.findViewById(R.id.webview);
//
//        m_obj_webviewUtil = new WebViewUtil();
//        m_webview_progress = (ProgressBar) view.findViewById(R.id.custom_progressbar);
//        m_obj_webview.setProgressbar(m_webview_progress);
//        //m_obj_webviewUtil.setView(m_obj_webview, this, Constant.homeUrl);
//
//        //initRunnable();
//        //initSwipeLayout(m_obj_mydiscountActivity_view);
//
//        m_obj_loadError_UI = (RelativeLayout) view.findViewById(R.id.id_load_error_ui);
//        m_obj_refresh = (Button) view.findViewById(R.id.id_reload_page);
//        if(!netWorkState.isNetWorkAvailable(getApplicationContext())){
//            m_obj_loadError_UI.setVisibility(View.VISIBLE);
//        }else{
//
//            m_obj_webviewUtil.setView(m_obj_webview, this, Constant.homeUrl);
//        }
//        m_obj_refresh.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                if (!netWorkState.isNetWorkAvailable(getApplicationContext())) {
//                    m_obj_loadError_UI.setVisibility(View.VISIBLE);
//                } else {
//                    m_obj_loadError_UI.setVisibility(View.GONE);
//                    //m_obj_webview.reload();
//                    m_obj_webviewUtil.setView(m_obj_webview, HomeActivity.this, Constant.homeUrl);
//                }
//            }
//        });
//    }
//
//
//
////    private void initSwipeLayout(View view){
////        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
////        swipeLayout.setColorSchemeResources(R.color.color_orange2,
////                R.color.color_orange1,
////                R.color.color_orange2,
////                R.color.color_orange3);
//////        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//////
//////            @Override
//////            public void onRefresh() {
//////                //重新刷新页面
//////                handler.postDelayed(runnable, 500);
//////            }
//////        });
////    }
//    //下拉刷新之后的操作Runnable的初始化
////    private void initRunnable() {
////        //下拉刷新后的操作
////        runnable = new Runnable() {
////            @Override
////            public void run() {
//////                swipeLayout.setRefreshing(false);
//////                m_obj_webviewUtil.getWebView().reload();
////                swipeLayout.setRefreshing(false);
////                if(!netWorkState.isNetWorkAvailable(getApplicationContext())){
////                    m_obj_loadError_UI.setVisibility(View.VISIBLE);
////                }else{
////                    m_obj_loadError_UI.setVisibility(View.GONE);
////                    m_obj_webview.reload();
////                }
//////                m_obj_pull2Refresh.onRefreshComplete();
////            }
////        };
////    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        m_obj_webview.reload();
//
//        //重新加载后回到view顶部
//        m_obj_webview.scrollTo(0,0);
//
//    }
//}
