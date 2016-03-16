package com.example.leqiang.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.leqiang.R;
import com.example.leqiang.view.AutoPlayPager;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    /**
     * 资源图片
     */
    private int picture[] = {R.drawable.meinv, R.drawable.yinghua, R.drawable.suolong};

    private List<ImageView> mData;

    private AutoPlayPager mAutoPaly;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getData();
        View view = inflater.inflate(R.layout.layout_fragment_first, container, false);
        mAutoPaly = (AutoPlayPager) view.findViewById(R.id.auto_player);
        mAutoPaly.addPicture(mData);
        return view;
    }

    private void getData() {
        mData = new ArrayList<ImageView>();
        for (int i = 0; i < picture.length; i++) {
            ImageView view = new ImageView(getActivity());
            view.setImageResource(picture[i]);
            mData.add(view);
        }
    }


}
