package com.example.forev.seriesboiler.Models;

public class TotalSeasonModel{
	private String season;
	private String episode;

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

	@Override
 	public String toString(){
		return 
			"TotalSeasonModel{" + 
			"season = '" + season + '\'' + 
			",episode = '" + episode + '\'' + 
			"}";
		}
}
