package com.ynwa.kdl.hosein.shopping.activity_without_mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ynwa.kdl.hosein.shopping.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneDetailActivity extends AppCompatActivity {


    @BindView(R.id.iv_close_detail_activity)
    ImageView ivClose;
    @BindView(R.id.tv_title_detail_activity)
    TextView tvName;

    @BindView(R.id.tv_year__detail_activity)
    TextView tvYear;
    @BindView(R.id.tv_phone_size__detail_activity)
    TextView tvPhoneSize;
    @BindView(R.id.tv_weight__detail_activity)
    TextView tvWeight;
    @BindView(R.id.tv_sim_number__detail_activity)
    TextView tvSimNumber;
    @BindView(R.id.tv_sim_size__detail_activity)
    TextView tvSimSize;

    @BindView(R.id.tv_storage__detail_activity)
    TextView tvStorage;
    @BindView(R.id.tv_ram_size__detail_activity)
    TextView tvRamSize;
    @BindView(R.id.tv_cpu_chip__detail_activity)
    TextView tvCpuChip;
    @BindView(R.id.tv_cpu_name__detail_activity)
    TextView tvCpuName;
    @BindView(R.id.tv_cpu_type__detail_activity)
    TextView tvCpuType;
    @BindView(R.id.tv_cpu_frequency__detail_activity)
    TextView tvCpuFreq;
    @BindView(R.id.tv_gpu__detail_activity)
    TextView tvGpu;

    @BindView(R.id.tv_screen_type__detail_activity)
    TextView tvScreenType;
    @BindView(R.id.tv_screen_size__detail_activity)
    TextView tvScreenSize;
    @BindView(R.id.tv_screen_pixel__detail_activity)
    TextView tvScreenPixel;

    @BindView(R.id.tv_camera_main__detail_activity)
    TextView tvCameraMain;
    @BindView(R.id.tv_camera_front__detail_activity)
    TextView tvCamerafront;
    @BindView(R.id.tv_camera_filmed__detail_activity)
    TextView tvCameraFilmed;

    @BindView(R.id.tv_os__detail_activity)
    TextView tvOs;
    @BindView(R.id.tv_battery__detail_activity)
    TextView tvBattery;
    @BindView(R.id.tv_sensors__detail_activity)
    TextView tvSensors;
    @BindView(R.id.tv_features__detail_activity)
    TextView tvFeatures;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_detail);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.iv_close_detail_activity)
    public void toolbarClose(){
        finish();
    }
}
