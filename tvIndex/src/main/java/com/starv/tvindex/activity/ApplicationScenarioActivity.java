package com.starv.tvindex.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.starv.tvindex.R;
import com.starv.tvindex.adapter.ASadapter;
import com.starv.tvindex.base.BaseActivity;
import com.starv.tvindex.util.Constant;
import com.starv.tvindex.util.ShareSdkUtil;
import com.starv.tvindex.util.dialog.ShareDialog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysq on 2019/2/25.
 */

public class ApplicationScenarioActivity extends BaseActivity {


//    private ListView lv_list;

//    private ASadapter aSadapter;
//    private List<Integer> mlist;

    private ImageView iv_left_btn,iv_right_btn;

    private RelativeLayout rl_title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as);
//        lv_list= (ListView) findViewById(R.id.lv_list);
        iv_left_btn= (ImageView) findViewById(R.id.iv_left_btn);
        iv_right_btn= (ImageView) findViewById(R.id.iv_right_btn);
        rl_title= (RelativeLayout) findViewById(R.id.rl_title);
//        mlist = new ArrayList<>();
//        for (int i=0;i<4;i++){
//            mlist.add(i);
//        }
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rl_title.getLayoutParams();
//        aSadapter = new ASadapter(ApplicationScenarioActivity.this,mlist,layoutParams.height);
//        lv_list.setAdapter(aSadapter);

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
                    ShareSdkUtil.getInstance(ApplicationScenarioActivity.this, ShareSdkUtil.Special.NO,shareListener).setValues(getResources().getString(R.string.app_name), BitmapFactory.decodeResource(getResources(),R.mipmap.logo),Constant.apkUrl,"电视媒介星红榜！网罗全国收视大数据，洞察传媒纵深新价值！");
                    ShareSdkUtil.getInstance(ApplicationScenarioActivity.this, ShareSdkUtil.Special.NO,shareListener).showShare();
//                    ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO).setValues(getResources().getString(R.string.app_name), R.mipmap.logo+"",Constant.apkUrl,"电视媒介星红榜！网罗全国收视大数据，洞察传媒纵深新价值！");
//                    ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO).showShare();
                    break;
            }
        }
    };

//    private int getStateBar2() {int  statusBarHeight = 0; Class c = null; try { c = Class.forName("com.android.internal.R$dimen"); Object obj = c.newInstance(); Field field = c.getField("status_bar_height"); int x = Integer.parseInt(field.get(obj).toString()); statusBarHeight = this.getResources().getDimensionPixelSize(x);} catch (Exception e) { e.printStackTrace(); }finally {
//        return statusBarHeight;
//    }
//    }


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private ShareSdkUtil.ShareListener shareListener = new ShareSdkUtil.ShareListener() {
        @Override
        public void onCompleteShareListener() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    Toast.makeText(ApplicationScenarioActivity.this,"分享成功!",Toast.LENGTH_LONG).show();
                    show("分享成功");
                }
            });
        }

        @Override
        public void ononCancelShareListener() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    Toast.makeText(ApplicationScenarioActivity.this,"分享取消!",Toast.LENGTH_LONG).show();
                    show("分享取消");
                }
            });
        }
    };

    public void show(String text){
        ShareDialog dialog = new ShareDialog(ApplicationScenarioActivity.this).builder()
                .setTitle(text)
                .setNegativeButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

        dialog.show();
    }

}
