package com.ynwa.kdl.hosein.shopping.retrofit.my_api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;

    public static Retrofit getClient(){
        //
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    //192.168.87.2
                    //192.168.56.1
                    //192.168.42.108

                    .baseUrl("http://192.168.56.1:8000/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
