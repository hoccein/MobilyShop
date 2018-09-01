package com.ynwa.kdl.hosein.shopping.mvp.signup;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.wang.avi.AVLoadingIndicatorView;
import com.ynwa.kdl.hosein.shopping.R;
import com.ynwa.kdl.hosein.shopping.di.component.DaggerApiServiceComponent;
import com.ynwa.kdl.hosein.shopping.di.module.ApiServiceModule;
import com.ynwa.kdl.hosein.shopping.di.module.ContextModule;
import com.ynwa.kdl.hosein.shopping.pojo.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class SignUpActivity extends AppCompatActivity implements SignUp.View, View.OnClickListener,View.OnFocusChangeListener{

    private static final String TAG_REQ = "signup_activity";

    @BindView(R.id.iv_back_signup)
    ImageView ivBack;
    @BindView(R.id.av_loading)
    AVLoadingIndicatorView loading;
    @BindView(R.id.til_signup_name)
    TextInputLayout tilName;
    @BindView(R.id.til_signup_email)
    TextInputLayout tilEmail;
    @BindView(R.id.til_signup_pass)
    TextInputLayout tilPass;
    @BindView(R.id.et_name)
    TextInputEditText etName;
    @BindView(R.id.et_email)
    TextInputEditText etEmail;
    @BindView(R.id.et_mobile)
    TextInputEditText etMobile;
    @BindView(R.id.et_pass)
    TextInputEditText etPass;
    @BindView(R.id.et_address)
    TextInputEditText etAddress;
    @BindView(R.id.btn_reg)
    Button btnRegister;
    @BindView(R.id.tv_register_status)
    TextView tvStatusRegister;

    private SignUp.Presenter mPresenter;



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();
        listeners();

        mPresenter = new SignUpPresenter(this, this);

        hideLoading();
    }



    @Override
    public void init() {

        ButterKnife.bind(this);

        DaggerApiServiceComponent.builder()
                .contextModule(new ContextModule(this))
                .apiServiceModule(new ApiServiceModule())
                .build()
                .Inject(this);
    }

    @Override
    public void listeners() {
        etName.setOnFocusChangeListener(this);
        etEmail.setOnFocusChangeListener(this);
        etMobile.setOnFocusChangeListener(this);
        etPass.setOnFocusChangeListener(this);
        etAddress.setOnFocusChangeListener(this);

        btnRegister.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void changeIconColor(TextInputEditText view, int ic, int color) {

        Drawable icon = getResources().getDrawable(ic);
        icon.setColorFilter(getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);
        view.setCompoundDrawablesWithIntrinsicBounds(0,0, ic, 0);
    }

   @Override
    public void statusMessage(String message, int color){
        tvStatusRegister.setVisibility(View.VISIBLE);
        tvStatusRegister.setText(message);
        tvStatusRegister.setTextColor(getResources().getColor(color));

    }

    @Override
    public void goneVisibilityStatusMessage(){
        tvStatusRegister.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        loading.show();
    }

    @Override
    public void hideLoading(){
        loading.hide();
    }

    @Override
    public void enableViews(){
        etName.setEnabled(true);
        etEmail.setEnabled(true);
        etMobile.setEnabled(true);
        etPass.setEnabled(true);
        etAddress.setEnabled(true);
        btnRegister.setEnabled(true);
    }

    @Override
    public void disableViews(){
        etName.setEnabled(false);
        etEmail.setEnabled(false);
        etMobile.setEnabled(false);
        etPass.setEnabled(false);
        etAddress.setEnabled(false);
        btnRegister.setEnabled(false);
    }

    @Override
    public void defaultColorForViews(){
        changeIconColor(etName, R.drawable.ic_user, R.color.gray_root);
        changeIconColor(etEmail, R.drawable.ic_email, R.color.gray_root);
        changeIconColor(etMobile, R.drawable.ic_mobile, R.color.gray_root);
        changeIconColor(etPass, R.drawable.ic_pass, R.color.gray_root);
        changeIconColor(etAddress, R.drawable.ic_home, R.color.gray_root);
    }

    @Override
    public void errorOnEditTextName(String msg) {
        tilName.setError(msg);
    }

    @Override
    public void errorOnEditTextEmail(String msg) {
        tilEmail.setError(msg);
    }

    @Override
    public void errorOnEditTextpass(String msg) {
        tilPass.setError(msg);
    }

    @Override
    public void setErrorEditTextsToNull() {
        tilName.setError(null);
        tilEmail.setError(null);
        tilPass.setError(null);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (hasFocus)
        {
            //name(user)
            if (etName.getId() == v.getId())
                changeIconColor(etName, R.drawable.ic_user, R.color.colorAccent);
            else
                changeIconColor(etName, R.drawable.ic_user, R.color.gray_root);
            //email
            if (etEmail.getId() == v.getId())
                changeIconColor(etEmail, R.drawable.ic_email, R.color.colorAccent);
            else
                changeIconColor(etEmail, R.drawable.ic_email, R.color.gray_root);
            //mobile
            if (etMobile.getId() == v.getId())
                changeIconColor(etMobile, R.drawable.ic_mobile, R.color.colorAccent);
            else
                changeIconColor(etMobile, R.drawable.ic_mobile, R.color.gray_root);
            //pass
            if (etPass.getId() == v.getId())
                changeIconColor(etPass, R.drawable.ic_pass, R.color.colorAccent);
            else
                changeIconColor(etPass, R.drawable.ic_pass, R.color.gray_root);
            //address (home)
            if (etAddress.getId() == v.getId())
                changeIconColor(etAddress, R.drawable.ic_home, R.color.colorAccent);
            else
                changeIconColor(etAddress, R.drawable.ic_home, R.color.gray_root);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        defaultColorForViews();
        tvStatusRegister.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {
        if (v == btnRegister)
        {
            UserInfo userInfo = new UserInfo();
            userInfo.setName(etName.getText().toString().trim());
            userInfo.setEmail(etEmail.getText().toString().trim());
            userInfo.setMobile(etMobile.getText().toString().trim());
            userInfo.setPassword(etPass.getText().toString());
            userInfo.setAddress(etAddress.getText().toString());

            mPresenter.reqUserRegister(userInfo);
        }
        else if (v == ivBack){
            finish();
        }
    }
}
