package application;

import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SkiComp extends AnchorPane {

	static final String FILE_NAME = "src\\application\\resources\\file.xml";
	private SkiTableView tableView = null;
	List<Competitor> competitorsList = null;

	TextField textFieldName = null;
	TextField textFieldLast = null;
	TextField textFieldNumber = null;
	TextField textFieldClub = null;

	Timer timer;

	public SkiComp() {

		VBox vbox = new VBox();

		AnchorPane.setTopAnchor(vbox, 2.0);
		AnchorPane.setBottomAnchor(vbox, 5.0);
		AnchorPane.setLeftAnchor(vbox, 5.0);
		AnchorPane.setRightAnchor(vbox, 5.0);

		Label labelName = new Label("First name\t");
		Label labelLast = new Label("Last name\t");
		Label labelClub = new Label("Club\t\t\t");
		labelClub.getStyleClass().add("club");

		textFieldName = new TextField();
		textFieldLast = new TextField();
		textFieldNumber = new TextField();
		textFieldClub = new TextField();

		HBox hboxName = new HBox();
		hboxName.getChildren().addAll(labelName, textFieldName);

		HBox hboxLast = new HBox();
		hboxLast.getChildren().addAll(labelLast, textFieldLast);

		HBox hboxClub = new HBox();
		hboxClub.getChildren().addAll(labelClub, textFieldClub);

		VBox addCompetitorLine = new VBox();
		addCompetitorLine.getChildren().addAll(hboxLast, hboxName, hboxClub);
		addCompetitorLine.setSpacing(5.0);
		addCompetitorLine.getStyleClass().add("competitor");
		addCompetitorLine.setPadding(new Insets(10, 10, 10, 10));

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
		// Button result = new Button("Resultat");

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

		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						if (tableView.getSelectionModel().getSelectedIndex() != -1) {
							Competitor comp = tableView.getSelectionModel().getSelectedItem();
							textFieldName.setText(comp.getFirstName());
							textFieldLast.setText(comp.getLastName());
							textFieldNumber.setText(String.valueOf(comp.getNumber()));
							textFieldClub.setText(comp.getClub());
						}
					}
				}
			}
		});

		Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				tableView.addCompetitor(
						new Competitor(textFieldName.getText(), textFieldName.getText(), 0, textFieldClub.getText()));
				clearInputFields();
			}
		});

		Button updateButton = new Button("Update");

		updateButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				tableView.updateCompetitor(new Competitor(textFieldName.getText(), textFieldLast.getText(),
						Integer.valueOf(textFieldNumber.getText()), textFieldClub.getText()));
				clearInputFields();
			}
		});

		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				if (tableView.getSelectionModel().getSelectedIndex() != -1) {

					observableList.remove(tableView.getSelectionModel().getSelectedIndex());
					clearInputFields();
				}
			}
		});

		Button deleteAllButton = new Button("Delete all");
		deleteAllButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				observableList.clear();
				clearInputFields();
			}
		});

		Button stopButton = new Button("Stop");
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

		VBox clockButtons = new VBox();
		clockButtons.setSpacing(10.0);
		clockButtons.getChildren().addAll(startButton, mellanButton, stopButton);
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

		Region leftAddButtonLine = new Region();
		HBox.setHgrow(leftAddButtonLine, Priority.ALWAYS);

		HBox buttonLine = new HBox();
		buttonLine.getChildren().addAll(leftAddButtonLine, addButton, updateButton, deleteButton, deleteAllButton);
		buttonLine.setSpacing(50.0);
		buttonLine.setPadding(new Insets(0, 30, 0, 0));

		VBox colLeft = new VBox();
		VBox colRight = new VBox();
		HBox addCompetitorframe = new HBox();

		colLeft.getChildren().addAll(addCompetitorLine);
		colRight.getChildren().addAll(buttonLine);

		colRight.setAlignment(Pos.CENTER);
		HBox.setHgrow(colRight, Priority.ALWAYS);
		addCompetitorframe.getStyleClass().add("competitor");
		addCompetitorframe.getChildren().addAll(colLeft, colRight);

		///////////////////////////////////////
		vbox.getChildren().addAll(addCompetitorframe, Clockline, tableView);
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
	 * Clears inputfields of all data
	 */
	private void clearInputFields() {
		textFieldName.clear();
		textFieldLast.clear();
		textFieldNumber.clear();
		textFieldClub.clear();
	}
}
