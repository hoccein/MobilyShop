package com.ynwa.kdl.hosein.shopping.mvp.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rd.PageIndicatorView;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;
import com.ynwa.kdl.hosein.shopping.MyUtils;
import com.ynwa.kdl.hosein.shopping.R;
import com.ynwa.kdl.hosein.shopping.adapter.MyRCAdapter;
import com.ynwa.kdl.hosein.shopping.adapter.ViewPagerAdapter;
import com.ynwa.kdl.hosein.shopping.di.component.DaggerApiServiceComponent;
import com.ynwa.kdl.hosein.shopping.di.module.ApiServiceModule;
import com.ynwa.kdl.hosein.shopping.di.module.ContextModule;
import com.ynwa.kdl.hosein.shopping.di.module.PicassoModule;
import com.ynwa.kdl.hosein.shopping.eventbus.BusStation;
import com.ynwa.kdl.hosein.shopping.eventbus.Message;
import com.ynwa.kdl.hosein.shopping.mvp.login.LoginActivity;
import com.ynwa.kdl.hosein.shopping.realm_db.Phone;
import com.ynwa.kdl.hosein.shopping.retrofit.ApiServiceWalMarket;
import com.ynwa.kdl.hosein.shopping.retrofit.RetrofitClient;
import com.ynwa.kdl.hosein.shopping.retrofit.model.Search.Items;
import com.ynwa.kdl.hosein.shopping.retrofit.model.Search.Search;
import com.ynwa.kdl.hosein.shopping.retrofit.model.paginated.ItemPaginated;
import com.ynwa.kdl.hosein.shopping.retrofit.model.paginated.Paginated;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import io.reactivex.functions.Consumer;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener, Home.View {

    public static final String API_KEY = "fvcxmxuuc8yk2qb5xcc9mamk";

    Home.Presenter mPresenter ;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.drawer)
    NavigationView drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.ib_toolbar_menu)
    ImageButton menuToolbar;

    @BindView(R.id.av_load)
    AVLoadingIndicatorView avLoading;

    @BindView(R.id.view_Pager)
    ViewPager viewPager;

    @BindView(R.id.indicator)
    PageIndicatorView indicator;

    @BindView(R.id.layout_rcv_one)
    RelativeLayout layout_rcv_one;
    @BindView(R.id.tv_all__new_phones_row__home_activity)
    TextView tvAll_newPhonesRow;
    @BindView(R.id.rc_new_phones__home_activity)
    RecyclerView rcvNewPhones;

    @BindView(R.id.layout_rcv_two)
    RelativeLayout layout_rcv_two;
    @BindView(R.id.tv_all__suggest_phones_row__home_activity)
    TextView tvAll_SuggestPhonesRow;
    @BindView(R.id.rc_suggest_phones__home_activity)
    RecyclerView rcvSuggestPhones;

    @BindView(R.id.nav_drawer)
     View nav_drawer_layout;



    @Inject
    ApiServiceWalMarket apiServiceWalMarket;

    @Inject
    Picasso picasso;

    @Inject
    MyRCAdapter adapterTest;
    @Inject
    MyRCAdapter adapterSuggest;



    private DrawerLayoutItems drawerLayoutItems;
    private List<Drawable> imageList = new ArrayList<>();
    private boolean loggedIn = false;


//------------------------------------ navigation drawer items ----------------------------
    static class DrawerLayoutItems {

    //header items
        @BindView(R.id.iv_close)
        ImageView ivHeader_close;

        @BindView(R.id.tv_header_name)
        TextView tvHeader_name;

        @BindView(R.id.tv_header_email)
        TextView tvHeader_email;

        @BindView(R.id.iv_login_logout)
        ImageView ivLoginLogout;

        @BindView(R.id.tv_login_logout)
        TextView tvLoginLogout;

    //menu items
        @BindView(R.id.drawer_item__home)
        LinearLayout drawerItem_home;

    @BindView(R.id.drawer_item__category)
    LinearLayout drawerItem_category;

        @BindView(R.id.drawer_item__basket)
        RelativeLayout drawerItem_buyBasket;
        //child item (counter)
            @BindView(R.id.tv_buy_basket_count)
            TextView tvBuyBasketCount;

        @BindView(R.id.drawer_item_like)
        LinearLayout drawerItem_lik;

        @BindView(R.id.drawer_item_buy_list)
        LinearLayout drawerItem_buyList;

        @BindView(R.id.drawer_item__account)
        LinearLayout drawerItem_account;

        @BindView(R.id.drawer_item__login_logout)
        LinearLayout drawerItem_login_logout;

    }

