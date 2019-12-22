package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;

public class Timer {
  long currentTime = 0;
  Timeline timeline;
  Text time;

  public Timer(Text time) {
    this.time = time;
  }

  // Start timer so it start count up
  public void start() {
    long endTime = System.currentTimeMillis() - currentTime;
    DateFormat timeFormat = new SimpleDateFormat( "mm:ss:SSS" );

    this.timeline = new Timeline(
      new KeyFrame(
        Duration.millis(1),
        event -> {
          this.currentTime = System.currentTimeMillis() - endTime;

          if ( currentTime < 0 ) {
            time.setText(timeFormat.format(0));
          } else {
            time.setText(timeFormat.format(currentTime));
          }
        }
      )
    );

    timeline.setCycleCount( Animation.INDEFINITE );
    timeline.play();
  }

  public void stop() {
    timeline.stop();
  }

  /**
   * Get the current time within the timer without stopping.
   * @return time in milliseconds
   */
  public long getTime() {
    return currentTime;
  }
}
