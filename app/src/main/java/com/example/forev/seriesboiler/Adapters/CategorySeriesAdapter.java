package com.example.forev.seriesboiler.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.forev.seriesboiler.Fragments.HomeFragment;
import com.example.forev.seriesboiler.Fragments.SeriesDetailsFragment;
import com.example.forev.seriesboiler.Models.CategoryModel;
import com.example.forev.seriesboiler.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategorySeriesAdapter extends RecyclerView.Adapter<CategorySeriesAdapter.MyViewHolder> {

    Context context;
    Fragment fragment;
    List<CategoryModel> catList;

    public CategorySeriesAdapter(Context context, List<CategoryModel> catList) {
        this.context = context;
        this.catList = catList;
    }

    @NonNull
    @Override
    public CategorySeriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_series,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategorySeriesAdapter.MyViewHolder myViewHolder, final int i) {

        String a = catList.get(i).getTitle();
        if(a.length()>14)
        {
            a = catList.get(i).getTitle().toString().substring(0, 14)+"..";
        }
        myViewHolder.catTitle.setText(a);

        Picasso.get().load(catList.get(i).getImg()).into(myViewHolder.catImage);

       myViewHolder.catLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("title",catList.get(i).getTitle());
                bundle.putString("imgurl",catList.get(i).getImg().toString());
                bundle.putString("id",catList.get(i).getSeriesid().toString());
                bundle.putString("desc",catList.get(i).getDescription().toString());

                fragment = new SeriesDetailsFragment();
                fragment.setArguments(bundle);
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainframe,fragment,"fragment")
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
                HomeFragment.VALUE = 0;
            }
        });

    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView catTitle;
        private ImageView catImage;
        private ConstraintLayout catLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            catTitle = itemView.findViewById(R.id.catTextView);
            catImage = itemView.findViewById(R.id.catImageView);
            catLayout = itemView.findViewById(R.id.catLayout);
        }
    }
}
