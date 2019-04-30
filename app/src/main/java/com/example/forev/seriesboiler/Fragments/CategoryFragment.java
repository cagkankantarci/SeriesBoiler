package com.example.forev.seriesboiler.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forev.seriesboiler.Adapters.CategorySeriesAdapter;
import com.example.forev.seriesboiler.Models.CategoryModel;
import com.example.forev.seriesboiler.R;
import com.example.forev.seriesboiler.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    View view;
    RecyclerView categoryRecyclerView;
    List<CategoryModel> list;
    CategorySeriesAdapter categorySeriesAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_category, container, false);
        define();
        String s = getArguments().getString("categoryid").toString();
        listCategory(s);
        return view;
    }

    void define()
    {
        categoryRecyclerView = (RecyclerView)view.findViewById(R.id.categoryRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        categoryRecyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
    }

    public void listCategory(String catid)
    {
        Call<List<CategoryModel>> req = ManagerAll.getInstance().getCategory(catid);
        req.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                list = response.body();
                categorySeriesAdapter = new CategorySeriesAdapter(getContext(),list);
                categoryRecyclerView.setAdapter(categorySeriesAdapter);
            }
            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {

            }
        });
    }

}
