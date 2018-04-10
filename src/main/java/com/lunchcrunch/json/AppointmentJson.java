package com.lunchcrunch.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class AppointmentJson{

	@JsonProperty("dateTime")
	private DateTime dateTime;

	@JsonProperty("id")
	private int id;

	public void setDateTime(DateTime dateTime){
		this.dateTime = dateTime;
	}

	public DateTime getDateTime(){
		return dateTime;
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
			"AppointmentJson{" + 
			"dateTime = '" + dateTime + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}