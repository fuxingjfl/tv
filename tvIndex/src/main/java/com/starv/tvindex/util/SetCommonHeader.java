package com.starv.tvindex.util;

import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.starv.tvindex.R;
import com.starv.tvindex.TvindexApp;
import com.starv.tvindex.util.view.IconView;
import com.starv.tvindex.util.view.MyImageView;

/**
 * Created by weizhi on 2016/8/15.
 */
public class SetCommonHeader {
    private static SetCommonHeader instance;
    private SetCommonHeader(){}

    public static SetCommonHeader getInstance(){
        if(null == instance){
            instance = new SetCommonHeader();
        }
        return instance;
    }




    //标题
    private TextView m_obj_header_title;
    //标题左侧1号图标 FontAwesomeText
    private IconView m_obj_header_left_first_icon;
    //标题栏左侧1号icon
    private ImageView m_obj_header_left_first_img;
    //标题栏右侧1、2、3号FontAwesomeText
    private IconView m_obj_header_right_first_icon;
    private IconView m_obj_header_right_second_icon;
    private IconView m_obj_header_right_third_icon;


//    区域显示
    private TextView m_obj_area;

    /**
     * 初始化
     * */
    public SetCommonHeader init(View view){

        m_obj_header_title = (TextView) view.findViewById(R.id.id_common_header_title);
        m_obj_header_left_first_icon = (IconView) view.findViewById(R.id.id_common_header_left_icon);
        m_obj_header_left_first_img = (ImageView) view.findViewById(R.id.id_common_header_left_img);


        m_obj_header_right_first_icon = (IconView) view.findViewById(R.id.id_common_header_right_firsticon);
        m_obj_header_right_second_icon = (IconView) view.findViewById(R.id.id_common_header_right_secondicon);
        m_obj_header_right_third_icon = (IconView) view.findViewById(R.id.id_common_header_right_thirdicon);

        m_obj_area = (TextView) view.findViewById(R.id.id_area);
        return this;
    }


    /**
     * 设置标题栏标题
     * */
    public SetCommonHeader setTitle(String title){
        m_obj_header_title.setVisibility(View.VISIBLE);
        m_obj_header_title.setText(title);
        return this;
    }
    /**
     *设置标题栏左侧  img
     * resId是图片资源
     * */
    public SetCommonHeader setLeftFirstImg(int resId){
        m_obj_header_left_first_img.setVisibility(View.VISIBLE);
        m_obj_header_left_first_img.setImageResource(resId);

        return this;
    }


    /**
     * 设置标题栏左侧 icon
     * @param res 显示的资源
     * @param lister 点击响应事件
     * */
    public SetCommonHeader setLeftFirstIcon(String res,View.OnClickListener lister){
        m_obj_header_left_first_icon.setVisibility(View.VISIBLE);
        m_obj_header_left_first_icon.setText(res);
        if(null != lister){
            m_obj_header_left_first_icon.setOnClickListener(lister);
        }
        return this;
    }

    /**
     * 设置标题栏右侧 区域显示
     * @param area 显示的资源
     * @param lister 点击响应事件
     * */
    public SetCommonHeader setAreaTextAndClick(String area,View.OnClickListener lister){
        setAreaText(area);
        if(null != lister){
            m_obj_area.setOnClickListener(lister);
        }
        return this;
    }
    /**
     * 代码布局
     * android:ellipsize="marquee"
     android:focusable="true"
     android:marqueeRepeatLimit="marquee_forever"
     android:focusableInTouchMode="true"
     android:scrollHorizontally="true"
     * */
    public SetCommonHeader setAreaText(String area){
        m_obj_area.setVisibility(View.VISIBLE);
        m_obj_area.setText(area);
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        m_obj_area.measure(w, h);
        int height =m_obj_area.getMeasuredHeight();
        int width =m_obj_area.getMeasuredWidth();
//        Log.i("test","width = " + m_obj_area.getMeasuredWidth());
//        4.1版本上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if(m_obj_area.getMeasuredWidth() >= m_obj_area.getMaxWidth()){
                //重复设置可能会导致跑马灯效果失效
//                Log.i("test","set scroll");
                m_obj_area.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                m_obj_area.setFocusable(true);
                m_obj_area.setMarqueeRepeatLimit(-1);//-1无限循环
                m_obj_area.setFocusableInTouchMode(true);
                m_obj_area.setHorizontallyScrolling(true);
                m_obj_area.requestFocus();
                m_obj_area.setSingleLine();

            }
        }
        return this;
    }


    /**
     * 设置标题栏右侧 第一个icon
     * @param res 显示的资源
     * @param lister 点击响应事件
     * */
    public SetCommonHeader setRightFirstIcon(String res,View.OnClickListener lister){
        m_obj_header_right_first_icon.setVisibility(View.VISIBLE);
        m_obj_header_right_first_icon.setText(res);
        if(null != lister){
            m_obj_header_right_first_icon.setOnClickListener(lister);
        }
        return this;
    }

    /**
     * 设置标题栏右侧 第二个icon
     * @param res 显示的资源
     * @param lister 点击响应事件
     * */
    public SetCommonHeader setRightSecondIcon(String res,View.OnClickListener lister){
        m_obj_header_right_second_icon.setVisibility(View.VISIBLE);
        m_obj_header_right_second_icon.setText(res);
        if(null != lister){
            m_obj_header_right_second_icon.setOnClickListener(lister);
        }
        return this;
    }

    /**
     * 设置标题栏右侧 第三个icon
     * @param res 显示的资源
     * @param lister 点击响应事件
     * */
    public SetCommonHeader setRightThirdIcon(String res,View.OnClickListener lister){
        m_obj_header_right_third_icon.setVisibility(View.VISIBLE);
        m_obj_header_right_third_icon.setText(res);
        if(null != lister){
            m_obj_header_right_third_icon.setOnClickListener(lister);
        }
        return this;
    }


//    webview页面刷新的时候  TextView焦点失效的解决方法
    public void getTextViewFoucs(){
        if (null == m_obj_area){
            return;
        }
        m_obj_area.setFocusable(true);
        m_obj_area.requestFocus();
    }
}
