package com.lunchcrunch.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The type User json.
 */
@Generated("com.robohorse.robopojogenerator")
public class UserJson{

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("apiKey")
	private String apiKey;

	@JsonProperty("dateActive")
	private DateActive dateActive;

	@JsonProperty("organization")
	private String organization;

	@JsonProperty("active")
	private boolean active;

	@JsonProperty("id")
	private int id;

	@JsonProperty("appointments")
	@JsonIgnore
	private int appointments;

	/**
	 * Set first name.
	 *
	 * @param firstName the first name
	 */
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	/**
	 * Get first name string.
	 *
	 * @return the string
	 */
	public String getFirstName(){
		return firstName;
	}

	/**
	 * Set last name.
	 *
	 * @param lastName the last name
	 */
	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	/**
	 * Get last name string.
	 *
	 * @return the string
	 */
	public String getLastName(){
		return lastName;
	}

	/**
	 * Set api key.
	 *
	 * @param apiKey the api key
	 */
	public void setApiKey(String apiKey){
		this.apiKey = apiKey;
	}

	/**
	 * Get api key string.
	 *
	 * @return the string
	 */
	public String getApiKey(){
		return apiKey;
	}

	/**
	 * Set date active.
	 *
	 * @param dateActive the date active
	 */
	public void setDateActive(DateActive dateActive){
		this.dateActive = dateActive;
	}

	/**
	 * Get date active date active.
	 *
	 * @return the date active
	 */
	public DateActive getDateActive(){
		return dateActive;
	}

	/**
	 * Set organization.
	 *
	 * @param organization the organization
	 */
	public void setOrganization(String organization){
		this.organization = organization;
	}

	/**
	 * Get organization string.
	 *
	 * @return the string
	 */
	public String getOrganization(){
		return organization;
	}

	/**
	 * Set active.
	 *
	 * @param active the active
	 */
	public void setActive(boolean active){
		this.active = active;
	}

	/**
	 * Is active boolean.
	 *
	 * @return the boolean
	 */
	public boolean isActive(){
		return active;
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
			"UserJson{" + 
			"firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",apiKey = '" + apiKey + '\'' + 
			",dateActive = '" + dateActive + '\'' + 
			",organization = '" + organization + '\'' + 
			",active = '" + active + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}