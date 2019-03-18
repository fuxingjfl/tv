//package com.starv.tvindex.model.local.module.view.activity;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentTransaction;
//
//import com.starv.tvindex.model.local.module.view.fragment.loginPageFragment;
//
///**
// * Created by weizhi on 2016/8/22.
// */
//public class LoginPageActivity extends FragmentActivity {
//
//    private loginPageFragment mFragment;
//
//    @Override
//    protected void onCreate(Bundle arg0) {
//        super.onCreate(arg0);
//        onInitContent();
//    }
//    private void onInitContent(){
//        mFragment=(loginPageFragment) Fragment.instantiate(this,loginPageFragment.class.getName());
//        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
//        ft.add(android.R.id.content, mFragment);
//        ft.commit();
//    }
//
//}
