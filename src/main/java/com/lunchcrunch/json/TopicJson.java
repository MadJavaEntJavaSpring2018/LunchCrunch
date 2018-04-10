package com.lunchcrunch.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class TopicJson{

	@JsonProperty("description")
	private String description;

	@JsonProperty("id")
	private int id;

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Topic{" +
			"description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}