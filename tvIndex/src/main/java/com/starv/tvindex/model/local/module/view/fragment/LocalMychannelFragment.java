//package com.starv.tvindex.model.local.module.view.fragment;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//
//import com.starv.tvindex.R;
//import com.starv.tvindex.model.local.base.fragment.BaseSwipeRefreshFragment;
//import com.starv.tvindex.model.local.event.WebViewFlagEvent;
//import com.starv.tvindex.util.Constant;
//import com.starv.tvindex.util.RxBus;
//import com.starv.tvindex.util.SharePreferenceUtil;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import rx.functions.Action1;
//
///**
// * Created by weizhi on 2016/8/15.
// * 我的频道页面
// */
//public class LocalMychannelFragment extends BaseSwipeRefreshFragment {
//
//
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.local_tab_fragment;
//    }
//
//    @Override
//    protected void initViews(View self, Bundle savedInstanceState) {
//        Map<String,String> map = new HashMap<>();
//        map.put(Constant.localData_areaId, SharePreferenceUtil.getInstance(getActivity()).get(Constant.localData_areaId));
//        addMap(map);
//    }
//
//
//
//    @Override
//    protected void initListeners() {
//        super.initListeners();
//        mSubscription = RxBus.getInstance().toObservable(WebViewFlagEvent.class)
//                .subscribe(new Action1<WebViewFlagEvent>() {
//
//                    @Override
//                    public void call(WebViewFlagEvent changeEvent) {
//                        if(changeEvent.getFlag().equals(WebViewFlagEvent.Flag.FLAG_AreaChange)){
//                            Map<String,String> map = new HashMap();
//                            map.put(Constant.localData_areaId,SharePreferenceUtil.getInstance(getActivity()).get(Constant.localData_areaId));
//
//                            addMap(map);
//                            loadPage();
//                        }
//                    }
//                });
//    }
//
//
//
//    @Override
//    protected void initData() {
//        super.initData();
//    }
//
//    @Override
//    protected String getLoadUrl() {
////        Log.i("test","url = " + Constant.myChannel_url + "?" + "regionId="+SharePreferenceUtil.getInstance(getActivity()).get(Constant.localData_areaId));
//        return Constant.myChannel_url + "?" + "regionId="+SharePreferenceUtil.getInstance(getActivity()).get(Constant.localData_areaId);
//    }
//}
