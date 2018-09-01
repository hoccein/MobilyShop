package com.ynwa.kdl.hosein.shopping.mvp.signup;


import android.content.Context;
import android.support.design.widget.TextInputEditText;

import com.ynwa.kdl.hosein.shopping.pojo.RegisterStatus;
import com.ynwa.kdl.hosein.shopping.pojo.UserInfo;

public interface SignUp {

    interface Model{
        void registerUser(UserInfo userInfo, Context context, CallBackData callBackData);

        interface CallBackData{
            void onresult(RegisterStatus registerStatus);
        }
    }

    interface View{
        void init();
        void listeners();
        void changeIconColor(TextInputEditText view, int ic, int color);
        void goneVisibilityStatusMessage();
        void statusMessage(String message, int color);
        void showLoading();
        void hideLoading();
        void enableViews();
        void disableViews();
        void defaultColorForViews();
        void errorOnEditTextName(String msg);
        void errorOnEditTextEmail(String msg);
        void errorOnEditTextpass(String msg);
        void setErrorEditTextsToNull();

    }

    interface Presenter{

        void reqUserRegister(UserInfo userInfo);
        void startHomeActivity();
        boolean isUserValid(UserInfo userInfo);

    }
}
