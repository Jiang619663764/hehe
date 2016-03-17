package com.example.leqiang.view;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.leqiang.R;

import java.util.List;

/**
 * Created by Administrator on 2016/3/17.
 */
public class CommonGridView extends LinearLayout {

    private Context mContext;

    private GridView mGridView;

    private List<Integer> mData;

    private GridAdapter mAdapter;

    private GridViewListener mListener;

    public CommonGridView(Context context) {
        super(context);
    }

    public CommonGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_gridview, this);
        mGridView = (GridView) view.findViewById(R.id.gridview);

    }

    public void addDataToGrid(List<Integer> data,GridViewListener listener){
        mData=data;
        mListener=listener;
        mAdapter=new GridAdapter(mContext,mData);
        mGridView.setNumColumns(data.size()/2);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onImageClick(position,view);
            }
        });
    }

    private class GridAdapter extends BaseAdapter {

        private Context mContext;

        private List<Integer> mData;



        public GridAdapter(Context context, List<Integer> data) {
            mContext = context;
            mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = null;
            ViewHolder holder = null;
            if (convertView == null) {
                inflater=LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.item_gridview, null);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.img_gv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.image.setImageResource(mData.get(position));
            return convertView;
        }
    }

    public class ViewHolder {
        ImageView image;
    }

    public static interface GridViewListener {

        /**
         * 单击图片事件
         *
         * @param position
         * @param imageView
         */
        public void onImageClick(int position, View imageView);
    }
}
