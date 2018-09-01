package com.ynwa.kdl.hosein.shopping.realm_db;

import android.util.Log;

import com.ynwa.kdl.hosein.shopping.MyUtils;

import io.realm.Realm;
import io.realm.RealmResults;

public class PhoneDAO {

    private final String TAG="REALM_TAG";
    private Realm realm;

    public PhoneDAO(){
        realm = Realm.getDefaultInstance();
    }

    public void save(final Phone phone){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Number currentId = realm.where(Phone.class).max("id");
                //update (if id exist)
                if (phone.getId() != 0) {
                    phone.setId(phone.getId());
                } else {
                    //insert new record
                    phone.setId(MyUtils.incrementId(currentId));
                }
                realm.copyToRealmOrUpdate(phone);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "onSuccess ---> phonDAO: " + "saved");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.i(TAG, "onError ---> phonDAO: "+ error.getMessage());
            }
        });
    }

    public void update(final Phone phone){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number currentId = realm.where(Phone.class).max("id");
                //update (if id exist)
                if (phone.getId() != 0) {
                    phone.setId(phone.getId());
                } else {
                    //insert new record
                    phone.setId(MyUtils.incrementId(currentId));
                }
                realm.copyToRealmOrUpdate(phone);
                Log.i(TAG, "phonDAO ---> updated: "+ phone.getName()+" - price: "+phone.getPrice());
            }
        });
    }

    public RealmResults<Phone> findAllPhones(){
        return realm.where(Phone.class).findAll();
    }

    public Phone findPhoneById(long id){
        return realm.where(Phone.class).equalTo("id", id).findFirst();
    }

    public void deleteAllPhones(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                findAllPhones().deleteAllFromRealm();
            }
        });
    }

    public void deletePhoneById(final long id){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                findPhoneById(id).deleteFromRealm();
            }
        });
    }

    public void close(){
        realm.close();
    }



}
