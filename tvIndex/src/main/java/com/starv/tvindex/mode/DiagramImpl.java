package com.starv.tvindex.mode;

import android.util.Log;

import com.starv.tvindex.entity.Diagram;
import com.starv.tvindex.entity.Info;
import com.starv.tvindex.entity.RealTimeData;
import com.starv.tvindex.entity.SpotData;
import com.starv.tvindex.okhttpUtils.HttpConstants;
import com.starv.tvindex.okhttpUtils.OkHttpClientManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DiagramImpl implements DataModel,OkHttpClientManager.HttpCallback {
	private onDataListener onDataListener;
	@Override
	public void onSuccess(String result, int code) {
		// TODO Auto-generated method stub
		
		Log.e("TAG", "频道轨迹数据=="+result);
		
		try {
			switch(code){
			case HttpConstants.search_news01:

				JSONObject jo1 = new JSONObject(result);

				if(jo1.getInt("code")==200){
					Info info = new Info();
					info.diagram = new Diagram();
					JSONObject data1 = jo1.getJSONObject("data");
					info.diagram.channel_url= data1.getString("channel_url");
					info.diagram.program_name=data1.getString("program_name");
					info.diagram.real_tv_img=data1.getString("real_tv_img");
					info.diagram.spotDatas = new ArrayList<>();
					JSONArray data2 = data1.getJSONArray("data");

					for (int i=0;i<data2.length();i++){

						SpotData spotData = new SpotData();
						JSONObject joo1 = data2.getJSONObject(i);
						spotData.channel_code = joo1.getString("channel_code");
						spotData.channel_name = joo1.getString("channel_name");
						spotData.market = joo1.getString("market");
						spotData.rat = joo1.getString("rat");
						spotData.time = joo1.getString("time");
						info.diagram.spotDatas.add(spotData);
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
