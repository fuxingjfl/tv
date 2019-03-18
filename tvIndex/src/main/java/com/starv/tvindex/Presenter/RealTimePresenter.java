package com.starv.tvindex.Presenter;

import android.os.Handler;

import com.starv.tvindex.entity.Info;
import com.starv.tvindex.mode.DataModel;
import com.starv.tvindex.mode.RealTimeDataImpl;
import com.starv.tvindex.okhttpUtils.HttpConstants;


public class RealTimePresenter {
	private Handler handler;

	private RealTimeView realTimeView;

	private DataModel dataModel;

	public RealTimePresenter(RealTimeView realTimeView){
		this.realTimeView = realTimeView;
		handler = new Handler();
		dataModel = new RealTimeDataImpl();
	}
	
	public void getDisposeData(){
		dataModel.getData(realTimeView.getRTUrl(), realTimeView.getRTCode(), realTimeView.getRTBody(), new DataModel.onDataListener() {
			
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

							realTimeView.getRTData(info);
							
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
						realTimeView.getRTDataFailureMsg(log);
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
						realTimeView.getRTDataFailureMsg(log);
					}
				});
			}
		});
	}
	
}
