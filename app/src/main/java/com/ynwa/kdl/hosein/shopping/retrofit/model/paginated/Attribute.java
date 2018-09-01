package com.ynwa.kdl.hosein.shopping.retrofit.model.paginated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attribute {

    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("compositeWood")
    @Expose
    private String compositeWood;
    @SerializedName("size")
    @Expose
    private String size;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCompositeWood() {
        return compositeWood;
    }

    public void setCompositeWood(String compositeWood) {
        this.compositeWood = compositeWood;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

