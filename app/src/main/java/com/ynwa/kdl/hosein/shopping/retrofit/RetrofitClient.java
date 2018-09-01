package com.ynwa.kdl.hosein.shopping.retrofit;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitClient {

    private static Retrofit retrofit;
    public static final String BASE_URL = "http://api.walmartlabs.com/";

    private static final String PROXY_HOST = "Proxy.20DNS.CO";
    private static final int PROXY_PORT = 2076;
    private static final String PROXY_USERNAME = "20speed372342";
    private static final String PROXY_PASSWORD = "250313";

    public static Retrofit getInstance(){

        if (retrofit == null)
        {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(PROXY_HOST, PROXY_PORT));
            OkHttpClient client = new OkHttpClient.Builder()
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

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
