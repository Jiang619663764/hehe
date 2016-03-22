package com.example.leqiang.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.leqiang.R;
import com.example.leqiang.modle.Product;
import com.example.leqiang.util.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
public class CommonListView extends LinearLayout {

    private ListView mListView;

    private List<Product> mData;

    private Context mContext;

    private ListViewAdapter mAdapter;

    public CommonListView(Context context) {
        super(context);
    }

    public CommonListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_listview, this);
        mListView = (ListView) view.findViewById(R.id.listview);

    }

    public void addToList(List<Product> data) {
        mData = data;
        mAdapter=new ListViewAdapter(mContext, mData);
        mListView.setAdapter(mAdapter);
    }

    private class ListViewAdapter extends BaseAdapter {

        private Context mContext;

        private List<Product> mList;

        private ImageLoader mImageLoader;

        public ListViewAdapter(Context context, List<Product> list) {
            mContext = context;
            mList = list;
            mImageLoader=new ImageLoader();
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            LayoutInflater inflater = null;
            if (convertView == null) {
                holder = new ViewHolder();
                inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.item_listview, null);
                holder.name = (TextView) convertView.findViewById(R.id.txt_name_lv);
                holder.price = (TextView) convertView.findViewById(R.id.txt_price_lv);
                holder.pictureUrl = (ImageView) convertView.findViewById(R.id.img_lv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.pictureUrl.setImageResource(R.mipmap.ic_launcher);

//            String url=mList.get(position).getPictureUrl();
//            holder.pictureUrl.setTag(url);
//            mImageLoader.showImageByAsyncTask(holder.pictureUrl,url);

            holder.name.setText(mList.get(position).getName());
            holder.price.setText("￥" + mList.get(position).getPrice());
            return convertView;
        }
    }

    public class ViewHolder {
        private TextView name;
        private TextView price;
        private ImageView pictureUrl;
    }
}
