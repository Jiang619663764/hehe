package com.example.leqiang.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.leqiang.R;
import com.example.leqiang.modle.Product;
import com.example.leqiang.view.AutoPlayPager;

import java.util.ArrayList;
import java.util.List;


public class SecondFragment extends Fragment {

    private AutoPlayPager mViewPager;

    /**
     * viewpager资源图片
     */
    private int picture[] = {R.drawable.meinv, R.drawable.yinghua, R.drawable.suolong};

    /**
     * 存放Viewpager图片资源的集合
     */
    private List<ImageView> mData;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getData();
        View view=inflater.inflate(R.layout.layout_fragment_second, container, false);
        mViewPager=(AutoPlayPager)view.findViewById(R.id.viewpager_second_frg);
        mViewPager.addPicture(mData,imageCycleViewListener);

        return view;
    }

    /**
     * 为集合添加图片
     */
    private void getData() {
        mData=new ArrayList<ImageView>();
        for (int i = 0; i < picture.length; i++) {
            ImageView view = new ImageView(getActivity());
            view.setImageResource(picture[i]);
            mData.add(view);
        }

    }

    /**
     * ViewPager图片监听，点击事件
     */
    AutoPlayPager.ImageCycleViewListener imageCycleViewListener = new AutoPlayPager.ImageCycleViewListener() {
        @Override
        public void onImageClick(int position, View imageView) {
            Toast.makeText(getActivity(), "shit", Toast.LENGTH_SHORT).show();
        }
    };

}
