//package com.starv.tvindex.model.local.module.view.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.util.Log;
//import android.view.Display;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.starv.tvindex.R;
//import com.starv.tvindex.TvindexApp;
//import com.starv.tvindex.base.SlideMenuActivity;
//import com.starv.tvindex.model.activity.MyActivity;
//import com.starv.tvindex.model.local.module.view.fragment.LocalDynamicViewFragment;
//import com.starv.tvindex.model.local.module.view.fragment.LocalMychannelFragment;
//import com.starv.tvindex.model.local.module.view.fragment.LocalMyprogramFragment;
//import com.starv.tvindex.util.Constant;
//import com.starv.tvindex.util.SetCommonHeader;
//import com.starv.tvindex.util.SharePreferenceUtil;
//
//import com.starv.tvindex.util.detectNetWork.netWorkState;
//import com.starv.tvindex.util.view.IconView;
//
///**
// * Created by weizhi on 2016/8/15.
// * 地方版星红榜的页面
// */
//public class LocalXhbTabActivity extends SlideMenuActivity{
//
////    //收视动态页面
////    private LocalDynamicViewFragment m_obj_dynamicview_fragment;
////    //我的频道页面
////    private LocalMychannelFragment m_obj_mychannel_fragment;
////    //我的节目页面
////    private LocalMyprogramFragment m_obj_myprogram_fragment;
////
////
////
////    //收视动态的liner
////    private LinearLayout m_obj_dynamicview_liner;
////    //我的频道的liner
////    private LinearLayout m_obj_mychannel_liner;
////    //我的节目的liner
////    private LinearLayout m_obj_myprogram_liner;
////    //返回总榜
////    private LinearLayout m_obj_id_back_liner;
////
////
////    private FragmentManager m_obi_fManager;
////    private FragmentTransaction m_obj_fTransaction;
////
////    LayoutInflater layoutInflater = null;
////    private View m_obj_local_tabHostView = null;
////
////
////    private IconView m_obj_dynamicview_fat;
////    private TextView m_obj_dynamicview_tx;
////
////
////    private IconView m_obj_mychannel_fat;
////    private TextView m_obj_mychannel_tx;
////
////
////    private IconView m_obj_myprogram_fat;
////    private TextView m_obj_myprogram_tx;
////
////
////
////    private static int m_i_tabindex;
////
////    public enum LocalColorEnum {
////        dyNamicView, myChannel, myProgram;
////    }
////    //判断当前是哪个tab
////    private LocalColorEnum m_enmu_tabIndex = LocalColorEnum.dyNamicView;
////
////    //share分享的数据
////    private String m_str_shareIcon = null;//图标地址
////    private String m_str_shareUrl = null;//点击后跳转地址
////    private String m_str_shareContent = null;//描述内容
////
////    private void getShareData(){
////        if(LocalColorEnum.dyNamicView == m_enmu_tabIndex){
////            m_str_shareIcon = m_obj_dynamicview_fragment.getIcon();
////            m_str_shareUrl = m_obj_dynamicview_fragment.getUrl();
////            m_str_shareContent = m_obj_dynamicview_fragment.getContent();
////        }else if(LocalColorEnum.myChannel == m_enmu_tabIndex){
////            m_str_shareIcon = m_obj_mychannel_fragment.getIcon();
////            m_str_shareUrl = m_obj_mychannel_fragment.getUrl();
////            m_str_shareContent = m_obj_mychannel_fragment.getContent();
////        }else if(LocalColorEnum.myProgram == m_enmu_tabIndex){
////            m_str_shareIcon = m_obj_myprogram_fragment.getIcon();
////            m_str_shareUrl = m_obj_myprogram_fragment.getUrl();
////            m_str_shareContent = m_obj_myprogram_fragment.getContent();
////        }
////
////        if(null == m_str_shareIcon){
////            m_str_shareIcon = Constant.local_icon_url;
////        }
////        if(null == m_str_shareUrl){
////            m_str_shareUrl = Constant.local_jump_url;
////        }
////        if(null == m_str_shareContent){
////            m_str_shareContent = Constant.local_content_desc;
////        }
////    }
////
////    @Override
////    protected void onCreate(@Nullable Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////
////        layoutInflater = LayoutInflater.from(this);
////        m_obj_local_tabHostView = layoutInflater.inflate(R.layout.localxhb_tabhost, null);
////        setContentView(m_obj_local_tabHostView);
////
////        m_obi_fManager = getSupportFragmentManager();
////
////        initview();
////        initTitle();
////        initFragmets();
////    }
////
////    /**
////     * 初始化标题栏
////     * */
////    private void initTitle() {
////        SetCommonHeader.getInstance().init(m_obj_local_tabHostView);
//////        SetCommonHeader.getInstance().setLeftFirstIcon(Constant.back_icon, new View.OnClickListener(){
//////            @Override
//////            public void onClick(View v) {
//////                LocalXhbTabActivity.this.finish();
//////            }
//////        });
////
////        SetCommonHeader.getInstance().setRightFirstIcon(getResources().getString(R.string.share_icon), new View.OnClickListener(){
////
////            @Override
////            public void onClick(View v) {
////                if(!netWorkState.isNetWorkAvailable(getApplicationContext())){
////                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.networkstate),Toast.LENGTH_SHORT).show();
////                }else {
////                    getShareData();
////
////                    ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO).setValues(getResources().getString(R.string.app_name),m_str_shareIcon,m_str_shareUrl,m_str_shareContent);
////
////                    ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO).showShare();
////
////
////                }
////            }
////        });
////        SetCommonHeader.getInstance().setRightSecondIcon(getResources().getString(R.string.help_icon), new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////
////
////                if(side_drawer.isMenuShowing()){
////                    side_drawer.showContent();
////                }else{
////                    side_drawer.showMenu();
////                }
////            }
////        });
////        SetCommonHeader.getInstance().setLeftFirstImg(R.mipmap.header_local_logo);
////
////        SetCommonHeader.getInstance().setAreaTextAndClick(SharePreferenceUtil.getInstance(getApplicationContext()).get(Constant.localData_area), new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent();
////                intent.setClass(getApplicationContext(),ChoiceAreaActivity.class);
////
////                intent.putExtra(getResources().getString(R.string.fromKey),getResources().getString(R.string.fromLocalTabhost));
//////                startActivity(intent);
////                startActivityForResult(intent,Constant.LocalTabActivity_toChoiceActivity);
////            }
////        });
////
//////        SetCommonHeader.getInstance().setTitle(getResources().getString(R.string.localxhb));
////
////    }
////
////
////    @Override
////    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////        SetCommonHeader.getInstance().setAreaText(SharePreferenceUtil.getInstance(getApplicationContext()).get(Constant.localData_area));
////
////    }
////
////    /**
////     * 初始化view
////     * */
////    private void initview() {
////        m_obj_dynamicview_liner = (LinearLayout) this.findViewById(R.id.id_dynamicview_liner);
////        m_obj_mychannel_liner = (LinearLayout) this.findViewById(R.id.id_mychannel_liner);
////        m_obj_myprogram_liner = (LinearLayout) this.findViewById(R.id.id_myprogram_liner);
////
////        m_obj_dynamicview_fat = (IconView) this.findViewById(R.id.id_dynamicview_liner_fat);
////        m_obj_mychannel_fat = (IconView) this.findViewById(R.id.id_mychannel_liner_fat);
////        m_obj_myprogram_fat = (IconView) this.findViewById(R.id.id_myprogram_liner_fat);
////
////        m_obj_dynamicview_tx = (TextView) this.findViewById(R.id.id_dynamicview_liner_tx);
////        m_obj_mychannel_tx = (TextView) this.findViewById(R.id.id_mychannel_liner_tx);
////        m_obj_myprogram_tx = (TextView) this.findViewById(R.id.id_myprogram_liner_tx);
////
////
////        m_obj_dynamicview_liner.setOnClickListener(this);
////        m_obj_mychannel_liner.setOnClickListener(this);
////        m_obj_myprogram_liner.setOnClickListener(this);
////
////        m_obj_id_back_liner = (LinearLayout) this.findViewById(R.id.id_back);
////        m_obj_id_back_liner.setOnClickListener(this);
////
////
////
////    }
////
////    /**
////     * 初始化Fragment
////     * */
////    private void initFragmets(){
////        m_obj_dynamicview_fragment = new LocalDynamicViewFragment();
////        m_obj_mychannel_fragment = new LocalMychannelFragment();
////        m_obj_myprogram_fragment = new LocalMyprogramFragment();
////
////        m_obj_fTransaction = m_obi_fManager.beginTransaction();
////        m_obj_fTransaction.add(R.id.local_ly_content,m_obj_dynamicview_fragment);
////        m_obj_fTransaction.add(R.id.local_ly_content,m_obj_mychannel_fragment);
////        m_obj_fTransaction.add(R.id.local_ly_content,m_obj_myprogram_fragment);
////        hideFragments(m_obj_fTransaction);
////        m_obj_fTransaction.commit();
////        setTabChoice(0);
////
////        m_obj_dynamicview_fat.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
////        m_obj_dynamicview_tx.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
////
////
////    }
////
////    //隐藏所有的Fragment,避免fragment混乱
////    private void hideFragments(FragmentTransaction transaction) {
////        if (m_obj_dynamicview_fragment != null) {
////            transaction.hide(m_obj_dynamicview_fragment);
////        }
////        if (m_obj_mychannel_fragment != null) {
////            transaction.hide(m_obj_mychannel_fragment);
////        }
////        if (m_obj_myprogram_fragment != null) {
////            transaction.hide(m_obj_myprogram_fragment);
////        }
////    }
////
////
////    private void setTabChoice(int index){
////        m_i_tabindex = index;
////        FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
////        switch (index){
////            case 0:
////                hideFragments(fTransaction);
////                fTransaction.show(m_obj_dynamicview_fragment);
////
////
////                break;
////            case 1:
////                hideFragments(fTransaction);
////                fTransaction.show(m_obj_mychannel_fragment);
////
////
////                break;
////            case 2:
////                hideFragments(fTransaction);
////                fTransaction.show(m_obj_myprogram_fragment);
////
////
////                break;
////            default:break;
////        }
////
////        fTransaction.commit();
////    }
////    private void resetState(){
////        m_obj_dynamicview_fat.setTextColor(getResources().getColor(R.color.nav_font_color));
////        m_obj_mychannel_fat.setTextColor(getResources().getColor(R.color.nav_font_color));
////        m_obj_myprogram_fat.setTextColor(getResources().getColor(R.color.nav_font_color));
////
////        m_obj_dynamicview_tx.setTextColor(getResources().getColor(R.color.nav_font_color));
////        m_obj_mychannel_tx.setTextColor(getResources().getColor(R.color.nav_font_color));
////        m_obj_myprogram_tx.setTextColor(getResources().getColor(R.color.nav_font_color));
////    }
////
////    @Override
////    public void onClick(View v) {
////
////        super.onClick(v);
////        int id = v.getId();
////        switch (id){
////            case R.id.id_dynamicview_liner:
////                setTabChoice(0);
////
////                m_enmu_tabIndex = LocalColorEnum.dyNamicView;
////                resetState();
////                m_obj_dynamicview_fat.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
////                m_obj_dynamicview_tx.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
////
////                break;
////            case R.id.id_mychannel_liner:
////
////                setTabChoice(1);
////
////                m_enmu_tabIndex = LocalColorEnum.myChannel;
////
////                resetState();
////                m_obj_mychannel_fat.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
////                m_obj_mychannel_tx.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
////                break;
////            case R.id.id_myprogram_liner:
////
////                setTabChoice(2);
////
////                m_enmu_tabIndex = LocalColorEnum.myProgram;
////
////                resetState();
////                m_obj_myprogram_fat.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
////                m_obj_myprogram_tx.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
////                break;
////            case R.id.id_back:
////                LocalXhbTabActivity.this.finish();
////                break;
////            default:break;
////        }
////    }
////
////
////
////    @Override
////    protected void onResume() {
////        super.onResume();
////
//////        SetCommonHeader.getInstance().setAreaText(SharePreferenceUtil.getInstance(getApplicationContext()).get(Constant.localData_area));
////    }
////
////    /**
////     * 判断是否打开侧滑菜单了
////     * */
////    @Override
////    public void onBackPressed() {
////        if(side_drawer.isMenuShowing()){
////            side_drawer.showContent();
////        }else
////            super.onBackPressed();
////    }
//
//}
