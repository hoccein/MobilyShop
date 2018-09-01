package com.ynwa.kdl.hosein.shopping.di.module;

import android.content.Context;

import com.ynwa.kdl.hosein.shopping.di.scope.ApiServiceScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApiServiceScope
    public Context contextHomeActivity(){
        return context;
    }
}
