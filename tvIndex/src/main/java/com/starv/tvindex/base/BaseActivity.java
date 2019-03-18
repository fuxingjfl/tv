package com.starv.tvindex.base;

import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;



public class BaseActivity extends FragmentActivity {
	/*
	 * public HomeActivity() {
	 * 
	 * }
	 * 
	 * public HomeActivity(View view) { super(view); }
	 */

    //设置Window窗口的透明度
    public void setWindowTranslucence(double d){
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha=(float) d;
        window.setAttributes(attributes);
    }

}
