package wrapper;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import action.ActionControl;
import action.InsertCapability;
import action.UpdateCapability;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfImageView;
import db.entity.Car;
import db.entity.Customer;
import db.interfaces.IEntity;
import handler.UiHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import messages.Request;
import messages.request.IFilter;
import messages.request.IInsert;
import messages.request.IRemove;
import messages.response.ResponseEvent;
import sceneswitch.Context;
import sceneswitch.ISceneSwitcher;
import sceneswitch.SceneBase;
import table.MfTable;
import widgets.table.MfAddremoveDecorator;
import widgets.table.Table;

@AutoGenerated
public class UpdateAddCarScreen extends SceneBase {

	private Customer _customer;
	private Car _car;

	private MfImageView _insertCustomerControl;
	private ActionControl _customerinsertAction;
	private MfImageView _updateCustomerControl;
	private ActionControl _customerupdateAction;
	private MfImageView _updatePaymentDetailsScreenControl;
	private Table<Car> _carTableWrapper;
	private MfTable<Car> _carTable;

	private MfAddremoveDecorator<Car> _carAddremoveDecorator;

	public UpdateAddCarScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
	}

	@Override
	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("UpdateAddCarScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		_updatePaymentDetailsScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$UpdatePaymentDetailsScreen"));
		_updatePaymentDetailsScreenControl.addEvent((event) -> { _switcher.switchScene("UpdatePaymentDetailsScreen"); });

		//entities instantiation
		if (_customer == null) {
			_customer = new Customer();
		}
		if (_car == null) {
			_car = new Car();
		}
		_car.setCustomer(_customer);

		//tables instantiation
		BorderPane carBp = (BorderPane) _scene.lookup("#uitable$editable$addremove$car");
		_carTableWrapper = new Table<Car>();
		_carTable = new MfTable<Car>(Car.class);
		_carTable.setEditable(true);
		_carAddremoveDecorator = new MfAddremoveDecorator<Car>();
		_carTableWrapper.addDecorator(_carAddremoveDecorator);
		_carTableWrapper.setTable(_carTable);
		carBp.setCenter(_carTable);

		//initializations
		_insertCustomerControl = new MfImageView((ImageView) _scene.lookup("#action$insert$customer"));
		_insertCustomerControl.
		setMouseImages("@resource/images/SaveChanges_btn.png", "@resource/images/SaveChanges_overbtn.png", "@resource/images/SaveChanges_clickbtn.png");
		_customerinsertAction = new ActionControl();
		_customerinsertAction.setControl(_insertCustomerControl);
		InsertCapability customerInsertCapability = new InsertCapability();
		customerInsertCapability.addEntity(_customer);
		_customerinsertAction.addCapability(customerInsertCapability);
		_customerinsertAction.setClient(_client);
		_customerinsertAction.setPreSend((request) -> {
			Collection<IEntity> entities = ((Request) request).getEntities();
			for (IEntity entity : entities) {
				((Car) entity).setCustomer(_customer);
			}
			return true;
		});
		_customerinsertAction.setCallback((response) -> {
			if (response.isPassed()) {
				UiHandler.showAlert(AlertType.INFORMATION, "Cars Update", "", "Cars Were Added");
			}
			else {
				UiHandler.showAlert(AlertType.ERROR, "Cars Update", "", response.getDescription());
			}
		});

		_updateCustomerControl = new MfImageView((ImageView) _scene.lookup("#action$update$customer"));
		_updateCustomerControl.
		setMouseImages("@resource/images/ReturnMain_btn.png", "@resource/images/ReturnMain_overbtn.png", "@resource/images/ReturnMain_clickbtn.png");
		_customerupdateAction = new ActionControl();
		_customerupdateAction.setControl(_updateCustomerControl);
		UpdateCapability customerUpdateCapability = new UpdateCapability();
		Set<IEntity> customerUpdateEntities = new HashSet<IEntity>();
		customerUpdateCapability.setEntities(customerUpdateEntities);
		_customerupdateAction.addCapability(customerUpdateCapability);
		_customerupdateAction.setClient(_client);
		_customerupdateAction.setPreSend((request) -> {
			customerUpdateEntities.addAll(_carTable.getObservableList());
			return true;
		});
		_customerupdateAction.setCallback((response) -> {

		});

		_carAddremoveDecorator.setAddListener((entity) -> {
			try {
				IInsert insertRequest = _client.getInsertRequest();
				Car car = (Car) entity;
				car.setCustomer(_customer);
				insertRequest.addEntity(car);
				ResponseEvent responseEvent = _client.sendRequest(insertRequest);
				responseEvent.continueWith((response) -> {
					if (!response.isPassed()) {
						UiHandler.showAlert(AlertType.ERROR, "Car Add", "", response.getDescription());
					}
				});

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		_carAddremoveDecorator.setRemoveListener((entity) -> {
			try {
				IRemove removeRequest = _client.getRemoveRequest();
				Car car = (Car) entity;
				car.setCustomer(_customer);
				removeRequest.addEntity(car);
				ResponseEvent responseEvent = _client.sendRequest(removeRequest);
				responseEvent.continueWith((response) -> {
					if (!response.isPassed()) {
						UiHandler.showAlert(AlertType.ERROR, "Car Removal", "", response.getDescription());
					}
				});

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Override
	protected void onLoad() throws IOException {
		IFilter filterRequest = _client.getFilterRequest();
		filterRequest.addQueryContainer(_car, "customer_fk", "=");
		ResponseEvent responseEvent = _client.sendRequest(filterRequest);
		responseEvent.continueWith((response) -> {
			if (response.isPassed()) {
				_carTable.setRows(response.getEntitiesAsType());
			}
			else {
				UiHandler.showAlert(AlertType.ERROR, "Cars Collection", "", response.getDescription());
			}
		});
	}

	@Override
	public void setParameters(IEntity[] entities) {
		super.setParameters(entities);
		for (IEntity entity : entities) {
			if (entity instanceof Customer) {
				_customer = (Customer) entity;
			}
		}
	}



}
