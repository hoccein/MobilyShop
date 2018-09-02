package com.ynwa.kdl.hosein.shopping.mvp.home;

import com.ynwa.kdl.hosein.shopping.realm_db.PhoneDAO;
import com.ynwa.kdl.hosein.shopping.retrofit.ApiServiceWalMarket;
import com.ynwa.kdl.hosein.shopping.retrofit.RetrofitClient;
import com.ynwa.kdl.hosein.shopping.retrofit.model.Search.ItemSearch;
import com.ynwa.kdl.hosein.shopping.retrofit.model.Search.Search;
import com.ynwa.kdl.hosein.shopping.util.Variables;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeModel implements Home.Model {

    List<ItemSearch> itemList;
    private PhoneDAO phoneDAO = new PhoneDAO();



    @Override
    public void fetchNewPhones(int year, CallbackAllPhones callback) {
        callback.allPhones(phoneDAO.findNewPhones(year));
    }

    @Override
    public void getTestRX(String txtSearch, ApiServiceWalMarket apiService, CallbackFlowableData callback) {
        //callback.getFlowable(apiService.getItemSearchRX(txtSearch,Variables.API_JSON_DATA_FORMAT, Variables.API_KEY));
        callback.getFlowable(apiService.samsungItemSearchRX(
                Variables.API_JSON_DATA_FORMAT,
                Variables.API_CATEGORY_SAMSUNG,
                Variables.API_KEY));
    }

    // change interface callback to Search
    @Override
    public void getSearchDataRX(String txtSearch, CallbackFlowableData callback) {
        /*ApiServiceWalMarket service = RetrofitClient.getInstance().create(ApiServiceWalMarket.class);

        callback.getFlowable(service.getItemSearchRX(txtSearch, Variables.API_JSON_DATA_FORMAT, Variables.API_KEY)); */
    }




    @Override
    public void getSearchData(final CallBackData dataStatus) {

        ApiServiceWalMarket service = RetrofitClient.getInstance().create(ApiServiceWalMarket.class);
        Call<Search> call = service.getItemSearch("note 8", Variables.API_JSON_DATA_FORMAT, Variables.API_KEY);
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if (response.body() != null)
                {
                    dataStatus.onSucces(response.body());
                }
                else
                {
                    dataStatus.onFailure("onResponse: "+ response.message());
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                dataStatus.onFailure("onFailure: "+ t.getMessage());
            }
        });
    }


}
