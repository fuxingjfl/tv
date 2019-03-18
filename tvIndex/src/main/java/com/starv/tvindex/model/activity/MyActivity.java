//package com.starv.tvindex.model.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.FragmentActivity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.RelativeLayout;
//
//import com.starv.tvindex.R;
//import com.starv.tvindex.model.local.module.view.activity.MyWebActivity;
//import com.starv.tvindex.util.Constant;
//import com.starv.tvindex.util.SetHeaderContent;
//import com.starv.tvindex.util.SharePreferenceUtil;
//
///**
// * Created by weizhi on 2016/6/16.
// */
//public class MyActivity extends FragmentActivity implements View.OnClickListener{
//
//    private RelativeLayout m_obj_help_rl;
//    private RelativeLayout m_obj_about_rl;
//    private RelativeLayout m_obj_my_rl;
//
//    private LayoutInflater layoutInflater;
//    private View m_obj_view;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        m_obj_view = layoutInflater.from(getApplicationContext()).inflate(R.layout.my,null);
//        setContentView(m_obj_view);
//        //setContentView(R.layout.my);
//
////        m_obj_help_rl = (RelativeLayout) this.findViewById(R.id.id_help_ll);
////        m_obj_about_rl = (RelativeLayout) this.findViewById(R.id.id_about_ll);
////        m_obj_my_rl = (RelativeLayout) this.findViewById(R.id.id_my_ll);
////
////        m_obj_help_rl.setOnClickListener(this);
////        m_obj_about_rl.setOnClickListener(this);
////        m_obj_my_rl.setOnClickListener(this);
//
//
////        if(!("").equals(SharePreferenceUtil.getInstance(getApplicationContext()).get(Constant.token))){
////            m_obj_my_rl.setVisibility(View.VISIBLE);
////        }
////        final MyActivity aa = this;
////        SetHeaderContent.getInstance().init(m_obj_view);
////        SetHeaderContent.getInstance().setTitle(getResources().getString(R.string.about));
////        SetHeaderContent.getInstance().setLeftIcon(getResources().getString(R.string.back_icon), new View.OnClickListener() {
////
////
////            @Override
////            public void onClick(View v) {
////                aa.finish();
////            }
////        });
//    }
//
//    @Override
//    public void onClick(View v) {
////        int id= v.getId();
////        Intent intent = new Intent();
////        switch (id){
////            case R.id.id_help_ll:
////
////                intent.putExtra("loadurl", Constant.helpUrl);
////                intent.putExtra("title",getResources().getString(R.string.help));
////
////                intent.setClass(getApplicationContext(),ShowWebViewActivity.class);
////                break;
////            case R.id.id_about_ll:
////
////                intent.setClass(getApplicationContext(),AboutActivity.class);
////                break;
////            case R.id.id_my_ll:
////
////                intent.setClass(getApplicationContext(),MyWebActivity.class);
////                break;
////            default:break;
////        }
////
////        startActivity(intent);
//    }
//
//
//
//}
