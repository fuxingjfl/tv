package com.starv.tvindex.adapter;


import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.starv.tvindex.R;
import com.starv.tvindex.entity.RealTimeData;
import com.starv.tvindex.util.Constant;


import java.util.List;

/**
 * Created by ysq on 2019/2/25.
 */

public class VarietyDetailsAdapter extends BaseQuickAdapter<RealTimeData, BaseViewHolder> {

    private int islayout;
    private boolean ispx;
    public VarietyDetailsAdapter(@LayoutRes int layoutResId, @Nullable List<RealTimeData> data,int islayout) {
        super(layoutResId, data);
        this.islayout=islayout;
    }

    @Override
    protected void convert(BaseViewHolder helper, RealTimeData item) {

        TextView tv_pq=helper.getView(R.id.tv_pq);

        final ImageView iv_logo = helper.getView(R.id.iv_logo);
        TextView tv_title_pd = helper.getView(R.id.tv_title_pd);
        TextView tv_jm_name=helper.getView(R.id.tv_jm_name);
        TextView tv_ssl=helper.getView(R.id.tv_ssl);
        TextView tv_ssfe=helper.getView(R.id.tv_ssfe);
//        ImageView iv_bg=helper.getView(R.id.iv_bg);
        String pq=null;
        int adapterPosition = helper.getAdapterPosition();
        if (ispx) {//升序
            int itemCount = getItemCount();
            int num = itemCount-adapterPosition;
            if ((num+"").length()==1){
                pq="00"+num;
            }else if ((num+"").length()==2){
                pq="0"+num;
            }else{
                pq=num+"";
            }
        }else{
            adapterPosition+=1;
            if ((adapterPosition+"").length()==1){

                pq="00"+adapterPosition;

            }else if ((adapterPosition+"").length()==2){
                pq="0"+adapterPosition;
            }else{
                pq=adapterPosition+"";
            }
        }


        tv_pq.setText(pq);

        if (islayout==0){
            tv_title_pd.setText(item.program_name);
            tv_jm_name.setText(item.channel_name);
            tv_jm_name.setTextColor(mContext.getResources().getColor(R.color.text_ys));
            iv_logo.setVisibility(View.INVISIBLE);
        }else if (islayout==1){
            tv_title_pd.setText(item.channel_name);
            if (item.program_name!=null){
                tv_jm_name.setText(item.program_name);
                tv_jm_name.setVisibility(View.VISIBLE);
            }else{
                tv_jm_name.setVisibility(View.GONE);
            }
//            Glide.with(mContext).load("http://"+ Constant.new_ip+item.channel_url).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv_logo) {
//                @Override
//                protected void setResource(Bitmap resource) {
//                    RoundedBitmapDrawable circularBitmapDrawable =
//                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
//                    circularBitmapDrawable.setCircular(true);
//                    iv_logo.setImageDrawable(circularBitmapDrawable);
//                }
//            });





            //图片
            Picasso.with(mContext).load("http://"+ Constant.new_ip+item.channel_url).transform(new RoundTransform(mContext,false)).into(iv_logo);
//            Picasso.with(mContext).load("http://"+ Constant.new_ip+item.channel_url).placeholder(R.mipmap.ic_launcher).transform(new RoundTransform(mContext,true)).into(iv_bg);

        }else{

            tv_title_pd.setText(item.program_name);
            tv_jm_name.setText(item.channel_name);
            tv_jm_name.setTextColor(mContext.getResources().getColor(R.color.text_ys));
            Picasso.with(mContext).load("http://"+ Constant.new_ip+item.channel_url).transform(new RoundTransform(mContext,false)).into(iv_logo);
        }

        tv_ssl.setText(item.rat);
        tv_ssfe.setText(item.market);
    }

    public void setSort(boolean ispx){
        this.ispx=ispx;
    }

}
