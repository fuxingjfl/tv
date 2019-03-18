package com.starv.tvindex.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ysq on 2019/2/28.
 */

public class Info implements Serializable{

    public List<RealTimeData> realTimeDatas;
    public Diagram diagram;
    public String dt;

}
