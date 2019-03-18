package com.starv.tvindex.Presenter;

import android.os.Handler;

import com.starv.tvindex.entity.Info;
import com.starv.tvindex.mode.DataModel;
import com.starv.tvindex.mode.DiagramImpl;
import com.starv.tvindex.mode.RealTimeDataImpl;
import com.starv.tvindex.okhttpUtils.HttpConstants;


public class DiagramPresenter {
	private Handler handler;

	private DiagramView diagramView;

	private DataModel dataModel;

	public DiagramPresenter(DiagramView diagramView){
		this.diagramView = diagramView;
		handler = new Handler();
		dataModel = new DiagramImpl();
	}
	
	public void getDisposeData(){
		dataModel.getData(diagramView.getDUrl(), diagramView.getDCode(), diagramView.getDBody(), new DataModel.onDataListener() {
			
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

							diagramView.getDData(info);
							
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
						diagramView.getDDataFailureMsg(log);
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
						diagramView.getDDataFailureMsg(log);
					}
				});
			}
		});
	}
	
}
