package com.starv.tvindex.util.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;



/**
 * 带进度条的WebView
 * 
 */
@SuppressWarnings("deprecation")
public class ProgressWebView extends WebView {

    private ProgressBar m_obj_progressbar;
    private ILoadFinish m_end_lister;
    private ScrollInterface m_obj_topLister;

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWebChromeClient(new WebChromeClient());

        //Log.i("test","newProgress22 = ");
    }

    public void setProgressbar(ProgressBar progressbar){
        //Log.i("test","newProgress33 = ");
        m_obj_progressbar = progressbar;
    }
    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if(null != m_obj_progressbar){

//                Log.i("test",view.getUrl() + "-------------newProgress = " + newProgress);
                if (newProgress == 100) {
                    m_obj_progressbar.setVisibility(GONE);
                } else {
                    if (m_obj_progressbar.getVisibility() == GONE)
                        m_obj_progressbar.setVisibility(VISIBLE);
                    m_obj_progressbar.setProgress(newProgress);
                }
            }
            super.onProgressChanged(view, newProgress);
        }



    }

//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        LayoutParams lp = (LayoutParams) m_obj_progressbar.getLayoutParams();
//        lp.x = l;
//        lp.y = t;
//        if(null != m_obj_progressbar){
//
//            m_obj_progressbar.setLayoutParams(lp);
//        }
//        super.onScrollChanged(l, t, oldl, oldt);
//        if(null != m_obj_topLister) {
//            m_obj_topLister.onSChanged(l, t, oldl, oldt);
//        }
//    }

    public void setProgressbarFlag(int flag){
        m_obj_progressbar.setVisibility(flag);
    }

    /**
     * 设置页面加载完成回调
     * 主要是用于下拉刷新的时候 通知下拉刷新界面结束
     * */
    public interface ILoadFinish{
        void finish();
    }

    public void setEndLister(ILoadFinish lister){
        m_end_lister = lister;
    }

    /**
     * 定义滑动接口
     */
    public interface ScrollInterface {

        public void onSChanged(int l, int t, int oldl, int oldt) ;
    }

    public void setTopLister(ScrollInterface lister){
        m_obj_topLister = lister;
    }



//    boolean mPreventParentTouch = false;
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        boolean ret = super.onTouchEvent(ev);
//        if (mPreventParentTouch) {
//            switch (ev.getAction()) {
//                case MotionEvent.ACTION_MOVE:
//                    requestDisallowInterceptTouchEvent(true);
//                    ret = true;
//                    break;
//                case MotionEvent.ACTION_UP:
//                case MotionEvent.ACTION_CANCEL:
//                    requestDisallowInterceptTouchEvent(false);
//                    mPreventParentTouch = false;
//                    break;
//            }
//        }
//        return ret;
//    }
//
//    public void preventParentTouchEvent () {
//        mPreventParentTouch = true;
//    }



    //http://blog.csdn.net/gufengpiaoyi/article/details/51775154
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(this.getScrollY() <= 0)
                    this.scrollTo(0,1);
                break;
            case MotionEvent.ACTION_UP:
//                if(this.getScrollY() == 0)
//                this.scrollTo(0,-1);

                break;
        }
        return super.onTouchEvent(event);
    }



}