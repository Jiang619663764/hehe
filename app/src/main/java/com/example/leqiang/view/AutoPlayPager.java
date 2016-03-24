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

    /**
     * ViewPager对象
     */
    private ViewPager mViewPager;

    /**
     * 小圆点数组
     */
    private ImageView[] mPointView = null;

    /**
     * 小圆点视图
     */
    private ImageView mView = null;

    /**
     * ViewPager适配器
     */
    private ViewPagerAdapter mAdapter;

    /**
     * 图片资源数据
     */
    private List<ImageView> mData;

    /**
     * 判断是否停止
     */
    private boolean isStop;

    private Handler mHandler = new Handler();

    /**
     * ViewPager底部小圆点容器
     */
    private ViewGroup mGroup = null;

    /**
     * 图片自动轮播Task
     */
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
        this(context,null);
    }

    public AutoPlayPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_viewpager, this);

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setOnPageChangeListener(new PictureChangeListener());

        mGroup = (ViewGroup) findViewById(R.id.point_group);
    }

    /**
     * 手势监听
     * 抬起时开启任务，按下移动时关闭任务
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            startImageTask();
        } else {
            stopImageTask();
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 填充图片数据
     *
     * @param data
     * @param imageCycleViewListener
     */
    public void addPicture(List<ImageView> data, ImageCycleViewListener imageCycleViewListener) {

        mGroup.removeAllViews();
        mPointView = new ImageView[data.size()];
        for (int i = 0; i < data.size(); i++) {
            mView = new ImageView(mContext);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 30;
            mView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mView.setLayoutParams(params);

            mPointView[i] = mView;
            if (i == 0) {
                mPointView[i].setBackgroundResource(R.mipmap.page_indicator_focused);
            } else {
                mPointView[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }
            mGroup.addView(mPointView[i]);
        }

        mData = data;
        mAdapter = new ViewPagerAdapter(mContext, mData, imageCycleViewListener);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        startImageTask();

    }

    /**
     * 轮播图片的监听
     */
    private final class PictureChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            startImageTask();
            position = position % mData.size();
            mPointView[position].setBackgroundResource(R.mipmap.page_indicator_focused);
            for (int i = 0; i < mData.size(); i++) {
                if (i != position) {
                    mPointView[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                startImageTask();
            } else if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                stopImageTask();
            }
        }
    }

    /**
     * 开启任务
     */
    private void startImageTask() {
        stopImageTask();
        mHandler.postDelayed(mImageTask, 3000);

    }

    /**
     * 关闭任务
     */
    private void stopImageTask() {
        isStop = true;
        mHandler.removeCallbacks(mImageTask);
    }

    /**
     * ViewPager适配器
     */
    private class ViewPagerAdapter extends PagerAdapter {

        private List<ImageView> mList;

        private Context mContext;

        /**
         * 广告图片点击监听
         */
        private ImageCycleViewListener mImageCycleViewListener;

        public ViewPagerAdapter(Context context, List<ImageView> list, ImageCycleViewListener imageCycleViewListener) {
            mList = list;
            mContext = context;
            mImageCycleViewListener = imageCycleViewListener;
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
        public Object instantiateItem(ViewGroup container, final int position) {

            ImageView view = mList.get(position % mList.size());
            view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

            //test
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mImageCycleViewListener.onImageClick(position % mList.size(), v);
                }
            });

            //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
            ViewParent vp = view.getParent();
            if (vp != null) {
                ViewGroup parent = (ViewGroup) vp;
                parent.removeView(view);
            }

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView view = (ImageView) object;
            mViewPager.removeView(view);
        }

    }

    /**
     * 轮播控件的监听事件
     *
     * @author minking
     */
    public static interface ImageCycleViewListener {

        /**
         * 单击图片事件
         *
         * @param position
         * @param imageView
         */
        public void onImageClick(int position, View imageView);
    }
}
