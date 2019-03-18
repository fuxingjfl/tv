package com.starv.tvindex.activity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.starv.tvindex.Presenter.NightProgramPresenter;
import com.starv.tvindex.Presenter.NightProgramView;
import com.starv.tvindex.Presenter.VarietyDetailsPresenter;
import com.starv.tvindex.Presenter.VarietyDetailsView;
import com.starv.tvindex.R;
import com.starv.tvindex.adapter.VarietyDetailsAdapter;
import com.starv.tvindex.base.BaseActivity;
import com.starv.tvindex.entity.Info;
import com.starv.tvindex.entity.RealTimeData;
import com.starv.tvindex.okhttpUtils.HttpConstants;
import com.starv.tvindex.util.Constant;
import com.starv.tvindex.util.DensityUtil;
import com.starv.tvindex.util.NetUtil;
import com.starv.tvindex.util.ShareSdkUtil;
import com.starv.tvindex.util.dialog.ShareDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ysq on 2019/2/25.
 */
public class VarietyDetailsActivity extends BaseActivity implements VarietyDetailsView, NightProgramView {


    private RecyclerView relycle;

    private VarietyDetailsAdapter adapter;
    private List<RealTimeData> mlist;
    private RadioGroup rg_zg;
    private RadioButton rb_btn1, rb_btn2, rb_btn3, rb_btn4;
    private View view1, view2, view3, view4;
    private LinearLayout ll_cn, ll_xia_line;
    private String dt;
    private String program_type;
    private TextView tv_nian, tv_yue, tv_ri, tv_yw;
    private VarietyDetailsPresenter vd;
    private TextView tv_tmwben;
    private String tiaomu;
    private TextView tv_name;
    private int i = 1;
    private List<RealTimeData> mys;//元数据
    private ImageView iv_left_btn, iv_right_btn;
    private NightProgramPresenter nightProgramPresenter;
    private ImageView iv_qie;
    private RelativeLayout rl_bg;
    private LinearLayout ll_tp;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private int width;
    private int more;
    int topMargin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variety_details);
        relycle = (RecyclerView) findViewById(R.id.recycle);
        tv_tmwben = (TextView) findViewById(R.id.tv_tmwben);
        tv_nian = (TextView) findViewById(R.id.tv_nian);
        ll_xia_line = (LinearLayout) findViewById(R.id.ll_xia_line);
        tv_ri = (TextView) findViewById(R.id.tv_ri);
        iv_right_btn = (ImageView) findViewById(R.id.iv_right_btn);
        iv_left_btn = (ImageView) findViewById(R.id.iv_left_btn);
        tv_name = (TextView) findViewById(R.id.tv_name);
        iv_qie = (ImageView) findViewById(R.id.iv_qie);
        tv_yw = (TextView) findViewById(R.id.tv_yw);
        tv_yue = (TextView) findViewById(R.id.tv_yue);
        rg_zg = (RadioGroup) findViewById(R.id.rg_zg);
        rb_btn1 = (RadioButton) findViewById(R.id.rb_btn1);
        rb_btn2 = (RadioButton) findViewById(R.id.rb_btn2);
        rb_btn3 = (RadioButton) findViewById(R.id.rb_btn3);
        rb_btn4 = (RadioButton) findViewById(R.id.rb_btn4);
        ll_cn = (LinearLayout) findViewById(R.id.ll_cn);
        rl_bg=findViewById(R.id.rl_bg);
        ll_tp=findViewById(R.id.ll_tp);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);
        setParams(rb_btn1, (float) 1);
        setParams(rb_btn2, (float) 1.5);
        setParams(rb_btn3, (float) 1);
        setParams(rb_btn4, (float) 1);
        rb_btn1.setBackgroundResource(R.color.rb_bg_wxz);
        rb_btn2.setBackgroundResource(R.color.rb_bg_xz);
        rb_btn3.setBackgroundResource(R.color.rb_bg_wxz);
        rb_btn4.setBackgroundResource(R.color.rb_bg_wxz);

        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;         // 屏幕宽度（像素）
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll_tp.getLayoutParams();
        topMargin=layoutParams.topMargin;
        Log.e("TAG","topMargin=="+topMargin);

        if (width<=720){
            Drawable drawable = getResources().getDrawable(R.mipmap.xxpx);
            drawable.setBounds(0,0,DensityUtil.px2dip(VarietyDetailsActivity.this,30f),DensityUtil.px2dip(VarietyDetailsActivity.this,30f));
            rb_btn3.setCompoundDrawables(null,null,drawable,null);
            rb_btn3.setPadding(DensityUtil.px2dip(VarietyDetailsActivity.this,70f),0,DensityUtil.px2dip(VarietyDetailsActivity.this,70f),0);
        }else{
            Drawable drawable = getResources().getDrawable(R.mipmap.xxpx);
            drawable.setBounds(0,0,DensityUtil.px2dip(VarietyDetailsActivity.this,80f),DensityUtil.px2dip(VarietyDetailsActivity.this,80f));
            rb_btn3.setCompoundDrawables(null,null,drawable,null);
            rb_btn3.setPadding(DensityUtil.px2dip(VarietyDetailsActivity.this,100f),0,DensityUtil.px2dip(VarietyDetailsActivity.this,100f),0);
        }
        vd = new VarietyDetailsPresenter(VarietyDetailsActivity.this);
        program_type = getIntent().getStringExtra("time_range");
        tiaomu = getIntent().getStringExtra("tiaomu");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);//往上推一天  30推三十天  365推一年
        Date mBefore = calendar.getTime();
        String format = sdf.format(mBefore);

        String[] split1 = format.split(" ");
        String[] split2 = split1[0].split("-");
        dt = split2[0] + split2[1] + split2[2];
        tv_name.setText(tiaomu);
        mlist = new ArrayList<>();
        mys = new ArrayList<>();
        relycle.setLayoutManager(new LinearLayoutManager(VarietyDetailsActivity.this));

        if ("卫视晚间档综艺排行榜单".equals(tiaomu)) {
            rb_btn1.setText("节目排名");
            rb_btn2.setText("节目名称");
            nightProgramPresenter = new NightProgramPresenter(VarietyDetailsActivity.this);
            adapter = new VarietyDetailsAdapter(R.layout.item_pd, mlist, 0);
            iv_qie.setImageResource(R.mipmap.zy_bg);
            getNightProgramData();

        } else if ("24小时全天历史数据排行".equals(tiaomu)) {
            rb_btn1.setText("频道排名");
            rb_btn2.setText("频道名称");
            adapter = new VarietyDetailsAdapter(R.layout.item_pd, mlist, 1);
            iv_qie.setImageResource(R.mipmap.ershisi);

//        relycle.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
            getData();
        } else if ("卫视黄金档电视剧排行榜单".equals(tiaomu)) {
            rb_btn1.setText("节目排名");
            rb_btn2.setText("节目名称");
            nightProgramPresenter = new NightProgramPresenter(VarietyDetailsActivity.this);
            adapter = new VarietyDetailsAdapter(R.layout.item_pd, mlist, 2);
            iv_qie.setImageResource(R.mipmap.hjd);
            iv_qie.setPadding(DensityUtil.px2dip(VarietyDetailsActivity.this,30f),DensityUtil.px2dip(VarietyDetailsActivity.this,20f),DensityUtil.px2dip(VarietyDetailsActivity.this,10f),DensityUtil.px2dip(VarietyDetailsActivity.this,20f));

            getNightProgramData();
        }

        relycle.setAdapter(adapter);
        relycle.addOnScrollListener(onScrollListener);
        rg_zg.setOnCheckedChangeListener(lisener);
        rb_btn3.setOnClickListener(onclicke);
        rb_btn2.setOnClickListener(onclicke);
        iv_left_btn.setOnClickListener(onclicke);
        iv_right_btn.setOnClickListener(onclicke);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    private int mTopMargin,mBottomMargin,jl;
    private boolean isxh,istou;
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (RecyclerView.SCROLL_STATE_IDLE ==newState){
                //onScrollStateChanged 方法
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager //只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    int lastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    int firstItemPosition = linearManager.findFirstVisibleItemPosition();
//                    if (isxh){//下滑
                        if (firstItemPosition==0){
                            rl_bg.setVisibility(View.VISIBLE);
                            mTopMargin=0;
//                            mBottomMargin = 0;
                        }
//                    }else{//上滑
//
//                    }

                }
            }

        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

