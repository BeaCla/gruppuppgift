package application.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Competitor implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -366688627924512042L;
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
	private boolean isStarted = false;
	
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
		setDisplayStartTime();
	}
	
	public Long getMiddleTime() {
		return middleTime;
	}
	
	public void setMiddleTime(Long middleTime) {
		this.middleTime = middleTime - getStartTime();
		setDisplayMiddleTime();
//		this.displayMiddleTime = middleTime.toString();
//		setDisplayMiddleTime();
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
		long startTimeMillis = getStartTime();
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SS");
		this.displayStartTime = sdf.format(startTimeMillis);
	}
	
	public String getDisplayStartTime() {
		return displayStartTime;
	}
	
	public void setDisplayMiddleTime() {
		long middleTimeMillis = getMiddleTime();
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SS");
		this.displayMiddleTime = sdf.format(middleTimeMillis);
	}
	
	public String getDisplayMiddleTime() {
		return displayMiddleTime;
	}
	
	public boolean getIsStarted() {
		return isStarted;
	}
	
	public void setIsStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
}


