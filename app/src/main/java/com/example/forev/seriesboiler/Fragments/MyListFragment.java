package com.example.forev.seriesboiler.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forev.seriesboiler.Adapters.MyListAdapter;
import com.example.forev.seriesboiler.Models.AllMyListModel;
import com.example.forev.seriesboiler.R;
import com.example.forev.seriesboiler.RestApi.ManagerAll;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyListFragment extends Fragment {

    View view;
    FirebaseAuth auth;
    FirebaseUser user;

    private List<AllMyListModel> seriesModelList;
    private RecyclerView mylistRecyclerView;
    private MyListAdapter myListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_my_list, container, false);
        define();
        listMyList();

        return view;
    }

    public void define()
    {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        mylistRecyclerView = (RecyclerView)view.findViewById(R.id.mylistRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        mylistRecyclerView.setLayoutManager(layoutManager);
        seriesModelList = new ArrayList<>();
    }

    public void listMyList()
    {
        Call<List<AllMyListModel>> req = ManagerAll.getInstance().getAllMyList(user.getUid());
        req.enqueue(new Callback<List<AllMyListModel>>() {
            @Override
            public void onResponse(Call<List<AllMyListModel>> call, Response<List<AllMyListModel>> response) {
                seriesModelList = response.body();
                myListAdapter = new MyListAdapter(getContext(),seriesModelList);
                mylistRecyclerView.setAdapter(myListAdapter);
            }

            @Override
            public void onFailure(Call<List<AllMyListModel>> call, Throwable t) {

            }
        });

    }
}
