
package application;

import application.model.Competitor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


/**
 *  Class for add/update/delete competitors.
 * @author nilin
 */
public class AddCompetitorframe extends HBox {
	
	TextField textFieldName = null;
	TextField textFieldLast = null;
//	TextField textFieldNumber = null;
	TextField textFieldClub = null;
	
	SkiTableView skiTableView = null;
	
	/**
	 * Default constructor.
	 */
	public AddCompetitorframe() {
	}
	
	
	/**
	 * Constuctor. 
	 * @param {@link skiTableView} 
	 */
	public AddCompetitorframe(SkiTableView skiTableView) {
		super(new HBox());
		this.skiTableView = skiTableView;
		
		VBox addCompetitorLine = setupInputFields();	
		HBox buttonLine = setupButtons();

		skiTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						if (skiTableView.getSelectionModel().getSelectedIndex() != -1) {
							Competitor comp = skiTableView.getSelectionModel().getSelectedItem();
							textFieldName.setText(comp.getFirstName());
							textFieldLast.setText(comp.getLastName());
							textFieldClub.setText(comp.getClub());
						}
					}
				}
			}
		});
		
		VBox colLeft = new VBox();
		VBox colRight = new VBox();

		colLeft.getChildren().addAll(addCompetitorLine);
		colRight.getChildren().addAll(buttonLine);

		colRight.setAlignment(Pos.CENTER);
		HBox.setHgrow(colRight, Priority.ALWAYS);
		super.getStyleClass().add("competitor");
		super.getChildren().addAll(colLeft, colRight);
	}


	/**
	 * Method for setting up buttons and actions.
	 * @param skiTableView
	 * @return
	 */
	public HBox setupButtons() {
		HBox buttonLine = new HBox();

		Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				skiTableView.addCompetitor(
						new Competitor(textFieldName.getText(), textFieldLast.getText(), 0, textFieldClub.getText()));
				clearInputFields();
			}
		});

		Button updateButton = new Button("Update");
		updateButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				skiTableView.updateCompetitor(new Competitor(textFieldName.getText(), textFieldLast.getText(),
						0, textFieldClub.getText()));			
				
				clearInputFields();
			}
		});

		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				if (skiTableView.getSelectionModel().getSelectedIndex() != -1) {
					skiTableView.getItems().remove(skiTableView.getSelectionModel().getSelectedIndex());
					clearInputFields();
				}
			}
		});

		Button deleteAllButton = new Button("Delete all");
		deleteAllButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				skiTableView.getItems().clear();
				clearInputFields();
			}
		});
		
		Region leftAddButtonLine = new Region();
		HBox.setHgrow(leftAddButtonLine, Priority.ALWAYS);

		buttonLine.getChildren().addAll(leftAddButtonLine, addButton, updateButton, deleteButton, deleteAllButton);
		buttonLine.setSpacing(50.0);
		buttonLine.setPadding(new Insets(0, 30, 0, 0));
		return buttonLine;
	}


	/**
	 * Metod for settup input fields with labels.
	 * @return {@link VBox}
	 */
	public VBox setupInputFields() {
		Label labelName = new Label("First name\t");
		Label labelLast = new Label("Last name\t");
		Label labelClub = new Label("Club\t\t\t");
		labelClub.getStyleClass().add("club");

		textFieldName = new TextField();
		textFieldLast = new TextField();
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
		return addCompetitorLine;
	}
	
	/**
	 * Clears inputfields of all data
	 */
	private void clearInputFields() {
		textFieldName.clear();
		textFieldLast.clear(); 
		textFieldClub.clear();
	}
}
