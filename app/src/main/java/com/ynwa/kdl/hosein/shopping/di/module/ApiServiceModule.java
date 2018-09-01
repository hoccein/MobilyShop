package com.ynwa.kdl.hosein.shopping.di.module;


import android.content.Context;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;

import com.ynwa.kdl.hosein.shopping.MyUtils;
import com.ynwa.kdl.hosein.shopping.di.qualifier.AuthorizationQualifier;
import com.ynwa.kdl.hosein.shopping.di.qualifier.WalmarketQualifier;
import com.ynwa.kdl.hosein.shopping.retrofit.ApiServiceWalMarket;
import com.ynwa.kdl.hosein.shopping.retrofit.Authentication_Service.ApiServiceAuth;
import com.ynwa.kdl.hosein.shopping.di.scope.ApiServiceScope;
import com.ynwa.kdl.hosein.shopping.pojo.LoginStatus;


import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;

import dagger.Module;
import dagger.Provides;
import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(includes = ContextModule.class)
public class ApiServiceModule {

    public static final String BASE_URL = "http://api.walmartlabs.com/";

    private static final String PROXY_HOST = "Proxy.20DNS.CO";
    private static final int PROXY_PORT = 2076;
    private static final String PROXY_USERNAME = "20speed372342";
    private static final String PROXY_PASSWORD = "250313";

    public ApiServiceModule() {
        //"password"
    }



    @Provides
    @ApiServiceScope
    public LoginStatus getLoginStatus(){
        return new LoginStatus();
    }

    @Provides
    @ApiServiceScope
    @AuthorizationQualifier
    public Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(MyUtils.BASE_URL_LOGIN_REGISTER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @ApiServiceScope
    @WalmarketQualifier
    public Retrofit getRetrofitWalmarket(OkHttpClient client){
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ApiServiceScope
    public OkHttpClient getClient(Proxy proxy, Cache cache){
        return new OkHttpClient.Builder()
                .cache(cache)
                .proxy(proxy)
                .proxyAuthenticator(new Authenticator() {
                    @Nullable
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        String credential = Credentials.basic(PROXY_USERNAME, PROXY_PASSWORD);
                        return response.request().newBuilder()
                                .header("Proxy-Authorization", credential)
                                .build();
                    }
                })
                .build();
    }

    @Provides
    @ApiServiceScope
    public Proxy getProxy(){
        return new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(PROXY_HOST, PROXY_PORT));
    }

    @Provides
    @ApiServiceScope
    public Cache cache(File cacheFile){
        return new Cache(cacheFile, 5 * 1000 * 1000);
    }

    @Provides
    @ApiServiceScope
    public File file (Context context){
        return new File(context.getCacheDir(), "okhttp_cache");
    }


    @Provides
    @ApiServiceScope
    public ApiServiceAuth getApiservice(@AuthorizationQualifier Retrofit retrofit){
        return retrofit.create(ApiServiceAuth.class);
    }

    @Provides
    @ApiServiceScope
    public ApiServiceWalMarket getApiServiceWalMarket(@WalmarketQualifier Retrofit retrofit){
        return retrofit.create(ApiServiceWalMarket.class);
    }

    @Provides
    @ApiServiceScope
    public HashMap<String, String> getHmap(){
        return new HashMap<>();
    }

}
