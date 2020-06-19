package wrapper;

import adapter.base.ControlAdapter;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfNumberField;
import controls.MfText;
import controls.MfTextField;
import db.entity.Car;
import db.entity.Customer;
import db.entity.FuelEnum;
import db.entity.FuelingPurchase;
import db.entity.Person;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sceneswitch.Context;
import sceneswitch.ISceneSwitcher;
import sceneswitch.SceneBase;

@AutoGenerated
public class FastFuelingScreen extends SceneBase {

	private FuelingPurchase _fuelingPurchase;
	private Customer _customer;
	private Person _person;
	private FuelEnum _fuelEnum;
	private Car _car;

	private MfText _firstNameControl;
	private MfText _pricePerLiterControl;
	private MfText _totalPriceControl;
	private MfText _fuelTypeKeyControl;
	private MfTextField _plateNumberControl;
	private MfNumberField _amountControl;

	public FastFuelingScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
	}

	@Override
	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("FastFuelingScreen.fxml"));
		_scene = new Scene(root);

		//entities instantiation
		if (_fuelingPurchase == null) {
			_fuelingPurchase = new FuelingPurchase();
		}
		if (_customer == null) {
			_customer = new Customer();
		}
		if (_person == null) {
			_person = new Person();
		}
		if (_fuelEnum == null) {
			_fuelEnum = new FuelEnum();
		}
		if (_car == null) {
			_car = new Car();
		}

		//entities assignments
		_fuelingPurchase.setCustomer(_customer);
		_customer.setPerson(_person);
		_fuelingPurchase.setFuelEnum(_fuelEnum);
		_fuelingPurchase.setCar(_car);

		//controls instantiation
		_firstNameControl = new MfText((Text) _scene.lookup("#table$fueling_purchase$customer$person$first_name"));
		_pricePerLiterControl = new MfText((Text) _scene.lookup("#table$fueling_purchase$price_per_liter"));
		_totalPriceControl = new MfText((Text) _scene.lookup("#table$fueling_purchase$total_price"));
		_fuelTypeKeyControl = new MfText((Text) _scene.lookup("#table$fueling_purchase$fuel_enum$fuel_type_key"));
		_plateNumberControl = new MfTextField((TextField) _scene.lookup("#table$fueling_purchase$car$plate_number"));
		_amountControl = new MfNumberField((TextField) _scene.lookup("#table$fueling_purchase$amount"), Double.class);

		//fields initializations
		_firstNameControl.setField(_person.getClass().getDeclaredField("_first_name"));
		_firstNameControl.setEntity(_person);

		_pricePerLiterControl.setField(_fuelingPurchase.getClass().getDeclaredField("_price_per_liter"));
		_pricePerLiterControl.setEntity(_fuelingPurchase);

		_totalPriceControl.setField(_fuelingPurchase.getClass().getDeclaredField("_total_price"));
		_totalPriceControl.setEntity(_fuelingPurchase);

		_fuelTypeKeyControl.setField(_fuelEnum.getClass().getDeclaredField("_fuel_type_key"));
		_fuelTypeKeyControl.setEntity(_fuelEnum);

		_totalPriceControl.setField(_fuelingPurchase.getClass().getDeclaredField("_total_price"));
		_totalPriceControl.setEntity(_fuelingPurchase);

		_plateNumberControl.setField(_car.getClass().getDeclaredField("_plate_number"));
		_plateNumberControl.setEntity(_car);

		_amountControl.setField(_fuelingPurchase.getClass().getDeclaredField("_amount"));
		_amountControl.setEntity(_fuelingPurchase);

		//grouping
		groupControls(new ControlAdapter[] { _totalPriceControl, _totalPriceControl });

	}

	@Override
	protected void onLoad() {
		
	}

	@Override
	public void setParameters(IEntity[] entities) {
		super.setParameters(entities);
	}


	public FuelingPurchase getFuelingPurchase() {
		 return _fuelingPurchase;
	}

	public void setFuelingPurchase(FuelingPurchase fuelingPurchase) {
		 _fuelingPurchase = fuelingPurchase;
	}

	public Customer getCustomer() {
		 return _customer;
	}

	public void setCustomer(Customer customer) {
		 _customer = customer;
	}

	public Person getPerson() {
		 return _person;
	}

	public void setPerson(Person person) {
		 _person = person;
	}

	public FuelEnum getFuelEnum() {
		 return _fuelEnum;
	}

	public void setFuelEnum(FuelEnum fuelEnum) {
		 _fuelEnum = fuelEnum;
	}

	public Car getCar() {
		 return _car;
	}

	public void setCar(Car car) {
		 _car = car;
	}
}
