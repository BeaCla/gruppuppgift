package application.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
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
	private Long finishTime = 0L;
	private String displayFinishTime = "0";
	private String result = "-";
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
			String displayMiddleTime, String displayFinishTime, String result) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.club = club;
		this.displayStartTime = displayStartTime;
		this.displayMiddleTime = displayMiddleTime;
		this.displayFinishTime = displayFinishTime;
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
	}
	
	public Long getFinishTime() {
		return finishTime;
	}
	
	public void setFinishTime(Long finishTime) {
		this.finishTime = finishTime - getStartTime();
		setDisplayFinishTime();
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
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
	
	public void setDisplayFinishTime() {
		long finishTimeMillis = getFinishTime();
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SS");
		this.displayFinishTime = sdf.format(finishTimeMillis);
	}
	
	public String getDisplayFinishTime() {
		return displayFinishTime;
	}
	
	public boolean getIsStarted() {
		return isStarted;
	}
	
	public void setIsStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	
	public Comparator<Competitor> getCompResult(){
		Comparator<Competitor> comp = new Comparator<Competitor>() {

			@Override
			public int compare(Competitor o1, Competitor o2) {
				
			return o1.finishTime.compareTo(o2.finishTime);
			}
			
		};
		return comp;
	}
}


