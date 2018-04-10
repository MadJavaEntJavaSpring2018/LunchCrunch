package com.lunchcrunch.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The type Date active.
 */
@Generated("com.robohorse.robopojogenerator")
public class DateActive{

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

	/**
	 * Set day of week.
	 *
	 * @param dayOfWeek the day of week
	 */
	public void setDayOfWeek(String dayOfWeek){
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * Get day of week string.
	 *
	 * @return the string
	 */
	public String getDayOfWeek(){
		return dayOfWeek;
	}

	/**
	 * Set month.
	 *
	 * @param month the month
	 */
	public void setMonth(String month){
		this.month = month;
	}

	/**
	 * Get month string.
	 *
	 * @return the string
	 */
	public String getMonth(){
		return month;
	}

	/**
	 * Set hour.
	 *
	 * @param hour the hour
	 */
	public void setHour(int hour){
		this.hour = hour;
	}

	/**
	 * Get hour int.
	 *
	 * @return the int
	 */
	public int getHour(){
		return hour;
	}

	/**
	 * Set year.
	 *
	 * @param year the year
	 */
	public void setYear(int year){
		this.year = year;
	}

	/**
	 * Get year int.
	 *
	 * @return the int
	 */
	public int getYear(){
		return year;
	}

	/**
	 * Set day of month.
	 *
	 * @param dayOfMonth the day of month
	 */
	public void setDayOfMonth(int dayOfMonth){
		this.dayOfMonth = dayOfMonth;
	}

	/**
	 * Get day of month int.
	 *
	 * @return the int
	 */
	public int getDayOfMonth(){
		return dayOfMonth;
	}

	/**
	 * Set day of year.
	 *
	 * @param dayOfYear the day of year
	 */
	public void setDayOfYear(int dayOfYear){
		this.dayOfYear = dayOfYear;
	}

	/**
	 * Get day of year int.
	 *
	 * @return the int
	 */
	public int getDayOfYear(){
		return dayOfYear;
	}

	/**
	 * Set month value.
	 *
	 * @param monthValue the month value
	 */
	public void setMonthValue(int monthValue){
		this.monthValue = monthValue;
	}

	/**
	 * Get month value int.
	 *
	 * @return the int
	 */
	public int getMonthValue(){
		return monthValue;
	}

	/**
	 * Set nano.
	 *
	 * @param nano the nano
	 */
	public void setNano(int nano){
		this.nano = nano;
	}

	/**
	 * Get nano int.
	 *
	 * @return the int
	 */
	public int getNano(){
		return nano;
	}

	/**
	 * Set chronology.
	 *
	 * @param chronology the chronology
	 */
	public void setChronology(Chronology chronology){
		this.chronology = chronology;
	}

	/**
	 * Get chronology chronology.
	 *
	 * @return the chronology
	 */
	public Chronology getChronology(){
		return chronology;
	}

	/**
	 * Set minute.
	 *
	 * @param minute the minute
	 */
	public void setMinute(int minute){
		this.minute = minute;
	}

	/**
	 * Get minute int.
	 *
	 * @return the int
	 */
	public int getMinute(){
		return minute;
	}

	/**
	 * Set second.
	 *
	 * @param second the second
	 */
	public void setSecond(int second){
		this.second = second;
	}

	/**
	 * Get second int.
	 *
	 * @return the int
	 */
	public int getSecond(){
		return second;
	}

	@Override
 	public String toString(){
		return 
			"DateActive{" + 
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