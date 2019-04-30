package com.example.forev.seriesboiler.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forev.seriesboiler.Adapters.AdviceSeriesAdapter;
import com.example.forev.seriesboiler.Models.SeriesModel;
import com.example.forev.seriesboiler.R;
import com.example.forev.seriesboiler.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdviceFragment extends Fragment {

    View view;
    RecyclerView adviceRecyclerView;
    List<SeriesModel> list;
    AdviceSeriesAdapter adviceSeriesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_advice, container, false);
        define();
        adviceList();
        return view;
    }

    void define()
    {
        adviceRecyclerView = (RecyclerView)view.findViewById(R.id.adviceRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        adviceRecyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
    }

    private void adviceList()
    {
        Call<List<SeriesModel>> req = ManagerAll.getInstance().getAdviceSeries();
        req.enqueue(new Callback<List<SeriesModel>>() {
            @Override
            public void onResponse(Call<List<SeriesModel>> call, Response<List<SeriesModel>> response) {
                list = response.body();
                adviceSeriesAdapter = new AdviceSeriesAdapter(getContext(),list);
                adviceRecyclerView.setAdapter(adviceSeriesAdapter);
            }

            @Override
            public void onFailure(Call<List<SeriesModel>> call, Throwable t) {

            }
        });
    }


}
