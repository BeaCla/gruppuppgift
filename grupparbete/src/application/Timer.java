package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;

/**
 * Class for getting times.
 * 
 * @author nilin
 */
public class Timer {
	long currentTime = 0;
	long endTime = 0;
	Timeline timeline;
	Text time;
	DateFormat timeFormat = null;

	/**
	 * Constuctor for the clock time.
	 * 
	 * @param time {@link Text}
	 */
	public Timer(Text time) {
		this.time = time;
		timeFormat = new SimpleDateFormat("mm:ss.S");

		this.timeline = new Timeline();
		this.timeline.setCycleCount(Animation.INDEFINITE);
	}

	/**
	 * Start timer so it start count up.
	 */
	public void start() {
		endTime = System.currentTimeMillis() - currentTime;
		if (timeline.getStatus() != null) {
			timeline.getKeyFrames().clear();
			
			timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> {
			this.currentTime = System.currentTimeMillis() - endTime;

			if (currentTime < 0) {
				time.setText(getFormatedTime());//timeFormat.format(0L));
			} else {
				time.setText(getFormatedTime());//timeFormat.format(currentTime));
			}
		}));
		}
		timeline.playFromStart();
	}

	/**
	 * Stop timer from counting up.
	 */
	public void stop() {
		timeline.stop();
		time.setText(timeFormat.format(0L));
		this.currentTime = 0L;
		this.endTime = 0L;
	}

	/**
	 * Get the current time within the timer without stopping.
	 * 
	 * @return time in milliseconds
	 */
	public long getTime() {
		return currentTime;
	}
	
	/**
	 * Format time in format h:mm:ss.S if hours exists else mm:ss.S

	 * @return {@link String} formated time string.
	 */
	public String getFormatedTime() {
		long hour = (long) ((currentTime / 3600000) % 24);
		long minutes = (long) (currentTime / 60000) % 60;
		long seconds = (long) currentTime / 1000 % 60;
		long millisec = (long) currentTime % 1000/100;
		String s1 = String.format("%d:%02d:%02d.%d",hour, minutes, seconds, millisec);
		String s = s1.replaceFirst ("^00:|^0:|^0", "");
		return s;
	}
	
}
