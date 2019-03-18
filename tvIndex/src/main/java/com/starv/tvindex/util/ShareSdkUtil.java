package com.starv.tvindex.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.starv.tvindex.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ReflectablePlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


/**
 * Created by weizhi on 2016/7/7.
 */
public class ShareSdkUtil {

    private ShareListener shareListener;
//    是不是自定义
    public enum Special {
        YES, NO;
    }
    public void showShare(){


        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        oks. setShareContentCustomizeCallback (
                new ShareContentDifPlatform()) ;

//        oks.setCallback(new PlatformActionListener() {
//            @Override
//            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//
//                shareListener.onCompleteShareListener();
//
//            }
//
//            @Override
//            public void onError(Platform platform, int i, Throwable throwable) {
//
//            }
//
//            @Override
//            public void onCancel(Platform platform, int i) {
//                shareListener.ononCancelShareListener();
//            }
//        });

        oks.show(m_obj_context);

    }

    public static ShareSdkUtil getInstance(Context context,Special special,ShareListener shareListener){
        m_obj_context = context;

        if(null == instance){
            instance = new ShareSdkUtil(special,shareListener);
        }
        return instance;
    }


    public ShareSdkUtil setValues(String title,Bitmap icon,String url,String content){
        m_str_title = title;
        m_str_shareIcon = icon;
        m_str_shareUrl = url;
        m_str_shareContent = content;
        return this;
    }






    private OnekeyShare oks = null;
    private static ShareSdkUtil instance;
    private static Context m_obj_context;

    //share分享的数据
    private Bitmap m_str_shareIcon;//图标地址
    private String m_str_shareUrl;//点击后跳转地址
    private String m_str_shareContent;//描述内容


    //share标题
    private String m_str_title = "";




    Platform.ShareParams sp_specil = new Platform.ShareParams();
    private ShareSdkUtil(Special special,ShareListener shareListener){
        oks = new OnekeyShare();
        this.shareListener=shareListener;
    }


    /**
     * 快捷分享项目现在添加为不同的平台添加不同分享内容的方法。
     */
    public  class ShareContentDifPlatform  implements ShareContentCustomizeCallback {

        public  void onShare (Platform platform, Platform.ShareParams paramsToShare )  {
            Log.i("TAG","platName = " + platform.getName());

            if(QQ.NAME == platform.getName()){
                paramsToShare.setTitle(m_str_title);
                paramsToShare.setTitleUrl(m_str_shareUrl);
                paramsToShare.setImageData(m_str_shareIcon);
                paramsToShare.setText(m_str_shareContent);
            }else if(QZone.NAME == platform.getName()){
                paramsToShare.setTitle(m_str_title);
                paramsToShare.setTitleUrl(m_str_shareUrl);
                paramsToShare.setImageData(m_str_shareIcon);
                paramsToShare.setText(m_str_shareContent);
            }else if(Wechat.NAME == platform.getName()){
                paramsToShare.setShareType(Platform.SHARE_WEBPAGE);//非常重要：一定要设置分享属性
                paramsToShare.setTitle(m_str_title);
                paramsToShare.setTitleUrl(m_str_shareUrl);
                paramsToShare.setImageData(m_str_shareIcon);
                paramsToShare.setText(m_str_shareContent);
                paramsToShare.setUrl(m_str_shareUrl);

            }else if(WechatMoments.NAME == platform.getName()){
                paramsToShare.setShareType(Platform.SHARE_WEBPAGE); //非常重要：一定要设置分享属性
                paramsToShare.setTitle(m_str_shareContent);
                paramsToShare.setImageData(m_str_shareIcon);
                paramsToShare.setUrl(m_str_shareUrl);   //网友点进链接后，可以看到分享的详情
            }else if(SinaWeibo.NAME == platform.getName()){
                paramsToShare.setShareType(Platform.SHARE_WEBPAGE); //非常重要：一定要设置分享属性
                paramsToShare.setTitle(m_str_title);
                paramsToShare.setTitleUrl(m_str_shareUrl);
                paramsToShare.setImageData(m_str_shareIcon);
                paramsToShare.setText(m_str_shareContent);
                paramsToShare.setUrl(m_str_shareUrl);
            }

        }
    }

    /**************************自定义控件的响应******************************/
    public void WxCircleShare(){
        sp_specil.setShareType(Platform.SHARE_WEBPAGE); //非常重要：一定要设置分享属性
        sp_specil.setTitle(m_str_shareContent);
        sp_specil.setImageData(m_str_shareIcon);
        sp_specil.setUrl(m_str_shareUrl);   //网友点进链接后，可以看到分享的详情

        Platform wxCircle = ShareSDK.getPlatform (WechatMoments.NAME);
        wxCircle.setPlatformActionListener (new PlatformActionListener(){

            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        }); // 设置分享事件回调
// 执行图文分享
        wxCircle.share(sp_specil);
    }
    public void WxShare(){
        sp_specil.setShareType(Platform.SHARE_WEBPAGE);//非常重要：一定要设置分享属性
        sp_specil.setTitle(m_str_title);
        sp_specil.setTitleUrl(m_str_shareUrl);
        sp_specil.setImageData(m_str_shareIcon);
        sp_specil.setText(m_str_shareContent);
        sp_specil.setUrl(m_str_shareUrl);

        Platform wx = ShareSDK.getPlatform (Wechat.NAME);
        wx.setPlatformActionListener (new PlatformActionListener(){

            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        }); // 设置分享事件回调
// 执行图文分享
        wx.share(sp_specil);
    }

    public void QQShare(){
        sp_specil.setTitle(m_str_title);
        sp_specil.setTitleUrl(m_str_shareUrl);
        sp_specil.setImageData(m_str_shareIcon);
        sp_specil.setText(m_str_shareContent);

        Platform qq = ShareSDK.getPlatform (QQ.NAME);
        qq.setPlatformActionListener (new PlatformActionListener(){

            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        }); // 设置分享事件回调
// 执行图文分享
        qq.share(sp_specil);
    }

    public void QZoneShare(){
        sp_specil.setTitle(m_str_title);
        sp_specil.setTitleUrl(m_str_shareUrl);
        sp_specil.setImageData(m_str_shareIcon);
        sp_specil.setText(m_str_shareContent);

        Platform qzone = ShareSDK.getPlatform (QZone.NAME);
        qzone.setPlatformActionListener (new PlatformActionListener(){

            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        }); // 设置分享事件回调
// 执行图文分享
        qzone.share(sp_specil);
    }

    public void WBShare(){
        sp_specil.setTitle(m_str_title);
        //paramsToShare.setTitleUrl(m_str_shareUrl+m_str_shareContent);
        //paramsToShare.setImageUrl("http://192.168.10.70:8080/weizhi/36.png");
        //paramsToShare.setImageUrl(m_str_shareIcon);
        sp_specil.setText(m_str_shareContent+m_str_shareUrl);

        Platform wb = ShareSDK.getPlatform (SinaWeibo.NAME);
        wb.setPlatformActionListener (new PlatformActionListener(){

            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        }); // 设置分享事件回调
// 执行图文分享
        wb.share(sp_specil);
    }

    public interface  ShareListener{
        void onCompleteShareListener();
        void ononCancelShareListener();
    }
    /************************************************************************/

}
