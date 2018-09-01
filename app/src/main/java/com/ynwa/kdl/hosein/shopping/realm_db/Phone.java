package com.ynwa.kdl.hosein.shopping.realm_db;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Phone extends RealmObject  {

    @PrimaryKey
    private long id;
    private String name;
    private String brand;
    private String desc;
    private String image;
    private int year;
    private int price;
    private RealmList<String> color;
    private Detail detail;
    private RealmList<String> otherImg;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public RealmList<String> getColor() {
        return color;
    }

    public void setColor(RealmList<String> color) {
        this.color = color;
    }

    public RealmList<String> getOtherImg() {
        return otherImg;
    }

    public void setOtherImg(RealmList<String> otherImg) {
        this.otherImg = otherImg;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}


