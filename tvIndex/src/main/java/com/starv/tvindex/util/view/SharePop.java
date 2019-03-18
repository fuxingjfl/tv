package com.starv.tvindex.util.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.starv.tvindex.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysq on 2018/10/25.
 */

public class SharePop extends PopupWindow {
    private int resId;
    private Context context;
    private LayoutInflater inflater;
    public View defaultView;
    private ShareSelectorListener shareSelectorListener;
    private ImageView iv_wx,iv_wx_py,iv_qq,iv_qq_py,iv_wb;
    private TextView tv_clean;

    public SharePop(Context context, int resId
            , ShareSelectorListener shareSelectorListener) {
        super(context);
        this.context = context;
        this.resId = resId;
        this.shareSelectorListener = shareSelectorListener;

        initPopupWindow();
    }

    public void initPopupWindow() {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        defaultView = inflater.inflate(this.resId, null);
        defaultView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        setContentView(defaultView);

        tv_clean= (TextView) defaultView.findViewById(R.id.tv_clean);
        iv_wx= (ImageView) defaultView.findViewById(R.id.iv_wx);
        iv_wx_py= (ImageView) defaultView.findViewById(R.id.iv_wx_py);
        iv_qq= (ImageView) defaultView.findViewById(R.id.iv_qq);
        iv_qq_py= (ImageView) defaultView.findViewById(R.id.iv_qq_py);
        iv_wb= (ImageView) defaultView.findViewById(R.id.iv_wb);
        tv_clean.setOnClickListener(onClickListener);
        iv_wx.setOnClickListener(onClickListener);
        iv_wx_py.setOnClickListener(onClickListener);
        iv_qq.setOnClickListener(onClickListener);
        iv_qq_py.setOnClickListener(onClickListener);
        iv_wb.setOnClickListener(onClickListener);


        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
		 setAnimationStyle(R.style.popwin_anim_style);
//        setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(android.R.color.transparent)));
        setFocusable(false);
        setOutsideTouchable(false);
        update();

    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_clean:
                    shareSelectorListener.onCancelListener();
                    break;
                case R.id.iv_wx:
                    shareSelectorListener.onShareSelectorListener("WX");
                    break;
                case R.id.iv_wx_py:
                    shareSelectorListener.onShareSelectorListener("WX_PY");
                    break;
                case R.id.iv_qq:
                    shareSelectorListener.onShareSelectorListener("QQ");
                    break;
                case R.id.iv_qq_py:
                    shareSelectorListener.onShareSelectorListener("QQ_PY");
                    break;
                case R.id.iv_wb:
                    shareSelectorListener.onShareSelectorListener("WB");
                    break;
            }
        }
    };




    public interface ShareSelectorListener {

        void onCancelListener();

        void onShareSelectorListener(String data);

    }

    /**
     * @return pop的View
     */
    public View getDefaultView() {
        return defaultView;
    }


}