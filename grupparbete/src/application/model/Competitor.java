package application.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Competitor implements Serializable {
	
	private String firstName = "";
	private String lastName = "";
	private String number = null;
	private String club = "";
	private Long startTime = null;
	private String displayStartTime = null;
	private String displayMiddleTime = null;
	private Long middleTime = null;
	private String finishTime = null;
	private Integer result = null;
	
	public Competitor() {
		
	}
	
	public Competitor (String firstName, String lastName, String number, String club) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.club = club;
		
	}
	
	public Competitor (String firstName, String lastName, String number, String club, String displayStartTime) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.club = club;
		this.displayStartTime = displayStartTime;
		
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	public String getClub() {
		return club;
	}
	
	public void setClub(String club) {
		this.club = club;
	}
	
	public Long getStartTime() {
		return startTime;
	}
	
	public void setStartTime (Long startTime) {
		this.startTime = startTime;
	}
	
	public Long getMiddleTime() {
		return middleTime;
	}
	
	public void setMiddleTime(Long middleTime) {
		this.middleTime = middleTime;
	}
	
	public String getFinishTime() {
		return finishTime;
	}
	
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	
	public Integer getResult() {
		return result;
	}
	
	public void setResult(Integer result) {
		this.result = result;
	}
	
	public void setDisplayStartTime() {
		this.displayStartTime = (new SimpleDateFormat("hh:mm:ss")).format(new Date(startTime));
	}
	
	public String getDisplayStartTime() {
		return displayStartTime;
	}
	
	public void setDisplayMiddleTime() {
		this.displayMiddleTime = (new SimpleDateFormat("hh:mm:ss")).format(new Date(middleTime));
	}
	
	public String getDisplayMiddleTime() {
		return displayMiddleTime;
	}
	
}


