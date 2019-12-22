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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SkiComp extends AnchorPane {

	static final String FILE_NAME = "src\\application\\resources\\file.xml";
	private TableView<Competitor> tableView = null;
	Timer timer;

	public SkiComp() {
		
		VBox vbox = new VBox();
		
		AnchorPane.setTopAnchor(vbox, 5.0);
		AnchorPane.setBottomAnchor(vbox, 5.0);
		AnchorPane.setLeftAnchor(vbox, 5.0);
		AnchorPane.setRightAnchor(vbox, 5.0);
		
		Label labelName = new Label("First name\t");
		Label labelLast = new Label("Last name\t");
		Label labelNumber = new Label("Number\t\t");
		Label labelClub = new Label("Club\t\t\t");
		labelClub.getStyleClass().add("club");

		// this.setPadding(new Insets(10, 10, 10, 10));

		TextField textFieldName = new TextField();
		TextField textFieldLast = new TextField();
		TextField textFieldNumber = new TextField();
		TextField textFieldClub = new TextField();

		HBox hboxName = new HBox();
		hboxName.getChildren().addAll(labelName, textFieldName);

		HBox hboxLast = new HBox();
		hboxLast.getChildren().addAll(labelLast, textFieldLast);

		HBox hboxNumber = new HBox();
		hboxNumber.getChildren().addAll(labelNumber, textFieldNumber);

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
		//Button result = new Button("Resultat");

		Region left = new Region();
		HBox.setHgrow(left, Priority.ALWAYS);

		HBox ComButton = new HBox();
		ComButton.setSpacing(30.0);
		ComButton.setPadding(new Insets(0, 30, 0, 0));
		ComButton.getChildren().addAll(massStart, indi, hunt);
		ComButton.setAlignment(Pos.CENTER);

		/////////////////////////////////////
		List<Competitor> competitorsList = new ArrayList<Competitor>();
		ObservableList<Competitor> observableList = FXCollections.observableArrayList();
		tableView = new TableView<Competitor>(observableList);

		TableColumn<Competitor, String> col1 = new TableColumn<Competitor, String>("Number");
		col1.setCellValueFactory(new PropertyValueFactory<Competitor, String>("number"));
		TableColumn<Competitor, String> col2 = new TableColumn<>("Last Name");
		col2.setCellValueFactory(new PropertyValueFactory<Competitor, String>("lastName"));
		TableColumn<Competitor, String> col3 = new TableColumn<Competitor, String>("First Name");
		col3.setCellValueFactory(new PropertyValueFactory<Competitor, String>("firstName"));
		TableColumn<Competitor, String> col4 = new TableColumn<Competitor, String>("Club");
		col4.setCellValueFactory(new PropertyValueFactory<Competitor, String>("club"));
		TableColumn<Competitor, String> col5 = new TableColumn<Competitor, String>("Start time");
		col5.setCellValueFactory(new PropertyValueFactory<Competitor, String>("displayStartTime"));
		TableColumn<Competitor, String> col6 = new TableColumn<Competitor, String>("Middle Time");
		col6.setCellValueFactory(new PropertyValueFactory<Competitor, String>("displayMiddleTime"));
		TableColumn<Competitor, String> col7 = new TableColumn<Competitor, String>("Finish");
		col7.setCellValueFactory(new PropertyValueFactory<Competitor, String>("finishTime"));
		TableColumn<Competitor, String> col8 = new TableColumn<Competitor, String>("Result");
		col8.setCellValueFactory(new PropertyValueFactory<Competitor, String>("result"));

		tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		col1.setMaxWidth(75); col1.setMinWidth(75);
		col2.setMinWidth(100);
		col3.setMinWidth(100);
		col4.setMinWidth(50); col4.setMaxWidth(50);
		col5.setMinWidth(100); col5.setMaxWidth(100);
		col6.setMinWidth(100); col6.setMaxWidth(100);
		col7.setMinWidth(100); col7.setMaxWidth(100);
		col8.setMaxWidth(75); col8.setMinWidth(75);


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

				/**
				 * Hittar högsta värdet på en åkares nummer
				 */
				int biggestNumber = 0;
				for (int i = 0; i < observableList.size(); i++) {
					if (Integer.valueOf(observableList.get(i).getNumber()) > biggestNumber) {
						biggestNumber = Integer.valueOf(observableList.get(i).getNumber());

					}
				}
				/**
				 * Här hittar vi nästa lägsta lediga nummer
				 */

				int nextFreeNumber = 1;
				for (Competitor compeg : observableList) {
					for (int i = 0; i < observableList.size(); i++) {
						if (Integer.valueOf(observableList.get(i).getNumber()) == nextFreeNumber) {
							nextFreeNumber = Integer.valueOf(observableList.get(i).getNumber()) + 1;

						}
					}
				}

				observableList.add(new Competitor(textFieldName.getText(), textFieldLast.getText(), nextFreeNumber, textFieldClub.getText()));
				textFieldName.clear();
				textFieldLast.clear();
				textFieldNumber.clear();
				textFieldClub.clear();
			}
		});

		Button updateButton = new Button("Update");
		updateButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				int competitorIndex = tableView.getSelectionModel().getSelectedIndex();

				if (tableView.getSelectionModel().getSelectedIndex() != -1) {
					observableList.set(tableView.getSelectionModel().getSelectedIndex(),
							new Competitor(textFieldName.getText(), textFieldLast.getText(), Integer.valueOf(textFieldNumber.getText()), 
									textFieldClub.getText(), observableList.get(competitorIndex).getDisplayStartTime(),
									observableList.get(competitorIndex).getDisplayMiddleTime(),
									observableList.get(competitorIndex).getFinishTime(),
									observableList.get(competitorIndex).getResult()));
					textFieldName.clear();
					textFieldLast.clear();
					textFieldNumber.clear();
					textFieldClub.clear();
				}
			}
		});

		updateButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				int competitorIndex = tableView.getSelectionModel().getSelectedIndex();

				if (tableView.getSelectionModel().getSelectedIndex() != -1) {
					observableList.set(tableView.getSelectionModel().getSelectedIndex(),
									new Competitor(textFieldName.getText(), textFieldLast.getText(), Integer.valueOf(textFieldNumber.getText()),
													textFieldClub.getText(), observableList.get(competitorIndex).getDisplayStartTime(),
													observableList.get(competitorIndex).getDisplayMiddleTime(),
													observableList.get(competitorIndex).getFinishTime(),
													observableList.get(competitorIndex).getResult()));
					textFieldName.clear();
					textFieldLast.clear();
					textFieldNumber.clear();
					textFieldClub.clear();
				}
			}
		});

		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				if (tableView.getSelectionModel().getSelectedIndex() != -1) {

					observableList.remove(tableView.getSelectionModel().getSelectedIndex());
					textFieldName.clear();
					textFieldLast.clear();
					textFieldNumber.clear();
					textFieldClub.clear();
				}

			}
		});

		Button deleteAllButton = new Button("Delete all");
		deleteAllButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				observableList.clear();
				textFieldName.clear();
				textFieldLast.clear();
				textFieldNumber.clear();
				textFieldClub.clear();
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
			StartTimeScreen startTimeScreen = new StartTimeScreen();
			
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

		HBox buttonLine = new HBox();
		buttonLine.getChildren().addAll(addButton, updateButton, deleteButton, deleteAllButton);
		buttonLine.setSpacing(50.0);
		buttonLine.setPadding(new Insets(0, 0, 0, 50));

		hboxName.getChildren().addAll(buttonLine);

		///////////////////////////////////////
		vbox.getChildren().addAll(addCompetitorLine, Clockline, tableView);
		getChildren().addAll(vbox);

	}

	/**
	 * Method for getting a list of competitors stored in tableView
	 * 
	 * @return ObservableList {@link ObservableList} <{@link Competitor}>
	 */
	public ObservableList<Competitor> getCompetitorList() {

		return tableView.getItems();
	}
}
