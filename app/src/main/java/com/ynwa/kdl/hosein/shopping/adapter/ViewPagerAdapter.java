package com.ynwa.kdl.hosein.shopping.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ynwa.kdl.hosein.shopping.R;

import java.util.List;

import javax.inject.Inject;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Drawable> list;





    public ViewPagerAdapter(Context context, List<Drawable> list){
    this.context = context;
    this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_slider, null);

        ImageView ivSlide = view.findViewById(R.id.iv_slide);
        ivSlide.setImageDrawable(list.get(position));
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 0){
                    Toast.makeText(context, ""+ position, Toast.LENGTH_SHORT).show();
                } else if (position == 1){
                    Toast.makeText(context, ""+ position, Toast.LENGTH_SHORT).show();
                } else if (position == 2){
                    Toast.makeText(context, ""+ position, Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
