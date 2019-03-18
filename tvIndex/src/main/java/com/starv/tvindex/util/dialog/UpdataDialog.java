package com.starv.tvindex.util.dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.starv.tvindex.R;


public class UpdataDialog {
	private Context context;
	private Dialog dialog;
	private TextView tv_ts,tv_msg,tv_shgx,tv_xzgx;
	private Display display;
	private boolean showTitle = false;
	private boolean showMsg = false;
	private boolean showPosBtn = false;
	private boolean showNegBtn = false;
	private LinearLayout lLayout_bg;
	//private TextView m_obj_warning;


	public UpdataDialog(Context context) {
		this.context = context;
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
	}

	public UpdataDialog builder() {
		// 获取Dialog布局
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_layout, null);
		tv_ts= (TextView) view.findViewById(R.id.tv_ts);
		tv_msg= (TextView) view.findViewById(R.id.tv_msg);
		tv_shgx= (TextView) view.findViewById(R.id.tv_shgx);
		tv_xzgx= (TextView) view.findViewById(R.id.tv_xzgx);
		lLayout_bg= (LinearLayout) view.findViewById(R.id.lLayout_bg);
		// 定义Dialog布局和参数
		dialog = new Dialog(context, R.style.Dialog_FS);
		dialog.setContentView(view);
		dialog.setCanceledOnTouchOutside(false);

		// 调整dialog背景大小
		lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
				.getWidth() * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT));

		return this;

	}

	public UpdataDialog setTitle(String title) {
		showTitle = true;
		if ("".equals(title)) {
			tv_ts.setText("标题");
		} else {
			tv_ts.setText("发现新版本:"+title);
		}
		return this;
	}

	public UpdataDialog setMsg(String msg) {
		showMsg = true;

		if ("".equals(msg)) {
			tv_msg.setText("内容");
		} else {
			tv_msg.setText(msg);
//			Log.i("test","html = " + msg);
		}
		return this;
	}

	public UpdataDialog setCancelable(boolean cancel) {
		dialog.setCancelable(cancel);
		return this;
	}

	public UpdataDialog setPositiveButton(String text,
										  final OnClickListener listener) {
		showPosBtn = true;
		if ("".equals(text)) {
			tv_shgx.setText("稍后更新");
		} else {
			tv_shgx.setText(text);
		}
		tv_shgx.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onClick(v);
				dialog.dismiss();
			}
		});
		return this;
	}

	public UpdataDialog setNegativeButton(String text,
										  final OnClickListener listener) {
		showNegBtn = true;
		if ("".equals(text)) {
			tv_xzgx.setText("下载更新");
		} else {
			tv_xzgx.setText(text);
		}
		tv_xzgx.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onClick(v);
				dialog.dismiss();
			}
		});
		return this;
	}

	private void setLayout() {
		if (!showTitle && !showMsg) {
			tv_ts.setText("提示");
			tv_ts.setVisibility(View.VISIBLE);
		}

		if (showTitle) {
			tv_ts.setVisibility(View.VISIBLE);
		}

		if (showMsg) {
			tv_msg.setVisibility(View.VISIBLE);
		}

		if (!showPosBtn && !showNegBtn) {
			tv_shgx.setText("确定");
			tv_shgx.setVisibility(View.VISIBLE);
			tv_shgx.setBackgroundResource(R.drawable.alertdialog_single_selector);
			tv_shgx.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
		}

		if (showPosBtn && showNegBtn) {
			tv_shgx.setVisibility(View.VISIBLE);
			tv_shgx.setBackgroundResource(R.drawable.alertdialog_right_selector);
			tv_shgx.setVisibility(View.VISIBLE);
			tv_xzgx.setBackgroundResource(R.drawable.alertdialog_left_selector);

		}

		if (showPosBtn && !showNegBtn) {
			tv_shgx.setVisibility(View.VISIBLE);
			tv_shgx.setBackgroundResource(R.drawable.alertdialog_single_selector);
		}

		if (!showPosBtn && showNegBtn) {
			tv_xzgx.setVisibility(View.VISIBLE);
			tv_xzgx.setBackgroundResource(R.drawable.alertdialog_single_selector);
		}
	}

	public void show() {
		setLayout();
		dialog.show();
	}
}
