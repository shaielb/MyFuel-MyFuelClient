package wrapper;

import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import db.entity.*;
import adapter.base.ControlAdapter;
import java.util.HashSet;
import widgets.table.Table;
import application.Main;
import controls.*;
import annotations.AutoGenerated;
import java.util.HashMap;
import javafx.scene.Parent;
import sceneswitch.SceneBase;
import java.util.Map;
import java.util.Collection;
import table.MfTable;
import widgets.table.*;
import client.IClient;

import javafx.scene.Scene;
import javafx.scene.control.*;
import action.*;
import db.interfaces.IEntity;
import javafx.scene.image.ImageView;
import java.util.Set;
import javafx.scene.text.Text;

@AutoGenerated
public class MainMenuStationManagerScreen extends SceneBase {

	public MainMenuStationManagerScreen(ISceneSwitcher sceneSwitcher, IClient client) throws Exception {
		super(sceneSwitcher, client);
		initialize();
	}

	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("MainMenuStationManagerScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		MfImageView fuelThresholdLevelScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$FuelThresholdLevelScreen"));
		fuelThresholdLevelScreenControl.addEvent((event) -> { _switcher.switchScene("FuelThresholdLevelScreen"); });

		MfImageView ordersStationManagerScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$OrdersStationManagerScreen"));
		ordersStationManagerScreenControl.addEvent((event) -> { _switcher.switchScene("OrdersStationManagerScreen"); });

		MfImageView mainMenuMyFuelScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMyFuelScreen"));
		mainMenuMyFuelScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMyFuelScreen"); });

		MfImageView inventoryReportScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$InventoryReportScreen"));
		inventoryReportScreenControl.addEvent((event) -> { _switcher.switchScene("InventoryReportScreen"); });

		MfImageView incomeReportScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$IncomeReportScreen"));
		incomeReportScreenControl.addEvent((event) -> { _switcher.switchScene("IncomeReportScreen"); });

		MfImageView expenseReportScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$ExpenseReportScreen"));
		expenseReportScreenControl.addEvent((event) -> { _switcher.switchScene("ExpenseReportScreen"); });


	}
	private Map<IEntity, Map<String, String>> prepareQuery(SystemUser systemUser) {
		Map<IEntity, Map<String, String>> map = new HashMap<IEntity, Map<String, String>>();
		
		Map<String, String> queryMap = new HashMap<String, String>();
		
		map.put(systemUser, queryMap);
		return map;
	}
}
