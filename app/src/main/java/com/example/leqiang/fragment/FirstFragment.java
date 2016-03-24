package com.example.leqiang.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.leqiang.R;
import com.example.leqiang.modle.Product;
import com.example.leqiang.util.NetUtil;
import com.example.leqiang.view.AutoPlayPager;
import com.example.leqiang.view.CommonListView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGAStickinessRefreshViewHolder;

public class FirstFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    /**
     * 下拉刷新控件
     */
    private BGARefreshLayout mBGARefresh;

    private CommonListView mListView;

    /**
     * 广告条轮播控件
     */
    private BGABanner mBanner;

    /**
     * viewpager资源图片
     */
    private int picture[] = {R.drawable.meinv, R.drawable.yinghua, R.drawable.suolong};

    /**
     * 存放Viewpager图片资源的集合
     */
    private List<View> mData;

    /**
     * 存放ListView图片资源的集合
     */
    private List<Product> mListData;


    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment_first, container, false);
        mListView= (CommonListView) view.findViewById(R.id.list_fresh);
        getData();

        mListView.addToList(mListData);
        initRefreshLayout(view);

//        mAutoPaly = (AutoPlayPager) view.findViewById(R.id.auto_player);
//        mAutoPaly.addPicture(mData, imageCycleViewListener);

        return view;
    }

    /**
     * 初始化BGARefreshLayout对象
     * @param view
     */
    private void initRefreshLayout(View view) {

        //获取BGARefreshLayout对象
        mBGARefresh= (BGARefreshLayout) view.findViewById(R.id.refresh_layout);
        //为BGARefreshLayout设置代理（实现接口）
        mBGARefresh.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder=new BGANormalRefreshViewHolder(getActivity(),false);
        // 设置下拉刷新和上拉加载更多的风格
        mBGARefresh.setRefreshViewHolder(refreshViewHolder);

        //设置正在加载更多时的文本
        refreshViewHolder.setLoadingMoreText("loading...");
        // 设置整个加载更多控件的背景颜色资源id
        refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.LoadMoreBackgroundColorRes);

        //设置自定义头部视图    参数1：自定义头部视图（例如广告位）， 参数2：上拉加载更多是否可用
        mBGARefresh.setCustomHeaderView(getCustomHeader(getActivity()), false);

    }

    private View getCustomHeader(Context context){
        View view=LayoutInflater.from(context).inflate(R.layout.view_custom_header,null);
        mBanner= (BGABanner) view.findViewById(R.id.banner);
        // 用Java代码方式设置切换动画
        mBanner.setTransitionEffect(BGABanner.TransitionEffect.Rotate);
        // 设置page切换时长
        mBanner.setPageChangeDuration(2000);
        //
        mBanner.setViews(mData);
        return view;
    }


    /**
     * 为集合添加图片
     */
    private void getData() {

        //Banner图片
        mData = new ArrayList<View>();
        for (int i = 0; i < picture.length; i++) {
            ImageView view = new ImageView(getActivity());
            view.setImageResource(picture[i]);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            mData.add(view);
        }
        //listview图片
        mListData=new ArrayList<Product>();
        for (int i=0;i<20;i++){
            Product pro=new Product();
            pro.setName("asd");
            mListData.add(pro);
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing() {
        //在这里加载最新数据

        if(NetUtil.checkNet(getActivity())){
            //判断如果联网，刷新数据
            new AsyncTask<Void,Void,Void>(){

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    // 加载完毕后在UI线程结束下拉刷新
                    mBGARefresh.endRefreshing();
                }
            }.execute();
        }else{
            //没有联网，弹出提示
            Toast.makeText(getActivity(),"没有连接网络",Toast.LENGTH_LONG).show();
            //取消刷新
            mBGARefresh.endRefreshing();
        }
    }

    @Override
    public void onBGARefreshLayoutBeginLoadingMore() {
        // 在这里加载更多数据，或者更具产品需求实现上拉刷新也可以

        if(NetUtil.checkNet(getActivity())){
            //判断如果联网，刷新数据
            new AsyncTask<Void,Void,Void>(){

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    // 加载完毕后在UI线程结束下拉刷新
                    mBGARefresh.endRefreshing();

                }
            }.execute();
        }else{
            //没有联网，弹出提示
            Toast.makeText(getActivity(),"没有连接网络",Toast.LENGTH_LONG).show();
            //取消刷新
            mBGARefresh.endRefreshing();
        }
    }

}
