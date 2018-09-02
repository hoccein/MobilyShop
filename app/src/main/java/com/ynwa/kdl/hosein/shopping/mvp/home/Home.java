package com.ynwa.kdl.hosein.shopping.mvp.home;

import com.ynwa.kdl.hosein.shopping.adapter.MyRCAdapter;
import com.ynwa.kdl.hosein.shopping.realm_db.Phone;
import com.ynwa.kdl.hosein.shopping.retrofit.ApiServiceWalMarket;
import com.ynwa.kdl.hosein.shopping.retrofit.model.Search.Search;
import com.ynwa.kdl.hosein.shopping.retrofit.model.paginated.Paginated;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.realm.RealmResults;

public interface Home {

    interface Model
    {
        void fetchNewPhones(int year, CallbackAllPhones callback);
        void fetchSuggestPhones(CallbackRandomPhones callback);
        void getTestRX(String txtSearch, ApiServiceWalMarket apiService, CallbackFlowableData callback);
        void getSearchDataRX(String txtSearch, CallbackFlowableData callback);
        void getSearchData(CallBackData callBackData);

        interface CallbackFlowableData {
            void getFlowable(Flowable<Paginated> flowable);
        }

        interface CallBackData {
            void onSucces(Search data);
            void onFailure(String msg);
        }

        interface CallbackAllPhones{
            void allPhones(RealmResults<Phone> phones);
        }

        interface CallbackRandomPhones{
            void allPhones(List<Phone> phones);
        }

    }

    interface View
    {
        void init();
        void listeners();
        void showNewPhones(RealmResults<Phone> phones);
        void showSuggestPhones(List<Phone> phones);
        Consumer<Search> searchFlowable();
        Consumer<Paginated> searchFlowablePaginated();
        void setDataToRecycler(MyRCAdapter adapter);
        void toastMessage(String txt);
        void showLoading();
        void hideLoading();
    }

    interface Presenter
    {
        void reqNewPhones();
        void reqSuggestPhones();
        void reqTestRX(String s, ApiServiceWalMarket apiService);
        void reqDataFromServerRX(String s);
        void reqDataFromServer();

    }
}
