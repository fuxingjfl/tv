//package com.starv.tvindex.model.local.base.fragment;
//
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
// * Created by weizhi on 2016/8/16.
// * 具有下拉刷新的fragment
// */
//public abstract class BaseSwipeRefreshFragment extends BaseWebViewFragment {
//
//
//
//    private Runnable runnable;
//    private Handler handler=new Handler();
//
//    @Bind(R.id.local_swipe_container)
//    SwipeRefreshLayout swipeLayout;
//
//
//
//    @Override
//    protected void initData() {
//        super.initData();
//        initSwipeLayout();
//        initRunnable();
//    }
//
//
//    @Override
//    protected void initListeners() {
//        super.initListeners();
//    }
//
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
//                handler.postDelayed(runnable, 200);
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
//                //判断webview是否可见  不可见时下拉无效
//                swipeLayout.setRefreshing(false);
//                if(!m_obj_webview.isShown()){
//                    return;
//                }
//
//
//                if(!netWorkState.isNetWorkAvailable(getActivity())){
//                    m_obj_loadError_UI.setVisibility(View.VISIBLE);
//                    m_obj_webview.setVisibility(View.GONE);
//                }else{
//                    m_obj_loadError_UI.setVisibility(View.GONE);
//                    m_obj_webview.setVisibility(View.VISIBLE);
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
