package wrapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import action.ActionControl;
import action.FilterCapability;
import adapter.base.ControlAdapter;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfComboBox;
import controls.MfHyperlink;
import controls.MfImageView;
import controls.MfText;
import db.entity.FuelEnum;
import db.entity.Station;
import db.entity.StationManagerReports;
import db.entity.StationsFuel;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sceneswitch.SceneBase;

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
		StationsFuel stationsFuel = new StationsFuel();
		FuelEnum fuelEnum = new FuelEnum();

		//entities assignments
		stationManagerReports.setStation(station);
		stationsFuel.setStation(station);
		stationsFuel.setFuelEnum(fuelEnum);

		//controls instantiation
		MfText currentAmountControlSolerCurrent_amount = new MfText((Text) _scene.lookup("#table$station_manager_reports$station$stations_fuel$fuel_enum$$$soler$current_amount$$$txt"));
		MfText currentAmountControlMotorcycleCurrent_amount = new MfText((Text) _scene.lookup("#table$station_manager_reports$station$stations_fuel$fuel_enum$$$motorcycle$current_amount$$$txt"));
		MfText reportDateControlTxt = new MfText((Text) _scene.lookup("#table$station_manager_reports$report_date$$$txt"));
		MfText stationNameControl = new MfText((Text) _scene.lookup("#table$station_manager_reports$station$station_name"));
		MfText currentAmountControlBenzinCurrent_amount = new MfText((Text) _scene.lookup("#table$station_manager_reports$station$stations_fuel$fuel_enum$$$benzin$current_amount"));
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
		currentAmountControlSolerCurrent_amount.setField(fuelEnum.getClass().getDeclaredField("_current_amount"));
		currentAmountControlSolerCurrent_amount.setEntity(fuelEnum);

		currentAmountControlMotorcycleCurrent_amount.setField(fuelEnum.getClass().getDeclaredField("_current_amount"));
		currentAmountControlMotorcycleCurrent_amount.setEntity(fuelEnum);

		reportDateControlTxt.setField(stationManagerReports.getClass().getDeclaredField("_report_date"));
		reportDateControlTxt.setEntity(stationManagerReports);

		stationNameControl.setField(station.getClass().getDeclaredField("_station_name"));
		stationNameControl.setEntity(station);

		currentAmountControlBenzinCurrent_amount.setField(fuelEnum.getClass().getDeclaredField("_current_amount"));
		currentAmountControlBenzinCurrent_amount.setEntity(fuelEnum);

		reportDateControlCmb.setField(stationManagerReports.getClass().getDeclaredField("_report_date"));
		reportDateControlCmb.setEntity(stationManagerReports);

		//grouping
		groupControls(new ControlAdapter[] { reportDateControlTxt, reportDateControlCmb });
		groupControls(new ControlAdapter[] { currentAmountControlSolerCurrent_amount, currentAmountControlMotorcycleCurrent_amount, currentAmountControlBenzinCurrent_amount });

	}
	private Map<IEntity, Map<String, String>> prepareQuery(StationManagerReports stationManagerReports) {
		Map<IEntity, Map<String, String>> map = new HashMap<IEntity, Map<String, String>>();
		
		Map<String, String> queryMap = new HashMap<String, String>();
		
		map.put(stationManagerReports, queryMap);
		return map;
	}
}
