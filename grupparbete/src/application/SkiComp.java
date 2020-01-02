package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import application.model.Competitor;
import application.utils.XmlFileUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Class får creating a ski completion app.
 * @author nilin
 */
public class SkiComp extends AnchorPane {

	static final String FILE_NAME = "src\\application\\resources\\file.xml";
	private SkiTableView tableView = null;
	List<Competitor> competitorsList = null;

	Timer timer;

	/**
	 * Default constructor for setting up the app.
	 */
	public SkiComp() {

		VBox vbox = new VBox();

		AnchorPane.setTopAnchor(vbox, 2.0);
		AnchorPane.setBottomAnchor(vbox, 5.0);
		AnchorPane.setLeftAnchor(vbox, 5.0);
		AnchorPane.setRightAnchor(vbox, 5.0);

		// Clock stuff
		HBox Clockline = new HBox();

		HBox hBoxClock = new HBox();
		hBoxClock.setAlignment(Pos.CENTER);
		hBoxClock.setPadding(new Insets(0, 0, 0, 10));

		Text clockText = new Text("00:00:00");

		timer = new Timer(clockText);

		clockText.getStyleClass().add("clock");
		hBoxClock.getChildren().addAll(clockText);

		// * End of clock stuff

		Button massStart = new Button("Mass start");
		Button indi = new Button("Intervall start");
		Button hunt = new Button("Jaktstart");

		Region left = new Region();
		HBox.setHgrow(left, Priority.ALWAYS);

		HBox ComButton = new HBox();
		ComButton.setSpacing(30.0);
		ComButton.setPadding(new Insets(0, 30, 0, 0));
		ComButton.getChildren().addAll(massStart, indi, hunt);
		ComButton.setAlignment(Pos.CENTER);

		/////////////////////////////////////
		competitorsList = new ArrayList<Competitor>();
		ObservableList<Competitor> observableList = FXCollections.observableArrayList();
		tableView = new SkiTableView(observableList);

		Competitor[] c = XmlFileUtils.readXMLDecoder(FILE_NAME);
		tableView.getItems().addAll(Arrays.asList(c));

		Button stopButton = new Button("Stop/Reset");
		stopButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				timer.stop();
			}
		});

		Button startButton = new Button("Start");
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timer.start();
			}
		});

		Button mellanButton = new Button("Mellantid");
		mellanButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println(timer.getTime());
			}
		});
		
		Button finishButton = new Button("Finish");
		finishButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			
			}	
		});

		VBox clockButtons = new VBox();
		clockButtons.setSpacing(10.0);
		clockButtons.getChildren().addAll(startButton, mellanButton, finishButton, stopButton);
		clockButtons.setPadding(new Insets(10, 10, 10, 10));
		Clockline.getChildren().addAll(hBoxClock, clockButtons, left, ComButton);

		/**
		 * Actionevent för masstart Tar den inmatade tiden "t.ex. 10:00:30" och
		 * konverterar den till millisekunder. Millisekunderna sparas i varje accounts
		 * "startTime". setDisplayStartTime() hämtar i sin tur informationen från
		 * startTime och visar den i rätt format i tableViewn.
		 */
		massStart.setOnAction(e -> {

			String massStartTime = "10:30:00";
			SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
			Date date = null;
			try {
				date = timeFormatter.parse(massStartTime);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			long timeStamp = date.getTime();

			competitorsList.clear();
			for (Competitor competitor : observableList) {
				competitorsList.add(competitor);
				competitor.setStartTime(timeStamp);
				competitor.setDisplayStartTime();
			}
			observableList.clear();
			observableList.addAll(competitorsList);
		});


		///////////////////////////////////////
		vbox.getChildren().addAll(new AddCompetitorframe(tableView), Clockline, tableView);
		getChildren().addAll(vbox);
	}

	/**
	 * Method for getting a list of competitors stored in tableView
	 * 
	 * @return ObservableList {@link ObservableList} <{@link Competitor}>
	 */
	public ObservableList<Competitor> getCompetitorList() {
		ObservableList<Competitor> obl = tableView.getItems();
		return obl;
	}
}
