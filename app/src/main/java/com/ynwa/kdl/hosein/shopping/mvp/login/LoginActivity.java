package com.ynwa.kdl.hosein.shopping.mvp.login;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;
import com.ynwa.kdl.hosein.shopping.R;
import com.ynwa.kdl.hosein.shopping.mvp.signup.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, Login.View {

    public static final String TAG_REQ = "retrofit_req";

    private Login.Presenter mPresenter;

    private TextInputLayout tilEmail, tilPass;
    private TextInputEditText etEmail, etPass;
    private Button btnLogin, btnSignUp;
    private ImageView ivLogo;
    private TextView tvTitle;

    @BindView(R.id.tv_forgot_pass)
    TextView tvForgotPassword;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        init();
        listeners();

        mPresenter = new LoginActivityPresenter(this,this);
    }

//---------------------------------------- Login.View -------------------------------------------
    @Override
    public void init() {
        tilEmail = findViewById(R.id.til_email);
        tilPass = findViewById(R.id.til_pass);
        etEmail = findViewById(R.id.et_email);
        etPass = findViewById(R.id.et_pass);
        ivLogo = findViewById(R.id.ivLogo);
        tvTitle = findViewById(R.id.tv_title);
        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_register);
    }

    @Override
    public void listeners() {
        //open HomeActivity
        btnLogin.setOnClickListener(this);
        //open  SignUpActivity
        btnSignUp.setOnClickListener(this);

        etEmail.setOnFocusChangeListener(this);
        etPass.setOnFocusChangeListener(this);

        tvForgotPassword.setOnClickListener(this);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setNullToEdittexts(boolean b) {
        if (b) {
            etEmail.setText(null);
            etPass.setText(null);
        }
    }


    private void changeIconColor(TextInputEditText view, int ic, int color) {

        Drawable icon = getResources().getDrawable(ic);
        icon.setColorFilter(getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);
        view.setCompoundDrawablesWithIntrinsicBounds(0,0, ic, 0);
    }

    @Override
    public void onClick(View v) {

        if (v == btnSignUp){
            Pair[] pairs = new Pair[2];
            pairs[0] = new Pair<View, String>(ivLogo, "logoLogin");
            pairs[1] = new Pair<View, String>(tvTitle, "titleLogin");
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);

            startActivity(new Intent(
                    LoginActivity.this,
                     SignUpActivity.class),
                     options.toBundle()
            );
        }
        else if (v == btnLogin){
            mPresenter.onClickLogin(this,
                    etEmail.getText().toString().trim(),
                    etPass.getText().toString().trim()
            );
        }
        else if (v == tvForgotPassword){
            showMessage("i forgot pass");
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus)
        {
            //email
            if (etEmail.getId() == v.getId())
                changeIconColor(etEmail, R.drawable.ic_email, R.color.colorAccent);
            else
                changeIconColor(etEmail, R.drawable.ic_email, R.color.gray_root);
            //pass
            if (etPass.getId() == v.getId())
                changeIconColor(etPass, R.drawable.ic_pass, R.color.colorAccent);
            else
                changeIconColor(etPass, R.drawable.ic_pass, R.color.gray_root);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        changeIconColor(etEmail, R.drawable.ic_email, R.color.gray_root);
        changeIconColor(etPass, R.drawable.ic_pass, R.color.gray_root);
    }
}
