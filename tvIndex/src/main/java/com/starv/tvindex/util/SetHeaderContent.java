package com.starv.tvindex.util;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.starv.tvindex.R;
import com.starv.tvindex.util.view.IconView;


/**
 * Created by weizhi on 2016/4/19.
 */
public class SetHeaderContent {
    private static SetHeaderContent instance;
    private View m_obj_view = null;
    private ImageView m_obj_rightTitle;
    private ImageView m_obj_leftTitle;
    private SetHeaderContent(){

    }
    public static SetHeaderContent getInstance(){
        if(null == instance){
            instance = new SetHeaderContent();
        }
        return instance;
    }

    //标题
    private TextView m_obj_title;
    //左图标
    private IconView m_obj_leftIcon;
    //右图标
    private IconView m_obj_rightIcon1;


    //右图标
    private IconView m_obj_rightIcon_share;

    public void init(View view){
        m_obj_view = view;
        m_obj_title = (TextView) view.findViewById(R.id.id_header_title);
        m_obj_leftIcon = (IconView) view.findViewById(R.id.id_lefticon);
        m_obj_rightIcon1 = (IconView) view.findViewById(R.id.id_righticon);
        m_obj_rightTitle = (ImageView) view.findViewById(R.id.id_rightTitle);
        m_obj_leftTitle = (ImageView) view.findViewById(R.id.id_leftTitle);
        m_obj_rightIcon_share = (IconView) view.findViewById(R.id.id_righticon_share);
    }

    /**
     * 设置标题
     * @param title 标题
     * */
    public void setTitle(int title){
        m_obj_title.setText(title);
    }

    /**
     * 设置标题
     * @param title 标题
     * */
    public void setTitle(String title){
        m_obj_title.setText(title);
        m_obj_title.setVisibility(View.VISIBLE);
        //m_obj_title.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    /**
     * 设置标题
     * @param title 标题
     * */
    public void setTitle(String title,int color){
        m_obj_title.setText(title);
        m_obj_title.setTextColor(color);
    }

    /**
     * 设置左图标
     * */
    public void setLeftIcon(String resource,View.OnClickListener listener){
        m_obj_leftIcon.setText(resource);
        m_obj_leftIcon.setVisibility(View.VISIBLE);
        if(null != listener){
            m_obj_leftIcon.setOnClickListener(listener);
        }
    }

    /**
     * 设置右图标1
     * */
    public void setRightIcon(String resource,View.OnClickListener listener){
        m_obj_rightIcon1.setText(resource);
        m_obj_rightIcon1.setVisibility(View.VISIBLE);
        if(null != listener){
            m_obj_rightIcon1.setOnClickListener(listener);
        }
    }

    /**
     * 设置分享图标
     * */
    public void setShareRightIcon(View.OnClickListener listener){
        m_obj_rightIcon_share.setVisibility(View.VISIBLE);
        //m_obj_rightIcon_share.setIcon("fa-share-alt");
        if(null != listener){
            m_obj_rightIcon_share.setOnClickListener(listener);
        }
    }

    public IconView getShareView(){
        return m_obj_rightIcon_share;
    }

    /**
     * 设置左边图标题可见
     * */
    public void setLeftTitle(){
        m_obj_leftTitle.setVisibility(View.VISIBLE);
    }

    /**
     * 设置右边图标题可见
     * */
    public void setRightTitle(){
        m_obj_rightTitle.setVisibility(View.VISIBLE);
    }



}
