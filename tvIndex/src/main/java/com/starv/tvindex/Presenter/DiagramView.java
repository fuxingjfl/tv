package com.starv.tvindex.Presenter;


import com.starv.tvindex.entity.Info;

public interface DiagramView {

	String getDUrl();
	
	int getDCode();
	
	String getDBody();
	
	//数据返回的错误码
	void getDDataFailureMsg(String msg);
	
	void getDData(Info info);
	
}
