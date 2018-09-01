package com.ynwa.kdl.hosein.shopping.mvp.login;

import android.content.Context;

import com.ynwa.kdl.hosein.shopping.pojo.LoginStatus;

public interface Login {

    interface Model{

        void reqLogin(Context context, String email, String pass, CallBackData backData);
        interface CallBackData{
            void onResult(String msg, boolean status);
        }
    }

    interface View{

        void init();
        void listeners();
        void showMessage(String msg);
        void setNullToEdittexts(boolean b);
    }

    interface Presenter{

        void onClickLogin(Context context, String email, String pass);
        boolean isValidUser(String email, String pass);
        void startMainActivity();

    }
}
