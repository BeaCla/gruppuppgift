package application;

import application.model.Competitor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SkiComp extends VBox {
	
	
	public SkiComp() {
		Label labelName = new Label("First name\t");
		Label labelLast = new Label("Last name\t");
		Label labelNumber = new Label("Number\t\t");
		Label labelClub = new Label("Club\t\t\t") ;
		labelClub.getStyleClass().add("club");
		
		
		//this.setPadding(new Insets(10, 10, 10, 10));
		
		TextField textFieldName = new TextField();
		TextField textFieldLast = new TextField();
		TextField textFieldNumber = new TextField();
		TextField textFieldClub = new TextField();
		
		HBox hboxName = new HBox();
		hboxName.getChildren().addAll(labelName,textFieldName);
		
		HBox hboxLast = new HBox();
		hboxLast.getChildren().addAll(labelLast,textFieldLast);
		
		HBox hboxNumber = new HBox();
		hboxNumber.getChildren().addAll(labelNumber,textFieldNumber);
		
		HBox hboxClub = new HBox();
		hboxClub.getChildren().addAll(labelClub,textFieldClub);
		
		Button addButton = new Button("Add");
		Button updateButton = new Button("Update");
		Button deleteButton = new Button("Delete");
		Button deleteAllButton = new Button("Delete all");
		
		HBox buttonLine = new HBox();
		buttonLine.getChildren().addAll(addButton,updateButton,deleteButton,deleteAllButton);
		buttonLine.setSpacing(50.0);
		buttonLine.setPadding(new Insets(0,0,0,50));
		
		hboxNumber.getChildren().addAll(buttonLine);
		
		VBox addCompetitorLine = new VBox();
		addCompetitorLine.getChildren().addAll(hboxName,hboxLast,hboxNumber,hboxClub);
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
		
		//clock.setStyle("-fx-padding:10, 10, 10, 10"); funkar ej;
		
		VBox clockButtons = new VBox();
		clockButtons.setSpacing(10.0);
		Button start = new Button("Start");
		Button stop = new Button("Stop");
		Button mellan = new Button("Mellantid");
		
		clockButtons.getChildren().addAll(start, mellan, stop);
		clockButtons.setPadding(new Insets(10, 10, 10, 10));
		
		Button massStart = new Button("Mass start");
		Button indi= new Button("Intervall start");
		Button hunt = new Button("Jaktstart");
		Button result = new Button("Resultat");

        Region left = new Region();
        HBox.setHgrow(left, Priority.ALWAYS);
		
		HBox ComButton = new HBox();
		ComButton.setSpacing(30.0);
		ComButton.setPadding(new Insets(0, 30, 0, 0));
		ComButton.getChildren().addAll( massStart ,indi ,hunt, result);
		ComButton.setAlignment(Pos.CENTER);
		
		
		Clockline.getChildren().addAll(hBoxClock, clockButtons,left , ComButton);
		
		/////////////////////////////////////
		TableView<Competitor> tableView = new TableView<Competitor>();
		TableColumn col1 = new TableColumn<>("First Name");
		TableColumn col2 = new TableColumn<>("Last Name");
		TableColumn col3 = new TableColumn<>("Number");
		TableColumn col4 = new TableColumn<>("Club");
		TableColumn col5 = new TableColumn<>("Time");
		TableColumn col6 = new TableColumn<>("Result");
		
		
		tableView.getColumns().addAll(col1,col2,col3,col4,col5,col6);
		
		
		//////////////////////////////////
		
		
		
		this.getChildren().addAll(addCompetitorLine, Clockline, tableView);
		
	}
}
