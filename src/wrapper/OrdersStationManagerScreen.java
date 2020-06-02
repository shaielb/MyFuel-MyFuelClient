package wrapper;

import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import adapter.base.ControlAdapter;
import java.util.HashSet;
import widgets.table.Table;
import controls.*;
import annotations.AutoGenerated;
import java.util.HashMap;
import javafx.scene.Parent;
import sceneswitch.SceneBase;
import table.MfTable;
import widgets.table.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import action.*;
import db.interfaces.IEntity;
import javafx.scene.image.ImageView;
import java.util.Set;
import javafx.scene.text.Text;

@AutoGenerated
public class OrdersStationManagerScreen extends SceneBase {

	public OrdersStationManagerScreen(ISceneSwitcher sceneSwitcher) {
		super(sceneSwitcher);
	}

	public void initialize() {
		Parent root = FXMLLoader.load(getClass().getResource("OrdersStationManagerScreen.fxml"));
		_scene = new Scene(root);

		//entities instantiation


		//controls instantiation


		//initializations
		MfHyperlink mainMenuStationManagerScreenControl = new MfHyperlink((Hyperlink) _scene.lookup("#scene$MainMenuStationManagerScreen"));
		mainMenuStationManagerScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuStationManagerScreen"); });
		MfImageView mainMenuStationManagerScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuStationManagerScreen"));
		mainMenuStationManagerScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuStationManagerScreen"); });
		BorderPane stationSupplyOrderBp = (BorderPane) _scene.lookup("#uitable$noneditable$single$station_supply_order");
		Table<StationSupplyOrder> stationSupplyOrderTableWrapper = new Table<StationSupplyOrder>();
		MfTable<StationSupplyOrder> stationSupplyOrderTable = new MfTable<StationSupplyOrder>(StationSupplyOrder.class);
		stationSupplyOrderTable.setEditable(false);
		MfSingleCapability<StationSupplyOrder> stationSupplyOrderSingleCapability = new MfSingleCapability<StationSupplyOrder>();
		stationSupplyOrderTableWrapper.addCapability(stationSupplyOrderSingleCapability);
		stationSupplyOrderTableWrapper.setTable(stationSupplyOrderTable);
		stationSupplyOrderBp.setCenter(stationSupplyOrderTable.getInstance());
		MfImageView supplyOrderConfirmPopScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$SupplyOrderConfirmPopScreen"));
		supplyOrderConfirmPopScreenControl.addEvent((event) -> { _switcher.switchScene("SupplyOrderConfirmPopScreen"); });
		MfHyperlink mainMenuMarketing1Control = new MfHyperlink((Hyperlink) _scene.lookup("#scene$MainMenuMarketing1"));
		mainMenuMarketing1Control.addEvent((event) -> { _switcher.switchScene("MainMenuMarketing1"); });

		//fields initializations


		//grouping

	}

}