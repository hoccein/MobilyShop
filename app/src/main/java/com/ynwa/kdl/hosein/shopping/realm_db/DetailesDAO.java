package com.ynwa.kdl.hosein.shopping.realm_db;

import android.util.Log;

import com.ynwa.kdl.hosein.shopping.MyUtils;

import io.realm.Realm;
import io.realm.RealmResults;

public class DetailesDAO {

    private final String TAG="REALM_TAG";
    private Realm realm;

    public DetailesDAO(){
        realm = Realm.getDefaultInstance();
    }

    public void save(final Detail detail){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Number currentId = realm.where(Detail.class).max("id");

                //update (if id exist)
                if (detail.getId() != 0) {
                    detail.setId(detail.getId());
                } else {
                    //insert new record
                    detail.setId(MyUtils.incrementId(currentId));
                }
                realm.copyToRealmOrUpdate(detail);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "onSuccess ---> detailDAO: " + "saved");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.i(TAG, "OnError ---> detailDAO: " + error.getMessage());
            }
        });
    }

    public RealmResults<Detail> findAllDetailes(){
        return realm.where(Detail.class).findAll();
    }

    public Detail findDetaileById(long id){
        return realm.where(Detail.class).equalTo("id", id).findFirst();
    }

    public void deleteAllDetails(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                findAllDetailes().deleteAllFromRealm();
            }
        });
    }

    public void deleteDetaileById(final long id){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                findDetaileById(id).deleteFromRealm();
            }
        });
    }

    public void close(){
        realm.close();
    }
}
