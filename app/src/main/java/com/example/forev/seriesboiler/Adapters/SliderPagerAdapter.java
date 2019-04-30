package com.example.forev.seriesboiler.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.forev.seriesboiler.Models.Slide;
import com.example.forev.seriesboiler.R;

import java.util.List;

public class SliderPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Slide> mList;

    public SliderPagerAdapter(Context context, List<Slide> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = layoutInflater.inflate(R.layout.item_slide,null);

        ImageView slideImg = slideLayout.findViewById(R.id.slide_img);
        slideImg.setImageResource(mList.get(position).getImage());

        TextView slide_title = slideLayout.findViewById(R.id.slide_title);
        slide_title.setText(mList.get(position).getTitle().toString());

        TextView slideText = slideLayout.findViewById(R.id.slide_text);
        slideText.setText(mList.get(position).getText().toString());


        container.addView(slideLayout);
        return slideLayout;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
