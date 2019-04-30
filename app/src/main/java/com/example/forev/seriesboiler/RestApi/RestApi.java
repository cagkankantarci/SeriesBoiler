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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {

    @GET("/seriesfolder/listpopularseries.php")
    Call<List<SeriesModel>> getPopularSeries();

    @GET("/seriesfolder/listnewseries.php")
    Call<List<SeriesModel>> getNewSeries();

    @GET("/seriesfolder/listadviceseries.php")
    Call<List<SeriesModel>> getAdviceSeries();

    @FormUrlEncoded
    @POST("/seriesfolder/allmylist.php")
    Call<List<AllMyListModel>> getAllMyList(@Field("userid") String userid);

    @FormUrlEncoded
    @POST("/seriesfolder/category.php")
    Call<List<CategoryModel>> getCategory(@Field("catid") String catid);

    @FormUrlEncoded
    @POST("/seriesfolder/listseasonno.php")
    Call<List<TotalSeasonModel>> getTotalSeasonNumber(@Field("seriesid") String seriesid);

    @FormUrlEncoded
    @POST("/seriesfolder/addmylist.php")
    Call<AddMyListModel> addMyList(@Field("userid") String userid,@Field("seriesid") String seriesid,
                                   @Field("season") String season);

    @FormUrlEncoded
    @POST("/seriesfolder/listmylist.php")
    Call<List<ListMyListModel>> getMyList(@Field("userid") String userid);

    @FormUrlEncoded
    @POST("/seriesfolder/deletemylist.php")
    Call<DeleteMyListModel> deleteMyList(@Field("userid") String userid, @Field("seriesid") String seriesid,
                                         @Field("season") String season);

}
