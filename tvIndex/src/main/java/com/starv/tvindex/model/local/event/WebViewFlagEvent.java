package com.starv.tvindex.model.local.event;

import java.lang.reflect.Method;

/**
 * Created by weizhi on 2016/11/1.
 */
public class WebViewFlagEvent {

    /**
     * FLAG_AreaChange:地域修改
     * FLAG_loadTimeOut：网页加载超时
     * */
    public enum Flag{
        FLAG_AreaChange,FLAG_loadTimeOut
    }

    private Flag flag;
    public void setFlag(Flag flag){
        this.flag = flag;
    }
    public Flag getFlag(){
        return this.flag;
    }
}
