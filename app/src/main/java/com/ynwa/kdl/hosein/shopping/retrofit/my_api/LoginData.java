package com.ynwa.kdl.hosein.shopping.retrofit.my_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("success")
    @Expose
    private Success success;

    class Success{

        @SerializedName("token")
        @Expose
        private String token;


        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