//            more+=dy;//移动的距离
//            int a=topMargin-more;
//            if(a>=0){
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll_tp.getLayoutParams();
//                layoutParams.topMargin=a;
//                ll_tp.setLayoutParams(layoutParams);
////                fl_content.setScrollY(a);
////                fl_content.setPadding(0,a,0,0);
//            }
//            height=relycle.getChildAt(0).getHeight();
//            boolean b = recyclerView.canScrollVertically(1);//的值表示是否滚动到底部
//            boolean d=recyclerView.canScrollVertically(-1);//
//
//            if (jl>=0){
//                if (dy>0){
//                    jl=dy;
//                }else if (dy<0){
//                    jl=dy;
//                }
//            }else {//<0
//                if (dy>0){
//                    jl=dy;
//                }else{
//                    jl=dy;
//                }
//            }
//            Log.e("TAG","dj==="+jl);
//            if (jl>0){
//                if (mTopMargin>=height){
//                    if (!istou){
//                        rl_bg.setVisibility(View.GONE);
//                    }
//                }else{
//                    mTopMargin+=dy;
//                    istou=false;
//                }
//            }else{
//                isxh=true;
////                if (Math.abs(mTopMargin)<=height){
////                    rl_tab.setVisibility(View.VISIBLE);
////                }else{
//                    mTopMargin+=dy;
////                }
//
//                if (b&&!d){
//                    istou=true;
//
//                    rl_bg.setVisibility(View.VISIBLE);
//
//                    return;
//                }
//
//            }



            if (dy<0){//向下滚动
                isxh=true;
            }else if(dy>0){//向上移动

                if (mTopMargin>=height){
                    rl_bg.setVisibility(View.GONE);
                }else{
                    mTopMargin+=dy;
                }
                isxh=false;

            }
        }
    };

    private View.OnClickListener onclicke = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Drawable drawable;
            Comparator comparator;
            switch (view.getId()) {
                case R.id.iv_left_btn:
                    finish();
                    break;
                case R.id.rb_btn2:
                    comparator = Collections.reverseOrder();
                    Collections.sort(mlist, comparator);
                    adapter.setSort(false);
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                    i = 1;
                    break;
                case R.id.rb_btn3:
//                    rb_btn3.setPadding(DensityUtil.px2dip(VarietyDetailsActivity.this,200f),0,DensityUtil.px2dip(VarietyDetailsActivity.this,200f),0);
                    i++;
                    Log.e("TAG", "点击事件");

                    if (i % 2 == 0) {//升序排序

                        if (width<=720){
                            drawable = getResources().getDrawable(R.mipmap.xspx);
                            drawable.setBounds(0, 0, DensityUtil.px2dip(VarietyDetailsActivity.this, 30f), DensityUtil.px2dip(VarietyDetailsActivity.this, 30f));
                            rb_btn3.setCompoundDrawables(null, null, drawable, null);
                        }else{
                            drawable = getResources().getDrawable(R.mipmap.xspx);
                            drawable.setBounds(0, 0, DensityUtil.px2dip(VarietyDetailsActivity.this, 80f), DensityUtil.px2dip(VarietyDetailsActivity.this, 80f));
                            rb_btn3.setCompoundDrawables(null, null, drawable, null);
                        }
                        Collections.sort(mlist);
                        adapter.setSort(true);
                    } else {//降序排序
                        if (width<=720){
                            drawable = getResources().getDrawable(R.mipmap.xxpx);
                            drawable.setBounds(0, 0, DensityUtil.px2dip(VarietyDetailsActivity.this, 30f), DensityUtil.px2dip(VarietyDetailsActivity.this, 30f));
                            rb_btn3.setCompoundDrawables(null, null, drawable, null);
                        }else{
                            drawable = getResources().getDrawable(R.mipmap.xxpx);
                            drawable.setBounds(0, 0, DensityUtil.px2dip(VarietyDetailsActivity.this, 80f), DensityUtil.px2dip(VarietyDetailsActivity.this, 80f));
                            rb_btn3.setCompoundDrawables(null, null, drawable, null);
                        }

                        comparator = Collections.reverseOrder();
                        Collections.sort(mlist, comparator);
                        adapter.setSort(false);
                    }
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }

                    break;
                case R.id.iv_right_btn:
                    ShareSdkUtil.getInstance(VarietyDetailsActivity.this, ShareSdkUtil.Special.NO,shareListener).setValues(getResources().getString(R.string.app_name), BitmapFactory.decodeResource(getResources(), R.mipmap.logo), Constant.apkUrl, "电视媒介星红榜！网罗全国收视大数据，洞察传媒纵深新价值！");
                    ShareSdkUtil.getInstance(VarietyDetailsActivity.this, ShareSdkUtil.Special.NO,shareListener).showShare();
