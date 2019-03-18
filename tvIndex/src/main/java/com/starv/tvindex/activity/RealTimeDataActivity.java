package com.starv.tvindex.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ScrollingView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.starv.tvindex.Presenter.RealTimePresenter;
import com.starv.tvindex.Presenter.RealTimeView;
import com.starv.tvindex.R;
import com.starv.tvindex.base.BaseActivity;
import com.starv.tvindex.entity.Info;
import com.starv.tvindex.entity.RealTimeData;
import com.starv.tvindex.fragment.BaseFragment;
import com.starv.tvindex.fragment.ContentFragment;
import com.starv.tvindex.okhttpUtils.HttpConstants;
import com.starv.tvindex.util.Constant;
import com.starv.tvindex.util.MyScrollView;
import com.starv.tvindex.util.NetUtil;
import com.starv.tvindex.util.ShareSdkUtil;
import com.starv.tvindex.util.dialog.ShareDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ysq on 2019/2/25.
 */

public class RealTimeDataActivity extends BaseActivity implements RealTimeView , MyScrollView.OnScrollListener {

    private ContentFragment contentFragment1,contentFragment2,contentFragment3;
    private BaseFragment currentf;
    private RadioGroup rg_fragment;
    private RealTimePresenter realTimePresenter;
    private List<RealTimeData> whole,cctvs,satellitetv;
    private TimerTask realTimetask;
    private Timer realTimetimer;
    private RadioButton rb_f1,rb_f2,rb_f3;
    private ImageView iv_left_btn,iv_right_btn;
    private String time;
    private  MyThread myThread;
    private Handler handler;
    private Calendar calendar;
    private RelativeLayout rl_tab;
    private FrameLayout fl_content;
    private int more;
    int topMargin;
    private MyScrollView sv_bg;
@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_data);
        rg_fragment= (RadioGroup) findViewById(R.id.rg_fragment);
        rb_f1= (RadioButton) findViewById(R.id.rb_f1);
        rb_f2= (RadioButton) findViewById(R.id.rb_f2);
        rb_f3= (RadioButton) findViewById(R.id.rb_f3);
        fl_content=findViewById(R.id.fl_content);
//    sv_bg=findViewById(R.id.sv_bg);
        rl_tab=findViewById(R.id.rl_tab);
        iv_right_btn= (ImageView) findViewById(R.id.iv_right_btn);
        iv_left_btn= (ImageView) findViewById(R.id.iv_left_btn);
        handler = new Handler();
        whole = new ArrayList<>();
        cctvs = new ArrayList<>();
        satellitetv=new ArrayList<>();
        realTimePresenter = new RealTimePresenter(this);
        stopRealTimeData();
        getData();

//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fl_content.getLayoutParams();
//        topMargin=layoutParams.topMargin;
//        Log.e("TAG","topMargin=="+topMargin);

        rg_fragment.setOnCheckedChangeListener(listener);
        iv_left_btn.setOnClickListener(onClickListener);
        iv_right_btn.setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_left_btn:
                finish();
                break;
            case R.id.iv_right_btn:
                ShareSdkUtil.getInstance(RealTimeDataActivity.this, ShareSdkUtil.Special.NO,shareListener).setValues(getResources().getString(R.string.app_name), BitmapFactory.decodeResource(getResources(),R.mipmap.logo),Constant.apkUrl,"电视媒介星红榜！网罗全国收视大数据，洞察传媒纵深新价值！");
                ShareSdkUtil.getInstance(RealTimeDataActivity.this, ShareSdkUtil.Special.NO,shareListener).showShare();
