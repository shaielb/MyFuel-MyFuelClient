package wrapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.ActionControl;
import action.CharactirizationCapability;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfImageView;
import db.entity.Car;
import db.entity.Customer;
import db.entity.CustomersCharactirizationView;
import db.interfaces.IEntity;
import handler.UiHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import messages.QueryContainer;
import messages.Request;
import sceneswitch.Context;
import sceneswitch.ISceneSwitcher;
import sceneswitch.SceneBase;
import table.MfTable;
import widgets.table.MfSingleDecorator;
import widgets.table.Table;

@AutoGenerated
public class CustomerCharacterizationReportScreen extends SceneBase {

	private Customer _customer;
	private Car _car;
	private CustomersCharactirizationView _ccv;

	private MfImageView _mainMenuMarketingScreenControl;
	private MfImageView _customersManagementScreenControl;
	private DatePicker _dateTimeControlStart;
	private DatePicker _dateTimeControlEnd;
	private Table<CustomersCharactirizationView> _ccvTableWrapper;
	private MfTable<CustomersCharactirizationView> _ccvTable;
	private MfImageView _filterCustomersCharactirizationViewControl;
	private ActionControl _ccvfilterAction;

	public CustomerCharacterizationReportScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
	}

	@Override
	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("CustomerCharacterizationReportScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		_mainMenuMarketingScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMarketingScreen"));
		_mainMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMarketingScreen"); });

		_customersManagementScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$CustomersManagementScreen"));
		_customersManagementScreenControl.addEvent((event) -> { _switcher.switchScene("CustomersManagementScreen"); });

		//entities instantiation
		if (_customer == null) {
			_customer = new Customer();
		}
		if (_car == null) {
			_car = new Car();
		}
		if (_ccv == null) {
			_ccv = new CustomersCharactirizationView();
		}

		//entities assignments

		//controls instantiation
		_dateTimeControlStart = (DatePicker) _scene.lookup("#table$customer$car$fueling_purchase$date_time$$$start");
		_dateTimeControlEnd = (DatePicker) _scene.lookup("#table$customer$car$fueling_purchase$date_time$$$end");

		//tables instantiation
		BorderPane ccvBp = (BorderPane) _scene.lookup("#uitable$noneditable$single$fueling_purchase");
		_ccvTableWrapper = new Table<CustomersCharactirizationView>();
		_ccvTable = new MfTable<CustomersCharactirizationView>(CustomersCharactirizationView.class);
		_ccvTable.setEditable(false);
		MfSingleDecorator<CustomersCharactirizationView> ccvSingleDecorator = new MfSingleDecorator<CustomersCharactirizationView>();
		_ccvTableWrapper.addDecorator(ccvSingleDecorator);
		_ccvTableWrapper.setTable(_ccvTable);
		ccvBp.setCenter(_ccvTable);

		//initializations
		_filterCustomersCharactirizationViewControl = new MfImageView((ImageView) _scene.lookup("#action$collect$fueling_purchase"));
		_filterCustomersCharactirizationViewControl.
		setMouseImages("@resource/images/MakeAReport_btn.png", "@resource/images/MakeAReport_overbtn.png", "@resource/images/MakeAReport_clickbtn.png");
		_ccvfilterAction = new ActionControl();
		_ccvfilterAction.setControl(_filterCustomersCharactirizationViewControl);
		CharactirizationCapability charactirizationPurchaseFilterCapability = new CharactirizationCapability();
		charactirizationPurchaseFilterCapability.setQueryContainers(prepareQuery(_ccv));
		_ccvfilterAction.addCapability(charactirizationPurchaseFilterCapability);
		_ccvfilterAction.setClient(_client);
		_ccvfilterAction.setPreSend((request) -> {
			List<QueryContainer> containers = ((Request) request).getQueryContainers();
			QueryContainer qc = containers.get(0);
			Timestamp start = Timestamp.valueOf(_dateTimeControlStart.getValue().atStartOfDay());
			Timestamp end = Timestamp.valueOf(_dateTimeControlEnd.getValue().atStartOfDay());
			qc.setStartTime(start);
			qc.setEndTime(end);
			return true;
		});
		_ccvfilterAction.setCallback((response) -> {
			if (response.isPassed()) {
				_ccvTable.setRows(response.getEntitiesAsType());
			}
			else {
				UiHandler.showAlert(AlertType.ERROR, "Charactirization Collection", "", response.getDescription());
			}
		});
	}

	@Override
	protected void onLoad() {

	}

	@Override
	public void setParameters(IEntity[] entities) {
		super.setParameters(entities);
	}

	private List<QueryContainer> prepareQuery(CustomersCharactirizationView ccv) {
		List<QueryContainer> containers = new ArrayList<QueryContainer>();

		Map<String, String> queryMap = new HashMap<String, String>();

		QueryContainer container = new QueryContainer();
		container.setQueryEntity(ccv);
		container.setQueryMap(queryMap);
		containers.add(container);
		return containers;
	}

	public Customer getCustomer() {
		return _customer;
	}

	public void setCustomer(Customer customer) {
		_customer = customer;
	}

	public Car getCar() {
		return _car;
	}

	public void setCar(Car car) {
		_car = car;
	}

	public CustomersCharactirizationView getCustomersCharactirizationView() {
		return _ccv;
	}

	public void setCustomersCharactirizationView(CustomersCharactirizationView ccv) {
		_ccv = ccv;
	}
}
