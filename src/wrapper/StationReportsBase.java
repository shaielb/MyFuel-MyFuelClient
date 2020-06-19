package wrapper;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import client.IClient;
import controls.MfComboBox;
import controls.MfText;
import db.entity.FuelEnum;
import db.entity.FuelingPurchase;
import db.entity.Station;
import db.entity.StationManagerReports;
import db.interfaces.IEntity;
import handler.UiHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import messages.QueryContainer;
import messages.request.IFilter;
import messages.request.IInsert;
import messages.response.ResponseEvent;
import sceneswitch.Context;
import sceneswitch.ISceneSwitcher;
import sceneswitch.SceneBase;

public abstract class StationReportsBase extends SceneBase {

	protected static String Benzin = "Benzin";
	protected static String Soler = "Soler";
	protected static String MotorCycles = "Motorcycles";

	protected FuelEnum _fuelEnum;
	protected StationManagerReports _stationManagerReportsBenzin;
	protected StationManagerReports _stationManagerReportsSoler;
	protected StationManagerReports _stationManagerReportsMotorcycles;
	protected Station _station;

	protected MfText _amountControlBenzinAmount;
	protected MfText _amountControlSolerAmount;
	protected MfText _amountControlMotorcyclesAmount;
	protected MfText _priceControlBenzinPrice;
	protected MfText _priceControlSolerPrice;
	protected MfText _priceControlMotorcyclesPrice;

	protected MfComboBox _reportYearControl;
	protected MfComboBox _reportQuarterControl;

	protected StationManagerReports _stationManagerReports;

	protected Collection<StationManagerReports> _collectedReports = new ArrayList<StationManagerReports>();

	public interface DateTimeAssigner {
		public IEntity setDateTime(Timestamp ts);
	}

	protected StationReportsBase(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
	}

	@Override
	public void initialize() throws Exception {
		super.initialize();
		//entities instantiation
		if (_fuelEnum == null) {
			_fuelEnum = new FuelEnum();
		}
		if (_stationManagerReportsBenzin == null) {
			_stationManagerReportsBenzin = new StationManagerReports();
		}
		if (_stationManagerReportsSoler == null) {
			_stationManagerReportsSoler = new StationManagerReports();
		}
		if (_stationManagerReportsMotorcycles == null) {
			_stationManagerReportsMotorcycles = new StationManagerReports();
		}
		if (_station == null) {
			if (_context.getEmployee() != null && _context.getEmployee().getStation() != null) {
				_station = _context.getEmployee().getStation();
			}
			else {
				_station = new Station();
			}
		}
		if (_stationManagerReports == null) {
			_stationManagerReports = new StationManagerReports();
		}
		_stationManagerReports.setStation(_station);

		//entities assignments
		_stationManagerReportsBenzin.setFuelEnum(_fuelEnum);
		_stationManagerReportsSoler.setFuelEnum(_fuelEnum);
		_stationManagerReportsMotorcycles.setFuelEnum(_fuelEnum);
		_stationManagerReports.setStation(_station);

		//controls instantiation
		_reportYearControl = new MfComboBox((ComboBox) _scene.lookup("#table$station_manager_reports$report_year"));
		_reportQuarterControl = new MfComboBox((ComboBox) _scene.lookup("#table$station_manager_reports$report_quarter"));

		_amountControlBenzinAmount = new MfText((Text) _scene.lookup("#table$station_manager_reports$fuel_enum$$$benzin$amount"));
		_amountControlSolerAmount = new MfText((Text) _scene.lookup("#table$station_manager_reports$fuel_enum$$$soler$amount"));
		_amountControlMotorcyclesAmount = new MfText((Text) _scene.lookup("#table$station_manager_reports$fuel_enum$$$motorcycles$amount"));
		_priceControlBenzinPrice = new MfText((Text) _scene.lookup("#table$station_manager_reports$fuel_enum$$$benzin$price"));
		_priceControlSolerPrice = new MfText((Text) _scene.lookup("#table$station_manager_reports$fuel_enum$$$soler$price"));
		_priceControlMotorcyclesPrice = new MfText((Text) _scene.lookup("#table$station_manager_reports$fuel_enum$$$motorcycles$price"));

		//fields initializations
		_amountControlBenzinAmount.setField(_stationManagerReportsBenzin.getClass().getDeclaredField("_amount"));
		_amountControlBenzinAmount.setEntity(_stationManagerReportsBenzin);

		_amountControlSolerAmount.setField(_stationManagerReportsSoler.getClass().getDeclaredField("_amount"));
		_amountControlSolerAmount.setEntity(_stationManagerReportsSoler);

		_amountControlMotorcyclesAmount.setField(_stationManagerReportsMotorcycles.getClass().getDeclaredField("_amount"));
		_amountControlMotorcyclesAmount.setEntity(_stationManagerReportsMotorcycles);

		_priceControlBenzinPrice.setField(_stationManagerReportsBenzin.getClass().getDeclaredField("_price"));
		_priceControlBenzinPrice.setEntity(_stationManagerReportsBenzin);

		_priceControlSolerPrice.setField(_stationManagerReportsSoler.getClass().getDeclaredField("_price"));
		_priceControlSolerPrice.setEntity(_stationManagerReportsSoler);

		_priceControlMotorcyclesPrice.setField(_stationManagerReportsMotorcycles.getClass().getDeclaredField("_price"));
		_priceControlMotorcyclesPrice.setEntity(_stationManagerReportsMotorcycles);

		_reportYearControl.setField(_stationManagerReports.getClass().getDeclaredField("_report_year"));
		_reportYearControl.setEntity(_stationManagerReports);

		_reportQuarterControl.setField(_stationManagerReports.getClass().getDeclaredField("_report_quarter"));
		_reportQuarterControl.setEntity(_stationManagerReports);

	}