//                ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO).setValues(getResources().getString(R.string.app_name), R.mipmap.logo+"",Constant.apkUrl,"电视媒介星红榜！网罗全国收视大数据，洞察传媒纵深新价值！");
//                ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO).showShare();
                break;
        }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.rb_f1:
                    if(contentFragment1==null){
                        contentFragment1= new ContentFragment();
                        Info info  = new Info();
                        info.realTimeDatas = whole;
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data",info);
                        bundle.putString("time",time);
                        contentFragment1.setArguments(bundle);
                        contentFragment1.setOnContentScrollListener(scrollListener);
                    }else{
                        contentFragment1.ReloadLoading(whole,time);
//                        contentFragment1.RefreshData(whole,time);
                    }
                    addFragments(contentFragment1);
                    break;
                case R.id.rb_f2:
                    if(contentFragment2==null){
                        contentFragment2= new ContentFragment();
                        Info info  = new Info();
                        info.realTimeDatas = satellitetv;
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data",info);
                        bundle.putString("time",time);
                        contentFragment2.setArguments(bundle);
                        contentFragment2.setOnContentScrollListener(scrollListener);
                    }else{
                        contentFragment2.ReloadLoading(satellitetv,time);

//                        contentFragment2.RefreshData(satellitetv,time);
                    }
                    addFragments(contentFragment2);
                    break;
                case R.id.rb_f3:
                    if(contentFragment3==null){
                        contentFragment3= new ContentFragment();
                        Info info  = new Info();
                        info.realTimeDatas = cctvs;
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data",info);
                        bundle.putString("time",time);
                        contentFragment3.setArguments(bundle);
                        contentFragment3.setOnContentScrollListener(scrollListener);
                    }else{
                        contentFragment3.ReloadLoading(cctvs,time);
//                        contentFragment3.RefreshData(cctvs,time);
                    }
                    addFragments(contentFragment3);
                    break;
            }
        }
    };


    private void addFragments(BaseFragment f) {
        if (RealTimeDataActivity.this!=null&&!isdestroy){
            // 第一步：得到fragment管理类
            FragmentManager manager = getSupportFragmentManager();
            // 第二步：开启一个事务
            FragmentTransaction transaction = manager.beginTransaction();

            if (currentf != null) {
                //每次把前一个fragment给隐藏了
                transaction.hide(currentf);
            }
            //isAdded:判断当前的fragment对象是否被加载过
            if (!f.isAdded()) {
                // 第三步：调用添加fragment的方法 第一个参数：容器的id 第二个参数：要放置的fragment的一个实例对象
                transaction.add(R.id.fl_content, f);
            }
            //显示当前的fragment
            transaction.show(f);
            // 第四步：提交
            transaction.commitAllowingStateLoss();
            currentf = f;
        }
    }


    private void getData(){
        if (realTimetimer == null) {
            realTimetimer = new Timer();
        }
        if (realTimetask == null) {
            realTimetask = new TimerTask() {
                @Override
                public void run() {
                    if(NetUtil.checkNet(RealTimeDataActivity.this)){
                        realTimePresenter.getDisposeData();
                    }else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RealTimeDataActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                            }
                        });
                }
                }
            };
        }
        if (realTimetask != null && realTimetimer != null) {
            realTimetimer.schedule(realTimetask, 0, 5 * 1000);
        }
    }

    //关闭实时获取的资源
    public void stopRealTimeData() {
        if (realTimetimer != null) {
            realTimetimer.cancel();
            realTimetimer = null;
        }
        if (realTimetask != null) {
            realTimetask.cancel();
            realTimetask = null;
        }
    }

    @Override
    public String getRTUrl() {
        return "http://"+ Constant.new_ip+"/getRealChannel";
    }

    @Override
    public int getRTCode() {
        return HttpConstants.search_news01;
    }

    @Override
    public String getRTBody() {
        return "";
    }

    @Override
    public void getRTDataFailureMsg(String msg) {
        Log.e("TAG","获取数据失败1!!!!!");
    }

    @Override
    public void getRTData(Info info) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);
        String[] split = format.split(" ");
        time=split[1];
        List<RealTimeData> realTimeDatas = info.realTimeDatas;
        whole.clear();
        cctvs.clear();
        satellitetv.clear();
        if (realTimeDatas!=null){
            whole.addAll(realTimeDatas);
        }
        for (int i=0;i<realTimeDatas.size();i++){
            RealTimeData realTimeData = realTimeDatas.get(i);
            if ("央视".equals(realTimeData.channel_group)){
                cctvs.add(realTimeData);
            }else if ("卫视".equals(realTimeData.channel_group)){
                satellitetv.add(realTimeData);
            }
        }

        if (rb_f1.isChecked()){
            if(contentFragment1==null){
                if (myThread==null){
                    myThread = new MyThread();
                    myThread.start();
                }
                contentFragment1= new ContentFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",info);
                bundle.putString("time",time);
                contentFragment1.setArguments(bundle);
                contentFragment1.setOnContentScrollListener(scrollListener);
            }else{
                contentFragment1.RefreshData(whole,time);
            }
            addFragments(contentFragment1);
        }else if (rb_f2.isChecked()){
            if(contentFragment2==null){
                contentFragment2= new ContentFragment();
                Info info2 = new Info();
                info2.realTimeDatas = satellitetv ;
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",info2);
                bundle.putString("time",time);
                contentFragment2.setArguments(bundle);
                contentFragment2.setOnContentScrollListener(scrollListener);
            }else{
                contentFragment2.RefreshData(satellitetv,time);
            }
            addFragments(contentFragment2);
        }else if (rb_f3.isChecked()){
            if(contentFragment3==null){
                contentFragment3= new ContentFragment();
                Info info3 = new Info();
                info3.realTimeDatas = cctvs;
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",info3);
                bundle.putString("time",time);
                contentFragment3.setArguments(bundle);
                contentFragment3.setOnContentScrollListener(scrollListener);
            }else{
                contentFragment3.RefreshData(cctvs,time);
            }
            addFragments(contentFragment3);
        }


    }

    private boolean isdestroy;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        whole.clear();
        cctvs.clear();
        satellitetv.clear();
        isdestroy=true;
        if(myThread!=null&&!myThread.isAlive()){
            myThread.stop();
        }
        myThread=null;
        stopRealTimeData();

    }

    @Override
    public void onScroll(int scrollY) {

    }

    private class MyThread  extends Thread{

        public  StringBuffer stringBuffer;

        public MyThread(){
            stringBuffer = new StringBuffer();
        }

        @Override
        public void run() {
            do{
                try {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            calendar= Calendar.getInstance();
                            int day = calendar.get(Calendar.DAY_OF_MONTH);
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                            stringBuffer = new StringBuffer();
                            stringBuffer.delete(0,stringBuffer.length());//删除所有的数据
                            stringBuffer.append(sdf.format(new Date()));
                            if (contentFragment1!=null){
                                contentFragment1.RefreshTime(stringBuffer.toString());
                            }
                            if (contentFragment2!=null){
                                contentFragment2.RefreshTime(stringBuffer.toString());
                            }
                            if (contentFragment3!=null){
                                contentFragment3.RefreshTime(stringBuffer.toString());
                            }
                        }
                    });
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e("TAG","InterruptedException");
                }
            }while (true);
        }
    }

    private int mTopMargin,mBottomMargin,jl;
    private boolean isxh ,istou;
    private ContentFragment.ContentScrollListener scrollListener = new ContentFragment.ContentScrollListener() {
        @Override
        public void onContentScrollListener(int state,int dy,boolean b,boolean d,int height) {
            Log.i("TAG",",dy=="+dy+",d=="+d+",state=="+state);

//            more+=dy;//移动的距离
//            int a=topMargin-more;
//            if(a>=0){
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fl_content.getLayoutParams();
//                layoutParams.topMargin=a;
//                fl_content.setLayoutParams(layoutParams);
////                fl_content.setScrollY(a);
////                fl_content.setPadding(0,a,0,0);
//            }

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
//                        rl_tab.setVisibility(View.GONE);
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
//                    rl_tab.setVisibility(View.VISIBLE);
//
//                    return;
//                }
//
//            }



            if (state==0){//没动
                rl_tab.setVisibility(View.VISIBLE);
            }else if (state>0){//向上滑动
                if (mTopMargin>=height){
                    rl_tab.setVisibility(View.GONE);
                }else{
                    mTopMargin+=dy;
                }
                isxh=false;
            }else{//向下滑动
                isxh=true;

            }
//            Log.e("TAG","mTopMargin==="+mTopMargin+",,mBottomMargin=="+mBottomMargin);
        }

        @Override
        public void onScrollPositionListener(int firstItemPosition, int lastItemPosition) {
//            Log.i("TAG",",firstItemPosition=="+firstItemPosition+",lastItemPosition=="+lastItemPosition);

            if (isxh){//下滑
                if (firstItemPosition==0){
                    rl_tab.setVisibility(View.VISIBLE);
                    mTopMargin=0;
                    mBottomMargin = 0;
//                    jl=0;

                }else{

                }
            }else{//上滑

            }
        }

        @Override
        public void onScrollZListener(int firstItemPosition, int lastItemPosition) {

        }
    };

    private ShareSdkUtil.ShareListener shareListener = new ShareSdkUtil.ShareListener() {
        @Override
        public void onCompleteShareListener() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    Toast.makeText(RealTimeDataActivity.this,"分享成功!",Toast.LENGTH_LONG).show();
                    show("分享成功");
                }
            });
        }

        @Override
        public void ononCancelShareListener() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    Toast.makeText(RealTimeDataActivity.this,"分享取消!",Toast.LENGTH_LONG).show();
                    show("分享取消");
                }
            });
        }
    };

    public void show(String text){
        ShareDialog dialog = new ShareDialog(RealTimeDataActivity.this).builder()
                .setTitle(text)
                .setNegativeButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

        dialog.show();
    }

}
