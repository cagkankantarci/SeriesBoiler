package com.example.forev.seriesboiler.Fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forev.seriesboiler.Adapters.NewSeriesAdapter;
import com.example.forev.seriesboiler.Adapters.PopularSeriesAdapter;
import com.example.forev.seriesboiler.Adapters.SliderPagerAdapter;
import com.example.forev.seriesboiler.Models.SeriesModel;
import com.example.forev.seriesboiler.Models.Slide;
import com.example.forev.seriesboiler.R;
import com.example.forev.seriesboiler.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment{

    private View view;
    private List<Slide> slideList;
    private ViewPager sliderPager;
    private TabLayout tabLayout;

    private List<SeriesModel> popularSeriesList;
    private RecyclerView popularSeriesRV;
    private PopularSeriesAdapter popularSeriesAdapter;

    private List<SeriesModel> newSeriesList;
    private RecyclerView newSeriesRV;
    private NewSeriesAdapter newSeriesAdapter;

    public static int VALUE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        define();
        listPopularSeries();
        listNewSeries();

        //currently being tested. will be withdrawn from the webservices in the future.
        tabLayout = view.findViewById(R.id.indicator);
        sliderPager = view.findViewById(R.id.sliderPage);

        slideList = new ArrayList<>();
        slideList.add(new Slide(R.drawable.slide222, "The Flash","After being struck by lightning, Barry Allen wakes..."));
        slideList.add(new Slide(R.drawable.slide333,"The Arrow","Spoiled billionaire playboy Oliver Queen is missin..."));
        slideList.add(new Slide(R.drawable.peakybanner,"Peaky Blinders","A gangster family epic set in 1919 Birmingham, Eng..."));

        SliderPagerAdapter adapter = new SliderPagerAdapter(getContext(),slideList);
        sliderPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(sliderPager,true);

        if(VALUE == 1)
        {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new HomeFragment.sliderTimer(),4000,6000);
        }
        ////////////////////////////////////////////////////////////////////////////

        return view;
    }

    public void define()
    {
        popularSeriesRV = (RecyclerView)view.findViewById(R.id.popularRecyclerView);
        popularSeriesList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        popularSeriesRV.setLayoutManager(layoutManager);

        newSeriesRV = (RecyclerView)view.findViewById(R.id.newRecyclerView);
        newSeriesList= new ArrayList<>();
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        newSeriesRV.setLayoutManager(layoutManager2);
    }

    class sliderTimer extends TimerTask{

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(sliderPager.getCurrentItem() < slideList.size()-1)
                    {
                        sliderPager.setCurrentItem(sliderPager.getCurrentItem()+1);
                    }
                    else
                    {
                        sliderPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    public void listPopularSeries()
    {
        Call<List<SeriesModel>> req = ManagerAll.getInstance().getPopularSeries();
        req.enqueue(new Callback<List<SeriesModel>>() {
            @Override
            public void onResponse(Call<List<SeriesModel>> call, Response<List<SeriesModel>> response) {
                popularSeriesList = response.body();
                popularSeriesAdapter = new PopularSeriesAdapter(getContext(),popularSeriesList);
                popularSeriesRV.setAdapter(popularSeriesAdapter);
            }

            @Override
            public void onFailure(Call<List<SeriesModel>> call, Throwable t) {

            }
        });
    }

    public void listNewSeries(){
        Call<List<SeriesModel>> req = ManagerAll.getInstance().getNewSeries();
        req.enqueue(new Callback<List<SeriesModel>>() {
            @Override
            public void onResponse(Call<List<SeriesModel>> call, Response<List<SeriesModel>> response) {
                newSeriesList = response.body();
                newSeriesAdapter = new NewSeriesAdapter(getContext(),newSeriesList);
                newSeriesRV.setAdapter(newSeriesAdapter);
            }
            @Override
            public void onFailure(Call<List<SeriesModel>> call, Throwable t) {

            }
        });
    }


}
