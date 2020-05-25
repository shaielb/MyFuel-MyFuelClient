package application;

import java.util.Collection;

import client.Client;
import client.IClient;
import db.entity.ReportsInfo;
import db.interfaces.IEntity;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import panels.SearchPanel;
import table.MfTable;
import widgets.table.MfUpdateTable;


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
			MfTable<ReportsInfo> et = new MfTable<ReportsInfo>(ReportsInfo.class);
			MfUpdateTable<ReportsInfo> ut = new MfUpdateTable<ReportsInfo>(et, _client);

			SearchPanel<ReportsInfo> sp = new SearchPanel<ReportsInfo>(ReportsInfo.class, _client,
					(response) -> {
						Collection<IEntity> entities = response.getEntities();
						ObservableList<ReportsInfo> list = et.getObservableList();
						list.clear();
						if (entities.size() > 0) {
							list.addAll(entities.toArray(new ReportsInfo[entities.size()]));
						}
					});

			et.setTop(sp);

			Scene scene = new Scene(et);
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
