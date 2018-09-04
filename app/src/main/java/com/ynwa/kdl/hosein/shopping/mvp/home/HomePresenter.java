package com.ynwa.kdl.hosein.shopping.mvp.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ynwa.kdl.hosein.shopping.MyUtils;
import com.ynwa.kdl.hosein.shopping.R;
import com.ynwa.kdl.hosein.shopping.activity_without_mvp.MorePhonesActivity;
import com.ynwa.kdl.hosein.shopping.activity_without_mvp.SearchActivity;
import com.ynwa.kdl.hosein.shopping.parcelable.PhoneParcel;
import com.ynwa.kdl.hosein.shopping.parcelable.PhonesListParcel;
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

    private List<PhoneParcel> phoneParc_newPhones;
    private List<PhoneParcel> phoneParc_suggestPhones;


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
                    phoneParc_newPhones = MyUtils.convertPhonesListTOPhonesParcelList(phones);
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
                phoneParc_suggestPhones = MyUtils.convertPhonesListTOPhonesParcelList(phones);
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

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tv_all__new_phones_row__home_activity:
                if (phoneParc_newPhones.size() > 0){
                    Intent intent = new Intent(context, MorePhonesActivity.class);
                    intent.putExtra(MyUtils.MORE_PHONES_EXTRA, new PhonesListParcel(phoneParc_newPhones));
                    context.startActivity(intent);
                }
                break;

            case R.id.tv_all__suggest_phones_row__home_activity:
                if (phoneParc_suggestPhones.size() > 0){
                    Intent intent = new Intent(context, MorePhonesActivity.class);
                    intent.putExtra(MyUtils.MORE_PHONES_EXTRA, new PhonesListParcel(phoneParc_suggestPhones));
                    context.startActivity(intent);
                }
                break;

            case R.id.ib_toolbar_buy_basket_home_activity:
                mView.toastMessage("shopping basket");
                break;

            case R.id.ib_toolbar_search_home_activity:
                context.startActivity(new Intent(context, SearchActivity.class));
                break;
        }
    }


}
