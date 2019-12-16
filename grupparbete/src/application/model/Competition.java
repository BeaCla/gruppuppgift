package application.model;

import java.io.Serializable;

public class Competition  implements Serializable{
	
	private Competitors competitors = null; 
	
	public Competition() {
		competitors = new Competitors();
	}

	
	public void name() {
		competitors.add(new Competitor());
	}
	
	public Competitors getMassStart() {
		
		return null;
		
	}
}
