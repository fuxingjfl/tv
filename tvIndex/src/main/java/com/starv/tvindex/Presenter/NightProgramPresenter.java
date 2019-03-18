package com.starv.tvindex.Presenter;

import android.os.Handler;

import com.starv.tvindex.entity.Info;
import com.starv.tvindex.mode.DataModel;
import com.starv.tvindex.mode.NightProgramImpl;
import com.starv.tvindex.mode.RealTimeDataImpl;
import com.starv.tvindex.okhttpUtils.HttpConstants;


public class NightProgramPresenter {
	private Handler handler;

	private NightProgramView nightProgramView;

	private DataModel dataModel;

	public NightProgramPresenter(NightProgramView nightProgramView){
		this.nightProgramView = nightProgramView;
		handler = new Handler();
		dataModel = new NightProgramImpl();
	}
	
	public void getDisposeData(){
		dataModel.getData(nightProgramView.getNPUrl(), nightProgramView.getNPCode(), nightProgramView.getNPBody(), new DataModel.onDataListener() {
			
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

							nightProgramView.getNPData(info);
							
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
						nightProgramView.getNPDataFailureMsg(log);
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
						nightProgramView.getNPDataFailureMsg(log);
					}
				});
			}
		});
	}
	
}
