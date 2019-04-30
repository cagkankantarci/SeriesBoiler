package com.example.forev.seriesboiler.Models;

public class ListMyListModel{
	private String season;
	private String episode;
	private String id;
	private String userid;
	private String seriesid;

	public void setSeason(String season){
		this.season = season;
	}

	public String getSeason(){
		return season;
	}

	public void setEpisode(String episode){
		this.episode = episode;
	}

	public String getEpisode(){
		return episode;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUserid(String userid){
		this.userid = userid;
	}

	public String getUserid(){
		return userid;
	}

	public void setSeriesid(String seriesid){
		this.seriesid = seriesid;
	}

	public String getSeriesid(){
		return seriesid;
	}

	@Override
 	public String toString(){
		return 
			"ListMyListModel{" + 
			"season = '" + season + '\'' + 
			",episode = '" + episode + '\'' + 
			",id = '" + id + '\'' + 
			",userid = '" + userid + '\'' + 
			",seriesid = '" + seriesid + '\'' + 
			"}";
		}
}
