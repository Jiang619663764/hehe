package com.example.leqiang.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/3/22.
 */
public class ImageLoader {

    private ImageView mImageView;

    private String mImageUrl;
    //创建缓存
    private LruCache<String, Bitmap> mCache;

    public ImageLoader() {

        //获取最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        mCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //在每次存入缓存时调用
                return value.getByteCount();
            }
        };

    }

    /**
     * 根据URL获取bitmap
     *
     * @param urlString
     * @return
     */
    private Bitmap getBitmapFromURL(String urlString) {
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(conn.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            conn.disconnect();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param imageView
     * @param url
     */
    public void showImageByAsyncTask(ImageView imageView, String url) {

        //从缓存中获取图片
        Bitmap bitmap=getBitmapFromCache(url);
        if(bitmap==null){
            new ProductAsyncTask(imageView, url).execute(url);
        }else {
            imageView.setImageBitmap(bitmap);
        }

    }

    /**\
     * 将Bitmap添加到缓存中
     * @param url
     * @param bitmap
     */
    public void addBitmapToCache(String url,Bitmap bitmap){

        if(getBitmapFromCache(url)==null){
            mCache.put(url,bitmap);
        }
    }

    /**
     * 从缓存中获取Bitmap
     * @param url
     * @return
     */
    public Bitmap getBitmapFromCache(String url){

        return mCache.get(url);
    }


    private class ProductAsyncTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView image;

        private String imageURL;


        public ProductAsyncTask(ImageView imageView, String url) {
            image = imageView;
            imageURL = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url=params[0];
            //从网络获取图片
            Bitmap bitmap=getBitmapFromURL(url);
            if(bitmap!=null){
                //将图片添加到缓存
                addBitmapToCache(url,bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //
            if (image.getTag().equals(imageURL)) {
                image.setImageBitmap(bitmap);
            }

        }
    }
}
