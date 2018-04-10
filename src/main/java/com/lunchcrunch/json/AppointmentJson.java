package com.lunchcrunch.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The type Appointment json.
 */
@Generated("com.robohorse.robopojogenerator")
public class AppointmentJson{

	@JsonProperty("dateTime")
	private DateTime dateTime;

	@JsonProperty("id")
	private int id;

	/**
	 * Set date time.
	 *
	 * @param dateTime the date time
	 */
	public void setDateTime(DateTime dateTime){
		this.dateTime = dateTime;
	}

	/**
	 * Get date time date time.
	 *
	 * @return the date time
	 */
	public DateTime getDateTime(){
		return dateTime;
	}

	/**
	 * Set id.
	 *
	 * @param id the id
	 */
	public void setId(int id){
		this.id = id;
	}

	/**
	 * Get id int.
	 *
	 * @return the int
	 */
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