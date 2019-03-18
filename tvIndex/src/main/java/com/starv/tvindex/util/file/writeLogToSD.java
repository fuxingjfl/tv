package com.starv.tvindex.util.file;

import android.os.Environment;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by weizhi on 2016/3/14.
 */
public class writeLogToSD {

    String basePath = Environment.getExternalStorageDirectory().getPath();

    FileOutputStream fos = null;
    public  writeLogToSD(String fileName){
        try {
            fos = new FileOutputStream(FileUtil.createNewFile(basePath + "/" + fileName));
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("test","createfile error = " + e.toString());
        }
    }

    public void writeFileToSdCard(String buffer){
        try {
            fos.write(buffer.getBytes());
            fos.write("\r\n".getBytes());
        } catch (Exception e) {
            e.printStackTrace();

            try {
                fos.close();
            } catch (IOException e1) {
                e1.printStackTrace();

            }
        }
    }
}