//                    ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO).setValues(getResources().getString(R.string.app_name), R.mipmap.logo+"",Constant.apkUrl,"电视媒介星红榜！网罗全国收视大数据，洞察传媒纵深新价值！");
//                    ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO).showShare();
                    break;
            }
        }
    };


    private RadioGroup.OnCheckedChangeListener lisener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            Drawable drawable = null;
            switch (i) {
                case R.id.rb_btn1:

                    setParams(rb_btn1, (float) 1);
                    setParams(rb_btn2, (float) 1.5);
                    setParams(rb_btn3, (float) 1);
                    setParams(rb_btn4, (float) 1);

                    rb_btn1.setBackgroundResource(R.color.rb_bg_xz);
                    rb_btn2.setBackgroundResource(R.color.rb_bg_wxz);
                    rb_btn3.setBackgroundResource(R.color.rb_bg_wxz);
                    rb_btn4.setBackgroundResource(R.color.rb_bg_wxz);
                    setView(0);
                    setButtomView(0);
                    break;
                case R.id.rb_btn2:
                    setParams(rb_btn1, (float) 1);
                    setParams(rb_btn2, (float) 1.5);
                    setParams(rb_btn3, (float) 1);
                    setParams(rb_btn4, (float) 1);
                    rb_btn1.setBackgroundResource(R.color.rb_bg_wxz);
                    rb_btn2.setBackgroundResource(R.color.rb_bg_xz);
                    rb_btn3.setBackgroundResource(R.color.rb_bg_wxz);
                    rb_btn4.setBackgroundResource(R.color.rb_bg_wxz);
                    setView(1);
                    setButtomView(1);

                    if (width<=720){
                        drawable = getResources().getDrawable(R.mipmap.xxpx);
                        drawable.setBounds(0, 0, DensityUtil.px2dip(VarietyDetailsActivity.this, 30f), DensityUtil.px2dip(VarietyDetailsActivity.this, 30f));
                        rb_btn3.setCompoundDrawables(null, null, drawable, null);
                    }else{
                        drawable = getResources().getDrawable(R.mipmap.xxpx);
                        drawable.setBounds(0, 0, DensityUtil.px2dip(VarietyDetailsActivity.this, 80f), DensityUtil.px2dip(VarietyDetailsActivity.this, 80f));
                        rb_btn3.setCompoundDrawables(null, null, drawable, null);
                    }
//                    rb_btn3.setPadding(0,0,0,0);
                    break;
                case R.id.rb_btn3:
                    setParams(rb_btn1, (float) 1);
                    setParams(rb_btn2, (float) 1.5);
                    setParams(rb_btn3, (float) 1);
                    setParams(rb_btn4, (float) 1);
                    rb_btn1.setBackgroundResource(R.color.rb_bg_wxz);
                    rb_btn2.setBackgroundResource(R.color.rb_bg_wxz);
                    rb_btn3.setBackgroundResource(R.color.rb_bg_xz);
                    rb_btn4.setBackgroundResource(R.color.rb_bg_wxz);
                    setView(2);
                    setButtomView(2);
                    Log.e("TAG", "选中事件");
                    break;
                case R.id.rb_btn4:
                    setParams(rb_btn1, (float) 1);
                    setParams(rb_btn2, (float) 1.5);
                    setParams(rb_btn3, (float) 1);
                    setParams(rb_btn4, (float) 1);
                    rb_btn1.setBackgroundResource(R.color.rb_bg_wxz);
                    rb_btn2.setBackgroundResource(R.color.rb_bg_wxz);
                    rb_btn3.setBackgroundResource(R.color.rb_bg_wxz);
                    rb_btn4.setBackgroundResource(R.color.rb_bg_xz);
                    setView(3);
                    setButtomView(3);
                    break;
            }


        }
    };


    public void setParams(RadioButton rb, float i) {
        RadioGroup.LayoutParams layoutParams = (RadioGroup.LayoutParams) rb.getLayoutParams();
        layoutParams.weight = (float) i;
        rb.setLayoutParams(layoutParams);
    }

    //获取星期几
    private int getDayofWeek(String dateTime) {
        Calendar cal = Calendar.getInstance();
        if (dateTime.equals("")) {
            cal.setTime(new Date(System.currentTimeMillis()));
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date;
            try {
                date = sdf.parse(dateTime);
            } catch (ParseException e) {
                date = null;
                e.printStackTrace();
            }
            if (date != null) {
                cal.setTime(new Date(date.getTime()));
            }
        }
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public void setView(int pos) {


        for (int i = 0; i < ll_cn.getChildCount(); i++) {
            View childAt = ll_cn.getChildAt(i);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            if (i == 1) {
                layoutParams.weight = (float) 1.5;
            }
            if (pos == i) {


                childAt.setVisibility(View.VISIBLE);


            } else {

                childAt.setVisibility(View.INVISIBLE);
            }
            childAt.setLayoutParams(layoutParams);

        }

    }

    public void setButtomView(int pos) {
        for (int i = 0; i < ll_xia_line.getChildCount(); i++) {
            View childAt = ll_xia_line.getChildAt(i);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            if (i == 1) {
                layoutParams.weight = (float) 1.5;
            }
            if (pos == i) {


                childAt.setVisibility(View.INVISIBLE);


            } else {

                childAt.setVisibility(View.VISIBLE);
            }
            childAt.setLayoutParams(layoutParams);

        }

    }


    private void getData() {
        if (NetUtil.checkNet(VarietyDetailsActivity.this)) {
            vd.getDisposeData();
        } else {
            Toast.makeText(VarietyDetailsActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
        }
    }

    private void getNightProgramData() {
        if (NetUtil.checkNet(VarietyDetailsActivity.this)) {
            nightProgramPresenter.getDisposeData();
        } else {
            Toast.makeText(VarietyDetailsActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public String getVDUrl() {

//        dt = "20190217";
        return "http://" + Constant.new_ip + "/getChannelHistoryTop?dt=" + dt;

    }

    @Override
    public int getVDCode() {
        return HttpConstants.search_news01;
    }

    @Override
    public String getVDBody() {
        return "";
    }

    @Override
    public void getVDataFailureMsg(String msg) {
        setDate();
    }

    @Override
    public void getVDData(Info info) {
        String dt = info.dt;
        tv_nian.setText(dt.subSequence(0, 4));
        tv_yue.setText(dt.subSequence(4, 6));
        tv_ri.setText(dt.subSequence(6, 8));
        if ("ALL".equals(program_type)) {
            tv_tmwben.setText(tiaomu +" | "+dt.subSequence(0, 4) + "年" + dt.subSequence(4, 6) + "月" + dt.subSequence(6, 8) + "日");
        } else if ("TELEPLAY".equals(program_type)) {
            tv_tmwben.setText(tiaomu);
        } else {
            tv_tmwben.setText(tiaomu );
        }
        int dayofWeek = getDayofWeek(dt.subSequence(0, 4) + "年" + dt.subSequence(4, 6) + "月" + dt.subSequence(6, 8) + "日");
        dayofWeek-=1;

        Log.e("TAG","dayofWeek=="+dayofWeek+",dt=="+dt);
        switch (dayofWeek) {
            case 1:
                tv_yw.setText("Sunday");
                break;
            case 2:
                tv_yw.setText("Monday");
                break;
            case 3:
                tv_yw.setText("Tuesday");
                break;
            case 4:
                tv_yw.setText("Wednesday");
                break;
            case 5:
                tv_yw.setText("Thursday");
                break;
            case 6:
                tv_yw.setText("Friday");
                break;
            case 7:
                tv_yw.setText("Saturday");
                break;
        }
        mlist.clear();
        mys.clear();
        mlist.addAll(info.realTimeDatas);
        mys.addAll(info.realTimeDatas);
        Comparator comparator = Collections.reverseOrder();
        Collections.sort(mlist, comparator);

        if (adapter != null) {
            adapter.setSort(false);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public String getNPUrl() {
//        dt = "20190224";
        return "http://" + Constant.new_ip + "/getNightProgramTop?dt=" + dt + "&program_type=" + program_type;
    }

    @Override
    public int getNPCode() {
        return HttpConstants.search_news01;
    }

    @Override
    public String getNPBody() {
        return "";
    }

    @Override
    public void getNPDataFailureMsg(String msg) {
        setDate();
    }

    @Override
    public void getNPData(Info info) {

        String dt = info.dt;
        Log.e("TAG", "dt===" + dt);
        tv_nian.setText(dt.subSequence(0, 4));
        tv_yue.setText(dt.subSequence(4, 6));
        tv_ri.setText(dt.subSequence(6, 8));
        if ("ALL".equals(program_type)) {
            tv_tmwben.setText(tiaomu +" | "+dt.subSequence(0, 4) + "年" + dt.subSequence(4, 6) + "月" + dt.subSequence(6, 8) + "日");
        } else if ("TELEPLAY".equals(program_type)) {
            tv_tmwben.setText(tiaomu);
        } else {
            tv_tmwben.setText(tiaomu );
        }
        int dayofWeek = getDayofWeek(dt.subSequence(0, 4) + "年" + dt.subSequence(4, 6) + "月" + dt.subSequence(6, 8) + "日");
        dayofWeek-=1;
        switch (dayofWeek) {
            case 1:
                tv_yw.setText("Sunday");
                break;
            case 2:
                tv_yw.setText("Monday");
                break;
            case 3:
                tv_yw.setText("Tuesday");
                break;
            case 4:
                tv_yw.setText("Wednesday");
                break;
            case 5:
                tv_yw.setText("Thursday");
                break;
            case 6:
                tv_yw.setText("Friday");
                break;
            case 7:
                tv_yw.setText("Saturday");
                break;
        }

        mlist.clear();
        mys.clear();
        mlist.addAll(info.realTimeDatas);
        mys.addAll(info.realTimeDatas);
        Comparator comparator = Collections.reverseOrder();
        Collections.sort(mlist, comparator);

        if (adapter != null) {
            adapter.setSort(false);
            adapter.notifyDataSetChanged();
        }



    }
private int height;

    public void setDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);//往上推一天  30推三十天  365推一年
        Date mBefore = calendar.getTime();
        String format = sdf.format(mBefore);

        String[] split1 = format.split(" ");
        String[] split2 = split1[0].split("-");
        dt = split2[0] + split2[1] + split2[2];
        tv_name.setText(tiaomu);
        tv_nian.setText(split2[0]);
        tv_yue.setText(split2[1]);
        tv_ri.setText(split2[2]);
        if ("ALL".equals(program_type)) {
            tv_tmwben.setText(tiaomu +" | "+dt.subSequence(0, 4) + "年" + dt.subSequence(4, 6) + "月" + dt.subSequence(6, 8) + "日");
        } else if ("TELEPLAY".equals(program_type)) {
            tv_tmwben.setText(tiaomu);
        } else {
            tv_tmwben.setText(tiaomu );
        }
        int dayofWeek = getDayofWeek(split1[0]);
        dayofWeek-=1;
        switch (dayofWeek) {
            case 1:
                tv_yw.setText("Sunday");
                break;
            case 2:
                tv_yw.setText("Monday");
                break;
            case 3:
                tv_yw.setText("Tuesday");
                break;
            case 4:
                tv_yw.setText("Wednesday");
                break;
            case 5:
                tv_yw.setText("Thursday");
                break;
            case 6:
                tv_yw.setText("Friday");
                break;
            case 7:
                tv_yw.setText("Saturday");
                break;
        }
        Log.e("TAG", "执行到了====");
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("VarietyDetails Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private ShareSdkUtil.ShareListener shareListener = new ShareSdkUtil.ShareListener() {
        @Override
        public void onCompleteShareListener() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    Toast.makeText(VarietyDetailsActivity.this,"分享成功!",Toast.LENGTH_LONG).show();
                    show("分享成功");
                }
            });
        }

        @Override
        public void ononCancelShareListener() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    Toast.makeText(VarietyDetailsActivity.this,"分享取消!",Toast.LENGTH_LONG).show();
                    show("分享取消");
                }
            });
        }
    };

    public void show(String text){
        ShareDialog dialog = new ShareDialog(VarietyDetailsActivity.this).builder()
                .setTitle(text)
                .setNegativeButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

        dialog.show();
    }

}
