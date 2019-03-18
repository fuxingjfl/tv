package com.starv.tvindex.util;

import android.content.Context;
import android.util.Log;


import com.starv.tvindex.bean.updateMsg;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by weizhi on 2016/3/10.
 * pull解析器解析xml
 */
public class pullXmlUtil {
    updateMsg uMsg;
    private Context context;
    public pullXmlUtil(Context context){
        this.context = context;
    }


    public Object readXML(String content) throws Exception{
        InputStream is = getIs(content);
        //XmlPullParser pullParser = Xml.newPullParser();
        XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
        pullParser.setInput(is,"utf-8");
        uMsg = new updateMsg();
        String name = "";
        int eventType = pullParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
//            Log.i("choiceaccouttwo","name = " + pullParser.getName());
//            Log.i("choiceaccouttwo","text = " + pullParser.getText());
            //Log.i("choiceaccouttwo","eventType = " + pullParser.getEventType());
            if(eventType == XmlPullParser.START_DOCUMENT){

            }else if(eventType == XmlPullParser.START_TAG){
//                Log.i("choiceaccouttwo","start tag = " + pullParser.getName());
                name = pullParser.getName();
            }else if(eventType == XmlPullParser.TEXT){
//                Log.i("choiceaccouttwo","name = " + name);
                if(name.equals("version")){
                    uMsg.setVersion(pullParser.getText());
                }else if(name.equals("url")){
                    uMsg.setUrl(pullParser.getText());
                }else if(name.equals("description")){
                    uMsg.setDescription(pullParser.getText());

                }
                name = "";
//                Log.i("choiceaccouttwo","text = " + pullParser.getText());


            } else if(eventType == XmlPullParser.END_TAG){
//                Log.i("choiceaccouttwo","end tag = " + pullParser.getName());

            }else if(eventType == XmlPullParser.END_DOCUMENT){
                //Log.i("choiceaccouttwo","start tag = " + pullParser.getText() + pullParser.getName() + eventType);

            }
            eventType = pullParser.next();
        }
//        Log.i("choiceaccouttwo", "version = " + uMsg.getVersion());
//        Log.i("choiceaccouttwo", "url = " + uMsg.getUrl());
//        Log.i("choiceaccouttwo", "dec = " + uMsg.getDescription());
        return uMsg;
    }

    //测试
    private InputStream getIs(String content) {
        InputStream is = null;
//        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<information>\n" +
//                "\t<version>2.1.2</version>\n" +
//                "\t<url>http://189.etao189.com/f/v/wt_2.1.2.apk</url>\n" +
//                "\t<description>&lt;span style=\"color:#333333;font-family:Simsun;line-height:24px;background-color:#FFFFFF;\"&gt;我们发布了2.1.2版本，建议升级体验。&lt;/span&gt;&lt;br /&gt; &lt;br /&gt; &lt;span style=\"color:#333333;font-family:Simsun;line-height:24px;background-color:#FFFFFF;\"&gt;主要更新包括：&lt;/span&gt;&lt;br /&gt; &lt;span style=\"color:#333333;font-family:Simsun;line-height:24px;background-color:#FFFFFF;\"&gt;1.巡访报告支持拍摄视频；&lt;/span&gt;&lt;br /&gt; &lt;span style=\"color:#333333;font-family:Simsun;line-height:24px;background-color:#FFFFFF;\"&gt;2.修复其他一些BUG，提升性能；&lt;/span&gt;&lt;br /&gt; &lt;br /&gt; &lt;span style=\"color:#333333;font-family:Simsun;line-height:24px;background-color:#FFFFFF;\"&gt;更多新功能等待您的发现和体验！&lt;/span&gt;&lt;br /&gt; &lt;br /&gt; &lt;span style=\"color:#333333;font-family:Simsun;line-height:24px;background-color:#FFFFFF;\"&gt;您可以点击设置菜单“版本更新”进行升级。或者访问 http://189.m.etao189.com 下载重新安装。&lt;/span&gt;</description>\n" +
//                "</information>\n";
        String xml = content;

        try {
            is = new ByteArrayInputStream(xml.getBytes());

           //AssetManager assetManager = this.context.getResources().getAssets(); //获取管理assets目录资源的AssetManager引用
            //is = assetManager .open("update.xml"); //获取assets/data.txt资源

            //is.close();
        }catch (Exception e){
            e.printStackTrace();
        }
//        Log.i("choiceaccouttwo","is = " + is.toString());
        return  is;
    }
}
