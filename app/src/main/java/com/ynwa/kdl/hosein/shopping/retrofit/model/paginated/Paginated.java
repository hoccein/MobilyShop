package com.ynwa.kdl.hosein.shopping.retrofit.model.paginated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Paginated {
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("nextPage")
    @Expose
    private String nextPage;
    @SerializedName("totalPages")
    @Expose
    private String totalPages;
    @SerializedName("items")
    @Expose
    private List<ItemPaginated> items = null;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public List<ItemPaginated> getItems() {
        return items;
    }

    public void setItems(List<ItemPaginated> items) {
        this.items = items;
    }

}
