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
import com.example.forev.seriesboiler.Models.AllMyListModel;
import com.example.forev.seriesboiler.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {

    Context context;
    Fragment fragment;
    List<AllMyListModel> seriesList;

    public MyListAdapter(Context context, List<AllMyListModel> seriesList) {
        this.context = context;
        this.seriesList = seriesList;
    }

    @NonNull
    @Override
    public MyListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mylist,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.MyViewHolder myViewHolder, final int i) {

        myViewHolder.myListTitle.setText(seriesList.get(i).getTitle());
        Picasso.get().load(seriesList.get(i).getImg()).resize(0,175).into(myViewHolder.myListImage);

        myViewHolder.layoutmylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("title",seriesList.get(i).getTitle());
                bundle.putString("imgurl",seriesList.get(i).getImg().toString());
                bundle.putString("id",seriesList.get(i).getSeriesid().toString());
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
        private TextView myListTitle;
        private ImageView myListImage;
        private ConstraintLayout layoutmylist;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            myListTitle = itemView.findViewById(R.id.myListTextView);
            myListImage = itemView.findViewById(R.id.myListImageView);

            layoutmylist = itemView.findViewById(R.id.layoutmylist);
        }
    }
}
