//package com.starv.tvindex.model.local.module.view.fragment;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.TextView;
//
//import com.starv.tvindex.R;
//import com.starv.tvindex.model.local.base.fragment.BaseSwipeRefreshFragment;
//import com.starv.tvindex.model.local.event.WebViewFlagEvent;
//import com.starv.tvindex.model.local.module.view.activity.ChoiceAreaActivity;
//import com.starv.tvindex.model.local.module.view.activity.LocalXhbTabActivity;
//import com.starv.tvindex.util.Constant;
//import com.starv.tvindex.util.RxBus;
//import com.starv.tvindex.util.SetCommonHeader;
//import com.starv.tvindex.util.SharePreferenceUtil;
//import com.starv.tvindex.util.view.IconView;
//import com.starv.tvindex.util.view.WebViewUtil;
//
//import butterknife.Bind;
//import butterknife.OnClick;
//
///**
// * Created by weizhi on 2016/10/31.
// */
//public class ChoiceAreaFragment extends BaseSwipeRefreshFragment {
//
//
//
//    private WebViewFlagEvent event;
//    @Bind(R.id.id_lefticon)IconView m_obj_backIcon;
//    @Bind(R.id.id_header_title)TextView m_obj_title;
//
//    @Override
//    protected String getLoadUrl() {
//        return Constant.area_choice + "&" + SharePreferenceUtil.getInstance(getActivity()).get(Constant.localData_areaId);
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.common_webview_fragment;
//    }
//
//    @Override
//    protected void initViews(View self, Bundle savedInstanceState) {
//        m_obj_backIcon.setVisibility(View.VISIBLE);
//        m_obj_title.setVisibility(View.VISIBLE);
//        m_obj_title.setText("选择城市");
//
//
//
//
//        event = new WebViewFlagEvent();
//    }
//
//
//
//    @Override
//    protected void initListeners() {
//        super.initListeners();
//        m_obj_webviewUtil.setGetAreaLister(new WebViewUtil.IgetArea() {
//            @Override
//            public void setRegion(String areaid,String areaName) {
//
//                SharePreferenceUtil.getInstance(getActivity()).save(Constant.localData_areaId,areaid);
//                SharePreferenceUtil.getInstance(getActivity()).save(Constant.localData_area,areaName);
//
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if(((ChoiceAreaActivity)getActivity()).getFromKey().equals(getActivity().getResources().getString(R.string.fromLocalTabhost))){
//
//                            //必须放在uithread里面 不然发送event有问题
//                            event.setFlag(WebViewFlagEvent.Flag.FLAG_AreaChange);
//                            RxBus.getInstance().post(event);
//                        }else if(((ChoiceAreaActivity)getActivity()).getFromKey().equals(getActivity().getResources().getString(R.string.fromTabHost)) ||
//                                ((ChoiceAreaActivity)getActivity()).getFromKey().equals(getActivity().getResources().getString(R.string.fromLogin))){
//
//                            Intent intent = new Intent();
//                            intent.setClass(getActivity(), LocalXhbTabActivity.class);
//                            startActivity(intent);
//                        }
//                        getActivity().finish();
//                    }
//                });
//
//
//
//            }
//        });
//    }
//
//    @OnClick(R.id.id_lefticon)
//    public void back(){
//
//
////        SharePreferenceUtil.getInstance(getActivity()).save(Constant.localData_area,"长沙");
////        event.setFlag(WebViewFlagEvent.Flag.FLAG_AreaChange);
////        RxBus.getInstance().post(event);
//        getActivity().finish();
//    }
//
////    @OnClick(R.id.id_header_title)
////    public void choiceTest(){
////        String[] are = {"广西壮族自治区上海","上海"};
////        if(SharePreferenceUtil.getInstance(getActivity()).get(Constant.localData_area).length() > 2){
////            SharePreferenceUtil.getInstance(getActivity()).save(Constant.localData_area,are[1]);
////        }else{
////            SharePreferenceUtil.getInstance(getActivity()).save(Constant.localData_area,are[0]);
////        }
////
////
////        if(((ChoiceAreaActivity)getActivity()).getFromKey().equals(getActivity().getResources().getString(R.string.fromLocalTabhost))){
////
////            event.setFlag(WebViewFlagEvent.Flag.FLAG_AreaChange);
////            RxBus.getInstance().post(event);
////        }else if(((ChoiceAreaActivity)getActivity()).getFromKey().equals(getActivity().getResources().getString(R.string.fromTabHost)) ||
////                ((ChoiceAreaActivity)getActivity()).getFromKey().equals(getActivity().getResources().getString(R.string.fromLogin))){
////
////            Intent intent = new Intent();
////            intent.setClass(getActivity(), LocalXhbTabActivity.class);
////            startActivity(intent);
////        }
////        getActivity().finish();
////    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//}
