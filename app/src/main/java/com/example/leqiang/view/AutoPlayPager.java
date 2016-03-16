package com.example.leqiang.view;

import android.content.Context;
import android.media.Image;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.leqiang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
public class AutoPlayPager extends LinearLayout {

    private Context mContext;

    private ViewPager mViewPager;

    private ImageView[] mPointView=null;

    private ImageView mView=null;

    private ViewPagerAdapter mAdapter;

    private List<ImageView> mData;

    private boolean isStop;

    private Handler mHandler = new Handler();

    private ViewGroup mGroup;

    private Runnable mImageTask = new Runnable() {
        @Override
        public void run() {
            if (mData != null) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                if (!isStop) {
                    mHandler.postDelayed(mImageTask, 3000);
                }
            }
        }
    };

    public AutoPlayPager(Context context) {
        super(context);
    }

    public AutoPlayPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_viewpager, this);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setOnPageChangeListener(new PictureChangeListener());

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_UP){
            startImageTask();
        }else {
            stopImageTask();
        }
        return super.dispatchTouchEvent(ev);
    }

    public void addPicture(List<ImageView> data) {

        mData=data;
        mAdapter = new ViewPagerAdapter(mContext, mData);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        startImageTask();

    }

    private final class PictureChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            startImageTask();
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                startImageTask();
            }else if(state==ViewPager.SCROLL_STATE_DRAGGING){
                stopImageTask();
            }
        }
    }

    private void startImageTask() {
        stopImageTask();
        mHandler.postDelayed(mImageTask, 3000);

    }

    private void stopImageTask() {
        isStop = true;
        mHandler.removeCallbacks(mImageTask);
    }

    private class ViewPagerAdapter extends PagerAdapter {

        private List<ImageView> mList;

        private Context mContext;

        public ViewPagerAdapter(Context context, List<ImageView> list) {
            mList = list;
            mContext = context;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

           ImageView view=mList.get(position%mList.size());

            //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
            ViewParent vp=view.getParent();
            if(vp!=null){
                ViewGroup parent=(ViewGroup)vp;
                parent.removeView(view);
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView(mImage[position % mImage.length]);
            ImageView view=(ImageView)object;
            mViewPager.removeView(view);
        }

    }
}
