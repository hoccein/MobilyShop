package com.ynwa.kdl.hosein.shopping.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import com.ynwa.kdl.hosein.shopping.realm_db.Detail;

import java.util.List;

import io.realm.RealmList;

public class PhoneParcel implements Parcelable {

    private long id;
    private String name;
    private String brand;
    private String desc;
    private String image;
    private int year;
    private int price;
    private List<String> color;
    private DetailParcel detail;
    private List<String> otherImg;


    public PhoneParcel(long id, String name, String brand, String desc, String image, int year, int price, List<String> color, DetailParcel detail, List<String> otherImg) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.desc = desc;
        this.image = image;
        this.year = year;
        this.price = price;
        this.color = color;
        this.detail = detail;
        this.otherImg = otherImg;
    }


    protected PhoneParcel(Parcel in) {
        id = in.readLong();
        name = in.readString();
        brand = in.readString();
        desc = in.readString();
        image = in.readString();
        year = in.readInt();
        price = in.readInt();
        color = in.createStringArrayList();
        detail = in.readParcelable(DetailParcel.class.getClassLoader());
        otherImg = in.createStringArrayList();
    }

    public static final Creator<PhoneParcel> CREATOR = new Creator<PhoneParcel>() {
        @Override
        public PhoneParcel createFromParcel(Parcel in) {
            return new PhoneParcel(in);
        }

        @Override
        public PhoneParcel[] newArray(int size) {
            return new PhoneParcel[size];
        }
    };

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public DetailParcel getDetail() {
        return detail;
    }

    public void setDetail(DetailParcel detail) {
        this.detail = detail;
    }

    public List<String> getOtherImg() {
        return otherImg;
    }

    public void setOtherImg(List<String> otherImg) {
        this.otherImg = otherImg;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(brand);
        dest.writeString(desc);
        dest.writeString(image);
        dest.writeInt(year);
        dest.writeInt(price);
        dest.writeStringList(color);
        dest.writeParcelable(detail, flags);
        dest.writeStringList(otherImg);
    }
}
