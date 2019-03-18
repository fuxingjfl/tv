//package com.starv.tvindex.model.fragment;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v4.app.Fragment;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.starv.tvindex.R;
//import com.starv.tvindex.model.activity.TabHostActivity;
//import com.starv.tvindex.util.Constant;
//import com.starv.tvindex.util.detectNetWork.netWorkState;
//import com.starv.tvindex.util.view.ProgressWebView;
//import com.starv.tvindex.util.view.WebViewUtil;
//
///**
// * Created by weizhi on 2016/6/13.
// */
//public class SmartTVDataFragment extends Fragment {
//
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
//
//
//    private SwipeRefreshLayout swipeLayout;
//
//    //share分享的数据
//    private String m_str_shareIcon = null;//图标地址
//    private String m_str_shareUrl = null;//点击后跳转地址
//    private String m_str_shareContent = null;//描述内容
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.homeactivity, null);
//    }
//
//    @Override
//    public void onViewCreated(View view,  Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        initView(view);
//    }
//
//    private void initView(View view){
//
////        Log.i("test","SmartTVDataFragment initview");
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
////        if(!netWorkState.isNetWorkAvailable(getActivity())){
////            m_obj_loadError_UI.setVisibility(View.VISIBLE);
////        }else{
////
////            m_obj_webviewUtil.setView(m_obj_webview, getActivity(), Constant.smartDataUrl);
////        }
//        m_obj_refresh.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                if (!netWorkState.isNetWorkAvailable(getActivity())) {
//                    m_obj_loadError_UI.setVisibility(View.VISIBLE);
//                    m_obj_webview.setVisibility(View.GONE);
//                } else {
//                    m_obj_loadError_UI.setVisibility(View.GONE);
//                    m_obj_webview.setVisibility(View.VISIBLE);
//                    //m_obj_webview.reload();
//                    m_obj_webviewUtil.setView(m_obj_webview, getActivity(), Constant.smartDataUrl);
//                }
//            }
//        });
//
//
//        //获取分享数据
//        m_obj_webviewUtil.setGetShareDataLister(new WebViewUtil.IgetShareData() {
//            @Override
//            public void getShareData(String icon, String url, String content) {
//
//                //((TabHostActivity)getActivity()).getShareData(icon,url,content);
//                m_str_shareIcon = icon;
//                m_str_shareUrl = url;
//                m_str_shareContent = content;
//            }
//        });
//
//        initRunnable();
//        initSwipeLayout(view);
//    }
//
//    public String getIcon(){
//        return m_str_shareIcon;
//    }
//    public String getUrl(){
//        return m_str_shareUrl;
//    }
//    public String getContent(){
//        return m_str_shareContent;
//    }
//    public void loadPage(){
//        if(!netWorkState.isNetWorkAvailable(getActivity())){
//            m_obj_loadError_UI.setVisibility(View.VISIBLE);
//            m_obj_webview.setVisibility(View.GONE);
//        }else{
//
//            m_obj_webview.setVisibility(View.VISIBLE);
//            m_obj_webviewUtil.setView(m_obj_webview, getActivity(), Constant.smartDataUrl);
//        }
//    }
//
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
//                if(!netWorkState.isNetWorkAvailable(getActivity())){
//                    m_obj_loadError_UI.setVisibility(View.VISIBLE);
//                    m_obj_webview.setVisibility(View.GONE);
//                }else{
//                    m_obj_loadError_UI.setVisibility(View.GONE);
//                    m_obj_webview.setVisibility(View.VISIBLE);
//                    m_obj_webview.reload();
//                }
////                m_obj_pull2Refresh.onRefreshComplete();
//            }
//        };
//    }
//}
