package com.lunchcrunch.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

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

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setApiKey(String apiKey){
		this.apiKey = apiKey;
	}

	public String getApiKey(){
		return apiKey;
	}

	public void setDateActive(DateActive dateActive){
		this.dateActive = dateActive;
	}

	public DateActive getDateActive(){
		return dateActive;
	}

	public void setOrganization(String organization){
		this.organization = organization;
	}

	public String getOrganization(){
		return organization;
	}

	public void setActive(boolean active){
		this.active = active;
	}

	public boolean isActive(){
		return active;
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