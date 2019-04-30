package com.example.forev.seriesboiler.Models;

public class AllMyListModel{
	private String img;
	private String description;
	private String title;
	private String seriesid;

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
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
			"AllMyListModel{" + 
			"img = '" + img + '\'' + 
			",description = '" + description + '\'' + 
			",title = '" + title + '\'' + 
			",seriesid = '" + seriesid + '\'' + 
			"}";
		}
}
