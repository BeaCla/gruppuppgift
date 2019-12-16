package application.model;

import java.io.Serializable;

public class Competitor implements Serializable {
	
	private String firstName = "";
	private String lastName = "";
	private String number = null;
	private String club = "";
	private Integer startTime = null;
	private Integer middleTime = null;
	private Integer finishTime = null;
	private Integer result = null;
	
	public Competitor() {
		
	}
	
	public Competitor (String firstName, String lastName, String number, String club, Integer startTime, Integer middleTime, Integer finishTime, Integer result) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.club = club;
		this.startTime = startTime;
		this.middleTime = middleTime;
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
	
	public Integer getStartTime() {
		return startTime;
	}
	
	public void setStartTime (Integer startTime) {
		this.startTime = startTime;
	}
	
	public Integer getMiddleTime() {
		return middleTime;
	}
	
	public void setMiddleTime(Integer middleTime) {
		this.middleTime = middleTime;
	}
	
	public Integer getFinishTime() {
		return finishTime;
	}
	
	public void setFinishTime(Integer finishTime) {
		this.finishTime = finishTime;
	}
	
	public Integer getResult() {
		return result;
	}
	
	public void setResult(Integer result) {
		this.result = result;
	}
	
}


