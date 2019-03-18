package com.starv.version;

import com.starv.version.entry.AppVersionInfo;

import java.util.HashMap;

/**
 * Created by sunsiyuan on 2017/10/10.
 */

public interface CheckVersionCallBack {
    void callBack(AppVersionInfo info);
}
