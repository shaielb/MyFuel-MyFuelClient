package application;

import client.Client;
import client.IClient;
import db.entity.ReportsInfo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sceneswitch.ScenesSwitch;


public class Main extends Application {

	private IClient _client;

	@Override
	public void start(Stage stage) {
		try {
			stage.setTitle("Employee Details");

			_client = new Client("localhost", 5555);

			_client.cacheEntityEnums(ReportsInfo.class, (response) -> {
				synchronized (stage) {
					stage.notifyAll();
				}
			});

			synchronized (stage) {
				stage.wait();
			}

			ScenesSwitch sceneSwitch = new ScenesSwitch(stage);

			Parent root = FXMLLoader.load(getClass().getResource("HomeHeatingTrack.fxml"));

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}



	public static void main(String[] args) {
		launch(args);
	}
}
