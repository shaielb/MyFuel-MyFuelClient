package wrapper;

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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sceneswitch.Context;
import sceneswitch.SceneBase;

@AutoGenerated
public class HomeHeatingOrderDetailsScreen extends SceneBase {

	private HomeHeatingOrder _homeHeatingOrder;
	private Customer _customer;
	private Payment _payment;
	private Person _person;
	private Address _address;

	private MfImageView _insertHomeHeatingOrderControl;
	private ActionControl _homeHeatingOrderinsertAction;
	private MfImageView _homeHeatingNewOrderScreenControl;
	private MfText _creditCardNumberControl;
	private MfText _firstNameControl;
	private MfText _lastNameControl;
	private MfText _cityControl;
	private MfText _streetControl;
	private MfText _apartmentControl;

	public HomeHeatingOrderDetailsScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
		initialize();
	}

	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("HomeHeatingOrderDetailsScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		_homeHeatingNewOrderScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$HomeHeatingNewOrderScreen"));
		_homeHeatingNewOrderScreenControl.addEvent((event) -> { _switcher.switchScene("HomeHeatingNewOrderScreen"); });

		//entities instantiation
		_homeHeatingOrder = new HomeHeatingOrder();
		_customer = new Customer();
		_payment = new Payment();
		_person = new Person();
		_address = new Address();

		//entities assignments
		_homeHeatingOrder.setCustomer(_customer);
		_customer.setPayment(_payment);
		_customer.setPerson(_person);
		_customer.setAddress(_address);

		//controls instantiation
		_creditCardNumberControl = new MfText((Text) _scene.lookup("#table$home_heating_order$customer$payment$credit_card_number"));
		_firstNameControl = new MfText((Text) _scene.lookup("#table$home_heating_order$customer$person$first_name"));
		_lastNameControl = new MfText((Text) _scene.lookup("#table$home_heating_order$customer$person$last_name"));
		_cityControl = new MfText((Text) _scene.lookup("#table$home_heating_order$customer$address$city"));
		_streetControl = new MfText((Text) _scene.lookup("#table$home_heating_order$customer$address$street"));
		_apartmentControl = new MfText((Text) _scene.lookup("#table$home_heating_order$customer$address$apartment"));

		//initializations
		_insertHomeHeatingOrderControl = new MfImageView((ImageView) _scene.lookup("#action$insert$home_heating_order"));
		_homeHeatingOrderinsertAction = new ActionControl();
		_homeHeatingOrderinsertAction.setControl(_insertHomeHeatingOrderControl);
		InsertCapability homeHeatingOrderInsertCapability = new InsertCapability();
		homeHeatingOrderInsertCapability.addEntity(_homeHeatingOrder);
		_homeHeatingOrderinsertAction.addCapability(homeHeatingOrderInsertCapability);
		_homeHeatingOrderinsertAction.setClient(_client);
		_homeHeatingOrderinsertAction.setCallback((response) -> {
			
		});

		//fields initializations
		_creditCardNumberControl.setField(_payment.getClass().getDeclaredField("_credit_card_number"));
		_creditCardNumberControl.setEntity(_payment);

		_firstNameControl.setField(_person.getClass().getDeclaredField("_first_name"));
		_firstNameControl.setEntity(_person);

		_lastNameControl.setField(_person.getClass().getDeclaredField("_last_name"));
		_lastNameControl.setEntity(_person);

		_cityControl.setField(_address.getClass().getDeclaredField("_city"));
		_cityControl.setEntity(_address);

		_streetControl.setField(_address.getClass().getDeclaredField("_street"));
		_streetControl.setEntity(_address);

		_apartmentControl.setField(_address.getClass().getDeclaredField("_apartment"));
		_apartmentControl.setEntity(_address);


	}


	public HomeHeatingOrder getHomeHeatingOrder() {
		 return _homeHeatingOrder;
	}

	public void setHomeHeatingOrder(HomeHeatingOrder homeHeatingOrder) {
		 _homeHeatingOrder = homeHeatingOrder;
	}

	public Customer getCustomer() {
		 return _customer;
	}

	public void setCustomer(Customer customer) {
		 _customer = customer;
	}

	public Payment getPayment() {
		 return _payment;
	}

	public void setPayment(Payment payment) {
		 _payment = payment;
	}

	public Person getPerson() {
		 return _person;
	}

	public void setPerson(Person person) {
		 _person = person;
	}

	public Address getAddress() {
		 return _address;
	}

	public void setAddress(Address address) {
		 _address = address;
	}
}
