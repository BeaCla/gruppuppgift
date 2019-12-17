package application.model;

import java.io.Serializable;

public class Competitor implements Serializable {
	
	private String firstName = "";
	private String lastName = "";
	private String number = null;
	private String club = "";
	private Long startTime = null;
	private Long middleTime = null;
	private Long finishTime = null;
	private Integer result = null;
	
	public Competitor() {
		
	}
	
	public Competitor (String firstName, String lastName, String number, String club, Long startTime, Long middleTime, Long finishTime, Integer result) {
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
	
	public Long getFinishTime() {
		return finishTime;
	}
	
	public void setFinishTime(Long finishTime) {
		this.finishTime = finishTime;
	}
	
	public Integer getResult() {
		return result;
	}
	
	public void setResult(Integer result) {
		this.result = result;
	}
	
}


