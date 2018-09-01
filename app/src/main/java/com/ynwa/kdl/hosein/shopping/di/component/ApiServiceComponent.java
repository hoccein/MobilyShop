package com.ynwa.kdl.hosein.shopping.di.component;


import com.ynwa.kdl.hosein.shopping.di.module.ContextModule;
import com.ynwa.kdl.hosein.shopping.di.module.PicassoModule;
import com.ynwa.kdl.hosein.shopping.mvp.home.HomeActivity;
import com.ynwa.kdl.hosein.shopping.mvp.signup.SignUpActivity;
import com.ynwa.kdl.hosein.shopping.di.module.ApiServiceModule;
import com.ynwa.kdl.hosein.shopping.di.scope.ApiServiceScope;
import com.ynwa.kdl.hosein.shopping.mvp.login.LoginActivityModel;
import com.ynwa.kdl.hosein.shopping.mvp.signup.SignUpModel;


import dagger.Component;

@Component(modules = {ApiServiceModule.class, PicassoModule.class, ContextModule.class})
@ApiServiceScope
public interface ApiServiceComponent {

    void Inject(LoginActivityModel loginActivityModel);
    void Inject(SignUpModel signUpModel);
    void Inject(SignUpActivity signUpActivity);
    void Inject(HomeActivity homeActivity);

}
