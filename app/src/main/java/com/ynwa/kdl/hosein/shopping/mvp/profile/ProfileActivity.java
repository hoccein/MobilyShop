package com.ynwa.kdl.hosein.shopping.mvp.profile;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.rd.PageIndicatorView;
import com.ynwa.kdl.hosein.shopping.MyUtils;
import com.ynwa.kdl.hosein.shopping.R;
import com.ynwa.kdl.hosein.shopping.adapter.CustomViewPagerAdapter;
import com.ynwa.kdl.hosein.shopping.parcelable.PhoneParcel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    public static final String PHONE_PROFILE = "phone_item_profile";

    @BindView(R.id.appbar_profile)
    AppBarLayout appBar;

    @BindView(R.id.collapse_profile)
    CollapsingToolbarLayout collaps;

    @BindView(R.id.toolbar_profile)
    Toolbar toolbar;

    @BindView(R.id.view_pager_profile)
    ViewPager viewPager;

    @BindView(R.id.indicator_profile)
    PageIndicatorView indicator;


    @BindView(R.id.tv_info)
    TextView tvInfo;

    @BindView(R.id.ic_favorite_toolbar_profile)
    ImageButton icFavorite;
    @BindView(R.id.ic_back_toolbar_profile)
    ImageButton icBack;
    @BindView(R.id.ic_buybasket_toolbar_profile)
    ImageButton icBuyBasket;

    @BindView(R.id.tv_name_profile)
    TextView tvName;

    @BindView(R.id.tv_brand_profile)
    TextView tvBrand;

    @BindView(R.id.tv_price_profile)
    TextView tvPrice;

    @BindView(R.id.tv_description_profile)
    TextView tvDescription;

    private boolean isShow;
    private int scrollRange = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        appBar.addOnOffsetChangedListener(this);

        PhoneParcel phone = getIntent().getParcelableExtra(PHONE_PROFILE);

        viewPager.setAdapter(new CustomViewPagerAdapter(this, phone.getOtherImg()));

        if (phone.getOtherImg().size() > 0){
            indicator.setCount( phone.getOtherImg().size() );
        }


        tvName.setText("گوشی مدل  "+ phone.getName());
        tvBrand.setText(phone.getBrand());
        tvPrice.setText(MyUtils.priceSeprator(phone.getPrice())+ " تومان");




        String strSimCard = "- "+ phone.getDetail().getSimNumber()+ "\n\n";
        String strScreen = "- صفحه نمایش "+ phone.getDetail().getScreenSize()+ "\n\n";
        String strGpu = "- گرافیک "+ phone.getDetail().getGpu()+ "\n\n";
        String strCpu = "- تراشه پردازنده "+ phone.getDetail().getCpuChip()+ "\n\n";
        String strRam = "- رم "+ phone.getDetail().getRamSize()+ "\n\n";
        String strCamera = "- دوربین اصلی "+ phone.getDetail().getCameraMain()+ "\n";

        tvInfo.setText(strSimCard+ strScreen+ strGpu+ strCpu+ strRam+ strCamera);

        if (phone.getDesc().isEmpty())
            tvDescription.setText("---");
        else
            tvDescription.setText(phone.getDesc());

    }

    @OnClick({R.id.ic_back_toolbar_profile})
    public void btnBack(){
        finish();
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        if (scrollRange == -1) {
            scrollRange = appBarLayout.getTotalScrollRange();
            icBack.setColorFilter( getResources().getColor(R.color.gray_icon) );
            icBuyBasket.setColorFilter( getResources().getColor(R.color.gray_icon) );
        }
        if (scrollRange + verticalOffset == 0) {
            //collapse map
            //TODO: change share icon color - set white share icon
            isShow = true;
            //
            icBack.setColorFilter( getResources().getColor(R.color.white) );
            icBuyBasket.setColorFilter( getResources().getColor(R.color.white) );
        } else if (isShow) {
            //expanded map
            //TODO: change share icon color - set dark share icon
            isShow = false;
            icBack.setColorFilter( getResources().getColor(R.color.gray_icon) );
            icBuyBasket.setColorFilter( getResources().getColor(R.color.gray_icon) );
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
