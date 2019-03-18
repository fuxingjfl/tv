package com.starv.tvindex.Presenter;

import android.os.Handler;

import com.starv.tvindex.entity.Info;
import com.starv.tvindex.mode.DataModel;
import com.starv.tvindex.mode.RealTimeDataImpl;
import com.starv.tvindex.mode.VarietyDetailsImpl;
import com.starv.tvindex.okhttpUtils.HttpConstants;


public class VarietyDetailsPresenter {
	private Handler handler;

	private VarietyDetailsView varietyDetailsView;

	private DataModel dataModel;

	public VarietyDetailsPresenter(VarietyDetailsView varietyDetailsView){
		this.varietyDetailsView = varietyDetailsView;
		handler = new Handler();
		dataModel = new VarietyDetailsImpl();
	}
	
	public void getDisposeData(){
		dataModel.getData(varietyDetailsView.getVDUrl(), varietyDetailsView.getVDCode(), varietyDetailsView.getVDBody(), new DataModel.onDataListener() {
			
			@Override
			public void onSuccessListener(Object string, int code) {
				// TODO Auto-generated method stub
				
				switch(code){
				case HttpConstants.search_news01:
					
					final Info info =(Info) string;
					
					handler .post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub

							varietyDetailsView.getVDData(info);
							
						}
					});
					break;
				}
				
			}
			
			@Override
			public void onFailureListener(final String log) {
				// TODO Auto-generated method stub
				handler .post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						varietyDetailsView.getVDataFailureMsg(log);
					}
				});
			}
			
			@Override
			public void onFailureCodeListener(final String log, int code) {
				// TODO Auto-generated method stub 
				handler .post(new Runnable() {
	
					@Override
					public void run() {
						// TODO Auto-generated method stub
						varietyDetailsView.getVDataFailureMsg(log);
					}
				});
			}
		});
	}
	
}
