package com.ynwa.kdl.hosein.shopping.mvp.signup;

import android.content.Context;
import android.util.Log;

import com.ynwa.kdl.hosein.shopping.MyUtils;
import com.ynwa.kdl.hosein.shopping.R;
import com.ynwa.kdl.hosein.shopping.di.component.DaggerApiServiceComponent;
import com.ynwa.kdl.hosein.shopping.di.module.ApiServiceModule;
import com.ynwa.kdl.hosein.shopping.di.module.ContextModule;
import com.ynwa.kdl.hosein.shopping.pojo.RegisterStatus;
import com.ynwa.kdl.hosein.shopping.pojo.UserInfo;
import com.ynwa.kdl.hosein.shopping.retrofit.Authentication_Service.ApiServiceAuth;
import com.ynwa.kdl.hosein.shopping.retrofit.Authentication_Service.User;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpModel implements SignUp.Model{

    @Inject
    ApiServiceAuth service;

    @Inject
    HashMap<String, String> reqBody;


    @Override
    public void registerUser(UserInfo userInfo, Context context, final CallBackData callBackData) {
        DaggerApiServiceComponent.builder()
                .apiServiceModule(new ApiServiceModule())
                .contextModule(new ContextModule(context))
                .build()
                .Inject(this);

        reqBody.put("email", userInfo.getEmail());
        reqBody.put("password", userInfo.getPassword());
        reqBody.put("name", userInfo.getName());

        Call<User> call = service.registerUser(reqBody);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                Log.d("reqRegister", "onResponse: "+ response.message());
                if (response.body()!= null && response.isSuccessful())
                {
                    callBackData.onresult(new RegisterStatus(true, "اطلاعات با موفقیت ثبت شد!"));
                    //reqLogin();
                }
                else if (response.code() == 422){
                    callBackData.onresult(new RegisterStatus(false, "این کاربر وجود دارد!!!"));
                    Log.d("reqRegister",response.message());
                }
                else {
                    callBackData.onresult(new RegisterStatus(false, "عملیات ناموفق!!"));
                    Log.d("reqRegister","reqBody is null");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callBackData.onresult(new RegisterStatus(false, "عملیات ناموفق! دوباره تلاش کنید."));
                Log.d("reqRegister",t.getMessage());
            }
        });
    }


     /* private void reqLogin() {


        loginCall.enqueue(new Callback<LoginObject>() {
            @Override
            public void onResponse(Call<LoginObject> call, Response<LoginObject> response) {

                LoginObject object = response.reqBody();
                if (object != null && response.isSuccessful())
                {

                }
                else {
                    Toast.makeText(SignUpActivity.this, "null object", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginObject> call, Throwable t) {
                String m = t.getUserEmail();

                Toast.makeText(SignUpActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
