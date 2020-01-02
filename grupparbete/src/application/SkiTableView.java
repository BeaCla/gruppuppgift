package application;

import application.model.Competitor;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Class for creating a table with competitors.
 * @author nilin
 */
public class SkiTableView extends TableView<Competitor>  {

	/**
	 * Default constructor
	 */
	public SkiTableView() {
	}
	
	
	SkiTableView(ObservableList<Competitor> observableList) {
		 super(observableList);
		 
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
			TableColumn<Competitor, String> col6 = new TableColumn<Competitor, String>("Intermediate");
			col6.setCellValueFactory(new PropertyValueFactory<Competitor, String>("displayMiddleTime"));
			TableColumn<Competitor, String> col7 = new TableColumn<Competitor, String>("Finish");
			col7.setCellValueFactory(new PropertyValueFactory<Competitor, String>("finishTime"));
			TableColumn<Competitor, String> col8 = new TableColumn<Competitor, String>("Result");
			col8.setCellValueFactory(new PropertyValueFactory<Competitor, String>("result"));
			
			super.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8);
			super.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			
			col1.setMaxWidth(75); col1.setMinWidth(75);
			col2.setMinWidth(100);
			col3.setMinWidth(100);
			col4.setMinWidth(50); col4.setMaxWidth(50);
			col5.setMinWidth(100); col5.setMaxWidth(100);
			col6.setMinWidth(100); col6.setMaxWidth(100);
			col7.setMinWidth(100); col7.setMaxWidth(100);
			col8.setMaxWidth(75); col8.setMinWidth(75);

		
	}
	
	 /**
	 * Method that returns the competitors as a observable list from table. 
	 * @return {@link ObservableList}<{@link Competitor}> list of competitors
	 */
	public ObservableList<Competitor> getCompetitorList() {
			ObservableList<Competitor> obl = super.getItems();
			return obl;
	}
	 
	 /**
	  * Add a competitor to the table giving the first free number.
	 * @param {@link Competitor}
	 */
	public void addCompetitor(Competitor competitor) {
		 
		 	ObservableList<Competitor> observableList = getItems();
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
//			for (Competitor compeg : observableList) {  //FUL KOD, utan kommentar!!!
//				for (int i = 0; i < observableList.size(); i++) {
			for (int j = 0 ; j <  observableList.size(); j++) { //Old style kod
				for (int i = 0; i < observableList.size(); i++) {
					if (Integer.valueOf(observableList.get(i).getNumber()) == nextFreeNumber) {
						nextFreeNumber = Integer.valueOf(observableList.get(i).getNumber()) + 1;
					}
				}
			}
			competitor.setNumber(nextFreeNumber);
			observableList.add(competitor);		
	}
	 
	 /**
	 * Uppdate the selected competitor.
	 * @param {@link Competitor}
	 */
	public void updateCompetitor(Competitor competitor) {
		 ObservableList<Competitor> observableList = getItems();	
		 int competitorIndex = getSelectionModel().getSelectedIndex();

			if (getSelectionModel().getSelectedIndex() != -1) {
								
				competitor.setNumber(observableList.get(competitorIndex).getNumber());
				competitor.setStartTime(observableList.get(competitorIndex).getStartTime());
				competitor.setMiddleTime(observableList.get(competitorIndex).getMiddleTime());
				competitor.setFinishTime(observableList.get(competitorIndex).getFinishTime());
				competitor.setResult(observableList.get(competitorIndex).getResult());
				
				observableList.set(getSelectionModel().getSelectedIndex(), competitor);
			}
	 }
}

	