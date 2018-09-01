package com.ynwa.kdl.hosein.shopping.mvp.login;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.ynwa.kdl.hosein.shopping.mvp.home.HomeActivity;
import com.ynwa.kdl.hosein.shopping.MyUtils;
import com.ynwa.kdl.hosein.shopping.pojo.LoginStatus;
import com.ynwa.kdl.hosein.shopping.util.Validation;

public class LoginActivityPresenter implements Login.Presenter {
    private Context context;
    private Login.Model mModel;
    private Login.View mView;
    private LoginStatus login;

    public LoginActivityPresenter(Context context, Login.View mView) {
        this.mView = mView;
        mModel = new LoginActivityModel();
    }

    @Override
    public void onClickLogin(final Context context, final String email, String pass) {
        this.context = context;
        if (isValidUser(email, pass))
        {
            mModel.reqLogin(context, email, pass, new Login.Model.CallBackData() {
                @Override
                public void onResult(String msg, boolean status)
                {
                    MyUtils.saveLoginStatus(context, email, status);
                    if (status)
                        startMainActivity();
                    else
                        mView.showMessage(msg);
                   /* if (status)
                        mView.showMessage(msg);
                    else
                        mView.showMessage(msg);*/
                }
            });
        }
        else
            mView.showMessage("اطلاعات صحیح وارد نمایید!!!");

    }

    @Override
    public boolean isValidUser(String email, String pass) {

        if (!Validation.email(email))
            return false;

        if (!Validation.emailPattern(email))
            return false;

        if (!Validation.password(pass))
            return false;

        if (!Validation.passwordLength(pass))
            return false;

        return true;
    }

    @Override
    public void startMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                context.startActivity(new Intent(context, HomeActivity.class));
            }
        }, 500);
    }


}
