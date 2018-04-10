package com.lunchcrunch.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Chronology{

	@JsonProperty("calendarType")
	private String calendarType;

	@JsonProperty("id")
	private String id;

	public void setCalendarType(String calendarType){
		this.calendarType = calendarType;
	}

	public String getCalendarType(){
		return calendarType;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Chronology{" + 
			"calendarType = '" + calendarType + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}