//package com.starv.tvindex.model.local.base.fragment;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//
//import com.starv.tvindex.R;
//import com.starv.tvindex.TvindexApp;
//import com.starv.tvindex.util.Constant;
//import com.starv.tvindex.util.SharePreferenceUtil;
//import com.starv.tvindex.util.detectNetWork.netWorkState;
//import com.starv.tvindex.util.view.ProgressWebView;
//import com.starv.tvindex.util.view.WebViewUtil;
//
//import java.text.SimpleDateFormat;
//import java.util.HashMap;
//import java.util.Map;
//
//import butterknife.Bind;
//import butterknife.OnClick;
//
///**
// * Created by weizhi on 2016/8/16.
// * 加载webview的fragment
// */
//public abstract class BaseWebViewFragment extends BaseFragment {
//
//    //webview
//    @Bind(R.id.local_webview)
//    protected ProgressWebView m_obj_webview;
//
//
//    //进度条
//    @Bind(R.id.local_custom_progressbar)
//    ProgressBar m_webview_progress;
//
//    //加载失败显示页面
//    @Bind(R.id.id_load_error_ui)
//    RelativeLayout m_obj_loadError_UI;
//
//    protected WebViewUtil m_obj_webviewUtil;
//
//    private Map<String,String> map = new HashMap<>();
//
//
//    //share分享的数据
//    private String m_str_shareIcon = null;//图标地址
//    private String m_str_shareUrl = null;//点击后跳转地址
//    private String m_str_shareContent = null;//描述内容
//
//
//    /**
//     * 获取需要加载的url
//     * */
//    protected abstract String getLoadUrl();
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        /*mSubscription = RxBus.getInstance().toObservable(WebViewFlagEvent.class)
//                .subscribe(new Action1<WebViewFlagEvent>() {
//
//                    @Override
//                    public void call(WebViewFlagEvent changeEvent) {
//                        Log.i("test","changeEvent");
//                        if(changeEvent.getFlag().equals(WebViewFlagEvent.Flag.FLAG_AreaChange)){
//                            map.put(Constant.localData_area,SharePreferenceUtil.getInstance(getActivity()).get(Constant.localData_area));
//                            loadPage();
//                        }else if(changeEvent.getFlag().equals(WebViewFlagEvent.Flag.FLAG_loadTimeOut)){
//                            ShowReloadPage();
//                        }
//                    }
//                });*/
//
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }
//
//    @Override
//    protected void initData() {
//        m_obj_webviewUtil = new WebViewUtil();
//        m_obj_webview.setProgressbar(m_webview_progress);
//
//        map.put(Constant.token, SharePreferenceUtil.getInstance(getActivity()).get(Constant.token));
//        map.put(Constant.deviceType, "android");
//        init();
//
//    }
//
//    public void addMap(Map map_value){
//        map.putAll(map_value);
//    }
//
//
//    private void init(){
//        loadPage();
//    }
//
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            switch (msg.what){
//                case 1:
//                    SharePreferenceUtil.getInstance(getActivity()).save(Constant.token,m_str_token);
//                    reloadPage();
//                    break;
//            }
//        }
//    };
//    private String m_str_token;
//    @Override
//    protected void initListeners() {
//        m_obj_webviewUtil.setTimeOutLister(new WebViewUtil.IDoTimeOut() {
//            @Override
//            public void ReloadPage() {
//                ShowReloadPage();
//            }
//        });
//        m_obj_webviewUtil.setShowLoadPage(new WebViewUtil.IShowLoadPage() {
//            @Override
//            public void ReloadPage() {
//                ShowReloadPage();
//            }
//        });
//        m_obj_webviewUtil.setGetShareDataLister(new WebViewUtil.IgetShareData() {
//            @Override
//            public void getShareData(String icon, String url, String content) {
//                m_str_shareIcon = icon;
//                m_str_shareUrl = url;
//                m_str_shareContent = content;
//            }
//        });
//    }
//
//    public void loadPage(){
//        if(!netWorkState.isNetWorkAvailable(getActivity())){
//            m_obj_loadError_UI.setVisibility(View.VISIBLE);
//            m_obj_webview.setVisibility(View.GONE);
//        }else{
//
//            m_obj_webview.setVisibility(View.VISIBLE);
//            m_obj_loadError_UI.setVisibility(View.GONE);
//
//            m_obj_webviewUtil.initMap(map).setView(m_obj_webview, getActivity(), getLoadUrl());
//
//        }
//    }
//
//
//    /**
//     * 显示重新加载的页面
//     * */
//    public void ShowReloadPage(){
//        if(m_obj_loadError_UI.isShown()){
//            return;
//        }
//        m_obj_loadError_UI.setVisibility(View.VISIBLE);
//        m_obj_webview.setVisibility(View.GONE);
//        m_obj_webview.stopLoading();
//
//    }
//
//    /**
//     * 重新加载响应
//     * */
//    @OnClick(R.id.id_reload_page)
//    public void reloadPage(){
////        if (!netWorkState.isNetWorkAvailable(getActivity())) {
////            m_obj_loadError_UI.setVisibility(View.VISIBLE);
////            m_obj_webview.setVisibility(View.GONE);
////        } else {
////            m_obj_loadError_UI.setVisibility(View.GONE);
////            m_obj_webview.setVisibility(View.VISIBLE);
////            //m_obj_webview.reload();
////            m_obj_webviewUtil.initMap(map).setView(m_obj_webview, getActivity(), getLoadUrl());
////        }
//        loadPage();
//    }
//
//
//
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        m_obj_webview.destroy();
//        m_obj_webviewUtil.cancleHander();
//    }
//
//    /*******  分享内容的返回 *************/
//    public String getIcon(){
//        return m_str_shareIcon;
//    }
//
//    public String getUrl(){
//        return m_str_shareUrl;
//    }
//
//    public String getContent(){
//        return m_str_shareContent;
//    }
//
//
//
//
//}
