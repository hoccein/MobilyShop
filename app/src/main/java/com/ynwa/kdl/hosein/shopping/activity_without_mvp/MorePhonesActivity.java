package com.ynwa.kdl.hosein.shopping.activity_without_mvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ynwa.kdl.hosein.shopping.MyUtils;
import com.ynwa.kdl.hosein.shopping.R;
import com.ynwa.kdl.hosein.shopping.adapter.MorePhonesAdapter;
import com.ynwa.kdl.hosein.shopping.adapter.MyRCAdapter;
import com.ynwa.kdl.hosein.shopping.parcelable.PhonesListParcel;
import com.ynwa.kdl.hosein.shopping.realm_db.Phone;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MorePhonesActivity extends AppCompatActivity {

    @BindView(R.id.rcv__more_phones)
    RecyclerView rcvMorePhones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_phones);
        ButterKnife.bind(this);

        PhonesListParcel phonesListParcel = getIntent().getParcelableExtra(MyUtils.MORE_PHONES_EXTRA);


        rcvMorePhones.setNestedScrollingEnabled(false);
        rcvMorePhones.setHasFixedSize(true);
        rcvMorePhones.setLayoutManager(new GridLayoutManager(this, 2));
        rcvMorePhones.setAdapter(new MorePhonesAdapter(this, phonesListParcel.getParcelList()));


    }
}
