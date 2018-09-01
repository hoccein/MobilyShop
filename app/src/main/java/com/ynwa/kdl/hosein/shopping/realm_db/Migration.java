package com.ynwa.kdl.hosein.shopping.realm_db;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema schema = realm.getSchema();

        // add field priceInt (int)
        if (oldVersion == 0){
            RealmObjectSchema phoneSchema = schema.get("Phone");
            phoneSchema.addField("priceInt", int.class);
        }

        // remove field price (string)
        if (oldVersion == 1){
            RealmObjectSchema phoneSchema = schema.get("Phone");
            phoneSchema.removeField("price");
        }

        // rename field priceInt to price
        if (oldVersion == 2){
            RealmObjectSchema phoneSchema = schema.get("Phone");
            phoneSchema.renameField("priceInt", "price");
        }

        // add field year
        if (oldVersion == 3){
            RealmObjectSchema phoneSchema = schema.get("Phone");
            phoneSchema.addField("year", int.class);
        }
    }
}
