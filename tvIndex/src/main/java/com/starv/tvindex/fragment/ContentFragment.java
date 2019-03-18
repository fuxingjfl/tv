package com.starv.tvindex.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.starv.tvindex.Presenter.RealTimeView;
import com.starv.tvindex.R;
import com.starv.tvindex.activity.DiagramActivity;
import com.starv.tvindex.activity.VarietyDetailsActivity;
import com.starv.tvindex.adapter.VarietyDetailsAdapter;
import com.starv.tvindex.entity.Info;
import com.starv.tvindex.entity.RealTimeData;
import com.starv.tvindex.util.DensityUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by ysq on 2019/2/25.
 */

public class ContentFragment extends BaseFragment {

    private RecyclerView relycle;
    private VarietyDetailsAdapter adapter;
    private List<RealTimeData> mlist;
    private RadioGroup rg_zg;
    private RadioButton rb_btn1,rb_btn2,rb_btn3,rb_btn4;
    private View view1,view2,view3,view4;
    private LinearLayout ll_cn,ll_xia_line;
    private int i=1;
    private TextView tv_ss_text;
    private List<RealTimeData> mys;//元数据
    private RelativeLayout rl_tab;
    private int width;
    @Override
    protected int setContentView() {
        return R.layout.fragment_content;
    }

    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        Bundle arguments = getArguments();
        Info info= (Info) arguments.getSerializable("data");
        relycle= (RecyclerView)contentView. findViewById(R.id.recycle);
        rg_zg= (RadioGroup)contentView. findViewById(R.id.rg_zg);
        ll_xia_line= (LinearLayout) contentView.findViewById(R.id.ll_xia_line);
        rb_btn1= (RadioButton)contentView. findViewById(R.id.rb_btn1);
        rb_btn2= (RadioButton)contentView. findViewById(R.id.rb_btn2);
        rb_btn3= (RadioButton)contentView. findViewById(R.id.rb_btn3);
        rb_btn4= (RadioButton)contentView.  findViewById(R.id.rb_btn4);
        ll_cn= (LinearLayout)contentView.  findViewById(R.id.ll_cn);
        tv_ss_text= (TextView) contentView.findViewById(R.id.tv_ss_text);
        rl_tab=contentView.findViewById(R.id.rl_tab);
        view1=contentView. findViewById(R.id.view1);
        view2=contentView. findViewById(R.id.view2);
        view3=contentView. findViewById(R.id.view3);
        view4=contentView. findViewById(R.id.view4);
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        width= dm.widthPixels;         // 屏幕宽度（像素）
//        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
//        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）

