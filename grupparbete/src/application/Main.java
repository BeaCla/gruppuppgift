package application;
	
import application.utils.XmlFileUtils;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	
	static final String FILE_NAME = "src\\application\\resources\\file.xml";
	
	SkiComp root = null;
	Stage primaryStage = null;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			root = new SkiComp();
			Scene scene = new Scene(root,800,500);
			
			//preventing resizeing the application
			primaryStage.setMinWidth(800);        
			primaryStage.setMinHeight(585);
			
			scene.getStylesheets().add(getClass().getResource("resources/application.css").toExternalForm());
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("resources/cross-country-skiing.png")));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() throws Exception {
		XmlFileUtils.writeXMLEncoder(root.getCompetitorList(), FILE_NAME);
		super.stop();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
