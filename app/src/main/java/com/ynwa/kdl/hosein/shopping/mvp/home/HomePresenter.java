package com.ynwa.kdl.hosein.shopping.mvp.home;

import android.annotation.SuppressLint;
import android.content.Context;

import com.ynwa.kdl.hosein.shopping.realm_db.Phone;
import com.ynwa.kdl.hosein.shopping.retrofit.ApiServiceWalMarket;
import com.ynwa.kdl.hosein.shopping.retrofit.model.Search.Search;
import com.ynwa.kdl.hosein.shopping.retrofit.model.paginated.Paginated;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmResults;
import io.realm.Sort;

public class HomePresenter implements Home.Presenter {

    private Context context;
    private Home.View mView;
    private Home.Model mModel ;


    public HomePresenter(Context context, Home.View mView) {
        this.context = context;
        this.mView = mView;
        this.mModel = new HomeModel();
    }


    @Override
    public void reqNewPhones() {

        int year = Calendar.getInstance().get(Calendar.YEAR);
        mModel.fetchNewPhones(year, new Home.Model.CallbackAllPhones() {
            @Override
            public void allPhones(RealmResults<Phone> phones) {
                if (phones.size() > 0){
                    mView.showNewPhones(phones);
                }
            }
        });
    }

    @Override
    public void reqSuggestPhones() {
        mModel.fetchSuggestPhones(new Home.Model.CallbackRandomPhones() {
            @Override
            public void allPhones(List<Phone> phones) {

                mView.showSuggestPhones(phones);
            }
        });

    }

    @Override
    public void reqTestRX(String s, ApiServiceWalMarket apiService) {
        mView.showLoading();
        mModel.getTestRX(s, apiService, new Home.Model.CallbackFlowableData() {
            @SuppressLint("CheckResult")
            @Override
            public void getFlowable(Flowable<Paginated> flowable) {
                flowable.timeout(10, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mView.searchFlowablePaginated());
            }
        });
    }


    @Override
    public void reqDataFromServerRX(String txtSearch) {
      /* mView.showLoading();
        mModel.getSearchDataRX(txtSearch, new Home.Model.CallbackFlowableData() {
            @Override
            public void getFlowable(Flowable<Search> flowable) {
                flowable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mView.searchFlowable());
            }
        });*/
    }

    @Override
    public void reqDataFromServer() {
        mView.showLoading();

        mModel.getSearchData(new Home.Model.CallBackData() {
            @Override
            public void onSucces(Search data) {

                if (data != null)
                {
                    if (data.getItems().size()> 0)
                    {
                       // mView.setDataToRecycler(setAdapter(data.getItems()));
                    }
                    else
                        mView.toastMessage("data size: == 0");
                    mView.hideLoading();
                }
                else
                    mView.toastMessage("data == null");
                mView.hideLoading();
            }

            @Override
            public void onFailure(String msg) {
                mView.hideLoading();
                mView.toastMessage(msg);
            }
        });

        /*List<ItemPaginated> items = mModel.reqSearch("note 8");
        if (items != null && items.size()> 0 )
        {
            mView.toastMessage("ارتباط صحیح");
            //mView.toastMessage("size: "+ data.getItems().size());
            mView.showLoading();
            //mView.setDataToRecycler(setAdapter(data.getItems()));
            mView.hideLoading();
        }
        else
        {
            mView.toastMessage("عدم ارتباط با سرور");
        }*/
    }


}
