//package com.starv.tvindex.model.activity;
//
//import android.content.Intent;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.support.annotation.Nullable;
//import android.support.design.widget.NavigationView;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.widget.DrawerLayout;
//import android.text.Html;
//import android.text.method.LinkMovementMethod;
//import android.text.method.ScrollingMovementMethod;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.starv.tvindex.R;
//import com.starv.tvindex.TvindexApp;
//import com.starv.tvindex.base.CommonActivity;
//import com.starv.tvindex.bean.updateMsg;
//import com.starv.tvindex.model.fragment.LiveDataFragment;
//import com.starv.tvindex.model.fragment.RealtimeDataFragment;
//import com.starv.tvindex.model.fragment.SmartTVDataFragment;
//import com.starv.tvindex.model.local.Iinterface.IWatcher;
//import com.starv.tvindex.model.local.module.view.activity.ChoiceAreaActivity;
//import com.starv.tvindex.model.local.module.view.activity.LocalXhbTabActivity;
//import com.starv.tvindex.model.local.module.view.activity.LoginPageActivity;
//import com.starv.tvindex.model.local.module.view.activity.MyWebActivity;
//import com.starv.tvindex.util.Constant;
//import com.starv.tvindex.util.SharePreferenceUtil;
//import com.starv.tvindex.util.SetHeaderContent;
//import com.starv.tvindex.util.detectNetWork.netWorkState;
//import com.starv.tvindex.util.dialog.AlertDialog;
//import com.starv.tvindex.util.net.volleyUtil;
//import com.starv.tvindex.util.pullXmlUtil;
//import com.starv.tvindex.util.tips.GuideView;
//import com.starv.tvindex.util.view.IconView;
//import com.starv.tvindex.util.view.SlideMenuView;
//
//import org.w3c.dom.Text;
//
//import java.util.HashMap;
//
///**
// * Created by weizhi on 2016/6/13.
// */
//public class TabHostActivity extends CommonActivity implements IWatcher{
//    @Override
//    public void update() {
//
//    }
////    private LinearLayout m_obj_realData_ll;
////    private IconView m_obj_realData_fat;
////    private TextView m_obj_realData_tx;
////
////
////    private LinearLayout m_obj_liveData_ll;
////    private IconView m_obj_liveData_fat;
////    private TextView m_obj_liveData_tx;
////
////
////    private LinearLayout m_obj_smartTvData_ll;
////    private IconView m_obj_smartTvData_fat;
////    private TextView m_obj_smartTvData_tx;
////
////    private FragmentManager m_obi_fManager;
////    private FragmentTransaction m_obj_fTransaction;
////    private RealtimeDataFragment m_f_realData;
////    private LiveDataFragment m_f_liveData;
////    private SmartTVDataFragment m_f_smartTvData;
////
////
////    //直播是否是第一次点击
////    private boolean m_b_liveIsFirstClick = false;
////    //智能电视是否是第一次点击
////    private boolean m_b_smartTvIsFirstClick = false;
////
////    LayoutInflater layoutInflater = null;
////    private View m_obj_tabHostView = null;
////
////
////
////    //share分享的数据
////    private String m_str_shareIcon = null;//图标地址
////    private String m_str_shareUrl = null;//点击后跳转地址
////    private String m_str_shareContent = null;//描述内容
////
////    //判断当前是哪个tab
////    private ColorEnum m_i_tabIndex = ColorEnum.live;
////
////
////
////
//////    slidemenu点击事件
////    private LinearLayout m_obj_slide_user;
////    private LinearLayout m_obj_slide_help;
////    private LinearLayout m_obj_slide_about;
////    private LinearLayout m_obj_slide_contact;
////
//////    侧滑菜单的分享
////    private IconView m_obj_slide_WXCircle;
////    private IconView m_obj_slide_WX;
////    private IconView m_obj_slide_QQ;
////    private IconView m_obj_slide_QZone;
////    private IconView m_obj_slide_weibo;
////
////    @Override
////    public void update() {
////        changeFragment();
////    }
////
////    public enum ColorEnum {
////        real, live, smartTv;
////    }
////    @Override
////    protected void onCreate(@Nullable Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////
////
////        layoutInflater = LayoutInflater.from(this);
////        m_obj_tabHostView = layoutInflater.inflate(R.layout.tabhost, null);
////        setContentView(m_obj_tabHostView);
////        ShareSDK.initSDK(this);
////        //setContentView(R.layout.tabhost);
////
////        //更新apk
////
////        getUpdateApkMsg(Constant.updateUrl);
//////        getUpdateApkMsg("http://192.168.10.70:8080/weizhi/update.xml");
////        initView();
////
////        initTitle();
////        resetState();
////
////        m_obj_realData_fat.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
////        m_obj_realData_tx.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
////
////        m_f_realData = (RealtimeDataFragment)  Fragment.instantiate(this, RealtimeDataFragment.class.getName());
////        m_f_liveData = (LiveDataFragment)  Fragment.instantiate(this, LiveDataFragment.class.getName());
////        m_f_smartTvData = (SmartTVDataFragment)  Fragment.instantiate(this, SmartTVDataFragment.class.getName());
////
////        m_obi_fManager = getSupportFragmentManager();
////        m_obj_fTransaction = m_obi_fManager.beginTransaction();
////        m_obj_fTransaction.add(R.id.ly_content, m_f_realData);
////        m_obj_fTransaction.add(R.id.ly_content, m_f_liveData);
////        m_obj_fTransaction.add(R.id.ly_content, m_f_smartTvData);
////        hideFragments(m_obj_fTransaction);
////
////        m_obj_fTransaction.commitAllowingStateLoss();
////
////        setTabChoice(0);
////        if(!SharePreferenceUtil.getInstance(getApplicationContext()).get(Constant.first_flag).equals(Constant.first_no)){
////
////            //第一次登陆
////            initTips();
////        }
////        //initTips();
////
////        SharePreferenceUtil.getInstance(getApplicationContext()).save(Constant.first_flag, Constant.first_no);
////
////
////        TvindexApp.watched.addWatcher(this);
////
////    }
////
////    private void initView(){
////
////        m_obj_realData_ll = (LinearLayout) findViewById(R.id.id_realData_liner);
////        m_obj_realData_fat = (IconView) findViewById(R.id.id_realData);
////        m_obj_realData_tx = (TextView) this.findViewById(R.id.id_realData_tx);
////        m_obj_realData_ll.setOnClickListener(this);
////
////        m_obj_liveData_ll = (LinearLayout) findViewById(R.id.id_liveData_liner);
////        m_obj_liveData_fat = (IconView) findViewById(R.id.id_liveData);
////        m_obj_liveData_tx = (TextView) this.findViewById(R.id.id_liveData_tx);
////        m_obj_liveData_ll.setOnClickListener(this);
////
////        m_obj_smartTvData_ll = (LinearLayout) findViewById(R.id.id_smartTv_ll);
////        m_obj_smartTvData_fat = (IconView) findViewById(R.id.id_smartTv);
////        m_obj_smartTvData_tx = (TextView) this.findViewById(R.id.id_smartTv_tx);
////        m_obj_smartTvData_ll.setOnClickListener(this);
////
//////        m_obj_about = (Button) this.findViewById(R.id.id_menu_about);
//////        m_obj_about.setOnClickListener(this);
////
////        //m_obj_drawer = (DrawerLayout) this.findViewById(R.id.drawer_layout);
////
////
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
////        m_obj_slide_WXCircle = (IconView) side_drawer.findViewById(R.id.id_weixinCircle);
////        m_obj_slide_WX = (IconView) side_drawer.findViewById(R.id.id_weixin);
////        m_obj_slide_QQ = (IconView) side_drawer.findViewById(R.id.id_qq);
////        m_obj_slide_weibo = (IconView) side_drawer.findViewById(R.id.id_weibo);
////        m_obj_slide_WXCircle.setOnClickListener(this);
////        m_obj_slide_WX.setOnClickListener(this);
////        m_obj_slide_QQ.setOnClickListener(this);
////        m_obj_slide_weibo.setOnClickListener(this);
////
////
////
////
////
////
////
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
////
////    //private FontAwesomeText m_obj_rightIcon_share;
////    private void initTitle(){
////        //m_obj_rightIcon_share = (FontAwesomeText) this.findViewById(R.id.id_righticon_share);
////        final TabHostActivity fa = this;
////        SetHeaderContent.getInstance().init(m_obj_tabHostView);
////        //m_obj_rightIcon_share = (FontAwesomeText) this.findViewById(R.id.id_righticon_share);
////        //SetHeaderContent.getInstance().setTitle(R.string.app);
////        SetHeaderContent.getInstance().setRightIcon(getResources().getString(R.string.help_icon), new View.OnClickListener() {
////
////            @Override
////            public void onClick(View view) {
////
////                //fa.finish();
////                //Toast.makeText(getApplicationContext(),"help",Toast.LENGTH_SHORT).show();
//////                Intent intent = new Intent();
//////
//////                intent.putExtra("loadurl", Constant.helpUrl);
//////                intent.putExtra("title",getResources().getString(R.string.help));
//////
//////                intent.setClass(getApplicationContext(),ShowWebViewActivity.class);
//////                startActivity(intent);
////
//////                Intent intent1 = new Intent();
//////                intent1.setClass(getApplicationContext(),MyActivity.class);
//////                startActivity(intent1);
////
////               //m_obj_drawer.openDrawer(findViewById(R.id.id_left_layout));
////                if(side_drawer.isMenuShowing()){
////                    side_drawer.showContent();
////                }else{
////                    side_drawer.showMenu();
////                }
////
////            }
////        });
////        SetHeaderContent.getInstance().setLeftTitle();
////        SetHeaderContent.getInstance().setShareRightIcon(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                if(!netWorkState.isNetWorkAvailable(getApplicationContext())){
////                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.networkstate),Toast.LENGTH_SHORT).show();
////                }else {
////
////                    getShareData();//获取数据
//////                    if(null == m_str_shareIcon){
//////                        m_str_shareIcon = Constant.icon_url;
//////                    }
//////                    if(null == m_str_shareUrl){
//////                        m_str_shareUrl = Constant.jump_url;
//////                    }
//////                    if(null == m_str_shareContent){
//////                        m_str_shareContent = Constant.content_desc;
//////                    }
////
////                    ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO).setValues(getResources().getString(R.string.app_name),m_str_shareIcon,m_str_shareUrl,m_str_shareContent);
////
////                    ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO).showShare();
////                    //用完后清空
//////                    m_str_shareIcon = null;
//////                    m_str_shareUrl = null;
//////                    m_str_shareContent = null;
////                }
////
////            }
////        });
////
////    }
////
////    private void getShareData(){
////        if(ColorEnum.real == m_i_tabIndex){
////            m_str_shareIcon = m_f_realData.getIcon();
////            m_str_shareUrl = m_f_realData.getUrl();
////            m_str_shareContent = m_f_realData.getContent();
////            if(null == m_str_shareUrl){
////                m_str_shareUrl = Constant.realDataUrl;
////            }
////        }else if(ColorEnum.live == m_i_tabIndex){
////            m_str_shareIcon = m_f_liveData.getIcon();
////            m_str_shareUrl = m_f_liveData.getUrl();
////            m_str_shareContent = m_f_liveData.getContent();
////            if(null == m_str_shareUrl){
////                m_str_shareUrl = Constant.liveDataUrl;
////            }
////        }else if(ColorEnum.smartTv == m_i_tabIndex){
////            m_str_shareIcon = m_f_smartTvData.getIcon();
////            m_str_shareUrl = m_f_smartTvData.getUrl();
////            m_str_shareContent = m_f_smartTvData.getContent();
////            if(null == m_str_shareUrl){
////                m_str_shareUrl = Constant.smartDataUrl;
////            }
////        }
////
////        if(null == m_str_shareIcon){
////            m_str_shareIcon = Constant.icon_url;
////        }
//////        if(null == m_str_shareUrl){
//////            m_str_shareUrl = Constant.jump_url;
//////        }
////        if(null == m_str_shareContent){
////            m_str_shareContent = Constant.content_desc;
////        }
////    }
////
////    private void resetState(){
////        m_obj_realData_fat.setTextColor(getResources().getColor(R.color.nav_font_color));
////        m_obj_liveData_fat.setTextColor(getResources().getColor(R.color.nav_font_color));
////        m_obj_smartTvData_fat.setTextColor(getResources().getColor(R.color.nav_font_color));
////
////        m_obj_realData_tx.setTextColor(getResources().getColor(R.color.nav_font_color));
////        m_obj_liveData_tx.setTextColor(getResources().getColor(R.color.nav_font_color));
////        m_obj_smartTvData_tx.setTextColor(getResources().getColor(R.color.nav_font_color));
////    }
////
////    //隐藏所有的Fragment,避免fragment混乱
////    private void hideFragments(FragmentTransaction transaction) {
////        if (m_f_realData != null) {
////            transaction.hide(m_f_realData);
////        }
////        if (m_f_liveData != null) {
////            transaction.hide(m_f_liveData);
////        }
////        if (m_f_smartTvData != null) {
////            transaction.hide(m_f_smartTvData);
////        }
////    }
////
////    private void setTabChoice(int index){
////
////        FragmentTransaction fTransaction1 = getSupportFragmentManager().beginTransaction();
////        switch (index){
////            case 0:
////                m_i_tabIndex = ColorEnum.real;
////                hideFragments(fTransaction1);
////                fTransaction1.show(m_f_realData);
////                break;
////            case 1:
////                m_i_tabIndex = ColorEnum.live;
////                hideFragments(fTransaction1);
////                fTransaction1.show(m_f_liveData);
////
////                if(!m_b_liveIsFirstClick){
////                    m_f_liveData.loadPage();
////                }
////                m_b_liveIsFirstClick = true;
////                break;
////            case 2:
////                m_i_tabIndex = ColorEnum.smartTv;
////                if(!m_b_smartTvIsFirstClick){
////                    m_f_smartTvData.loadPage();
////                }
////                m_b_smartTvIsFirstClick = true;
////                hideFragments(fTransaction1);
////                fTransaction1.show(m_f_smartTvData);
////                break;
////            default:break;
////        }
////
////        fTransaction1.commitAllowingStateLoss();
////    }
////
////
////
////
////    @Override
////    public void onClick(View v) {
////        super.onClick(v);
////
////        Intent intent_slide = new Intent();
////        switch (v.getId()) {
////            case R.id.id_realData_liner:
////                resetState();
////                m_obj_realData_fat.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
////                m_obj_realData_tx.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
////                setTabChoice(0);
////                break;
////            case R.id.id_liveData_liner:
////                resetState();
////                m_obj_liveData_fat.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
////                m_obj_liveData_tx.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
////                setTabChoice(1);
////                break;
////            case R.id.id_smartTv_ll:
//////                resetState();
//////                m_obj_smartTvData_fat.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
//////                m_obj_smartTvData_tx.setTextColor(getResources().getColor(R.color.nav_font_color_selected));
//// //               setTabChoice(2);
//////                Intent intent = new Intent();
//////                intent.setClass(TabHostActivity.this,LocalXhbTabActivity.class);
//////                startActivity(intent);
////
////                if(("").equals(SharePreferenceUtil.getInstance(getApplicationContext()).get(Constant.token))){
////
////                    Intent intent = new Intent();
////                    intent.setClass(TabHostActivity.this,LoginPageActivity.class);
////                    startActivity(intent);
////
////                }else{
////
////                    Intent intent1 = new Intent();
////                    //登录过后判断是否进行过区域选择
////                    if(("").equals(SharePreferenceUtil.getInstance(getApplicationContext()).get(Constant.localData_areaId)) &&
////                            ("").equals(SharePreferenceUtil.getInstance(getApplicationContext()).get(Constant.localData_area))){
////                        intent1.setClass(getApplicationContext(), ChoiceAreaActivity.class);
////                        intent1.putExtra(getResources().getString(R.string.fromKey),getResources().getString(R.string.fromTabHost));
////                    }else{
////                        intent1.setClass(getApplicationContext(),LocalXhbTabActivity.class);
////                    }
////                    startActivity(intent1);
////                }
////
////                break;
//////            case R.id.id_menu_about:
//////                Toast.makeText(getApplicationContext(),"about...",Toast.LENGTH_SHORT).show();
//////                break;
////
////        }
////    }
////
////
////    /**
////     * 切换fragment
////     * */
////    public void changeFragment(){
////        if(ColorEnum.real == m_i_tabIndex){
////            setTabChoice(0);
////        }else if(ColorEnum.live == m_i_tabIndex){
////            setTabChoice(1);
////        }
////    }
////
////    //新手指引
////    /****************************************************************************/
////
////    private GuideView m_obj_guideView_one;
////    private GuideView m_obj_guideView_two;
////    private GuideView m_obj_guideView_three;
////
////    private GuideView m_obj_ShareView;
////    private void initTips(){
////
////        // 使用图片
////        final ImageView iv = new ImageView(this);
////        iv.setImageResource(R.mipmap.img_new_task_guide);
////        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
////        iv.setLayoutParams(params);
////
////        // 使用文字
//////        TextView tv = new TextView(this);
//////        tv.setText("欢迎使用");
//////        tv.setTextColor(getResources().getColor(R.color.white));
//////        tv.setTextSize(30);
//////        tv.setGravity(Gravity.CENTER);
//////
//////        // 使用文字
//////        final TextView tv2 = new TextView(this);
//////        tv2.setText("欢迎使用2");
//////        tv2.setTextColor(getResources().getColor(R.color.white));
//////        tv2.setTextSize(30);
//////        tv2.setGravity(Gravity.CENTER);
//////
//////        View view = layoutInflater.inflate(R.layout.share_tips, null);
//////        TextView textView = (TextView) view.findViewById(R.id.id_sharetips_textview);
//////        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/huakangwawati.ttf");
//////        textView.setTypeface(tf);
////
////        m_obj_ShareView = GuideView.Builder
////                .newInstance(this)
////                .setTargetView(m_obj_smartTvData_fat)//设置目标
////                .setCustomGuideView(iv)
////                .setDirction(GuideView.Direction.LEFT_TOP)
////                .setShape(GuideView.MyShape.CIRCULAR)   // 设置圆形显示区域，
////                .setBgColor(getResources().getColor(R.color.shadow))
////                .setOnclickListener(new GuideView.OnClickCallback() {
////                    @Override
////                    public void onClickedGuideView() {
////                        m_obj_ShareView.hide();
////                    }
////                })
////                .build();
////        m_obj_ShareView.show();
////
//////        m_obj_guideView_one = GuideView.Builder
//////                .newInstance(this)
//////                .setTargetView(m_obj_realData_fat)//设置目标
//////                .setCustomGuideView(iv)
//////                .setDirction(GuideView.Direction.LEFT_TOP)
//////                .setShape(GuideView.MyShape.CIRCULAR)   // 设置圆形显示区域，
//////                .setBgColor(getResources().getColor(R.color.shadow))
//////                .setOnclickListener(new GuideView.OnClickCallback() {
//////                    @Override
//////                    public void onClickedGuideView() {
//////                        m_obj_guideView_one.hide();
//////                        m_obj_guideView_two.show();
//////                    }
//////                })
//////                .build();
//////
//////
//////        m_obj_guideView_two = GuideView.Builder
//////                .newInstance(this)
//////                .setTargetView(m_obj_liveData_fat)
//////                .setCustomGuideView(iv)
//////                .setDirction(GuideView.Direction.LEFT_TOP)
//////                .setShape(GuideView.MyShape.CIRCULAR)   // 设置椭圆形显示区域，
//////                .setBgColor(getResources().getColor(R.color.shadow))
//////                .setOnclickListener(new GuideView.OnClickCallback() {
//////                    @Override
//////                    public void onClickedGuideView() {
//////                        m_obj_guideView_two.hide();
//////                        m_obj_guideView_three.show();
//////                    }
//////                })
//////                .build();
//////
//////
//////        m_obj_guideView_three = GuideView.Builder
//////                .newInstance(this)
//////                .setTargetView(m_obj_smartTvData_fat)
//////                .setCustomGuideView(iv)
//////                .setDirction(GuideView.Direction.LEFT_TOP)
//////                .setShape(GuideView.MyShape.CIRCULAR)   // 设置矩形显示区域，
////////                .setRadius(80)          // 设置圆形或矩形透明区域半径，默认是targetView的显示矩形的半径，如果是矩形，这里是设置矩形圆角大小
//////                .setBgColor(getResources().getColor(R.color.shadow))
//////                .setOnclickListener(new GuideView.OnClickCallback() {
//////                    @Override
//////                    public void onClickedGuideView() {
//////                        m_obj_guideView_three.hide();
//////                    }
//////                })
//////                .build();
//////
//////        m_obj_guideView_one.show();
////    }
////    private void showTips(){
////    }
////    /******************************************************************************/
////    //    检查更新
////    /****************************/
////    //获取是否需要更新apk的服务器数据
////    private void getUpdateApkMsg(String url){
////        volleyUtil.getInstance().init(TabHostActivity.this, url, volleyUtil.EMethod.EMethod_Get, null, new volleyUtil.IResponse() {
////
////            @Override
////            public void success(String json) {
////                Log.i("choiceaccouttwo", "update xml = " + json);
////
////                jugeUpdateApk(json);
////            }
////
////            @Override
////            public void error(String error) {
////
////                //Toast.makeText(HomeActivity.this,"当前为最新版本"+getVersion(),Toast.LENGTH_SHORT).show();
////            }
////        }).volley_str_update();
////    }
////
////    //更新apk
////    private void updateApk(final String url,String dec) {
////        //dec = "检测到新的版本!!!";
////        new AlertDialog(TabHostActivity.this).builder().setTitle("版本更新")
////                .setMsg(dec, Gravity.LEFT,false)
////                .setPositiveButton("确认", new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        //启动下载apk的服务
////                        //Toast.makeText(HomeActivity.this, "正在下载...", Toast.LENGTH_SHORT).show();
//////                        Intent downloadIntent = new Intent(HomeActivity.this, downloadService.class);
//////                        downloadIntent.putExtra("update_url", url);
//////                        HomeActivity.this.startService(downloadIntent);
////                        //调用系统下载apk
//////                        if(!SharePreferenceUtil.getInstance(getApplicationContext()).get(Constant.token).equals("")){
//////                            SharePreferenceUtil.getInstance(getApplicationContext()).save(Constant.token,"");
//////                        }
////                        Uri uri = Uri.parse(url);
////                        Intent downloadIntent = new Intent(Intent.ACTION_VIEW, uri);
////                        startActivity(downloadIntent);
////                    }
////                }).setNegativeButton("取消", new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////            }
////        }).show();
////    }
////
////    //判断是否需要更新
////    updateMsg m_obj_updateMsg;
////    private void jugeUpdateApk(String content){
////        //读取xml文件读取版本号  是否需要更新
////        pullXmlUtil pxu = new pullXmlUtil(TabHostActivity.this);
////        try {
////            m_obj_updateMsg = (updateMsg) pxu.readXML(content);
////            if(getVersion().compareTo(m_obj_updateMsg.getVersion()) != 0){
////                //版本升级
////
////                updateApk(m_obj_updateMsg.getUrl(),m_obj_updateMsg.getDescription());
////            }
////            else if(getVersion().compareTo(m_obj_updateMsg.getVersion()) == 0){
////                //Toast.makeText(HomeActivity.this,"当前为最新版本"+getVersion(),Toast.LENGTH_SHORT).show();
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////    }
////    private String getVersion(){
////        String versionName = "";
////        int versioncode = 0;
////        try {
////            // ---get the package info---
////            PackageManager pm = TabHostActivity.this.getPackageManager();
////            PackageInfo pi = pm.getPackageInfo(TabHostActivity.this.getPackageName(), 0);
////            versionName = pi.versionName;
////            versioncode = pi.versionCode;
//////            if (versionName == null || versionName.length() <= 0) {
//////                return "";
//////            }
////        } catch (Exception e) {
////            Log.e("VersionInfo", "Exception", e);
////        }
////        Log.i("choiceaccouttwo","versionName = " + versionName);
////        return versionName;
////    }
////    /**********************************************/
////
////
////    /**
////     * 获取sharesdk分享的数据 从各个Fragment回调
////     * */
////    public void getShareData(String icon,String url,String content){
////
//////        Log.i("test","iconurl = " + icon);
////        m_str_shareIcon = icon;
////        m_str_shareUrl = url;
////        m_str_shareContent = content;
////    }
////
////    @Override
////    protected void onDestroy() {
////        super.onDestroy();
////        ShareSDK.stopSDK();
////    }
//}
