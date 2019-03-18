package com.starv.version;

/**
 * Created by sunsiyuan on 2018/2/5.
 */

public interface OnProgressListener {
    /**
     * 下载进度
     *
     * @param fraction 已下载/总大小
     */
    void onProgress(float fraction);
}
