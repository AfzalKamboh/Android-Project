package com.example.teaheaven;

import android.widget.ImageView;

public class TeaCardModel {
    private int image;
    private String name;
    private String price;
    private String weight;
    private String total;

    public TeaCardModel(int image, String name, String price, String weight) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public TeaCardModel(int image, String name, String price, String weight, String total) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.total = total;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
