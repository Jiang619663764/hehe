package com.example.leqiang.util;

import android.os.AsyncTask;

import com.example.leqiang.modle.Product;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2016/3/21.
 */
public class JsonData {

    private List<Product> mData;

    private String mPath;

    public JsonData(String path){
        mPath=path;
    }

    /**
     * 通过path路径获取Json格式数据
     *
     * @return
     */
    private String getJsonData() {
        try {
            URL url=new URL(mPath);
            String data=null;
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            if(conn.getResponseCode()==200){
                InputStream is=conn.getInputStream();
                data=parseData(is);
                return data;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将InputStream（通过读行）流转化成字符串
     * @param is
     * @return
     */
    private String parseData(InputStream is){

        //字节流转字符流
        InputStreamReader isr=new InputStreamReader(is);
        //读取
        BufferedReader reader=new BufferedReader(isr);
        String result = null;
        try {
            if((result=reader.readLine())!=null){
                result+=result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 解析Json数据到List集合中去
     */
    private List<Product> getList(){

        return null;
    }

}
