package com.starv.version.util.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * 字符串处理工具类
 * @author jiangshihua 2014年8月25日
 *
 */
public class StringUtils {

    /**
     * 从流中读取字符串
     * @param input
     * @return
     * @throws IOException
     */
    public static String readString(InputStream input) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;    
        while((len=(input.read(buffer))) != -1){  
            baos.write(buffer,0,len);    
        }
        String retStr = new String(baos.toByteArray());
        input.close();
        return retStr;
    }
}
