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
public class InventoryReportScreen extends SceneBase {

	public InventoryReportScreen(ISceneSwitcher sceneSwitcher, IClient client) throws Exception {
		super(sceneSwitcher, client);
		initialize();
	}

	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("InventoryReportScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		MfHyperlink main_menu_managerControl = new MfHyperlink((Hyperlink) _scene.lookup("#scene$main_menu_manager"));
		main_menu_managerControl.addEvent((event) -> { _switcher.switchScene("main_menu_manager"); });

		MfImageView mainMenuStationManagerScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuStationManagerScreen"));
		mainMenuStationManagerScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuStationManagerScreen"); });

		//entities instantiation
		StationManagerReports stationManagerReports = new StationManagerReports();
		Station station = new Station();
		StationsFuel stationsFuelSolerCurrentAmount = new StationsFuel();
		FuelEnum fuelEnum = new FuelEnum();
		StationsFuel stationsFuelMotorcycleCurrentAmount = new StationsFuel();
		StationsFuel stationsFuelBenzinCurrentAmount = new StationsFuel();

		//entities assignments
		stationManagerReports.setStation(station);
		stationsFuelSolerCurrentAmount.setStation(station);
		stationsFuelSolerCurrentAmount.setFuelEnum(fuelEnum);
		stationsFuelMotorcycleCurrentAmount.setStation(station);
		stationsFuelMotorcycleCurrentAmount.setFuelEnum(fuelEnum);
		stationsFuelBenzinCurrentAmount.setStation(station);
		stationsFuelBenzinCurrentAmount.setFuelEnum(fuelEnum);

		//controls instantiation
		MfText currentAmountControlSolerCurrentAmount = new MfText((Text) _scene.lookup("#table$station_manager_reports$station$stations_fuel$fuel_enum$$$soler$current_amount$$$txt"));
		MfText currentAmountControlMotorcycleCurrentAmount = new MfText((Text) _scene.lookup("#table$station_manager_reports$station$stations_fuel$fuel_enum$$$motorcycle$current_amount$$$txt"));
		MfText reportDateControlTxt = new MfText((Text) _scene.lookup("#table$station_manager_reports$report_date$$$txt"));
		MfText stationNameControl = new MfText((Text) _scene.lookup("#table$station_manager_reports$station$station_name"));
		MfText currentAmountControlBenzinCurrentAmount = new MfText((Text) _scene.lookup("#table$station_manager_reports$station$stations_fuel$fuel_enum$$$benzin$current_amount"));
		MfComboBox reportDateControlCmb = new MfComboBox((ComboBox) _scene.lookup("#table$station_manager_reports$report_date$$$cmb"));

		//initializations
		MfImageView filterStationManagerReportsControl = new MfImageView((ImageView) _scene.lookup("#action$collect$station_manager_reports"));
		ActionControl stationManagerReportsfilterAction = new ActionControl();
		stationManagerReportsfilterAction.setControl(filterStationManagerReportsControl);
		FilterCapability stationManagerReportsFilterCapability = new FilterCapability();
		stationManagerReportsFilterCapability.setQueryEntities(prepareQuery(stationManagerReports));
		stationManagerReportsfilterAction.addCapability(stationManagerReportsFilterCapability);
		stationManagerReportsfilterAction.setClient(_client);
		stationManagerReportsfilterAction.setCallback((response) -> {
			Collection<IEntity> entities = response.getEntities();
			for (IEntity ientity : entities) {
				StationManagerReports entity = (StationManagerReports) ientity;
			}
		});

		//fields initializations
		currentAmountControlSolerCurrentAmount.setField(stationsFuelSolerCurrentAmount.getClass().getDeclaredField("_current_amount"));
		currentAmountControlSolerCurrentAmount.setEntity(stationsFuelSolerCurrentAmount);

		currentAmountControlMotorcycleCurrentAmount.setField(stationsFuelMotorcycleCurrentAmount.getClass().getDeclaredField("_current_amount"));
		currentAmountControlMotorcycleCurrentAmount.setEntity(stationsFuelMotorcycleCurrentAmount);

		reportDateControlTxt.setField(stationManagerReports.getClass().getDeclaredField("_report_date"));
		reportDateControlTxt.setEntity(stationManagerReports);

		stationNameControl.setField(station.getClass().getDeclaredField("_station_name"));
		stationNameControl.setEntity(station);

		currentAmountControlBenzinCurrentAmount.setField(stationsFuelBenzinCurrentAmount.getClass().getDeclaredField("_current_amount"));
		currentAmountControlBenzinCurrentAmount.setEntity(stationsFuelBenzinCurrentAmount);

		reportDateControlCmb.setField(stationManagerReports.getClass().getDeclaredField("_report_date"));
		reportDateControlCmb.setEntity(stationManagerReports);

		//grouping
		groupControls(new ControlAdapter[] { reportDateControlTxt, reportDateControlCmb });
		groupControls(new ControlAdapter[] { currentAmountControlSolerCurrentAmount, currentAmountControlMotorcycleCurrentAmount, currentAmountControlBenzinCurrentAmount });

	}
	private Map<IEntity, Map<String, String>> prepareQuery(StationManagerReports stationManagerReports) {
		Map<IEntity, Map<String, String>> map = new HashMap<IEntity, Map<String, String>>();
		
		Map<String, String> queryMap = new HashMap<String, String>();
		
		map.put(stationManagerReports, queryMap);
		return map;
	}
}
