package com.example.forev.seriesboiler.Models;

public class CategoryModel{
	private String catid;
	private String img;
	private String catname;
	private String description;
	private String title;
	private String seriesid;

	public void setCatid(String catid){
		this.catid = catid;
	}

	public String getCatid(){
		return catid;
	}

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setCatname(String catname){
		this.catname = catname;
	}

	public String getCatname(){
		return catname;
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
			"CategoryModel{" + 
			"catid = '" + catid + '\'' + 
			",img = '" + img + '\'' + 
			",catname = '" + catname + '\'' + 
			",description = '" + description + '\'' + 
			",title = '" + title + '\'' + 
			",seriesid = '" + seriesid + '\'' + 
			"}";
		}
}
