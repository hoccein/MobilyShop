package com.ynwa.kdl.hosein.shopping.retrofit;

import com.ynwa.kdl.hosein.shopping.retrofit.model.Search.Items;
import com.ynwa.kdl.hosein.shopping.retrofit.model.Search.Search;
import com.ynwa.kdl.hosein.shopping.retrofit.model.paginated.Paginated;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceWalMarket {

    @GET("v1/items/{item_id}")
    Call<Items> getItem(@Path("item_id") int itemId,
                        @Query("format") String format,
                        @Query("apiKey") String apiKey);

    @GET("v1/search")
    Call<Search> getItemSearch(@Query("query") String txtSearch,
                               @Query("format") String format,
                               @Query("apiKey") String apiKey);


    @GET("v1/search")
    Flowable<Search> getItemSearchRX(@Query("query") String txtSearch,
                                     @Query("format") String format,
                                     @Query("apiKey") String apiKey);

    @GET("v1/paginated/items")
    Flowable<Paginated> samsungItemSearchRX(

            @Query("format") String format,
            @Query("categoryId") String ctgId,
            @Query("apiKey") String apiKey
    );
}
