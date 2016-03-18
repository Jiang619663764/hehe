package com.example.leqiang.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.leqiang.R;
import com.example.leqiang.modle.Product;
import com.example.leqiang.view.AutoPlayPager;
import com.example.leqiang.view.CommonGridView;
import com.example.leqiang.view.CommonListView;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    /**
     * viewpager资源图片
     */
    private int picture[] = {R.drawable.meinv, R.drawable.yinghua, R.drawable.suolong};

    /**
     * gridview资源图片
     */
    private int gridPicture[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,};

    /**
     * 存放Viewpager图片资源的集合
     */
    private List<ImageView> mData;

    /**
     * 存放Gridview图片资源的集合
     */
    private List<Integer> mGridData;

    /**
     * 存放Listview图片资源的集合
     */
    private List<Product> mListData;

    /**
     * 自定义的ViewPager
     */
    private AutoPlayPager mAutoPaly;

    /**
     * 自定义GridView
     */
    private CommonGridView mGridView;

    /**
     * 自定义ListView
     */
    private CommonListView mListView;

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
        mGridView=(CommonGridView)view.findViewById(R.id.grid_view);
        mListView=(CommonListView)view.findViewById(R.id.list_view);

        mAutoPaly.addPicture(mData, imageCycleViewListener);
        mGridView.addDataToGrid(mGridData, gridViewListener);
        mListView.addToList(mListData);
        return view;
    }

    /**
     * 为集合添加图片
     */
    private void getData() {
        mData = new ArrayList<ImageView>();
        mGridData =new ArrayList<Integer>();
        mListData=new ArrayList<Product>();
        for (int i = 0; i < picture.length; i++) {
            ImageView view = new ImageView(getActivity());
            view.setImageResource(picture[i]);
            mData.add(view);
        }
        for (int i=0;i<gridPicture.length;i++){
            mGridData.add(gridPicture[i]);
        }
        for (int i=0;i<10;i++){
            Product pro=new Product();
            pro.setName("产品");
            pro.setPrice(120);
            mListData.add(pro);
        }
    }

    /**
     * ViewPager图片监听，点击事件
     */
    AutoPlayPager.ImageCycleViewListener imageCycleViewListener = new AutoPlayPager.ImageCycleViewListener() {
        @Override
        public void onImageClick(int position, View imageView) {
            Toast.makeText(getActivity(),"shit",Toast.LENGTH_SHORT).show();
        }
    };

    CommonGridView.GridViewListener gridViewListener=new CommonGridView.GridViewListener() {
        @Override
        public void onImageClick(int position, View imageView) {
            Toast.makeText(getActivity(),"tihs",Toast.LENGTH_SHORT).show();
        }
    };
}
