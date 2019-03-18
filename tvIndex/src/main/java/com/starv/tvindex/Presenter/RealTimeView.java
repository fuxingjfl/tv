package com.starv.tvindex.Presenter;


import com.starv.tvindex.entity.Info;

public interface RealTimeView {

	String getRTUrl();
	
	int getRTCode();
	
	String getRTBody();
	
	//数据返回的错误码
	void getRTDataFailureMsg(String msg);
	
	void getRTData(Info info);
	
}
