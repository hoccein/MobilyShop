package com.ynwa.kdl.hosein.shopping.retrofit.my_api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyApiService {

    @POST("login")
    @FormUrlEncoded
    Call<LoginData> login(@Field("email") String email, @Field("password") String password);

    @GET("phone")
    Call<PhoneApi> getPhones();
}
