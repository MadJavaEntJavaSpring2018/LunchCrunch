package com.lunchcrunch.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class DateTime{

	@JsonProperty("dayOfWeek")
	private String dayOfWeek;

	@JsonProperty("month")
	private String month;

	@JsonProperty("hour")
	private int hour;

	@JsonProperty("year")
	private int year;

	@JsonProperty("dayOfMonth")
	private int dayOfMonth;

	@JsonProperty("dayOfYear")
	private int dayOfYear;

	@JsonProperty("monthValue")
	private int monthValue;

	@JsonProperty("nano")
	private int nano;

	@JsonProperty("chronology")
	private Chronology chronology;

	@JsonProperty("minute")
	private int minute;

	@JsonProperty("second")
	private int second;

	public void setDayOfWeek(String dayOfWeek){
		this.dayOfWeek = dayOfWeek;
	}

	public String getDayOfWeek(){
		return dayOfWeek;
	}

	public void setMonth(String month){
		this.month = month;
	}

	public String getMonth(){
		return month;
	}

	public void setHour(int hour){
		this.hour = hour;
	}

	public int getHour(){
		return hour;
	}

	public void setYear(int year){
		this.year = year;
	}

	public int getYear(){
		return year;
	}

	public void setDayOfMonth(int dayOfMonth){
		this.dayOfMonth = dayOfMonth;
	}

	public int getDayOfMonth(){
		return dayOfMonth;
	}

	public void setDayOfYear(int dayOfYear){
		this.dayOfYear = dayOfYear;
	}

	public int getDayOfYear(){
		return dayOfYear;
	}

	public void setMonthValue(int monthValue){
		this.monthValue = monthValue;
	}

	public int getMonthValue(){
		return monthValue;
	}

	public void setNano(int nano){
		this.nano = nano;
	}

	public int getNano(){
		return nano;
	}

	public void setChronology(Chronology chronology){
		this.chronology = chronology;
	}

	public Chronology getChronology(){
		return chronology;
	}

	public void setMinute(int minute){
		this.minute = minute;
	}

	public int getMinute(){
		return minute;
	}

	public void setSecond(int second){
		this.second = second;
	}

	public int getSecond(){
		return second;
	}

	@Override
 	public String toString(){
		return 
			"DateTime{" + 
			"dayOfWeek = '" + dayOfWeek + '\'' + 
			",month = '" + month + '\'' + 
			",hour = '" + hour + '\'' + 
			",year = '" + year + '\'' + 
			",dayOfMonth = '" + dayOfMonth + '\'' + 
			",dayOfYear = '" + dayOfYear + '\'' + 
			",monthValue = '" + monthValue + '\'' + 
			",nano = '" + nano + '\'' + 
			",chronology = '" + chronology + '\'' + 
			",minute = '" + minute + '\'' + 
			",second = '" + second + '\'' + 
			"}";
		}
}