package com.ynwa.kdl.hosein.shopping.activity_without_mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ynwa.kdl.hosein.shopping.R;
import com.ynwa.kdl.hosein.shopping.mvp.profile.ProfileActivity;
import com.ynwa.kdl.hosein.shopping.parcelable.PhoneParcel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ynwa.kdl.hosein.shopping.mvp.profile.ProfileActivity.PHONE_PROFILE;

public class PhoneDetailActivity extends AppCompatActivity {

    public static final String PHONE_DETAIL ="detail_PhoneDetailActivity";


    @BindView(R.id.iv_close_detail_activity)
    ImageView ivClose;
    @BindView(R.id.tv_title_detail_activity)
    TextView tvName;

    @BindView(R.id.tv_brand__detail_activity)
    TextView tvBrand;
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

        PhoneParcel phone = getIntent().getParcelableExtra(ProfileActivity.PHONE_PROFILE);


        tvName.setText(phone.getName());

        tvBrand.setText(phone.getBrand());
        tvYear.setText(String.valueOf(phone.getYear()));
        tvPhoneSize.setText(phone.getDetail().getPhoneSize());
        tvWeight.setText(phone.getDetail().getWeight());
        tvSimNumber.setText(phone.getDetail().getSimNumber());
        tvSimSize.setText(phone.getDetail().getSimSize());

        tvStorage.setText(phone.getDetail().getStorageSize());
        tvRamSize.setText(phone.getDetail().getRamSize());
        tvCpuChip.setText(phone.getDetail().getCpuChip());
        tvCpuName.setText(phone.getDetail().getCpuName());
        tvCpuType.setText(phone.getDetail().getCpuType());
        tvCpuFreq.setText(phone.getDetail().getCpuFrequency());
        tvGpu.setText(phone.getDetail().getGpu());

        tvScreenType.setText(phone.getDetail().getScreenType());
        tvScreenSize.setText(phone.getDetail().getScreenSize());
        tvScreenPixel.setText(phone.getDetail().getScreenPixel());

        tvCameraMain.setText(phone.getDetail().getCameraMain());
        tvCamerafront.setText(phone.getDetail().getCameraFront());
        tvCameraFilmed.setText(phone.getDetail().getCameraFilmed());

        tvOs.setText(phone.getDetail().getOs());
        tvBattery.setText(phone.getDetail().getBattery());

        tvSensors.setText("");
        for (String sensor : phone.getDetail().getSensors()){
            tvSensors.append(sensor+ "\n");
        }

        tvFeatures.setText("");
        for (String feature : phone.getDetail().getFeatures()){
            tvFeatures.append(feature+ "\n");
        }

    }

    @OnClick(R.id.iv_close_detail_activity)
    public void toolbarClose(){
        finish();
    }
}
