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
import com.example.forev.seriesboiler.Models.SeriesModel;
import com.example.forev.seriesboiler.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewSeriesAdapter extends RecyclerView.Adapter<NewSeriesAdapter.MyViewHolder> {

    Context context;
    Fragment fragment;
    List<SeriesModel> seriesList;

    public NewSeriesAdapter(Context context, List<SeriesModel> seriesList) {
        this.context = context;
        this.seriesList = seriesList;
    }

    @NonNull
    @Override
    public NewSeriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_popular_series,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewSeriesAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.popularTitle.setText(seriesList.get(i).getTitle());
        Picasso.get().load(seriesList.get(i).getImg()).resize(0,175).into(myViewHolder.popularImage);

        myViewHolder.popularLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("title",seriesList.get(i).getTitle());
                bundle.putString("imgurl",seriesList.get(i).getImg().toString());
                bundle.putString("id",seriesList.get(i).getId().toString());
                bundle.putString("desc",seriesList.get(i).getDescription().toString());

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
        return seriesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView popularTitle;
        private ImageView popularImage;
        private ConstraintLayout popularLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            popularTitle = itemView.findViewById(R.id.popularTextView);
            popularImage = itemView.findViewById(R.id.popularImageView);
            popularLayout = itemView.findViewById(R.id.popularLayout);
        }
    }
}
