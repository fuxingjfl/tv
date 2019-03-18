package com.starv.tvindex.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ysq on 2019/2/28.
 */

public class Diagram implements Serializable{

    public String channel_url;
    public String program_name;
    public String real_tv_img;
    public List<SpotData> spotDatas;


}
