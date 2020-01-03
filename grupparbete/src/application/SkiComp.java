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
import javafx.stage.Popup;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

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
		Button indi = new Button("Interval Start");
		Button hunt = new Button("Pursuit");
		
		RadioButton femton = new RadioButton("15 sec");
		RadioButton trettio = new RadioButton("30 sec");
		Text intervalText = new Text("Choose interval:");
		
		ToggleGroup radioGroup = new ToggleGroup();

        femton.setToggleGroup(radioGroup);
        trettio.setToggleGroup(radioGroup);

		Region left = new Region();
		HBox.setHgrow(left, Priority.ALWAYS);
		
		HBox intervalBox = new HBox();
		intervalBox.setSpacing(30.0);
		intervalBox.setPadding(new Insets(0, 10, 0, 0));
		intervalBox.getChildren().addAll(intervalText, femton, trettio);
		intervalBox.setAlignment(Pos.CENTER);

		HBox comButton = new HBox();
		comButton.setSpacing(30.0);
		comButton.setPadding(new Insets(0, 30, 0, 0));
		comButton.getChildren().addAll(massStart, indi, hunt);
		comButton.setAlignment(Pos.CENTER);
		
		VBox intervalcom = new VBox();
		intervalcom.getChildren().addAll(comButton, intervalBox);
		intervalcom.setAlignment(Pos.CENTER);
		intervalcom.setPadding(new Insets(0, 30, 0, 0));
		intervalcom.setSpacing(20.0);
		

		/////////////////////////////////////
		competitorsList = new ArrayList<Competitor>();
		ObservableList<Competitor> observableList = FXCollections.observableArrayList();
		tableView = new SkiTableView(observableList);

		Competitor[] c = XmlFileUtils.readXMLDecoder(FILE_NAME);
		tableView.getItems().addAll(Arrays.asList(c));

		Button stopButton = new Button("Stop/Reset");
		stopButton.setOnAction(e-> {
				timer.stop();
		});

		Button startButton = new Button("Start");
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timer.start();
			}
		});

		Button mellanButton = new Button("Intermediate");
		mellanButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (tableView.getSelectionModel().getSelectedIndex() != -1) {
					competitorsList.clear();
					competitorsList.addAll(observableList);
					int selectionIndex = tableView.getSelectionModel().getSelectedIndex();
					if(competitorsList.get(selectionIndex).getStartTime() < timer.getTime()) {
						competitorsList.get(selectionIndex).setMiddleTime(timer.getTime());
						observableList.clear();
						observableList.addAll(competitorsList);
					}
					
				}
				System.out.println(timer.getTime());
			}
		});
		
		Button finishButton = new Button("Finish");
		finishButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (tableView.getSelectionModel().getSelectedIndex() != -1) {
					competitorsList.clear();
					competitorsList.addAll(observableList);
					int selectionIndex = tableView.getSelectionModel().getSelectedIndex();
					if(competitorsList.get(selectionIndex).getMiddleTime() < timer.getTime() && competitorsList.get(selectionIndex).getMiddleTime() > 0) {
						competitorsList.get(selectionIndex).setFinishTime(timer.getTime());
						observableList.clear();
						observableList.addAll(competitorsList);
					}
					
				}
			
			}	
		});

		VBox clockButtons = new VBox();
		clockButtons.getStyleClass().add("clockbutton");
		clockButtons.setSpacing(10.0);
		clockButtons.getChildren().addAll(startButton, mellanButton, finishButton, stopButton);
		clockButtons.setPadding(new Insets(10, 10, 10, 10));
		Clockline.getChildren().addAll(hBoxClock, clockButtons, left, intervalcom);

		/**
		 * Actionevent för masstart Tar den inmatade tiden "t.ex. 10:00:30" och
		 * konverterar den till millisekunder. Millisekunderna sparas i varje accounts
		 * "startTime". setDisplayStartTime() hämtar i sin tur informationen från
		 * startTime och visar den i rätt format i tableViewn.
		 */
		massStart.setOnAction(e -> {
			
			competitorsList.clear();
			for (Competitor competitor : observableList) {
				competitorsList.add(competitor);
				competitor.setStartTime(0L);
//				competitor.setDisplayStartTime(); // Denna körs från setStartTime() istället;
			}
			observableList.clear();
			observableList.addAll(competitorsList);
		});
		
		/**
		 * ActionEvent for Interval Button. 
		 */
		
		indi.setOnAction(e -> {
			competitorsList.clear();
			int intervalTime = 0;
			long time = 0;
			
			if (radioGroup.getSelectedToggle() == femton) {
				intervalTime = 15000;
			}
			
			else if (radioGroup.getSelectedToggle() == trettio) {
				intervalTime = 30000;
			}
			
			else  {
				return;
			}
			
			for (Competitor competitor : observableList) {
				competitorsList.add(competitor);
				competitor.setStartTime(time);
				time = time + intervalTime;
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
