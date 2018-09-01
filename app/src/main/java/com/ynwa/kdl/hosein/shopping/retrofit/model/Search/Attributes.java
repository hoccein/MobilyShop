package com.ynwa.kdl.hosein.shopping.retrofit.model.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


    public class Attributes {

        @SerializedName("ironBankCategory")
        @Expose
        private String ironBankCategory;
        @SerializedName("productUrlText")
        @Expose
        private String productUrlText;
        @SerializedName("size")
        @Expose
        private String size;

        public String getIronBankCategory() {
            return ironBankCategory;
        }

        public void setIronBankCategory(String ironBankCategory) {
            this.ironBankCategory = ironBankCategory;
        }

        public String getProductUrlText() {
            return productUrlText;
        }

        public void setProductUrlText(String productUrlText) {
            this.productUrlText = productUrlText;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

    }
