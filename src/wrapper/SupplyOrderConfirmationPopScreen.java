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
public class SupplyOrderConfirmationPopScreen extends SceneBase {

	public SupplyOrderConfirmationPopScreen(ISceneSwitcher sceneSwitcher) {
		super(sceneSwitcher);
	}

	public void initialize() {
		Parent root = FXMLLoader.load(getClass().getResource("SupplyOrderConfirmationPopScreen.fxml"));
		_scene = new Scene(root);

		//entities instantiation
		Station station = new Station();
		StationSupplyOrder stationSupplyOrder = new StationSupplyOrder();
		StationsFuel stationsFuel = new StationsFuel();

		//controls instantiation
		MfText orderIdControl = new MfText((Text) _scene.lookup("#table$station$station_supply_order$order_id"));
		MfText fuelTypeControl = new MfText((Text) _scene.lookup("#table$station$station_supply_order$fuel_type"));
		MfText amountControl = new MfText((Text) _scene.lookup("#table$station$station_supply_order$amount"));
		MfText nameControl = new MfText((Text) _scene.lookup("#table$station$name"));
		MfText addressControl = new MfText((Text) _scene.lookup("#table$station$address"));
		MfText currentAmountControl = new MfText((Text) _scene.lookup("#table$station$stations_fuel$current_amount"));

		//initializations
		MfImageView stationSupplyOrderControl = new MfImageView((ImageView) _scene.lookup("#action$update$station_supply_order"));
		ActionControl updateAction = new ActionControl();
		updateAction.setControl(stationSupplyOrderControl);
		UpdateCapability stationSupplyOrderUpdateCapability = new UpdateCapability();
		Set<IEntity> stationSupplyOrderUpdateEntities = new HashSet<IEntity>();
		stationSupplyOrderUpdateCapability.setEntities(stationSupplyOrderUpdateEntities);
		updateAction.addCapability(stationSupplyOrderUpdateCapability);
		updateAction.setCallback((response) -> {});
		MfImageView ordersStationManagerScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$OrdersStationManagerScreen"));
		ordersStationManagerScreenControl.addEvent((event) -> { _switcher.switchScene("OrdersStationManagerScreen"); });
		station.setStationSupplyOrder(stationSupplyOrder);
		station.setStationsFuel(stationsFuel);

		//fields initializations
		orderIdControl.setField(stationSupplyOrder.getClass().getField("_order_id"));
		orderIdControl.setEntity(stationSupplyOrder);

		fuelTypeControl.setField(stationSupplyOrder.getClass().getField("_fuel_type"));
		fuelTypeControl.setEntity(stationSupplyOrder);

		amountControl.setField(stationSupplyOrder.getClass().getField("_amount"));
		amountControl.setEntity(stationSupplyOrder);

		nameControl.setField(station.getClass().getField("_name"));
		nameControl.setEntity(station);

		addressControl.setField(station.getClass().getField("_address"));
		addressControl.setEntity(station);

		currentAmountControl.setField(stationsFuel.getClass().getField("_current_amount"));
		currentAmountControl.setEntity(stationsFuel);

		//grouping

	}

}
