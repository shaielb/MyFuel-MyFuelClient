package wrappers.tmp;

import java.util.HashMap;
import java.util.Map;

import adapter.base.ControlAdapter;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfComboBox;
import controls.MfImageView;
import controls.MfPasswordField;
import controls.MfRadioButton;
import controls.MfTextField;
import db.entity.Address;
import db.entity.Customer;
import db.entity.FuelingPurchase;
import db.entity.Person;
import db.entity.SystemUser;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sceneswitch.SceneBase;

@AutoGenerated
public class SignUpPersonalDetailsScreen extends SceneBase {

	public SignUpPersonalDetailsScreen(ISceneSwitcher sceneSwitcher, IClient client) throws Exception {
		super(sceneSwitcher, client);
		initialize();
	}

	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("SignUpPersonalDetailsScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		MfImageView mainMenuMarketingScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMarketingScreen"));
		mainMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMarketingScreen"); });

		MfImageView signUpPaymentDetailsScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$SignUpPaymentDetailsScreen"));
		signUpPaymentDetailsScreenControl.addEvent((event) -> { _switcher.switchScene("SignUpPaymentDetailsScreen"); });

		//entities instantiation
		Customer customer = new Customer();
		Person person = new Person();
		Address address = new Address();
		SystemUser systemUser = new SystemUser();

		//entities assignments
		customer.setPerson(person);
		customer.setAddress(address);
		customer.setSystemUser(systemUser);

		//controls instantiation
		MfTextField firstNameControl = new MfTextField((TextField) _scene.lookup("#table$customer$person$first_name"));
		MfTextField lastNameControl = new MfTextField((TextField) _scene.lookup("#table$customer$person$last_name"));
		MfTextField customerIdControl = new MfTextField((TextField) _scene.lookup("#table$customer$customer_id"));
		MfTextField emailControl = new MfTextField((TextField) _scene.lookup("#table$customer$person$email"));
		MfTextField phoneNumberControl = new MfTextField((TextField) _scene.lookup("#table$customer$person$phone_number"));
		MfComboBox cityControl = new MfComboBox((ComboBox) _scene.lookup("#table$customer$address$city"));
		MfTextField houseControl = new MfTextField((TextField) _scene.lookup("#table$customer$address$house"));
		MfComboBox streetControl = new MfComboBox((ComboBox) _scene.lookup("#table$customer$address$street"));
		MfRadioButton customerTypeControlCompany_owner = new MfRadioButton((RadioButton) _scene.lookup("#table$customer$customer_type$$company_owner"));
		MfPasswordField userPasswordControl = new MfPasswordField((PasswordField) _scene.lookup("#table$customer$system_user$user_password"));
		MfRadioButton customerTypeControlPrivate = new MfRadioButton((RadioButton) _scene.lookup("#table$customer$customer_type$$private"));
		MfTextField companyNameControl = new MfTextField((TextField) _scene.lookup("#table$customer$company_name"));
		MfTextField floorControl = new MfTextField((TextField) _scene.lookup("#table$customer$address$floor"));
		MfTextField apartmentControl = new MfTextField((TextField) _scene.lookup("#table$customer$address$apartment"));

		//fields initializations
		firstNameControl.setField(person.getClass().getDeclaredField("_first_name"));
		firstNameControl.setEntity(person);

		lastNameControl.setField(person.getClass().getDeclaredField("_last_name"));
		lastNameControl.setEntity(person);

		customerIdControl.setField(customer.getClass().getDeclaredField("_customer_id"));
		customerIdControl.setEntity(customer);

		emailControl.setField(person.getClass().getDeclaredField("_email"));
		emailControl.setEntity(person);

		phoneNumberControl.setField(person.getClass().getDeclaredField("_phone_number"));
		phoneNumberControl.setEntity(person);

		cityControl.setField(address.getClass().getDeclaredField("_city"));
		cityControl.setEntity(address);

		houseControl.setField(address.getClass().getDeclaredField("_house"));
		houseControl.setEntity(address);

		streetControl.setField(address.getClass().getDeclaredField("_street"));
		streetControl.setEntity(address);

		customerTypeControlCompany_owner.setField(customer.getClass().getDeclaredField("_customer_type"));
		customerTypeControlCompany_owner.setEntity(customer);

		userPasswordControl.setField(systemUser.getClass().getDeclaredField("_user_password"));
		userPasswordControl.setEntity(systemUser);

		customerTypeControlPrivate.setField(customer.getClass().getDeclaredField("_customer_type"));
		customerTypeControlPrivate.setEntity(customer);

		companyNameControl.setField(customer.getClass().getDeclaredField("_company_name"));
		companyNameControl.setEntity(customer);

		floorControl.setField(address.getClass().getDeclaredField("_floor"));
		floorControl.setEntity(address);

		apartmentControl.setField(address.getClass().getDeclaredField("_apartment"));
		apartmentControl.setEntity(address);

		//grouping
		groupControls(new ControlAdapter[] { customerTypeControlCompany_owner, customerTypeControlPrivate });

	}
	private Map<IEntity, Map<String, String>> prepareQuery(FuelingPurchase fuelingPurchase) {
		Map<IEntity, Map<String, String>> map = new HashMap<IEntity, Map<String, String>>();
		
		Map<String, String> queryMap = new HashMap<String, String>();
		
		map.put(fuelingPurchase, queryMap);
		return map;
	}
}