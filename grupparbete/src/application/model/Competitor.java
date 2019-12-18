package application.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Competitor implements Serializable {
	
	private String firstName = "";
	private String lastName = "";
	private Integer number = 0;
	private String club = "";
	private Long startTime = 0L;
	private String displayStartTime = "0";
	private String displayMiddleTime = "0";
	private Long middleTime = 0L;
	private String finishTime = "0";
	private Integer result = 0;
	
	public Competitor() {
		
	}
	
	public Competitor (String firstName, String lastName, Integer number, String club) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.club = club;
		
	}
	
	public Competitor (String firstName, String lastName, Integer number, String club, String displayStartTime,
			String displayMiddleTime, String finishTime, int result) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.club = club;
		this.displayStartTime = displayStartTime;
		this.displayMiddleTime = displayMiddleTime;
		this.finishTime = finishTime;
		this.result = result;
		
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
	
	public  Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
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


