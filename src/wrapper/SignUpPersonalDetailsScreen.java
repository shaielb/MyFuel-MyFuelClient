package wrapper;

import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import adapter.base.ControlAdapter;
import java.util.HashSet;
import widgets.table.Table;
import controls.*;
import annotations.AutoGenerated;
import java.util.HashMap;
import javafx.scene.Parent;
import sceneswitch.SceneBase;
import table.MfTable;
import widgets.table.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import action.*;
import db.interfaces.IEntity;
import javafx.scene.image.ImageView;
import java.util.Set;
import javafx.scene.text.Text;

@AutoGenerated
public class SignUpPersonalDetailsScreen extends SceneBase {

	public SignUpPersonalDetailsScreen(ISceneSwitcher sceneSwitcher) {
		super(sceneSwitcher);
	}

	public void initialize() {
		Parent root = FXMLLoader.load(getClass().getResource("SignUpPersonalDetailsScreen.fxml"));
		_scene = new Scene(root);

		//entities instantiation
		Customer customer = new Customer();
		Person person = new Person();
		Address address = new Address();
		User user = new User();

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
		MfPasswordField passwordControl = new MfPasswordField((PasswordField) _scene.lookup("#table$customer$user$password"));
		MfRadioButton customerTypeControlPrivate = new MfRadioButton((RadioButton) _scene.lookup("#table$customer$customer_type$$private"));
		MfTextField companyNameControl = new MfTextField((TextField) _scene.lookup("#table$customer$company_name"));
		MfTextField floorControl = new MfTextField((TextField) _scene.lookup("#table$customer$address$floor"));
		MfTextField appartmentControl = new MfTextField((TextField) _scene.lookup("#table$customer$address$appartment"));

		//initializations
		customer.setPerson(person);
		MfHyperlink mainMenuMarketingScreenControl = new MfHyperlink((Hyperlink) _scene.lookup("#scene$MainMenuMarketingScreen"));
		mainMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMarketingScreen"); });
		MfImageView mainMenuMarketingScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMarketingScreen"));
		mainMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMarketingScreen"); });
		customer.setAddress(address);
		customer.setUser(user);
		MfImageView signUpPaymentDetailsScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$SignUpPaymentDetailsScreen"));
		signUpPaymentDetailsScreenControl.addEvent((event) -> { _switcher.switchScene("SignUpPaymentDetailsScreen"); });

		//fields initializations
		firstNameControl.setField(person.getClass().getField("_first_name"));
		firstNameControl.setEntity(person);

		lastNameControl.setField(person.getClass().getField("_last_name"));
		lastNameControl.setEntity(person);

		customerIdControl.setField(customer.getClass().getField("_customer_id"));
		customerIdControl.setEntity(customer);

		emailControl.setField(person.getClass().getField("_email"));
		emailControl.setEntity(person);

		phoneNumberControl.setField(person.getClass().getField("_phone_number"));
		phoneNumberControl.setEntity(person);

		cityControl.setField(address.getClass().getField("_city"));
		cityControl.setEntity(address);

		houseControl.setField(address.getClass().getField("_house"));
		houseControl.setEntity(address);

		streetControl.setField(address.getClass().getField("_street"));
		streetControl.setEntity(address);

		customerTypeControlCompany_owner.setField(customer.getClass().getField("_customer_type"));
		customerTypeControlCompany_owner.setEntity(customer);

		passwordControl.setField(user.getClass().getField("_password"));
		passwordControl.setEntity(user);

		customerTypeControlPrivate.setField(customer.getClass().getField("_customer_type"));
		customerTypeControlPrivate.setEntity(customer);

		companyNameControl.setField(customer.getClass().getField("_company_name"));
		companyNameControl.setEntity(customer);

		floorControl.setField(address.getClass().getField("_floor"));
		floorControl.setEntity(address);

		appartmentControl.setField(address.getClass().getField("_appartment"));
		appartmentControl.setEntity(address);

		//grouping
		groupControls(new ControlAdapter[] { customerTypeControlCompany_owner, customerTypeControlPrivate });
	}

}