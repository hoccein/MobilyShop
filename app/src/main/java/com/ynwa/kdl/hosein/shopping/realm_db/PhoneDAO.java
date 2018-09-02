package com.ynwa.kdl.hosein.shopping.realm_db;

import android.util.Log;

import com.ynwa.kdl.hosein.shopping.MyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

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

    public RealmResults<Phone> findNewPhones(int year){
        RealmResults<Phone> phones = realm.where(Phone.class).equalTo("year", year).findAll().sort("price", Sort.DESCENDING);

        // check any mobile is exist in current year
        //if not exist get last year
        if (phones.size()==0) {
            year--;
            return findNewPhones(year);
        }
        return phones;
    }

    public List<Phone> findRandomPhones(){

        long randomId = 0;
        List<Phone> randomList = new ArrayList<>();
        RealmResults<Phone> list = realm.where(Phone.class).findAll();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            randomId = random.nextInt(list.size());
            randomList.add(findPhoneById(randomId));
        }
        return randomList;
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
