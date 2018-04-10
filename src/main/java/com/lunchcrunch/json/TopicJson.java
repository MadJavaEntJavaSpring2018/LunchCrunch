package com.lunchcrunch.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The type Topic json.
 */
@Generated("com.robohorse.robopojogenerator")
public class TopicJson{

	@JsonProperty("description")
	private String description;

	@JsonProperty("id")
	private int id;

	/**
	 * Set description.
	 *
	 * @param description the description
	 */
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * Get description string.
	 *
	 * @return the string
	 */
	public String getDescription(){
		return description;
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
			"Topic{" +
			"description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}