//package com.starv.tvindex.model.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Parcelable;
//import android.support.annotation.Nullable;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//
//import com.starv.tvindex.R;
//
//import java.util.ArrayList;
//
///**
// * Created by weizhi on 2016/6/14.
// */
//public class GuideActivity extends FragmentActivity {
//
//
//
//    private ViewGroup m_dot_group;
//    private ViewPager viewPager;
//    private ArrayList<View> pageViews;
//    private ImageView imageView;
//    private ImageView[] imageViews;
//    private View m_guide_View;
//    private View m_guide_viewThree;
//
////    立即体验
//    private Button m_obj_enterBtn;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        initView();
//    }
//
//    private void initView(){
//        LayoutInflater inflater = getLayoutInflater();
//        m_guide_View = (ViewGroup)inflater.inflate(R.layout.guide, null);
//        pageViews = new ArrayList<View>();
//        m_guide_viewThree = inflater.inflate(R.layout.guide_three, null);
//        pageViews.add(inflater.inflate(R.layout.guide_one, null));
//        pageViews.add(inflater.inflate(R.layout.guide_two, null));
//        pageViews.add(m_guide_viewThree);
//        m_obj_enterBtn = (Button) m_guide_viewThree.findViewById(R.id.id_enterBtn);
//        m_obj_enterBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(), TabHostActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//
//
//        imageViews = new ImageView[pageViews.size()];
//        m_dot_group = (ViewGroup)m_guide_View.findViewById(R.id.viewGroup);
//        viewPager = (ViewPager)m_guide_View.findViewById(R.id.guidePages);
////        for (int i = 0; i < pageViews.size(); i++) {
////            imageView = new ImageView(GuideActivity.this);
////
////
////            imageView.setLayoutParams(new ViewGroup.LayoutParams(30,30));
////
////            imageView.setPadding(20, 0, 20, 0);
////            imageViews[i] = imageView;
////
////            if (i == 0) {
////                imageViews[i].setBackgroundResource(R.mipmap.page_indicator_focused);
////            } else {
////                imageViews[i].setBackgroundResource(R.mipmap.page_indicator);
////            }
////
////            m_dot_group.addView(imageViews[i]);
////        }
//        imageViews[0] = (ImageView) m_guide_View.findViewById(R.id.id_dotone);
//        imageViews[1] = (ImageView) m_guide_View.findViewById(R.id.id_dottwo);
//        imageViews[2] = (ImageView) m_guide_View.findViewById(R.id.id_dotthree);
//        imageViews[0].setBackgroundResource(R.mipmap.page_indicator_focused);
//        imageViews[1].setBackgroundResource(R.mipmap.page_indicator);
//        imageViews[2].setBackgroundResource(R.mipmap.page_indicator);
//
//
//        setContentView(m_guide_View);
//        viewPager.setAdapter(new GuidePageAdapter());
//        viewPager.setOnPageChangeListener(new GuidePageChangeListener());
//    }
//        class GuidePageChangeListener implements ViewPager.OnPageChangeListener {
//
//        @Override
//        public void onPageScrollStateChanged(int arg0) {
//            // TODO Auto-generated method stub
////            switch (arg0) {
////
////                case ViewPager.SCROLL_STATE_DRAGGING:
////                    break;
////
////                case ViewPager.SCROLL_STATE_SETTLING:
////                    break;
////                case ViewPager.SCROLL_STATE_IDLE:
////                    if (viewPager.getCurrentItem() == viewPager.getAdapter()
////                            .getCount() - 1) {
////
////                        m_obj_enterBtn.setVisibility(View.VISIBLE);
////                    }else{
////                        m_obj_enterBtn.setVisibility(View.GONE);
////                    }
////            }
//        }
//
//        @Override
//        public void onPageScrolled(int arg0, float arg1, int arg2) {
//            // TODO Auto-generated method stub
//
//
//        }
//
//        @Override
//        public void onPageSelected(int arg0) {
//            for (int i = 0; i < imageViews.length; i++) {
//                imageViews[arg0].setBackgroundResource(R.mipmap.page_indicator_focused);
//
//                if (arg0 != i) {
//                    imageViews[i].setBackgroundResource(R.mipmap.page_indicator);
//                }
//            }
//        }
//    }
//
//    class GuidePageAdapter extends PagerAdapter {
//
//        @Override
//        public int getCount() {
//            return pageViews.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View arg0, Object arg1) {
//            return arg0 == arg1;
//        }
//
//        @Override
//        public int getItemPosition(Object object) {
//            // TODO Auto-generated method stub
//            return super.getItemPosition(object);
//        }
//
//        @Override
//        public void destroyItem(View arg0, int arg1, Object arg2) {
//            // TODO Auto-generated method stub
//            ((ViewPager) arg0).removeView(pageViews.get(arg1));
//        }
//
//        @Override
//        public Object instantiateItem(View arg0, int arg1) {
//            // TODO Auto-generated method stub
//            ((ViewPager) arg0).addView(pageViews.get(arg1));
//            return pageViews.get(arg1);
//        }
//
//        @Override
//        public void restoreState(Parcelable arg0, ClassLoader arg1) {
//            // TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public Parcelable saveState() {
//            // TODO Auto-generated method stub
//            return null;
//        }
//
//        @Override
//        public void startUpdate(View arg0) {
//            // TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public void finishUpdate(View arg0) {
//            // TODO Auto-generated method stub
//
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        return ;
//    }
//}
