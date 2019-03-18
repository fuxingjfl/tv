package com.starv.tvindex.util;

/**
 * Created by weizhi on 2016/5/26.
 */
public class Constant {


    public static String new_ip="tvindex.star-v.com.cn";

//     http://222.74.63.136:5000/index.html#/Contact  http://54.223.129.142:8080/versionH5/preview.html?a_id=50210f0be5894dd7bb72b3a3591969c7
    public static String apkUrl="http://tvindex.star-v.com.cn/share.html";


    public static String apkImgUrl="http://54.223.129.142:8080/upload/image/20190308/7728af5ad8984fb8b9f09afda482656e.png";

    //apk更新地址
    public static String updateUrl = "http://tvindex.m.star-v.com.cn/app/update.xml";


    //public static String homeUrl = "http://tvindex.m.star-v.com.cn/";
    //    public static String homeUrl = "http://122.144.168.27:8088/starv2/";
   public static String homeUrl = "http://tvindex.star-v.com.cn/";

    //实时数据
    public static String realDataUrl = homeUrl + "realtime/main.jhtml";
    //public static String realDataUrl = "http://192.168.10.58:8080/web-html/xzcs.html";

    //直播数据
    public static String liveDataUrl = homeUrl + "starv/main.jhtml";
    //public static String liveDataUrl = "http://192.168.10.58:8080/web-html/star-db.html";

    //智能电视数据
    public static String smartDataUrl = homeUrl + "smarttv/main.jhtml";
    // public static String smartDataUrl = "http://192.168.10.58:8080/web-html/star.html";

    //帮助
    public static String helpUrl = homeUrl + "help.jhtml";

    //返回
    public static String back_icon = "fa-chevron-left";
    //    help
    public static String help_icon = "fa-question-circle";

    public static String dataFile = "data";

//    是否第一次登陆
    public static String first_flag = "first";
    public static String first_yes = "yes";
    public static String first_no = "no";


//    存储版本号
    public static String app_version = "app_version";

    //存储区域版的城市
    public static String localData_area = "localData_area";
    public static String localData_areaId = "localData_areaId";


    //存储token
    public static String token = "token";
    //移动端型号
    public static String deviceType = "deviceType";


    //如果页面数据获取失败的默认值  总榜分享内容
    public static String icon_url = "http://tvindex.m.star-v.com.cn/app/images/s_logo.png";
    public static String content_desc = "网罗传媒大数据，引领智慧新生活。百城收视数据隆重发布，与您相约星红榜。";
    public static String jump_url = "http://tvindex.star-v.com.cn/";


    //区域版分享默认内容
    public static String local_icon_url = "http://tvindex.m.star-v.com.cn/app/images/s_logo.png";
    public static String local_content_desc = "网罗传媒大数据，引领智慧新生活。百城收视数据隆重发布，与您相约星红榜。";
    public static String local_jump_url = "http://tvindex.loc2.star-v.com.cn/";


    //侧滑菜单分享内容
    public static String slidemenu_icon_url = "http://tvindex.m.star-v.com.cn/app/images/s_logo.png";
    public static String slidemenu_local_content_desc = "网罗传媒大数据，引领智慧新生活。百城收视数据隆重发布，与您相约星红榜。";
    public static String slidemenu_local_jump_url = "http://tvindex.m.star-v.com.cn/app/download.html";


    //local星红榜的url
//    public static String loc_BaseUrl = "http://192.168.10.65/";
       public static String loc_BaseUrl ="http://tvindex.loc2.star-v.com.cn/";
//    public static String dynamicView_url = loc_BaseUrl + "viewingDynamics/viewingDynamics.shtml";
//    public static String myChannel_url = loc_BaseUrl + "channelMgr/channel.html";
//    public static String myProgram_url = loc_BaseUrl + "myprogram/index.html";
//    public static String login_url = loc_BaseUrl + "login.html";

    public static String dynamicView_url = loc_BaseUrl + "viewingDynamics/viewingDynamics.html";
    public static String myChannel_url = loc_BaseUrl + "channelMgr/channel.html";
    public static String myProgram_url = loc_BaseUrl + "myprogram/index.html";
    public static String login_url = loc_BaseUrl + "login.html";
    public static String area_choice = loc_BaseUrl + "selectCity.html?linkType=1";

    public static String my_url = loc_BaseUrl + "my.html";




//    各种状态
    public static String timeout_loapage = "timeout_loapage";//webview加载超时  重新加载页面状态


//    从localtab跳转到选择城市页面是的Activity的返回码
    public static int LocalTabActivity_toChoiceActivity = 0x001;





}