	protected boolean collectReposrts() {
		boolean found = false;
		for (StationManagerReports smr: _collectedReports) {
			if (_stationManagerReports.getReportType().equals(smr.getReportType())) {
				if (smr.getReportYear().equals(_stationManagerReports.getReportYear()) && 
						smr.getReportQuarter().equals(_stationManagerReports.getReportQuarter()) &&
						smr.getStation().getId().equals(_station.getId())) {
					found = true;
					try {
						if (smr.getFuelEnum().getFuelTypeKey().equals("Benzin")) {
							_amountControlBenzinAmount.setValue(smr.getAmount());
							_priceControlBenzinPrice.setValue(smr.getPrice());
						}
						if (smr.getFuelEnum().getFuelTypeKey().equals("Soler")) {
							_amountControlSolerAmount.setValue(smr.getAmount());
							_priceControlSolerPrice.setValue(smr.getPrice());
						}
						if (smr.getFuelEnum().getFuelTypeKey().equals("Motorcycles")) {
							_amountControlMotorcyclesAmount.setValue(smr.getAmount());
							_priceControlMotorcyclesPrice.setValue(smr.getPrice());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (smr.getTotalPrice() != null) {
						UiHandler.clone(smr, _stationManagerReports);
					}
				}
			}
		}
		return found;
	}

	protected void procudeReposrts(DateTimeAssigner startAssigner, DateTimeAssigner endAssigner) {
		if (!collectReposrts()) {
			QueryContainer qContainerStart = new QueryContainer();
			QueryContainer qContainerEnd = new QueryContainer();
			List<QueryContainer> qContainers = new ArrayList<QueryContainer>();
			qContainers.add(qContainerStart);
			qContainers.add(qContainerEnd);
			Map<String, String> queryMap = new HashMap<String, String>();
			String year = _stationManagerReports.getReportYear();
			String quarter = _stationManagerReports.getReportQuarter();

			String[] months = getMonth(quarter);
			//2010-09-29 00:00:00

			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date parsedDate = (Date) dateFormat.parse(String.format("%s-%s-01 00:00:00", year, months[0]));
				Timestamp timestamp = new Timestamp(parsedDate.getTime());

				IEntity start = startAssigner.setDateTime(timestamp);

				queryMap.put("date_time", ">=");
				qContainerStart.setQueryEntity(start);
				qContainerStart.setQueryMap(queryMap);

				Date parsedDate1 = (Date) dateFormat.parse(String.format("%s-%s-31 00:00:00", year, months[1]));
				Timestamp timestamp1 = new Timestamp(parsedDate1.getTime());

				IEntity end = endAssigner.setDateTime(timestamp1);

				queryMap.put("date_time", "<=");
				qContainerEnd.setQueryEntity(end);
				qContainerEnd.setQueryMap(queryMap);

				IFilter filterRequest = _client.getFilterRequest();
				filterRequest.setQueryContainers(qContainers);
				ResponseEvent responseEvent = _client.sendRequest(filterRequest);
				responseEvent.continueWith((response) -> {
					if (response.isPassed()) {
						Double benzinSum = 0d, solerSum = 0d, motorcyclesSum = 0d;
						Double benzinPrice = 0d, solerPrice = 0d, motorcyclesPrice = 0d;
						Collection<IEntity> entities = response.getEntities();
						for (IEntity entity: entities) {
							FuelingPurchase fp = (FuelingPurchase) entity;
							if (fp.getFuelEnum().getFuelTypeKey().equals(Benzin)) {
								benzinSum += fp.getAmount();
								benzinPrice += fp.getTotalPrice();
							}
							if (fp.getFuelEnum().getFuelTypeKey().equals(Soler)) {
								solerSum += fp.getAmount();
								solerPrice += fp.getTotalPrice();
							}
							if (fp.getFuelEnum().getFuelTypeKey().equals(MotorCycles)) {
								motorcyclesSum += fp.getAmount();
								motorcyclesPrice += fp.getTotalPrice();
							}
						}
						try {
							_amountControlBenzinAmount.setValue(benzinSum);
							_amountControlSolerAmount.setValue(solerSum);
							_amountControlMotorcyclesAmount.setValue(motorcyclesSum);
							_priceControlBenzinPrice.setValue(benzinPrice);
							_priceControlSolerPrice.setValue(solerSum);
							_priceControlMotorcyclesPrice.setValue(motorcyclesSum);
							postCollectHook();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else {
						UiHandler.showAlert(AlertType.ERROR, "Fueling Purchases Collection", "", response.getDescription());
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void postCollectHook() {}

	protected void insertReports() throws IOException {
		IInsert insertRequest = _client.getInsertRequest();
		insertRequest.addEntity(_stationManagerReportsBenzin);
		insertRequest.addEntity(_stationManagerReportsSoler);
		insertRequest.addEntity(_stationManagerReportsMotorcycles);

		ResponseEvent responseEvent = _client.sendRequest(insertRequest);
		responseEvent.continueWith((response) -> {
			if (response.isPassed()) {
				UiHandler.showAlert(AlertType.INFORMATION, "Station Manager Reports", "", "Reports Were Added");
			}
			else {
				UiHandler.showAlert(AlertType.ERROR, "Station Manager Reports", "", response.getDescription());
			}
		});
	}

	protected void collectReports(QueryContainer queryContainer) throws IOException {
		String[] values = new String[] {"January - March","April - June", "July - September", "October - December"};
		((ComboBox<String>) _reportQuarterControl.getInstance()).getItems().addAll(Arrays.asList(values));
		
		IFilter filterRequest = _client.getFilterRequest();
		StationManagerReports smr = new StationManagerReports();
		smr.setStation(_station);
		filterRequest.addQueryContainer(queryContainer);

		ResponseEvent responseEvent = _client.sendRequest(filterRequest);
		responseEvent.continueWith((response) -> {
			if (response.isPassed()) {
				Collection<IEntity> entitiesList = response.getEntities();
				Set<String> set = new HashSet<String>();
				for (IEntity entity: entitiesList) {
					set.add(((StationManagerReports)entity).getReportYear());
				}
				List<String> list = new ArrayList<String>(set);
				Collections.sort(list);
				((ComboBox) _reportYearControl.getInstance()).getItems().addAll(list);

				for (IEntity entity: entitiesList) {
					_collectedReports.add((StationManagerReports) entity);
				}
			}
			else {
				UiHandler.showAlert(AlertType.ERROR, "Station Manager Reports Collect", "", response.getDescription());
			}
		});
	}

	private String[] getMonth(String monthName) {
		if (monthName.contains("January")) {
			return new String[] { "01", "03" };
		}
		if (monthName.equals("April")) {
			return new String[] { "04", "06" };
		}
		if (monthName.equals("July")) {
			return new String[] { "07", "09" };
		}
		return new String[] { "10", "12" };
	}

	public StationManagerReports getStationManagerReportsBenzin() {
		return _stationManagerReportsBenzin;
	}

	public void setStationManagerReportsBenzin(StationManagerReports stationManagerReportsBenzin) {
		_stationManagerReportsBenzin = stationManagerReportsBenzin;
	}

	public FuelEnum getFuelEnum() {
		return _fuelEnum;
	}

	public void setFuelEnum(FuelEnum fuelEnum) {
		_fuelEnum = fuelEnum;
	}

	public StationManagerReports getStationManagerReportsSoler() {
		return _stationManagerReportsSoler;
	}

	public void setStationManagerReportsSolerAmount(StationManagerReports stationManagerReportsSoler) {
		_stationManagerReportsSoler = stationManagerReportsSoler;
	}

	public StationManagerReports getStationManagerReportsMotorcycles() {
		return _stationManagerReportsMotorcycles;
	}

	public void setStationManagerReportsMotorcycles(StationManagerReports stationManagerReportsMotorcycles) {
		_stationManagerReportsMotorcycles = stationManagerReportsMotorcycles;
	}

	public Station getStation() {
		return _station;
	}

	public void setStation(Station station) {
		_station = station;
	}

	public StationManagerReports getStationManagerReports() {
		return _stationManagerReports;
	}

	public void setStationManagerReports(StationManagerReports stationManagerReports) {
		_stationManagerReports = stationManagerReports;
	}
}
