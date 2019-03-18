package com.starv.tvindex.Presenter;


import com.starv.tvindex.entity.Info;

public interface VarietyDetailsView {

	String getVDUrl();
	
	int getVDCode();
	
	String getVDBody();
	
	//数据返回的错误码
	void getVDataFailureMsg(String msg);
	
	void getVDData(Info info);
	
}
