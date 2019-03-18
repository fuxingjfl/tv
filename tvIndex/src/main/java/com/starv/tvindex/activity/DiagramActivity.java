package com.starv.tvindex.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.starv.tvindex.Presenter.DiagramPresenter;
import com.starv.tvindex.Presenter.DiagramView;
import com.starv.tvindex.R;
import com.starv.tvindex.base.BaseActivity;
import com.starv.tvindex.entity.Info;
import com.starv.tvindex.entity.SpotData;
import com.starv.tvindex.okhttpUtils.HttpConstants;
import com.starv.tvindex.util.Constant;
import com.starv.tvindex.util.IndexListener;
import com.starv.tvindex.util.JsInterface;
import com.starv.tvindex.util.NetUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ysq on 2019/2/27.
 */

public class DiagramActivity extends BaseActivity implements DiagramView{

    private WebView wv_view;
    private ImageView iv_left_btn,iv_right_btn;
    private DiagramPresenter diagramPresenter;
    private String type="MINUTE";
    private String channel_code;
    private MyWebViewClient myWebViewClient;
    private Handler handler;
    private List<String> startTimeList= new ArrayList<>();
    private List<String> audienceRatingList = new ArrayList<>();
    private String channelName;
    private RadioGroup rg_diagram;
    private ImageView iv_pdimg;
    private TextView tv_rq,tv_time,tv_lv,tv_time_sj,tv_pd_name,tv_jm_name,tv_jm_lv;
    private TimerTask realTimetask;
    private Timer realTimetimer;
    private boolean isdj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram);
        wv_view= (WebView) findViewById(R.id.wv_view);
        tv_time= (TextView) findViewById(R.id.tv_time);
        iv_pdimg= (ImageView) findViewById(R.id.iv_pdimg);
        tv_lv= (TextView) findViewById(R.id.tv_lv);
        rg_diagram= (RadioGroup) findViewById(R.id.rg_diagram);
        tv_jm_lv= (TextView) findViewById(R.id.tv_jm_lv);
        tv_jm_name= (TextView) findViewById(R.id.tv_jm_name);
        tv_pd_name= (TextView) findViewById(R.id.tv_pd_name);
        tv_time_sj= (TextView) findViewById(R.id.tv_time_sj);
        tv_rq= (TextView) findViewById(R.id.tv_rq);
        handler= new Handler();
        iv_left_btn= (ImageView) findViewById(R.id.iv_left_btn);
        iv_right_btn= (ImageView) findViewById(R.id.iv_right_btn);
        iv_right_btn.setImageResource(R.mipmap.cd);
        iv_left_btn.setImageResource(R.mipmap.black);
        wv_view.getSettings().setAllowFileAccess(true);
        wv_view.setHorizontalScrollBarEnabled(false);//水平不显示
        wv_view.setVerticalScrollBarEnabled(false); //垂直不显示
        wv_view  .setVerticalScrollbarOverlay(true);
        wv_view.getSettings().setDefaultTextEncodingName("utf-8");
        wv_view.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        if (Build.VERSION.SDK_INT >= 21) {
            wv_view.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());

        String format = simpleDateFormat.format(date);
        String[] split = format.split(" ");
        tv_rq.setText(split[0]);
        //开启脚本支持
        wv_view.getSettings().setJavaScriptEnabled(true);
        JsInterface jsInterface = new JsInterface(this,indexListener);
        wv_view.addJavascriptInterface(jsInterface, "AndroidWebView"); //重要的
        wv_view.loadUrl("file:///android_asset/index.html");
        wv_view.setBackgroundColor(0);
