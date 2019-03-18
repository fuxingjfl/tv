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
//import com.starv.tvindex.model.local.module.view.activity.ChoiceAreaActivity;
//import com.starv.tvindex.model.local.module.view.activity.LocalXhbTabActivity;
//import com.starv.tvindex.util.Constant;
//import com.starv.tvindex.util.SharePreferenceUtil;
//import com.starv.tvindex.util.view.IconView;
//import com.starv.tvindex.util.view.WebViewUtil;
//
//import butterknife.Bind;
//import butterknife.OnClick;
//
///**
// * Created by weizhi on 2016/8/23.
// */
//public class loginPageFragment extends BaseSwipeRefreshFragment {
//
//    @Bind(R.id.id_lefticon)IconView m_obj_backIcon;
//    @Bind(R.id.id_header_title)TextView m_obj_title;
//    @Override
//    protected String getLoadUrl() {
//        return Constant.login_url;
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.login_fragment;
//    }
//
//    @Override
//    protected void initData() {
//        super.initData();
//    }
//
//    @Override
//    protected void initViews(View self, Bundle savedInstanceState) {
//        m_obj_backIcon.setVisibility(View.VISIBLE);
//        m_obj_title.setVisibility(View.VISIBLE);
//        m_obj_title.setText(getActivity().getResources().getString(R.string.localxhb));
//    }
//
//    @Override
//    protected void initListeners() {
//        super.initListeners();
//        m_obj_webviewUtil.setGetTokenLister(new WebViewUtil.IgetToken() {
//            @Override
//            public void setToken(String token) {
//                SharePreferenceUtil.getInstance(getActivity()).save(Constant.token,token);
//                Intent intent = new Intent();
//
//
////                Log.i("test","area = " + SharePreferenceUtil.getInstance(getActivity()).get(Constant.localData_area));
////                Log.i("test","area = " + SharePreferenceUtil.getInstance(getActivity()).get(Constant.localData_areaId));
//                //登录成功后判断有没有选择过区域  如果没有就进入区域选择的界面
//                if(SharePreferenceUtil.getInstance(getActivity()).get(Constant.localData_areaId).equals("") &&
//                        SharePreferenceUtil.getInstance(getActivity()).get(Constant.localData_area).equals("")){
//                    intent.setClass(getActivity(),ChoiceAreaActivity.class);
//                    intent.putExtra(getResources().getString(R.string.fromKey),getResources().getString(R.string.fromLogin));
//                }else{
//
//                    //跳转到区域版的界面
//                    intent.setClass(getActivity(),LocalXhbTabActivity.class);
//
//                }
//                startActivity(intent);
//                getActivity().finish();
//            }
//        });
//    }
//
//    @OnClick(R.id.id_lefticon)
//    public void back(){
//        getActivity().finish();
//    }
//}
