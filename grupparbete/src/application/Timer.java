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
		timeFormat = new SimpleDateFormat("mm:ss:SSS");

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
				time.setText(timeFormat.format(0));
			} else {
				time.setText(timeFormat.format(currentTime));
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
		time.setText(timeFormat.format(0));
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
}
