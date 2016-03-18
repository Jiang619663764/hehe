package com.example.leqiang.modle;

/**
 * Created by Administrator on 2016/3/18.
 */
public class Product {
    private String name;
    private String intro;
    private int price;
    private String pictureUrl;

    public void setName(String name) {
        this.name = name;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getName() {

        return name;
    }

    public String getIntro() {
        return intro;
    }

    public int getPrice() {
        return price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }



}
