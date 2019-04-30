package com.example.forev.seriesboiler.Models;

public class SeriesModel{
	private String jsonMemberNew;
	private String img;
	private String description;
	private String id;
	private String title;
	private String popular;

	public void setJsonMemberNew(String jsonMemberNew){
		this.jsonMemberNew = jsonMemberNew;
	}

	public String getJsonMemberNew(){
		return jsonMemberNew;
	}

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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setPopular(String popular){
		this.popular = popular;
	}

	public String getPopular(){
		return popular;
	}

	@Override
 	public String toString(){
		return 
			"SeriesModel{" + 
			"new = '" + jsonMemberNew + '\'' + 
			",img = '" + img + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",popular = '" + popular + '\'' + 
			"}";
		}
}
