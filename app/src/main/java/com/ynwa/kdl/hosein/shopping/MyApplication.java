package com.ynwa.kdl.hosein.shopping;

import android.app.Application;

import com.ynwa.kdl.hosein.shopping.realm_db.Migration;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // config font
        ViewPump.init(ViewPump.builder()
                .addInterceptor(
                        new CalligraphyInterceptor(
                                new CalligraphyConfig.Builder()
                                        .setDefaultFontPath("fonts/b_yekan_plus.ttf")
                                        .setFontAttrId(R.attr.fontPath)
                                        .build()
                        )
                )
                .build()
        );

        //config realm
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myDB.realm")
                .schemaVersion(4)
                .migration(new Migration())
                .build();
        Realm.setDefaultConfiguration(config);



    }
}