        if (width<=720){
            Drawable drawable = getResources().getDrawable(R.mipmap.xxpx);
            drawable.setBounds(0,0,DensityUtil.px2dip(getActivity(),30f),DensityUtil.px2dip(getActivity(),30f));
            rb_btn3.setCompoundDrawables(null,null,drawable,null);
            rb_btn3.setPadding(DensityUtil.px2dip(getActivity(),70f),0,DensityUtil.px2dip(getActivity(),70f),0);
        }else{
            Drawable drawable = getResources().getDrawable(R.mipmap.xxpx);
            drawable.setBounds(0,0,DensityUtil.px2dip(getActivity(),80f),DensityUtil.px2dip(getActivity(),80f));
            rb_btn3.setCompoundDrawables(null,null,drawable,null);
            rb_btn3.setPadding(DensityUtil.px2dip(getActivity(),100f),0,DensityUtil.px2dip(getActivity(),100f),0);
        }


//        rb_btn3.setPadding(0,0,0,0);
        setParams(rb_btn1, (float) 1);
        setParams(rb_btn2, (float) 1.5);
        setParams(rb_btn3, (float) 1);
        setParams(rb_btn4, (float) 1);
        rb_btn1.setBackgroundResource(R.color.rb_bg_wxz);
        rb_btn2.setBackgroundResource(R.color.rb_bg_xz);
        rb_btn3.setBackgroundResource(R.color.rb_bg_wxz);
        rb_btn4.setBackgroundResource(R.color.rb_bg_wxz);
        mlist = new ArrayList<>();
        mys = new ArrayList<>();
        if (info.realTimeDatas!=null){
            mlist.addAll(info.realTimeDatas);
            mys.addAll(info.realTimeDatas);
        }
        Comparator comparator=Collections.reverseOrder();
        Collections.sort(mlist,comparator);
        adapter = new VarietyDetailsAdapter(R.layout.item_pd,mlist,1);
        adapter.setSort(false);
        relycle.setLayoutManager(new LinearLayoutManager(getActivity()));
//        relycle.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        relycle.setAdapter(adapter);
        relycle.addOnScrollListener(onScrollListener);
        rg_zg.setOnCheckedChangeListener(lisener);
        rb_btn3.setOnClickListener(onClickListener);
        rb_btn2.setOnClickListener(onClickListener);
        adapter.setOnItemClickListener(onItemChildClickListener);


    }

    private ContentScrollListener contentScrollListener;

    public interface ContentScrollListener{
        void onContentScrollListener(int state,int dy,boolean isdb,boolean is,int height);
        void onScrollPositionListener(int firstItemPosition,int lastItemPosition);
        void onScrollZListener(int firstItemPosition,int lastItemPosition);
    }

    public void setOnContentScrollListener(ContentScrollListener contentScrollListener){
        this.contentScrollListener=contentScrollListener;
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

//            Log.i("TAG","dx=="+dx+",dy=="+dy);

            boolean b = recyclerView.canScrollVertically(1);//的值表示是否滚动到底部
            boolean d=recyclerView.canScrollVertically(-1);//

            int height = recyclerView.getChildAt(0).getHeight();
            Log.e("TAG","b=="+b+",d=="+d);
            if (dy<0){//向下滚动
                contentScrollListener.onContentScrollListener(-1,dy,b,d,height);
            }else if(dy>0){//向上移动
                contentScrollListener.onContentScrollListener(1,dy,b,d,height);
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            Log.i("TAG","newState=="+newState);
            int firstItemPosition = 0;
            int lastItemPosition=0;
//onScrollStateChanged 方法
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            //判断是当前layoutManager是否为LinearLayoutManager //只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                //获取最后一个可见view的位置
                lastItemPosition= linearManager.findLastVisibleItemPosition();
                //获取第一个可见view的位置
                firstItemPosition= linearManager.findFirstVisibleItemPosition();

            }
            if (RecyclerView.SCROLL_STATE_IDLE ==newState){
                contentScrollListener.onScrollPositionListener(firstItemPosition,lastItemPosition);
            }else if (RecyclerView.SCROLL_STATE_SETTLING  ==newState){
                Log.e("TAG","一直移动========");
//                contentScrollListener.onScrollPositionListener(firstItemPosition,lastItemPosition);
            }


        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Drawable drawable;
            Comparator comparator;
            switch (view.getId()){
                case R.id.rb_btn2:
                    comparator=Collections.reverseOrder();
                    Collections.sort(mlist,comparator);
                    adapter.setSort(false);
                    if (adapter!=null){
                        adapter.notifyDataSetChanged();
                    }
                    i=1;
                    break;
                case R.id.rb_btn3:
//                    rb_btn3.setPadding(DensityUtil.px2dip(getActivity(),200f),0,DensityUtil.px2dip(getActivity(),200f),0);
                    i++;
                    Log.e("TAG","点击事件");

                    if (i%2==0){//升序排序

                        if (width<=720){
                            drawable = getResources().getDrawable(R.mipmap.xspx);
                            drawable.setBounds(0,0,DensityUtil.px2dip(getActivity(),30f),DensityUtil.px2dip(getActivity(),30f));
                            rb_btn3.setCompoundDrawables(null,null,drawable,null);
                        }else{
                            drawable = getResources().getDrawable(R.mipmap.xspx);
                            drawable.setBounds(0,0,DensityUtil.px2dip(getActivity(),80f),DensityUtil.px2dip(getActivity(),80f));
                            rb_btn3.setCompoundDrawables(null,null,drawable,null);
                        }

                        Collections.sort(mlist);
                        adapter.setSort(true);

                    }else{//降序排序

                        if (width<=720){
                            drawable = getResources().getDrawable(R.mipmap.xxpx);
                            drawable.setBounds(0,0,DensityUtil.px2dip(getActivity(),30f),DensityUtil.px2dip(getActivity(),30f));
                            rb_btn3.setCompoundDrawables(null,null,drawable,null);
                        }else{
                            drawable = getResources().getDrawable(R.mipmap.xxpx);
                            drawable.setBounds(0,0,DensityUtil.px2dip(getActivity(),80f),DensityUtil.px2dip(getActivity(),80f));
                            rb_btn3.setCompoundDrawables(null,null,drawable,null);
                        }



                        comparator=Collections.reverseOrder();
                        Collections.sort(mlist,comparator);
                        adapter.setSort(false);


                    }
                    if (adapter!=null){
                        adapter.notifyDataSetChanged();
                    }

                    break;
            }
        }
    };

    private BaseQuickAdapter.OnItemClickListener onItemChildClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(getActivity(), DiagramActivity.class);
            intent.putExtra("channel_code",mlist.get(position).channel_code);
            startActivity(intent);
        }
    };

    private RadioGroup.OnCheckedChangeListener lisener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            Drawable drawable=null;
            switch (i){
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
                        drawable.setBounds(0,0,DensityUtil.px2dip(getActivity(),30f),DensityUtil.px2dip(getActivity(),30f));
                        rb_btn3.setCompoundDrawables(null,null,drawable,null);
                    }else{
                        drawable = getResources().getDrawable(R.mipmap.xxpx);
                        drawable.setBounds(0,0,DensityUtil.px2dip(getActivity(),80f),DensityUtil.px2dip(getActivity(),80f));
                        rb_btn3.setCompoundDrawables(null,null,drawable,null);
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


    public void setParams(RadioButton rb,float i){
        RadioGroup.LayoutParams layoutParams = (RadioGroup.LayoutParams) rb.getLayoutParams();
        layoutParams.weight= (float) i;
        rb.setLayoutParams(layoutParams);
    }

    public void setView(int pos){
        for (int i=0;i<ll_cn.getChildCount();i++){
            View childAt = ll_cn.getChildAt(i);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            if (i==1){
                layoutParams.weight= (float) 1.5;
            }
            if (pos==i){


                childAt.setVisibility(View.VISIBLE);


            }else{

                childAt.setVisibility(View.INVISIBLE);
            }
            childAt.setLayoutParams(layoutParams);

        }

    }


    public void setButtomView(int pos){
        for (int i=0;i<ll_xia_line.getChildCount();i++){
            View childAt = ll_xia_line.getChildAt(i);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            if (i==1){
                layoutParams.weight= (float) 1.5;
            }
            if (pos==i){


                childAt.setVisibility(View.INVISIBLE);


            }else{

                childAt.setVisibility(View.VISIBLE);
            }
            childAt.setLayoutParams(layoutParams);

        }

    }



    public void RefreshTime(String time){
//        Log.e("TAG","实时时间:::"+time);
        if (tv_ss_text!=null){
            tv_ss_text.setText("实时收视数据 ("+time+")");
        }
    }



    public void RefreshData(List<RealTimeData> list,String time){

        mlist.clear();
        mys.clear();
        mlist.addAll(list);
        mys.addAll(list);
        Drawable drawable;
        if (getActivity()!=null){
            if (i%2==0){//升序排序
                if (width<=720){
                    drawable = getResources().getDrawable(R.mipmap.xspx);
                    drawable.setBounds(0,0,DensityUtil.px2dip(getActivity(),30f),DensityUtil.px2dip(getActivity(),30f));
                    rb_btn3.setCompoundDrawables(null,null,drawable,null);
                }else{
                    drawable = getResources().getDrawable(R.mipmap.xspx);
                    drawable.setBounds(0,0,DensityUtil.px2dip(getActivity(),80f),DensityUtil.px2dip(getActivity(),80f));
                    rb_btn3.setCompoundDrawables(null,null,drawable,null);
                }
                Collections.sort(mlist);
                adapter.setSort(true);
            }else{//降序排序
                if (width<=720){
                    drawable = getResources().getDrawable(R.mipmap.xxpx);
                    drawable.setBounds(0,0,DensityUtil.px2dip(getActivity(),30f),DensityUtil.px2dip(getActivity(),30f));
                    rb_btn3.setCompoundDrawables(null,null,drawable,null);
                }else{
                    drawable = getResources().getDrawable(R.mipmap.xxpx);
                    drawable.setBounds(0,0,DensityUtil.px2dip(getActivity(),80f),DensityUtil.px2dip(getActivity(),80f));
                    rb_btn3.setCompoundDrawables(null,null,drawable,null);
                }
                Comparator comparator=Collections.reverseOrder();
                Collections.sort(mlist,comparator);
                adapter.setSort(false);
            }
        }
        adapter.notifyDataSetChanged();
    }


    public  void ReloadLoading(List<RealTimeData> list,String time){


        mlist.clear();
        mys.clear();
        mlist.addAll(list);
        mys.addAll(list);
        i=1;
        rb_btn2.setChecked(true);

        setParams(rb_btn1, (float) 1);
        setParams(rb_btn2, (float) 1.5);
        setParams(rb_btn3, (float) 1);
        setParams(rb_btn4, (float) 1);
        rb_btn1.setBackgroundResource(R.color.rb_bg_wxz);
        rb_btn2.setBackgroundResource(R.color.rb_bg_xz);
        rb_btn3.setBackgroundResource(R.color.rb_bg_wxz);
        rb_btn4.setBackgroundResource(R.color.rb_bg_wxz);
        Comparator comparator=Collections.reverseOrder();
        Collections.sort(mlist,comparator);
        adapter.setSort(false);

        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }

    }



}
