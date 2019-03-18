//package com.starv.tvindex.model.local.base.activity;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.view.View;
//
//import com.starv.tvindex.R;
//import com.starv.tvindex.util.detectNetWork.netWorkState;
//
//import butterknife.Bind;
//
///**
// * Created by weizhi on 2016/8/22.
// */
//public abstract class BaseSwipeRefreshActivity extends BaseWebViewActivity {
//
//    private Runnable runnable;
//    private Handler handler=new Handler();
//
//    @Bind(R.id.swipe_container)
//    SwipeRefreshLayout swipeLayout;
//    private void initSwipeLayout(){
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
//
//
//    @Override
//    protected void initViews(View self, Bundle savedInstanceState) {
//        super.initViews(self,savedInstanceState);
//    }
//
//    @Override
//    protected void initListeners() {
//        super.initListeners();
//    }
//
//    @Override
//    protected void initData() {
//        super.initData();
//        initSwipeLayout();
//        initRunnable();
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
////                    m_obj_webview.reload();
////                    m_obj_webview.setScaleY(1);
//                    loadPage();
//
//                }
////                m_obj_pull2Refresh.onRefreshComplete();
//            }
//        };
//    }
//}
