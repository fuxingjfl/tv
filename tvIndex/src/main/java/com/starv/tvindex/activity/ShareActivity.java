package com.starv.tvindex.activity;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.starv.tvindex.R;
import com.starv.tvindex.base.BaseActivity;
import com.starv.tvindex.util.Constant;
import com.starv.tvindex.util.ShareSdkUtil;
import com.starv.tvindex.util.dialog.ShareDialog;

/**
 * Created by ysq on 2019/2/27.
 */

public class ShareActivity extends BaseActivity {

    private ImageView iv_left_btn,iv_right_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        iv_left_btn= (ImageView) findViewById(R.id.iv_left_btn);
        iv_right_btn= (ImageView) findViewById(R.id.iv_right_btn);
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
                    ShareSdkUtil.getInstance(ShareActivity.this, ShareSdkUtil.Special.NO,shareListener).setValues(getResources().getString(R.string.app_name),  BitmapFactory.decodeResource(getResources(),R.mipmap.logo),Constant.apkUrl,"电视媒介星红榜！网罗全国收视大数据，洞察传媒纵深新价值！");
                    ShareSdkUtil.getInstance(ShareActivity.this, ShareSdkUtil.Special.NO,shareListener).showShare();
//                    ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO).setValues(getResources().getString(R.string.app_name), R.mipmap.logo+"",Constant.apkUrl,"电视媒介星红榜！网罗全国收视大数据，洞察传媒纵深新价值！");
//                    ShareSdkUtil.getInstance(getApplicationContext(), ShareSdkUtil.Special.NO).showShare();
                    break;
            }
        }
    };

    private ShareSdkUtil.ShareListener shareListener = new ShareSdkUtil.ShareListener() {
        @Override
        public void onCompleteShareListener() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    Toast.makeText(ShareActivity.this,"分享成功!",Toast.LENGTH_LONG).show();
                    show("分享成功");
                }
            });
        }

        @Override
        public void ononCancelShareListener() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    Toast.makeText(ShareActivity.this,"分享取消!",Toast.LENGTH_LONG).show();
                    show("分享取消");
                }
            });
        }
    };

    public void show(String text){
        ShareDialog dialog = new ShareDialog(ShareActivity.this).builder()
                .setTitle(text)
                .setNegativeButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

        dialog.show();
    }

}
