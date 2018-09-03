package com.ynwa.kdl.hosein.shopping.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.ynwa.kdl.hosein.shopping.MyUtils;
import com.ynwa.kdl.hosein.shopping.R;
import com.ynwa.kdl.hosein.shopping.mvp.profile.ProfileActivity;
import com.ynwa.kdl.hosein.shopping.parcelable.DetailParcel;
import com.ynwa.kdl.hosein.shopping.parcelable.PhoneParcel;
import com.ynwa.kdl.hosein.shopping.realm_db.Detail;
import com.ynwa.kdl.hosein.shopping.realm_db.Phone;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class MyRCAdapter extends RecyclerView.Adapter<MyRCAdapter.MyViewHolder>{

    Context context;
    List<Phone> items;
    Picasso picasso;



    @Inject
    public MyRCAdapter(Context context, Picasso picasso) {
        this.context = context;
        this.picasso = picasso;
    }

    public MyRCAdapter(Context context, List<Phone> items) {
        this.context = context;
        this.items = items;
    }

    public MyRCAdapter(Context context, List<Phone> items, Picasso picasso) {
        this.context = context;
        this.items = items;
        this.picasso = picasso;
    }


    public void setDataList(List<Phone> items){
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_recycler, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);

        final Phone item = items.get(position);
        if(item != null){

            picasso.load(item.getImage())
                    .placeholder(R.drawable.ic_no_image)
                    .error(R.drawable.ic_no_image)
                    .into(holder.ivPic);

             /*Picasso.get()
                .load(items.get(position).getLargeImage())
                .error(R.drawable.ic_no_image)
                .into(holder.ivPic);*/
            holder.tvName.setText(item.getName());
            holder.tvBrand.setText(item.getBrand());

            if (item.getPrice() > 0) {
                holder.tvPrice.setText( MyUtils.priceSeprator(item.getPrice())+ " تومان");
            }
            else {
                holder.tvPrice.setText(item.getPrice()+ " -----");
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, ""+ item.getName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ProfileActivity.class);
                    PhoneParcel phoneParcel = convertPhoneTOPhoneParcel(item);
                    intent.putExtra(MyUtils.PHONE_ITEM_Extera, phoneParcel);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public PhoneParcel convertPhoneTOPhoneParcel(Phone phone){

        List<String> colors = new ArrayList<>();

        for (int i = 0; i < phone.getColor().size(); i++) {
            colors.add(i, phone.getColor().get(i));
        }

        List<String> otherImages = new ArrayList<>();
        for (int i = 0; i < phone.getOtherImg().size(); i++) {
            otherImages.add(i, phone.getOtherImg().get(i));
        }

        DetailParcel detail = convertDetailToDetailParcelabe(phone.getDetail());

        PhoneParcel phoneParcel = new PhoneParcel(
                phone.getId(),
                phone.getName(),
                phone.getBrand(),
                phone.getDesc(),
                phone.getImage(),
                phone.getYear(),
                phone.getPrice(),
                colors,
                detail,
                otherImages
        );
        return phoneParcel;
    }

    public DetailParcel convertDetailToDetailParcelabe(Detail detail){

        List<String> sensors = new ArrayList<>();
        for (int i = 0; i < detail.getSensors().size(); i++) {
            sensors.add(i, detail.getSensors().get(i));
        }

        List<String> features = new ArrayList<>();
        for (int i = 0; i < detail.getFeatures().size(); i++) {
            features.add(i, detail.getFeatures().get(i));
        }

        DetailParcel parcel = new DetailParcel();
        parcel.setId( detail.getId() );
        parcel.setPhoneSize( detail.getPhoneSize() );
        parcel.setSimSize( detail.getSimSize() );
        parcel.setWeight( detail.getWeight() );
        parcel.setSimNumber( detail.getSimNumber() );
        parcel.setRamSize( detail.getRamSize() );
        parcel.setRamType( detail.getRamType() );
        parcel.setStorageSize( detail.getStorageSize() );
        parcel.setCpuChip( detail.getCpuChip() );
        parcel.setCpuName( detail.getCpuName() );
        parcel.setCpuType( detail.getCpuType() );
        parcel.setCpuFrequency( detail.getCpuFrequency() );
        parcel.setGpu( detail.getGpu() );
        parcel.setScreenType( detail.getScreenType() );
        parcel.setScreenSize( detail.getScreenSize() );
        parcel.setScreenResolution( detail.getScreenResolution() );
        parcel.setScreenPixel( detail.getScreenPixel() );
        parcel.setCameraFront( detail.getCameraFront() );
        parcel.setCameraMain( detail.getCameraMain() );
        parcel.setCameraFilmed( detail.getCameraFilmed() );
        parcel.setSensors( sensors );
        parcel.setOs( detail.getOs() );
        parcel.setBattery( detail.getBattery() );
        parcel.setFeatures( features );

        return  parcel;
    }

    class MyViewHolder extends RecyclerView.ViewHolder  {

        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_brand)
        TextView tvBrand;
        @BindView(R.id.tv_price)
        TextView tvPrice;



        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }
}
