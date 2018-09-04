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
                    PhoneParcel phoneParcel = MyUtils.convertPhoneTOPhoneParcel(item);
                    intent.putExtra(MyUtils.PHONE_ITEM_EXTRA, phoneParcel);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
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
