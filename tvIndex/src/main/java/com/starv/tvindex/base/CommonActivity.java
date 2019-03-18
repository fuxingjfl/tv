//package com.starv.tvindex.base;
//
//import android.support.v4.app.FragmentActivity;
//import android.widget.Toast;
//
//
//
//public class CommonActivity extends SlideMenuActivity {
//	/*
//	 * public HomeActivity() {
//	 *
//	 * }
//	 *
//	 * public HomeActivity(View view) { super(view); }
//	 */
//	@Override
//	public void onBackPressed() {
//		boolean isReturn = showConfirmToast();
//		if (isReturn)
//			return;
//
//
//		// SharePreferenceUtils.getInstance(getApplicationContext()).clearUser();
//		super.onBackPressed();
//	}
//
//	private long mExitTime;
//
//	public boolean showConfirmToast() {
//		long secondTime = System.currentTimeMillis();
//		if (secondTime - mExitTime > 2000) {
//			Toast.makeText(this, "再按一次退出程序",
//					Toast.LENGTH_SHORT).show();
//			mExitTime = secondTime;
//
//			return true;
//
//		}
//		return false;
//	}
//}
