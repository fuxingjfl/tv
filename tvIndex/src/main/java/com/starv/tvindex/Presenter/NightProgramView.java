package com.starv.tvindex.Presenter;


import com.starv.tvindex.entity.Info;

public interface NightProgramView {

	String getNPUrl();
	
	int getNPCode();
	
	String getNPBody();
	
	//数据返回的错误码
	void getNPDataFailureMsg(String msg);
	
	void getNPData(Info info);
	
}
