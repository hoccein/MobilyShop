package com.ynwa.kdl.hosein.shopping.retrofit.Authentication_Service;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServiceAuth {

    @Headers("Content-Type: application/json")
    @POST("api/v1/register")
    Call<User> registerUser(@Body HashMap<String, String> body);

    @Multipart
    @POST("oauth/token")
    Call<LoginObject> login(@Part("grant_type") RequestBody grant_type, @Part("username") RequestBody username, @Part("password") RequestBody password);

    @POST("oauth/token")
    Call<LoginObject> refreshToken(@Body HashMap<String, String> body);


    @GET("api/user")
    Call<User> getUser(@HeaderMap Map<String, String> headers);
}

/*

        "Accept"*/
