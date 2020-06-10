package wrappers.tmp;

import java.util.HashMap;
import java.util.Map;

import action.ActionControl;
import action.InsertCapability;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfImageView;
import controls.MfText;
import db.entity.Address;
import db.entity.Customer;
import db.entity.HomeHeatingOrder;
import db.entity.Payment;
import db.entity.Person;
import db.entity.StationManagerReports;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sceneswitch.SceneBase;

@AutoGenerated
public class HomeHeatingOrderDetailsScreen extends SceneBase {

	public HomeHeatingOrderDetailsScreen(ISceneSwitcher sceneSwitcher, IClient client) throws Exception {
		super(sceneSwitcher, client);
		initialize();
	}

	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("HomeHeatingOrderDetailsScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		MfImageView homeHeatingNewOrderScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$HomeHeatingNewOrderScreen"));
		homeHeatingNewOrderScreenControl.addEvent((event) -> { _switcher.switchScene("HomeHeatingNewOrderScreen"); });

		//entities instantiation
		HomeHeatingOrder homeHeatingOrder = new HomeHeatingOrder();
		Customer customer = new Customer();
		Payment payment = new Payment();
		Person person = new Person();
		Address address = new Address();

		//entities assignments
		homeHeatingOrder.setCustomer(customer);
		customer.setPayment(payment);
		customer.setPerson(person);
		customer.setAddress(address);

		//controls instantiation
		MfText creditCardNumberControl = new MfText((Text) _scene.lookup("#table$home_heating_order$customer$payment$credit_card_number"));
		MfText firstNameControl = new MfText((Text) _scene.lookup("#table$home_heating_order$customer$person$first_name"));
		MfText lastNameControl = new MfText((Text) _scene.lookup("#table$home_heating_order$customer$person$last_name"));
		MfText cityControl = new MfText((Text) _scene.lookup("#table$home_heating_order$customer$address$city"));
		MfText streetControl = new MfText((Text) _scene.lookup("#table$home_heating_order$customer$address$street"));
		MfText apartmentControl = new MfText((Text) _scene.lookup("#table$home_heating_order$customer$address$apartment"));

		//initializations
		MfImageView insertHomeHeatingOrderControl = new MfImageView((ImageView) _scene.lookup("#action$insert$home_heating_order"));
		ActionControl homeHeatingOrderinsertAction = new ActionControl();
		homeHeatingOrderinsertAction.setControl(insertHomeHeatingOrderControl);
		InsertCapability homeHeatingOrderInsertCapability = new InsertCapability();
		homeHeatingOrderInsertCapability.addEntity(homeHeatingOrder);
		homeHeatingOrderinsertAction.addCapability(homeHeatingOrderInsertCapability);
		homeHeatingOrderinsertAction.setClient(_client);
		homeHeatingOrderinsertAction.setCallback((response) -> {
			
		});

		//fields initializations
		creditCardNumberControl.setField(payment.getClass().getDeclaredField("_credit_card_number"));
		creditCardNumberControl.setEntity(payment);

		firstNameControl.setField(person.getClass().getDeclaredField("_first_name"));
		firstNameControl.setEntity(person);

		lastNameControl.setField(person.getClass().getDeclaredField("_last_name"));
		lastNameControl.setEntity(person);

		cityControl.setField(address.getClass().getDeclaredField("_city"));
		cityControl.setEntity(address);

		streetControl.setField(address.getClass().getDeclaredField("_street"));
		streetControl.setEntity(address);

		apartmentControl.setField(address.getClass().getDeclaredField("_apartment"));
		apartmentControl.setEntity(address);


	}
	private Map<IEntity, Map<String, String>> prepareQuery(StationManagerReports stationManagerReports) {
		Map<IEntity, Map<String, String>> map = new HashMap<IEntity, Map<String, String>>();
		
		Map<String, String> queryMap = new HashMap<String, String>();
		
		map.put(stationManagerReports, queryMap);
		return map;
	}
}