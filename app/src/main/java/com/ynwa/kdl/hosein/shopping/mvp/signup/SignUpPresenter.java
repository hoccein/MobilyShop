package com.ynwa.kdl.hosein.shopping.mvp.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.ynwa.kdl.hosein.shopping.MyUtils;
import com.ynwa.kdl.hosein.shopping.R;
import com.ynwa.kdl.hosein.shopping.mvp.home.HomeActivity;
import com.ynwa.kdl.hosein.shopping.pojo.RegisterStatus;
import com.ynwa.kdl.hosein.shopping.pojo.UserInfo;
import com.ynwa.kdl.hosein.shopping.util.Validation;

public class SignUpPresenter implements SignUp.Presenter {

    private Context context;
    private SignUp.View mView;
    private SignUp.Model mModel;

    public SignUpPresenter(Context context, SignUp.View mView){
        this.context = context;
        this.mView = mView;
        mModel = new SignUpModel();
    }


    @Override
    public void reqUserRegister(final UserInfo userInfo) {

        mView.goneVisibilityStatusMessage();
        if (isUserValid(userInfo))
        {
            mView.showLoading();
            mView.disableViews();
            mView.defaultColorForViews();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mModel.registerUser(userInfo, context, new SignUp.Model.CallBackData() {
                        @Override
                        public void onresult(RegisterStatus registerStatus) {

                            if (registerStatus.isRegistered()){
                                MyUtils.saveUserInfoToPref(context, userInfo);
                                MyUtils.saveLoginStatus(context, userInfo.getEmail(),true);
                                mView.hideLoading();
                                mView.statusMessage(registerStatus.getMsg(), R.color.colorAccent );
                                startHomeActivity();
                            }
                            else
                            {
                                MyUtils.saveLoginStatus(context, userInfo.getEmail(),false);
                                mView.enableViews();
                                mView.hideLoading();
                                mView.statusMessage(registerStatus.getMsg(), R.color.red );
                            }
                        }
                    });
                }
            }, 3000);
        }
    }

    @Override
    public boolean isUserValid(UserInfo userInfo) {

        mView.setErrorEditTextsToNull();

        if ( userInfo.getName().isEmpty() ||
                userInfo.getEmail().isEmpty() || !Validation.emailPattern(userInfo.getEmail()) ||
                userInfo.getPassword().isEmpty() || !Validation.passwordLength(userInfo.getPassword())
           )
        {
            if (!Validation.name(userInfo.getName()))
                mView.errorOnEditTextName("اجباری!");

            if (!Validation.email(userInfo.getEmail()))
                mView.errorOnEditTextEmail("اجباری!");
            else if (!Validation.emailPattern(userInfo.getEmail()))
                mView.errorOnEditTextEmail("لطفا ایمیل صحیح وارد نمایید!");

            if (!Validation.password(userInfo.getPassword()))
                mView.errorOnEditTextpass("اجباری!");
            else if (!Validation.passwordLength(userInfo.getPassword()))
                mView.errorOnEditTextpass("رمز کمتر از 6 کاراکتر نباید باشد!");

            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void startHomeActivity() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                context.startActivity(new Intent(context, HomeActivity.class));
            }
        }, 1300);
    }
}
