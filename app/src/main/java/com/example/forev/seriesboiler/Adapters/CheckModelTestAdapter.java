package com.example.forev.seriesboiler.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.forev.seriesboiler.Models.CheckModel;
import com.example.forev.seriesboiler.R;

import java.util.ArrayList;

public class CheckModelTestAdapter extends RecyclerView.Adapter<CheckModelTestAdapter.MyViewHolder> {

    Context context;
    CheckModel[] episodes;
    public ArrayList<CheckModel> checkedEpisode = new ArrayList<>();

    public CheckModelTestAdapter(Context context, CheckModel[] episodes) {
        this.context = context;
        this.episodes = episodes;
    }

    @NonNull
    @Override
    public CheckModelTestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_checkmodeltest,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckModelTestAdapter.MyViewHolder myViewHolder, final int position) {
        final CheckModel episode = episodes[position];
        myViewHolder.episodeTxt.setText(episode.getName());
        myViewHolder.myCheckBox.setChecked(episode.isSelected());


        CheckModel currentEpisode = episodes[position];
        if(myViewHolder.myCheckBox.isChecked())
        {
            currentEpisode.setSelected(true);
            checkedEpisode.add(currentEpisode);
        }
        else if(!myViewHolder.myCheckBox.isChecked())
        {
            currentEpisode.setSelected(false);
            checkedEpisode.remove(currentEpisode);
        }

        myViewHolder.setItemClickListener(new MyViewHolder.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CheckBox myCheckBox = (CheckBox) view;
                CheckModel currentEpisode = episodes[position];

                if(myCheckBox.isChecked())
                {
                    currentEpisode.setSelected(true);
                    checkedEpisode.add(currentEpisode);
                }
                else if(!myCheckBox.isChecked())
                {
                    currentEpisode.setSelected(false);
                    checkedEpisode.remove(currentEpisode);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return episodes.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView episodeTxt;
        CheckBox myCheckBox;

        ItemClickListener itemClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            episodeTxt = (TextView)itemView.findViewById(R.id.episodeTextView);
            myCheckBox = (CheckBox)itemView.findViewById(R.id.checkbox);

            myCheckBox.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener ic)
        {
            this.itemClickListener = ic;
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view,getLayoutPosition());
        }
        interface ItemClickListener{
            void onItemClick(View view,int position);
        }
    }
}
