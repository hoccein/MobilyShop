package com.ynwa.kdl.hosein.shopping.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ynwa.kdl.hosein.shopping.MyUtils;
import com.ynwa.kdl.hosein.shopping.R;
import com.ynwa.kdl.hosein.shopping.parcelable.PhoneParcel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MorePhonesAdapter extends RecyclerView.Adapter<MorePhonesAdapter.MyMoreViewHolder> {

    private Context context;
    private List<PhoneParcel> list;

    public MorePhonesAdapter(Context context, List<PhoneParcel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyMoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_more_phones, parent, false);
        return new MyMoreViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyMoreViewHolder holder, int position) {

        holder.setIsRecyclable(false);
        PhoneParcel phone = list.get(position);

        Picasso.get()
                .load(phone.getImage())
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .into(holder.ivImage);

        holder.tvName.setText("مدل "+ phone.getName());
        holder.tvBrand.setText(phone.getBrand());
        holder.tvPrice.setText( MyUtils.priceSeprator(phone.getPrice())+ " تومان" );


    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class MyMoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_image_more_phone_item)
        ImageView ivImage;

        @BindView(R.id.tv_name_more_phone_item)
        TextView tvName;

        @BindView(R.id.tv_brand_more_phone_item)
        TextView tvBrand;

        @BindView(R.id.tv_price_more_phone_item)
        TextView tvPrice;

        public MyMoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
