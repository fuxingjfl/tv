package com.starv.tvindex.util.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.starv.tvindex.R;
import com.starv.tvindex.TvindexApp;
//http://blog.csdn.net/anyfive/article/details/50455256  iconfont使用
public class IconView extends TextView {
    private int type = 0;
    public IconView(Context context) {
        super(context);
        init();
    }
    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.iconFont);
        type = typedArray.getInt(R.styleable.iconFont_iconType,0);
        init();
    }
    public IconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
//        设置字体图标
        this.setTypeface(TvindexApp.getInstance().getIconTypeFace(type));
    }
}