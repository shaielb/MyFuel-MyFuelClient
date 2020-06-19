package wrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.ActionControl;
import action.FilterCapability;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfComboBox;
import controls.MfImageView;
import controls.MfText;
import db.entity.FuelCompanyEnum;
import db.entity.Station;
import db.entity.StationManagerReports;
import db.interfaces.IEntity;
import handler.UiHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import messages.QueryContainer;
import messages.request.IFilter;
import messages.response.ResponseEvent;
import sceneswitch.Context;
import sceneswitch.ISceneSwitcher;

@AutoGenerated
public class CompanyManagerIncomeReportScreen extends StationReportsBase {

	private static String  ReportType = "Income Report";

	private MfImageView _mainMenuCompanyManagerScreenControl;
	private MfComboBox _stationNameControlCmb;
	private MfText _totalPriceControl;

	private MfImageView _filterStationManagerReportsControl;
	private ActionControl _stationManagerReportsFilterAction;

	private Map<String, Station> _stationsMap = new HashMap<String, Station>();

	public CompanyManagerIncomeReportScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
	}

	@Override
	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("CompanyManagerIncomeReportScreen.fxml"));
		_scene = new Scene(root);
		super.initialize();

		//scene switchers
		_mainMenuCompanyManagerScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuCompanyManagerScreen"));
		_mainMenuCompanyManagerScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuCompanyManagerScreen"); });

		//entities instantiation
		if (_stationManagerReports == null) {
			_stationManagerReports = new StationManagerReports();
		}

		//entities assignments
		_stationManagerReports.setStation(_station);
		_stationManagerReports.setFuelEnum(_fuelEnum);
		_stationManagerReports.setReportType(ReportType);
		_stationManagerReportsBenzin.setReportType(ReportType);
		_stationManagerReportsSoler.setReportType(ReportType);
		_stationManagerReportsMotorcycles.setReportType(ReportType);

		//controls instantiation
		_stationNameControlCmb = new MfComboBox((ComboBox) _scene.lookup("#table$station_manager_reports$station$station_name"));
		_totalPriceControl = new MfText((Text) _scene.lookup("#table$station_manager_reports$total_price"));


		//initializations
		_filterStationManagerReportsControl = new MfImageView((ImageView) _scene.lookup("#action$collect$station_manager_reports"));
		_filterStationManagerReportsControl.
		setMouseImages("@resource/images/Search_btn.png", "@resource/images/Search_overbtn.png", "@resource/images/Search_clickbtn.png");
		_stationManagerReportsFilterAction = new ActionControl();
		_stationManagerReportsFilterAction.setControl(_filterStationManagerReportsControl);
		FilterCapability stationManagerReportsFilterCapability = new FilterCapability();
		stationManagerReportsFilterCapability.setQueryContainers(prepareQuery(_stationManagerReports));
		_stationManagerReportsFilterAction.addCapability(stationManagerReportsFilterCapability);
		_stationManagerReportsFilterAction.setClient(_client);
		_stationManagerReportsFilterAction.setPreSend((request) -> {
			Station currStation = _stationsMap.get(_station.getStationName());
			_station.setId(currStation.getId());
			if (!collectReposrts()) {
				UiHandler.showAlert(AlertType.ERROR, "Station Reports Collection", "", "Requested Report Was Not Found");
			}
			((Text) _totalPriceControl.getInstance()).setText(_stationManagerReports.getTotalPrice().toString());
			return false;
		});
		_stationManagerReportsFilterAction.setCallback((response) -> {

		});

		//fields initializations
		_stationNameControlCmb.setField(_station.getClass().getDeclaredField("_station_name"));
		_stationNameControlCmb.setEntity(_station);
	}

	@Override
	protected void postCollectHook() {
		Double benzinPrice = _stationManagerReportsBenzin.getPrice();
		Double solerPrice = _stationManagerReportsSoler.getPrice();
		Double motorcyclesPrice = _stationManagerReportsMotorcycles.getPrice();

		Double totalPrice = benzinPrice + solerPrice + motorcyclesPrice;

		_stationManagerReportsBenzin.setTotalPrice(totalPrice);
		_stationManagerReportsSoler.setTotalPrice(totalPrice);
		_stationManagerReportsMotorcycles.setTotalPrice(totalPrice);

		((Text) _totalPriceControl.getInstance()).setText(totalPrice.toString());

		_stationManagerReportsBenzin.setReportQuarter(_stationManagerReports.getReportQuarter());
		_stationManagerReportsSoler.setReportQuarter(_stationManagerReports.getReportQuarter());
		_stationManagerReportsMotorcycles.setReportQuarter(_stationManagerReports.getReportQuarter());

		_stationManagerReportsBenzin.setReportYear(_stationManagerReports.getReportYear());
		_stationManagerReportsSoler.setReportYear(_stationManagerReports.getReportYear());
		_stationManagerReportsMotorcycles.setReportYear(_stationManagerReports.getReportYear());
	}


	@Override
	protected void onLoad() throws IOException {
		QueryContainer queryContainer = new QueryContainer();
		StationManagerReports smr = new StationManagerReports();
		queryContainer.setQueryEntity(smr);
		super.collectReports(queryContainer);
		_station.setFuelCompanyEnum(_client.getEnum(FuelCompanyEnum.class, "Paz"));//_context.getEmployee().getFuelCompanyEnum());
		IFilter filterRequest = _client.getFilterRequest();
		filterRequest.addQueryContainer(_station, "fuel_company_enum_fk", "=");
		ResponseEvent responseEvent = _client.sendRequest(filterRequest);
		responseEvent.continueWith((response) -> {
			if (response.isPassed()) {
				try {
					List<String> list = new ArrayList<String>();
					Collection<IEntity> entities = response.getEntitiesAsList();
					for (IEntity entity : entities) {
						Station station = (Station) entity;
						list.add(station.getStationName());
						_stationsMap.put(station.getStationName(), station);
					}

					_stationNameControlCmb.setValues(list);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				UiHandler.showAlert(AlertType.ERROR, "Stations Collection", "", response.getDescription());
			}
		});

	}

	@Override
	public void setParameters(IEntity[] entities) {
		super.setParameters(entities);
		//_station = new Station();
		//_station.setId(1);
	}

	private List<QueryContainer> prepareQuery(StationManagerReports stationManagerReports) {
		List<QueryContainer> containers = new ArrayList<QueryContainer>();

		Map<String, String> queryMap = new HashMap<String, String>();

		QueryContainer container = new QueryContainer();
		container.setQueryEntity(stationManagerReports);
		container.setQueryMap(queryMap);
		containers.add(container);
		return containers;
	}




	public void setStationManagerReports(StationManagerReports stationManagerReports) {
		_stationManagerReports = stationManagerReports;
	}

}
