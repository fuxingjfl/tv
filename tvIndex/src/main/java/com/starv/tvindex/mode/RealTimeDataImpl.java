package com.starv.tvindex.mode;

import android.util.Log;


import com.starv.tvindex.entity.Info;
import com.starv.tvindex.entity.RealTimeData;
import com.starv.tvindex.okhttpUtils.HttpConstants;
import com.starv.tvindex.okhttpUtils.OkHttpClientManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class RealTimeDataImpl implements DataModel,OkHttpClientManager.HttpCallback {
	private onDataListener onDataListener;
	@Override
	public void onSuccess(String result, int code) {
		// TODO Auto-generated method stub
		
//		Log.e("TAG", "实时列表数据=="+result);
		
		try {
			switch(code){
			case HttpConstants.search_news01:

				JSONObject jo1 = new JSONObject(result);

				if(jo1.getInt("code")==200){

					JSONArray data = jo1.getJSONArray("data");
					Info info = new Info();
					info.realTimeDatas = new ArrayList<>();
					for (int i=0;i<data.length();i++){

						JSONObject joo1 = data.getJSONObject(i);
						RealTimeData realTimeData = new RealTimeData();
						realTimeData.channel_code=joo1.getString("channel_code");
						realTimeData.channel_group=joo1.getString("channel_group");
						realTimeData.channel_name=joo1.getString("channel_name");
						realTimeData.channel_url=joo1.getString("channel_url");
						realTimeData.market=joo1.getString("market");
						realTimeData.program_name=joo1.getString("program_name");
						realTimeData.rat=joo1.getString("rat");
						info.realTimeDatas.add(realTimeData);

					}
					onDataListener.onSuccessListener(info,code);

				}else{
					onDataListener.onFailureCodeListener(jo1.getString("errorMessage"), code);
				}
				
				break;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("TAG","列表解析失败1");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("TAG","列表解析失败2");
		}
		
	}

	@Override
	public void onStart(int code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFailure(int code) {
		// TODO Auto-generated method stub
		onDataListener.onFailureListener("请求失败了");
	}

	@Override
	public void getData(String url, int code, String json,
						onDataListener onDataListener) {
		// TODO Auto-generated method stub
		this.onDataListener=onDataListener;
		OkHttp()._getOkHttp(url, code, json, this,5000);
	}
	public OkHttpClientManager OkHttp() {
		return OkHttpClientManager.getInstance();
	}

}
