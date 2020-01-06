package application.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;

/**
 * Class for holding information about the competitor.
 * @author nilin
 *
 */
/**
 * @author nilin
 *
 */
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
	
	/**
	 * Default constructor. 
	 */
	public Competitor() {
	}
	
	/**
	 * Constructor for setting competitors when register for competition.
	 * @param firstName {@link String} First name.
	 * @param lastName {@link String} Last name/sir name
	 * @param number {@link Integer} Start number in competition.
	 * @param club {@link String} competitors club.
	 */
	public Competitor (String firstName, String lastName, Integer number, String club) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.club = club;
		
	}
	
	/**
	 * Constructor for setting data to shown in tableview for the competition.
	 * @param firstName {@link String} First name.
	 * @param lastName {@link String} Last name/sir name
	 * @param number {@link Integer} Start number in competition.
	 * @param club {@link String} competitors club.
	 * @param displayStartTime {@link String} Time the competitor starts in the race.
	 * @param displayMiddleTime {@link String} The formated displayed intermediate time taken during the race.
	 * @param displayFinishTime {@link String} The formated displayed finish time for the competition 
	 * @param result {@link String} 
	 */
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
	
	/**
	 * return the first name of the competitor.
	 * @return {@link String} the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * set the first name of the competitor.
	 * @param  firstName {@link String}the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * return the last name/sir name of the competitor.
	 * @return {@link String} the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * set the last name/sir name of the competitor.
	 * @param  lastName {@link String} the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * set the start number of the competitor.
	 * @return number {@link Integer} the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * get the start number of the competitor.
	 * @param number {@link Integer} the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * Return the name of  the club of the competitor.
	 * @return {@link String} the club
	 */
	public String getClub() {
		return club;
	}

	/**
	 * Get the name of  the club of the competitor.
	 * @param club {@link String} the club to set
	 */
	public void setClub(String club) {
		this.club = club;
	}

	/**
	 * Return the start time for the competitor.
	 * @return {@link Long} the startTime
	 */
	public Long getStartTime() {
		return startTime;
	}

	/**
	 * Set the start time for the competitor.
	 * @param startTime {@link Long} the startTime to set competitor.
	 */
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
		setDisplayStartTime();
	}
	
	/**
	 * Return the intermediate time for the competitor.
	 * @return {@link Long} the MiddleTime
	 */
	public Long getMiddleTime() {
		return middleTime;
	}
	
	/**
	 * Set the intermediate and display time  for the competitor.
	 * @param middleTime {@link Long} the intermediate to set competitor.
	 */
	public void setMiddleTime(Long middleTime) {
		this.middleTime = middleTime;
		setDisplayMiddleTime();
	}
	
	/**
	 * Return the finish time for the competitor.
	 * @return {@link Long} the FinishTime
	 */
	public Long getFinishTime() {
		return finishTime;
	}
	
	/**
	 * Set the Finish time for the competitor.
	 * @param finishTime {@link Long} the FinishTime
	 */
	public void setFinishTime(Long finishTime) {
		this.finishTime = finishTime;
		setDisplayFinishTime();
	}
	
	
	/**
	 * return placing (number) for the competitor.
	 * @return the result 
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Set placing (number) for the competitor.
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Set the displayed start time  for the competitor.
	 */
	public void setDisplayStartTime() {
		long startTimeMillis = getStartTime();
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SS");
		this.displayStartTime = sdf.format(startTimeMillis);
	}
	
	/**
	 * Return the Display Start time for the competitor.
	 * @return {@link Long} the DisplayStartTime
	 */
	public String getDisplayStartTime() {
		return displayStartTime;
	}
	
	/**
	 * Set the displayed intermediate time for the competitor.
	 */
	public void setDisplayMiddleTime() {
		long middleTimeMillis = getMiddleTime();
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SS");
		this.displayMiddleTime = getFormatedTime(middleTimeMillis);
	}
	
	/**
	 * Return the intermediate time for the competitor.
	 * @return {@link Long} the DisplayMiddleTime
	 */
	public String getDisplayMiddleTime() {
		return displayMiddleTime;
	}
	
	/**
	 * Set the displayed finish time  for the competitor.
	 */
	public void setDisplayFinishTime() {
		long finishTimeMillis = getFinishTime();
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SS");
		this.displayFinishTime = getFormatedTime(finishTimeMillis); 
	}
	
	/**
	 * Return the finish time for the competitor.
	 * @return {@link Long} the DisplayFinishTime
	 */
	public String getDisplayFinishTime() {
		return displayFinishTime;
	}
	
	/**
	 * Return true if competitor has started. 
	 * @return  boolean, the isStarted
	 */
	public boolean isStarted() {
		return isStarted;
	}

	/**
	 * Set true if competitor has started. 
	 * @param isStarted the isStarted to set
	 */
	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	/**
	 * Format time in format h:mm:ss.S if hours exists else mm:ss.S
	 * @param millisec {@link Long} the result to set
	 * @return formatedTime {@link String} formated time string.
	 */
	public String getFormatedTime(Long millisec) {
		long hour = (long) ((millisec / 3600000) % 24);
		long minutes = (long) (millisec / 60000) % 60;
		long seconds = (long) millisec / 1000 % 60;
		long tenths = (long) millisec % 1000/100;
		String s1 = String.format("%d:%02d:%02d.%d",hour, minutes, seconds, tenths);
		String s = s1.replaceFirst ("^00:|^0:|^0", "");
		return s;
	}
	

	/**
	 * Returns a Comparator for competitor.
	 * @return compResult
	 */
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


