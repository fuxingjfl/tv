package com.starv.tvindex.model.local.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starv.tvindex.R;
import com.starv.tvindex.util.view.ProgressWebView;

//import butterknife.Bind;
//import butterknife.ButterKnife;
//import rx.Subscription;

/**
 * Created by weizhi on 2016/8/15.
 */
public abstract class BaseFragment extends Fragment {



    protected View self;

//    protected Subscription mSubscription;
    //切换到这个fragment的时候才会调用这个方法

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.self = inflater.inflate(this.getLayoutId(),container,false);

        //绑定这个view之后，后面继承这个类的所有Bind找id都是在这个view的布局里面找id
//        ButterKnife.bind(this,this.self);

        initViews(this.self,savedInstanceState);
        initData();
        initListeners();
        return this.self;
    }



    /**
     * get layout id
     *
     * @return layout id
     */
    protected abstract int getLayoutId();


    /**
     * Initialize the view in the layout
     *
     * @param self self
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void initViews(View self, Bundle savedInstanceState);

    /**
     * Initialize the View of the listener
     */
    protected abstract void initListeners();

    /**
     * Initialize the Activity data
     */
    protected abstract void initData();


    /**
     * Find the view by id
     *
     * @param id id
     * @param <V> V
     * @return V
     */
    @SuppressWarnings("unchecked") protected <V extends View> V findView(int id) {
        return (V) this.self.findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Log.i("test","basefragment destroy");
//        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
//            mSubscription.unsubscribe();
//        }
    }
}
