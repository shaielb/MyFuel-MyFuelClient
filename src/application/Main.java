package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import client.Client;
import client.IClient;
import db.services.Services;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sceneswitch.ScenesSwitch;


/**
 * @author shaielb
 *
 */
public class Main extends Application {

	/**
	 * 
	 */
	private IClient _client;

	/**
	 *
	 */
	@Override
	public void start(Stage stage) {
		try {
			Services.initialize();
			stage.setTitle("MyFuel");
			_client = new Client("localhost", 5555);
			
			ScenesSwitch sceneSwitch = new ScenesSwitch(stage, _client);
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent event) {
			        try {
			        	sceneSwitch.logout();
						System.exit(0);
					} catch (Exception e) {
						e.printStackTrace();
					}
			    }
			});

			Set<String> tables = Services.getTables();
			List<String> list = new ArrayList<String>();
			for (String table: tables) {
				if (table.endsWith("_enum")) {
					list.add(table);
				}
			}
			_client.cacheTables(list, (response) -> { });

			//Parent root = FXMLLoader.load(getClass().getResource("CustomerCharacterizationReportScreen.fxml"));

			Scene scene = sceneSwitch.getScene("FastFuelingScreen").getScene();
			//Scene scene = sceneSwitch.getScene("FastFuelingScreen").getScene();
			//Scene scene = sceneSwitch.getScene("HomeHeatingOrderDetailsScreen").getScene();
			//Scene scene = sceneSwitch.getScene("CustomerManagementScreen").getScene();

			//Parent root = FXMLLoader.load(getClass().getResource("HomeHeatingTrack.fxml"));

			//Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
