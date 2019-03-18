package com.starv.tvindex.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import com.starv.tvindex.R;

import org.w3c.dom.Text;

/**
 * Created by ysq on 2019/2/25.
 */

public class ASadapter extends BaseAdapter{

    private Context context;
    private List<Integer> mlist;
    private int height;
    private int[] res = {
        R.mipmap.bg_content2,
            R.mipmap.bg_content1,
            R.mipmap.bg_content3,
            R.mipmap.bg_content4
    };

    public ASadapter(Context context,List<Integer> mlist,int height){
        this.context=context;
        this.mlist=mlist;
        this.height=height;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHodler viewHodler=null;

        if (view==null){

            view = View.inflate(context,R.layout.item_yycj,null);
            viewHodler = new ViewHodler();
            viewHodler.iv_bg= (ImageView) view.findViewById(R.id.iv_bg);
            viewHodler.tv_content_text= (TextView) view.findViewById(R.id.tv_content_text);
            view.setTag(viewHodler);

        }else{
            viewHodler = (ViewHodler) view.getTag();
        }

        viewHodler.iv_bg.setImageResource(res[i]);
        switch (i){
            case 0:
                viewHodler.tv_content_text.setText("应用场景【一】");
                break;
            case 1:
                viewHodler.tv_content_text.setText("应用场景【二】");
                break;
            case 2:
                viewHodler.tv_content_text.setText("应用场景【三】");
                break;
            case 3:
                viewHodler.tv_content_text.setText("应用场景【四】");
                break;
        }

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewHodler.iv_bg.getLayoutParams();
        layoutParams.height=(getAndroiodScreenProperty()-height)/4;
        Log.e("TAG","高度:::"+(getAndroiodScreenProperty()-height));
        viewHodler.iv_bg.setLayoutParams(layoutParams);

        return view;
    }


    public int getAndroiodScreenProperty() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;       // 屏幕高度（像素）

        return height;

    }

    class ViewHodler{
        ImageView iv_bg;
        TextView tv_content_text;
    }


}
