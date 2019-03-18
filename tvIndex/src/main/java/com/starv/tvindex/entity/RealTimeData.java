package com.starv.tvindex.entity;

import java.io.Serializable;

/**
 * Created by ysq on 2019/2/28.
 */

public class RealTimeData implements Comparable<RealTimeData>, Serializable {

    public String channel_code;
    public String channel_group;
    public String channel_name;
    public String channel_url;
    public String market;
    public String program_name;
    public String rat;

    @Override
    public int compareTo(RealTimeData realTimeData) {




        if(Float.parseFloat(this.rat.split("%")[0])>Float.parseFloat(realTimeData.rat.split("%")[0])){//正序
            return 1;
        }
        else if(Float.parseFloat(this.rat.split("%")[0])<Float.parseFloat(realTimeData.rat.split("%")[0])){//倒序
            return -1;
        }
        else{//相等
            return 0;
        }

    }
}
