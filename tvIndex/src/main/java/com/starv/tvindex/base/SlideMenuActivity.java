//package com.starv.tvindex.base;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.FragmentActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.LinearLayout;
//
////import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
//import com.starv.tvindex.R;
//import com.starv.tvindex.TvindexApp;
//import com.starv.tvindex.model.activity.AboutActivity;
//import com.starv.tvindex.model.activity.ShowWebViewActivity;
//import com.starv.tvindex.model.local.module.view.activity.LoginPageActivity;
//import com.starv.tvindex.model.local.module.view.activity.MyWebActivity;
//import com.starv.tvindex.util.Constant;
//import com.starv.tvindex.util.SharePreferenceUtil;
//
//import com.starv.tvindex.util.view.IconView;
//import com.starv.tvindex.util.view.SlideMenuView;
//
///**
// * Created by weizhi on 2016/10/28.
// */
//public class SlideMenuActivity extends FragmentActivity implements View.OnClickListener{
//    @Override
//    public void onClick(View view) {
//
//    }
////    //    侧滑
////    protected SlidingMenu side_drawer = null;
////
////    //    slidemenu点击事件
////    private LinearLayout m_obj_slide_user;
////    private LinearLayout m_obj_slide_help;
////    private LinearLayout m_obj_slide_about;
////    private LinearLayout m_obj_slide_contact;
////
////
////    //    侧滑菜单的分享
////    private IconView m_obj_slide_WXCircle;
////    private IconView m_obj_slide_WX;
////    private IconView m_obj_slide_QQ;
////    private IconView m_obj_slide_QZone;
////    private IconView m_obj_slide_weibo;
////
////    @Override
////    protected void onCreate(@Nullable Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        if(null == side_drawer){
////            side_drawer = SlideMenuView.getInstance().SetActivity(this).initSlidingMenu();
////        }
////
////        m_obj_slide_user = (LinearLayout) side_drawer.findViewById(R.id.id_slide_user);
////        m_obj_slide_help = (LinearLayout) side_drawer.findViewById(R.id.id_slide_help);
////        m_obj_slide_about = (LinearLayout) side_drawer.findViewById(R.id.id_slide_about);
////        m_obj_slide_contact = (LinearLayout) side_drawer.findViewById(R.id.id_slide_contact);
////        m_obj_slide_user.setOnClickListener(this);
////        m_obj_slide_help.setOnClickListener(this);
////        m_obj_slide_about.setOnClickListener(this);
////        m_obj_slide_contact.setOnClickListener(this);
////
////
////        m_obj_slide_WXCircle = (IconView) side_drawer.findViewById(R.id.id_weixinCircle);
////        m_obj_slide_WX = (IconView) side_drawer.findViewById(R.id.id_weixin);
////        m_obj_slide_QQ = (IconView) side_drawer.findViewById(R.id.id_qq);
////        m_obj_slide_weibo = (IconView) side_drawer.findViewById(R.id.id_weibo);
////        m_obj_slide_WXCircle.setOnClickListener(this);
////        m_obj_slide_WX.setOnClickListener(this);
////        m_obj_slide_QQ.setOnClickListener(this);
////        m_obj_slide_weibo.setOnClickListener(this);
////    }
////
////    @Override
////    protected void onStop() {
////        super.onStop();
////    }
////
////    @Override
////    public void onClick(View v) {
////        int id = v.getId();
//////        Log.i("test","id = " + id);
////        Intent intent_slide = new Intent();
////        switch (id){
////            case R.id.id_slide_user:
////                //判断是否登录过
////
////                if (SharePreferenceUtil.getInstance(getApplicationContext()).get(Constant.token).equals("")){
////                    intent_slide.setClass(getApplicationContext(),LoginPageActivity.class);
////
////                }else{
////                    intent_slide.setClass(getApplicationContext(),MyWebActivity.class);
////                }
////                startActivity(intent_slide);
////                break;
////            case R.id.id_slide_help:
////                intent_slide.putExtra("loadurl", Constant.helpUrl);
////                intent_slide.putExtra("title",getResources().getString(R.string.help));
////
////                intent_slide.setClass(getApplicationContext(),ShowWebViewActivity.class);
////                startActivity(intent_slide);
////                break;
////            case R.id.id_slide_about:
////                intent_slide.setClass(getApplicationContext(),AboutActivity.class);
////                startActivity(intent_slide);
////                break;
////            case R.id.id_slide_contact:
////                break;
////            case R.id.id_weixinCircle:
//////                getShareData();//获取数据
////                ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.YES).
////                        setValues(getResources().getString(R.string.app_download),Constant.slidemenu_icon_url,Constant.slidemenu_local_jump_url,Constant.slidemenu_local_content_desc).WxCircleShare();
////
////                break;
////            case R.id.id_weixin:
//////                getShareData();//获取数据
//////                Log.i("test","weixinshare");
////                ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.YES).
////                        setValues(getResources().getString(R.string.app_download),Constant.slidemenu_icon_url,Constant.slidemenu_local_jump_url,Constant.slidemenu_local_content_desc).WxShare();
////                break;
////            case R.id.id_qq:
//////                getShareData();//获取数据
////                ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.YES).
////                        setValues(getResources().getString(R.string.app_download),Constant.slidemenu_icon_url,Constant.slidemenu_local_jump_url,Constant.slidemenu_local_content_desc).QQShare();
////
////                break;
////            case R.id.id_weibo:
//////                getShareData();//获取数据
////                ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.YES).
////                        setValues(getResources().getString(R.string.app_download),Constant.slidemenu_icon_url,Constant.slidemenu_local_jump_url,Constant.slidemenu_local_content_desc).WBShare();
////                break;
////        }
////    }
//}
