package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
 * Class for creating a ski competition app.
 * @author nilin
 */
public class SkiComp extends AnchorPane {

	static final String FILE_NAME = "src\\application\\resources\\file.xml";
	private SkiTableView tableView = null;
	List<Competitor> competitorsList = null;
//	List<Competitor> finishedSkiiersList = new ArrayList();
//	List<Competitor> sortedFinishedSkiiersList = new ArrayList();

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
		hBoxClock.setAlignment(Pos.CENTER_LEFT);
		hBoxClock.setPadding(new Insets(0, 0, 0, 10));
		hBoxClock.getStyleClass().add("clockframe");

		Text clockText = new Text("00:00.0");

		timer = new Timer(clockText);

		clockText.getStyleClass().add("clock");
		hBoxClock.getChildren().addAll(clockText);

		// * End of clock stuff
		
		/**
		 * Buttons for competitions
		 */
		Button massStart = new Button("Mass start");
		Button indi = new Button("Interval Start");
		Button hunt = new Button("Pursuit");
		
		/**
		 * RadioButtons for choosing intervals
		 */
		RadioButton femton = new RadioButton("15 sec");
		RadioButton trettio = new RadioButton("30 sec");
		Text intervalText = new Text("Choose interval:");
		
		ToggleGroup radioGroup = new ToggleGroup();

        femton.setToggleGroup(radioGroup);
        trettio.setToggleGroup(radioGroup);
        
        /**
         * Design for competitionsbuttons and intervalbuttons. 
         */

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
		

		competitorsList = new ArrayList<Competitor>();
		ObservableList<Competitor> observableList = FXCollections.observableArrayList();
		tableView = new SkiTableView(observableList);

		Competitor[] c = XmlFileUtils.readXMLDecoder(FILE_NAME);
		tableView.getItems().addAll(Arrays.asList(c));
		
		/**
		 * ActionEvents for clockbuttons. 
		 */

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
						Long startTime = competitorsList.get(selectionIndex).getStartTime();
						competitorsList.get(selectionIndex).setMiddleTime(startTime - timer.getTime());
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
						Long startTime = competitorsList.get(selectionIndex).getStartTime();
						competitorsList.get(selectionIndex).setFinishTime(startTime - timer.getTime());
						setResult(competitorsList.get(selectionIndex));
						observableList.clear();
						observableList.addAll(competitorsList);
					}
					
				}
			
			}	
		});

		/**
		 * Design for clock and second pane. 
		 */
		VBox clockButtons = new VBox();
		clockButtons.getStyleClass().add("clockbutton");
		clockButtons.setSpacing(10.0);
		clockButtons.getChildren().addAll(startButton, mellanButton, finishButton, stopButton);
		clockButtons.setPadding(new Insets(10, 10, 10, 10));
		Clockline.getChildren().addAll(hBoxClock, clockButtons, left, intervalcom);

		/**
		 * ActionEvent for masstart button. 
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
		
		hunt.setOnAction(e-> {
			makePursuitStartList(getCompetitorList());
		});
	
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
	
	/**
	 * Creates a Pursuit start list based on placing (result).
	 * @param finshed {@link ObservableList} <{@link Competitor}> new start list.
	 * @return
	 */
	public ObservableList<Competitor> makePursuitStartList(ObservableList<Competitor> finshed) {
		
		ObservableList<Competitor> newStartlist = FXCollections.observableArrayList();
		Comparator<Competitor> competitorComparator = Comparator.comparing(Competitor::getResult);
		finshed.sort(competitorComparator);
		
		if (finshed != null) {
			Long winnerTime = (finshed.get(0)).getFinishTime();
			for (int i = 0; i <  finshed.size(); i++) {
				Competitor updateCompetitor  = finshed.get(i);
				long newStartTime = updateCompetitor.getFinishTime() - winnerTime;
				updateCompetitor.setStartTime(newStartTime);
				updateCompetitor.setNumber(i+1);
				updateCompetitor.setFinishTime(0L);
				updateCompetitor.setMiddleTime(0L);
				updateCompetitor.setResult("-");
				
				newStartlist.add(i, updateCompetitor);
			}
			finshed.clear();
			finshed.addAll(newStartlist);
		}
		return newStartlist;
	}

	public void setResult(Competitor competitor) {
		Collections.sort(competitorsList, competitor.getCompResult());
		
		int counter = 1;
		for (Competitor competitor2 : competitorsList) {
			if(competitor2.getFinishTime() > 0) {
				competitor2.setResult(Integer.toString(counter));
				counter++;
			}
		}
		
	}
}

