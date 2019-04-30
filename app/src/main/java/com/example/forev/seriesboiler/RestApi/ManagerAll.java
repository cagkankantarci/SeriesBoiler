package com.example.forev.seriesboiler.RestApi;

import com.example.forev.seriesboiler.Models.AddMyListModel;
import com.example.forev.seriesboiler.Models.AllMyListModel;
import com.example.forev.seriesboiler.Models.CategoryModel;
import com.example.forev.seriesboiler.Models.DeleteMyListModel;
import com.example.forev.seriesboiler.Models.ListMyListModel;
import com.example.forev.seriesboiler.Models.SeriesModel;
import com.example.forev.seriesboiler.Models.TotalSeasonModel;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private  static ManagerAll ourInstance = new ManagerAll();

    public  static synchronized ManagerAll getInstance()
    {
        return  ourInstance;
    }

    public Call<List<SeriesModel>> getPopularSeries()
    {
        Call<List<SeriesModel>> x = getRestApi().getPopularSeries();
        return  x ;
    }

    public Call<List<SeriesModel>> getNewSeries()
    {
        Call<List<SeriesModel>> x = getRestApi().getNewSeries();
        return  x ;
    }

    public Call<List<SeriesModel>> getAdviceSeries()
    {
        Call<List<SeriesModel>> x = getRestApi().getAdviceSeries();
        return  x ;
    }

    public Call<List<AllMyListModel>> getAllMyList(String userid)
    {
        Call<List<AllMyListModel>> x = getRestApi().getAllMyList(userid);
        return  x ;
    }

    public Call<List<TotalSeasonModel>> getTotalSeasonNumber(String seriesid)
    {
        Call<List<TotalSeasonModel>> x = getRestApi().getTotalSeasonNumber(seriesid);
        return  x ;
    }

    public Call<AddMyListModel> addMyList(String userid,String seriesid,String season)
    {
        Call<AddMyListModel> x = getRestApi().addMyList(userid,seriesid,season);
        return  x ;
    }

    public Call<DeleteMyListModel> deleteMyList(String userid, String seriesid, String season)
    {
        Call<DeleteMyListModel> x = getRestApi().deleteMyList(userid,seriesid,season);
        return  x ;
    }

    public Call<List<ListMyListModel>> getMyList(String userid)
    {
        Call<List<ListMyListModel>> x = getRestApi().getMyList(userid);
        return  x ;
    }

    public Call<List<CategoryModel>> getCategory(String catid)
    {
        Call<List<CategoryModel>> x = getRestApi().getCategory(catid);
        return  x ;
    }
}
