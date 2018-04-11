package com.lunchcrunch.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The type Chronology.
 */
@Generated("com.robohorse.robopojogenerator")
public class Chronology{

	@JsonProperty("calendarType")
	private String calendarType;

	@JsonProperty("id")
	private String id;

	/**
	 * Set calendar type.
	 *
	 * @param calendarType the calendar type
	 */
	public void setCalendarType(String calendarType){
		this.calendarType = calendarType;
	}

	/**
	 * Get calendar type string.
	 *
	 * @return the string
	 */
	public String getCalendarType(){
		return calendarType;
	}

	/**
	 * Set id.
	 *
	 * @param id the id
	 */
	public void setId(String id){
		this.id = id;
	}

	/**
	 * Get id string.
	 *
	 * @return the string
	 */
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