//        wv_view.getBackground().setAlpha(0);
        diagramPresenter = new DiagramPresenter(DiagramActivity.this);
        channel_code=getIntent().getStringExtra("channel_code");
        stopRealTimeData();
        getData();

        iv_left_btn.setOnClickListener(listener);
        iv_right_btn.setOnClickListener(listener);
        rg_diagram.setOnCheckedChangeListener(checkedChangeListener);
    }


    private IndexListener indexListener = new IndexListener() {
        @Override
        public void onIndexListener(final int pos) {

            handler.post(new Runnable() {
                @Override
                public void run() {
                    int i = spotDatas.size()-1 - pos;
                    if(i>=0&&i<=spotDatas.size()-1){
                        String[] split = spotDatas.get(i).time.split(" ");
                        tv_time.setText(split[1]);
                        tv_lv.setText(spotDatas.get(i).rat);
                        isdj=true;
                    }
                }
            });

        }
    };

    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {

            switch (i){
                case R.id.rb_d1:
                    type="MINUTE";
                    stopRealTimeData();
                    getData();
                    break;
                case R.id.rb_d2:
                    type="SECOND";
                    stopRealTimeData();
                    getData();
                    break;
            }

        }
    };

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.iv_left_btn:
                    finish();
                    break;
                case R.id.iv_right_btn:
                    Intent intent = new Intent(DiagramActivity.this,MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            showLineChart(startTimeList,audienceRatingList);
        }
    }

    private void showLineChart(List<String> startTimeList,List<String> audienceRatingList){

        for (int i=0;i<startTimeList.size();i++){

            final int y=i;
            wv_view.loadUrl("javascript:setTime('"+startTimeList.get(y)+"');");
            wv_view.loadUrl("javascript:setData('"+audienceRatingList.get(y)+"');");

        }
        wv_view.loadUrl("javascript:setOption();");
        wv_view.loadUrl("javascript:setLegend('"+channelName+"');");
        wv_view.loadUrl("javascript:setName('"+channelName+"');");
    }

    private void getData(){
        if (realTimetimer == null) {
            realTimetimer = new Timer();
        }
        if (realTimetask == null) {
            realTimetask = new TimerTask() {
                @Override
                public void run() {
                    if(NetUtil.checkNet(DiagramActivity.this)){
                        diagramPresenter.getDisposeData();
                    }else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(DiagramActivity.this, "请检查当前网络是否可用！！！", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            };
        }

        if ("MINUTE".equals(type)){
            if (realTimetask != null && realTimetimer != null) {
                realTimetimer.schedule(realTimetask, 0, 60 * 1000);
            }
        }else{
            if (realTimetask != null && realTimetimer != null) {
                realTimetimer.schedule(realTimetask, 0, 5 * 1000);
            }
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
    public String getDUrl() {
        return "http://"+ Constant.new_ip+"/getRealChannelDetail?type="+type+"&channel_code="+channel_code;
    }

    @Override
    public int getDCode() {
        return HttpConstants.search_news01;
    }

    @Override
    public String getDBody() {
        return "";
    }

    @Override
    public void getDDataFailureMsg(String msg) {

    }


    private List<SpotData> spotDatas=new ArrayList<>();
    @Override
    public void getDData(Info info) {

        channelName=info.diagram.spotDatas.get(0).channel_name;
        spotDatas.clear();
        startTimeList.clear();
        audienceRatingList.clear();
        spotDatas.addAll(info.diagram.spotDatas);
        for (int i=info.diagram.spotDatas.size()-1;i>-1;i--){

            SpotData spotData = info.diagram.spotDatas.get(i);
            String[] split = spotData.time.split(" ");
            String split2 = split[1];
            if ("MINUTE".equals(type)){
                String[] split1 = split[1].split(":");
                split2 = split1[0]+":"+split1[1];
            }
            startTimeList.add(split2);
            String[] y = spotData.rat.split("%");
            audienceRatingList.add(y[0]);

            if (!isdj){
                if (i==0){
                    tv_time.setText(split[1]);

                }
            }
            if (i==0){
                tv_time_sj.setText(split[1]);
            }

        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);
        String[] split = format.split(" ");
        tv_rq.setText(split[0]);
        if (!isdj){
            tv_lv.setText(info.diagram.spotDatas.get(0).rat);
        }
        tv_jm_lv.setText(info.diagram.spotDatas.get(0).rat);
        tv_pd_name.setText(info.diagram.spotDatas.get(0).channel_name);
        tv_jm_name.setText(info.diagram.program_name);

//        Glide.with(DiagramActivity.this).load("http://"+Constant.new_ip+info.diagram.real_tv_img).into(iv_pdimg);

        Picasso.with(DiagramActivity.this).load("http://"+Constant.new_ip+info.diagram.real_tv_img).into(iv_pdimg);

        if(myWebViewClient==null){
            myWebViewClient=new MyWebViewClient();
            if (wv_view!=null){
                wv_view.setWebViewClient(myWebViewClient);
            }
        }else{
            if(wv_view!=null){
                wv_view.loadUrl("javascript:clearData();");
                showLineChart(startTimeList,audienceRatingList);
            }
        }
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wv_view != null) {
            wv_view.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            wv_view.clearHistory();
            ((ViewGroup) wv_view.getParent()).removeView(wv_view);
            wv_view.destroy();
            wv_view = null;
        }
        stopRealTimeData();
    }
}
