//package com.starv.tvindex.model.local.module.view.activity;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentTransaction;
//
//import com.starv.tvindex.R;
//import com.starv.tvindex.model.local.module.view.fragment.ChoiceAreaFragment;
//import com.starv.tvindex.model.local.module.view.fragment.loginPageFragment;
//import com.starv.tvindex.util.Constant;
//
///**
// * Created by weizhi on 2016/10/31.
// */
//public class ChoiceAreaActivity extends FragmentActivity {
//
//    private ChoiceAreaFragment mFragment;
//    private String fromKey;
//
//    @Override
//    protected void onCreate(Bundle arg0) {
//        super.onCreate(arg0);
//        fromKey = getIntent().getStringExtra(getResources().getString(R.string.fromKey));
//        onInitContent();
//    }
//    private void onInitContent(){
//        mFragment=(ChoiceAreaFragment) Fragment.instantiate(this,ChoiceAreaFragment.class.getName());
//        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
//        ft.add(android.R.id.content, mFragment);
//        ft.commit();
//    }
//
//    public String getFromKey(){
//        return fromKey;
//    }
//}