//set font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        drawerLayoutItems = new DrawerLayoutItems();
        ButterKnife.bind(drawerLayoutItems, nav_drawer_layout);
        init();
        listeners();


        DaggerApiServiceComponent.builder()
                .contextModule(new ContextModule(this))
                .apiServiceModule(new ApiServiceModule())
                .picassoModule(new PicassoModule())
                .build()
                .Inject(HomeActivity.this);

        checkUserLoggedIn();
        //reqRefreshToken();

        // slider
        viewPager.setAdapter(new ViewPagerAdapter(this, imageList));
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 3000, 6000);


        //row NewPhones
        goneRowNewPhones();
        goneRowSuggestPhones();
        showLoading();
        mPresenter.reqNewPhones();
        mPresenter.reqSuggestPhones();

    }

    @Override
    public void init() {

        setSupportActionBar(toolbar);

        mPresenter = new HomePresenter(this, this);

        rcvNewPhones.setHasFixedSize(true);
        rcvNewPhones.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        rcvSuggestPhones.setHasFixedSize(true);
        rcvSuggestPhones.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        imageList.add(getResources().getDrawable(R.drawable.samsung));
        imageList.add(getResources().getDrawable(R.drawable.huawei));
        imageList.add(getResources().getDrawable(R.drawable.iphone));
    }

    @Override
    public void listeners() {
        menuToolbar.setOnClickListener(this);
    }

    @Override
    public void showNewPhones(RealmResults<Phone> phones) {
        if (phones.size()<10){
            tvAll_newPhonesRow.setVisibility(View.INVISIBLE);
        }
        adapterTest.setDataList(phones);
        rcvNewPhones.setAdapter(adapterTest);

        hideLoading();
    }

    @Override
    public void showSuggestPhones(List<Phone> phones) {

        adapterSuggest.setDataList(phones);
        rcvSuggestPhones.setAdapter(adapterSuggest);
        rcvSuggestPhones.setHasFixedSize(true);

        hideLoading();
    }

    private void reqRefreshToken() {
        /*ApiServiceAuth service = ApiClient.getClient().create(ApiServiceAuth.class);

        HashMap<String, String> body = new HashMap<>();
        body.put("grant_type", "refresh_token");
        body.put("scope", "");
        body.put("refresh_token", MyUtils.getRefreshToken(this));

        Call<LoginObject> registerCall = service.refreshToken(body);
        registerCall.enqueue(new Callback<LoginObject>() {
            @Override
            public void onResponse(Call<LoginObject> registerCall, Response<LoginObject> response) {

                Log.d("reqLogin", response.message());
                LoginObject object = response.body();

                if (object != null)
                {
                    Log.d("reqLogin", "object have value");
                }
                else {
                    Log.d("reqLogin", "null object");
                }
            }

            @Override
            public void onFailure(Call<LoginObject> registerCall, Throwable t) {
                String m = t.getUserEmail();
                Log.d("reqLogin", "onFailure: "+ m);
            }
        });*/
    }

    public Consumer<Paginated> searchFlowablePaginated() {
        return new Consumer<Paginated>() {

            @Override
            public void accept(Paginated pa) throws Exception {
                List<ItemPaginated> limitedList = new ArrayList<>();
                for (int i =0 ; i < 10 ; i++){
                    limitedList.add(pa.getItems().get(i));
                }

                /*adapterTest.setDataList(limitedList);
                rcvNewPhones.setAdapter(adapterTest);
                rcvNewPhones.setHasFixedSize(true);
                hideLoading();*/
            }
        };
    }

    @Override
    public Consumer<Search> searchFlowable() {
        return new Consumer<Search>() {

            @Override
            public void accept(Search search) throws Exception {
              /*  List<ItemPaginated> limitedList = new ArrayList<>();
                for (int i =0 ; i < 10 ; i++){
                    limitedList.add(search.getItems().get(i));
                }

                adapterTest.setDataList(limitedList);
                rcvNewPhones.setAdapter(adapterTest);
                rcvNewPhones.setHasFixedSize(true);
                hideLoading();*/
            }
        };
    }

    private void reqItem(int itemId) {
        ApiServiceWalMarket service = RetrofitClient.getInstance().create(ApiServiceWalMarket.class);
        Call<Items> call = service.getItem(itemId, "json", API_KEY);
        call.enqueue(new Callback<Items>() {
            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {
                if (response.body() != null) {


                }
                else
                {
                    Toast.makeText(HomeActivity.this, "null req", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Items> call, Throwable t) {

            }
        });
    }


    private void checkUserLoggedIn() {
        //loggedIn = getIntent().getExtras().getBoolean(MyUtils.PREF_LOGIN_STATUS);
        loggedIn = MyUtils.getLoginStatus(HomeActivity.this).isLoggedIn();
        if (loggedIn == false)
            guestUser();
        else
            memberUser(MyUtils.getLoginStatus(HomeActivity.this).getUserEmail());
    }

    private void guestUser(){
        loggedIn = false;
        setHeaderViews(
                "مهمان عزیز",
                "خوش آمدید",
                R.drawable.ic_login,
                "ورود به حساب"
        );
    }

    private void memberUser(String emailUser){
        loggedIn = true;
       String nameUser = MyUtils.fetchCurrentUserInfoFromPref(this, emailUser).getName();
        if (MyUtils.checkUserPref(this))
            setHeaderViews(nameUser, emailUser, R.drawable.ic_logout_drawer_item,"خروج");
    }

    private void setHeaderViews(String name, String email,int img, String status){

            drawerLayoutItems.tvHeader_name.setText(name);
            drawerLayoutItems.tvHeader_email.setText(email);
            drawerLayoutItems.ivLoginLogout.setImageResource(img);
            drawerLayoutItems.tvLoginLogout.setText(status);
    }

    public void onClickDrawerItems(View v){

        if (v == drawerLayoutItems.drawerItem_login_logout){
            if (loggedIn){
                MyUtils.saveLoginStatus(this, "logged out", false);
                drawerLayout.closeDrawer(drawer);
                guestUser();
            }else {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        }
        else if (v == drawerLayoutItems.ivHeader_close){
            drawerLayout.closeDrawer(drawer);
        }
        else if (v == drawerLayoutItems.drawerItem_home){
            toastMessage("home");
        }
        else if (v == drawerLayoutItems.drawerItem_account){
            toastMessage("account");
        }
    }

    @Override
    public void onClick(View v) {

        if (v == menuToolbar)
        {
            drawerLayout.openDrawer(drawer);
        }
    }

    @Override
    public void setDataToRecycler(MyRCAdapter adapter) {
        rcvNewPhones.setAdapter(adapter);
    }

    @Override
    public void toastMessage(String txt) {
        Toast.makeText(this, ""+ txt, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        avLoading.show();

    }

    @Override
    public void hideLoading() {
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               avLoading.hide();
               visibleRowNewPhones();
               visibleRowSuggestPhones();
           }
       }, 1500);
    }

    public void goneRowNewPhones(){
        layout_rcv_one.setVisibility(View.GONE);
        rcvNewPhones.setVisibility(View.GONE);
    }

    public void visibleRowNewPhones(){
        layout_rcv_one.setVisibility(View.VISIBLE);
        rcvNewPhones.setVisibility(View.VISIBLE);
    }

    public void goneRowSuggestPhones(){
        layout_rcv_two.setVisibility(View.GONE);
        rcvSuggestPhones.setVisibility(View.GONE);
    }

    public void visibleRowSuggestPhones(){
        layout_rcv_two.setVisibility(View.VISIBLE);
        rcvSuggestPhones.setVisibility(View.VISIBLE);
    }

    private class SliderTimer extends TimerTask{

        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < imageList.size()-1){
                        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    private void reqGetUser() {

       /* String s = MyUtils.getAccessToken(this);
        ApiServiceAuth service = ApiClient.getClient().create(ApiServiceAuth.class);
        Map<String, String> headers = new HashMap<>();
        String token = "Bearer "+ MyUtils.getAccessToken(this);
        headers.put("Authorization", token);
        headers.put("Accept", "application/json");
        Call<User> registerCall = service.getUser(headers);
        registerCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> registerCall, Response<User> response) {
                Log.d("reqLogin",  response.message());
                User user = response.body();
                if (user !=null && response.isSuccessful())
                {
                    MyUtils.saveUserInfoToPref(HomeActivity.this, user.getName(), user.getEmail());
                    Toast.makeText(HomeActivity.this, "لاگین شد!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(HomeActivity.this, "failed in getUser req", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> registerCall, Throwable t) {

            }
        });*/
    }

    @Subscribe
    public void reciveMessage(Message message){
        drawerLayoutItems.tvBuyBasketCount.setVisibility(View.VISIBLE);
        drawerLayoutItems.tvBuyBasketCount.setText(message.getMsg());
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusStation.getBus().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusStation.getBus().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
