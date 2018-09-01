package com.ynwa.kdl.hosein.shopping.mvp.login;


import android.content.Context;
import android.content.SharedPreferences;
import android.renderscript.Sampler;
import android.util.Log;

import com.ynwa.kdl.hosein.shopping.MyUtils;
import com.ynwa.kdl.hosein.shopping.di.component.DaggerApiServiceComponent;
import com.ynwa.kdl.hosein.shopping.di.module.ApiServiceModule;
import com.ynwa.kdl.hosein.shopping.di.module.ContextModule;
import com.ynwa.kdl.hosein.shopping.retrofit.Authentication_Service.ApiServiceAuth;
import com.ynwa.kdl.hosein.shopping.retrofit.Authentication_Service.LoginObject;
import com.ynwa.kdl.hosein.shopping.mvp.login.Login;
import com.ynwa.kdl.hosein.shopping.pojo.LoginStatus;
import com.ynwa.kdl.hosein.shopping.retrofit.my_api.LoginData;
import com.ynwa.kdl.hosein.shopping.retrofit.my_api.MyApiService;
import com.ynwa.kdl.hosein.shopping.retrofit.my_api.RetrofitClient;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityModel implements Login.Model {


    public static final String TAG = "reqLogin";


    @Inject
    LoginStatus login, loginStatus;

    @Inject
    ApiServiceAuth service;

    RequestBody r1, r2, r3;



    @Override
    public void reqLogin(final Context context, String email, String pass, final CallBackData backData) {

        DaggerApiServiceComponent.builder()
                .contextModule(new ContextModule(context))
                .apiServiceModule(new ApiServiceModule())
                .build()
                .Inject(this);

        r1 = RequestBody.create(MediaType.parse("text.plain"), "password");
        r2 = RequestBody.create(MediaType.parse("text.plain"), email);
        r3 = RequestBody.create(MediaType.parse("text.plain"), pass);

        Call<LoginObject> call = service.login(r1, r2, r3);
        call.enqueue(new Callback<LoginObject>() {
            @Override
            public void onResponse(Call<LoginObject> call, Response<LoginObject> response)
            {
                if (response.body() != null)
                {
                    backData.onResult("لاگین شد!", true);
                }else if (response.code() == 401)
                    backData.onResult("چنین کاربری وجود ندارد !!", false);
                else
                    backData.onResult("error code: "+response.code(), false);
            }

            @Override
            public void onFailure(Call<LoginObject> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t.getMessage());
                backData.onResult("ناموفق!! دوباره تلاش کنید", false);
            }
        });

        /*MyApiService service = RetrofitClient.getClient().create(MyApiService.class);
        Call<LoginData> call = service.login(email, pass);
        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                if (response.isSuccessful()){
                    backData.onResult(response.message(), true);
                }
                else {
                    backData.onResult(response.message(), false);
                }
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                backData.onResult(t.getMessage(), false);
            }
        });*/
    }

}
