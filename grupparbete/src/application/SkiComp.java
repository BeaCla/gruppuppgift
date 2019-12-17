package application;

import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SkiComp extends VBox {

	static final String FILE_NAME = "src\\application\\resources\\file.xml";
	private TableView<Competitor> tableView = null;

	public SkiComp() {
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

		////////////////////////////////////////////////
		HBox Clockline = new HBox();

		HBox hBoxClock = new HBox();
		hBoxClock.setAlignment(Pos.CENTER);
		hBoxClock.setPadding(new Insets(0, 0, 0, 10));

		Text clock = new Text("00:00:00");
		clock.getStyleClass().add("clock");
		hBoxClock.getChildren().addAll(clock);

		// clock.setStyle("-fx-padding:10, 10, 10, 10"); funkar ej;

		VBox clockButtons = new VBox();
		clockButtons.setSpacing(10.0);
		Button start = new Button("Start");
		Button stop = new Button("Stop");
		Button mellan = new Button("Mellantid");

		clockButtons.getChildren().addAll(start, mellan, stop);
		clockButtons.setPadding(new Insets(10, 10, 10, 10));

		Button massStart = new Button("Mass start");
		Button indi = new Button("Intervall start");
		Button hunt = new Button("Jaktstart");
		Button result = new Button("Resultat");

		Region left = new Region();
		HBox.setHgrow(left, Priority.ALWAYS);

		HBox ComButton = new HBox();
		ComButton.setSpacing(30.0);
		ComButton.setPadding(new Insets(0, 30, 0, 0));
		ComButton.getChildren().addAll(massStart, indi, hunt, result);
		ComButton.setAlignment(Pos.CENTER);

		Clockline.getChildren().addAll(hBoxClock, clockButtons, left, ComButton);

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

		Competitor[] c = XmlFileUtils.readXMLDecoder(FILE_NAME);
		tableView.getItems().addAll(Arrays.asList(c));
		
		tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (tableView.getSelectionModel().getSelectedIndex() != -1) {
					Competitor comp = tableView.getSelectionModel().getSelectedItem();
					textFieldName.setText(comp.getFirstName());
					textFieldLast.setText(comp.getLastName());
					textFieldNumber.setText(comp.getNumber());
					textFieldClub.setText(comp.getClub());
				}
			}

			;
		});

		//////////////////////////////////
		Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				observableList.add(new Competitor(textFieldName.getText(), textFieldLast.getText(),
						String.valueOf(observableList.size() + 1), textFieldClub.getText()));
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
							new Competitor(textFieldName.getText(), textFieldLast.getText(), textFieldNumber.getText(),
									textFieldClub.getText(), observableList.get(competitorIndex).getDisplayStartTime()));
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
		
		massStart.setOnAction(e ->{
			competitorsList.clear();
			for (Competitor competitor : observableList) {
				competitorsList.add(competitor);
				competitor.setStartTime(System.currentTimeMillis());
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
		this.getChildren().addAll(addCompetitorLine, Clockline, tableView);

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
