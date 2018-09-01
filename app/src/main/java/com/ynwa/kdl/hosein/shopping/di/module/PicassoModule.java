package com.ynwa.kdl.hosein.shopping.di.module;

import android.content.Context;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.ynwa.kdl.hosein.shopping.di.qualifier.OnePicassoQualifier;
import com.ynwa.kdl.hosein.shopping.di.qualifier.TwoPicassoQualifier;
import com.ynwa.kdl.hosein.shopping.di.scope.ApiServiceScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = {ApiServiceModule.class, ContextModule.class})
public class PicassoModule {

    @Provides
    @ApiServiceScope
    public Picasso getPicasso(OkHttp3Downloader downloader, Context context){
        return new Picasso.Builder(context)
                .downloader(downloader)
                .build();
    }

    @Provides
    @ApiServiceScope
    public OkHttp3Downloader getDownloader(OkHttpClient client){
        return new OkHttp3Downloader(client);
    }
}
