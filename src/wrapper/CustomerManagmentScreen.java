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
public class CustomerManagmentScreen extends SceneBase {

	public CustomerManagmentScreen(ISceneSwitcher sceneSwitcher) {
		super(sceneSwitcher);
	}

	public void initialize() {
		Parent root = FXMLLoader.load(getClass().getResource("CustomerManagmentScreen.fxml"));
		_scene = new Scene(root);

		//entities instantiation
		Customer customer = new Customer();
		Person person = new Person();

		//controls instantiation
		MfTextField customerIdControl = new MfTextField((TextField) _scene.lookup("#table$customer$customer_id"));
		MfTextField firstNameControl = new MfTextField((TextField) _scene.lookup("#table$customer$person$first_name"));
		MfTextField lastNameControl = new MfTextField((TextField) _scene.lookup("#table$customer$person$last_name"));
		MfTextField phoneNumberControl = new MfTextField((TextField) _scene.lookup("#table$customer$person$phone_number"));

		//initializations
		MfHyperlink maimMenuMarketingScreenControl = new MfHyperlink((Hyperlink) _scene.lookup("#scene$MaimMenuMarketingScreen"));
		maimMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MaimMenuMarketingScreen"); });
		MfImageView maimMenuMarketingScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MaimMenuMarketingScreen"));
		maimMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MaimMenuMarketingScreen"); });
		BorderPane customerBp = (BorderPane) _scene.lookup("#uitable$editable$multi$customer");
		Table<Customer> customerTableWrapper = new Table<Customer>();
		MfTable<Customer> customerTable = new MfTable<Customer>(Customer.class);
		customerTable.setEditable(true);
		MfMultiCapability<Customer> customerMultiCapability = new MfMultiCapability<Customer>();
		customerTableWrapper.addCapability(customerMultiCapability);
		customerTableWrapper.setTable(customerTable);
		customerBp.setCenter(customerTable.getInstance());
		MfImageView customerCharacterizationReportScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$CustomerCharacterizationReportScreen"));
		customerCharacterizationReportScreenControl.addEvent((event) -> { _switcher.switchScene("CustomerCharacterizationReportScreen"); });
		customer.setPerson(person);
		MfImageView customerControl = new MfImageView((ImageView) _scene.lookup("#action$collect$customer"));
		ActionControl collectAction = new ActionControl();
		collectAction.setControl(customerControl);
		CollectCapability customerCollectCapability = new CollectCapability();
		collectAction.addCapability(customerCollectCapability);
		collectAction.setCallback((response) -> {});

		//fields initializations
		customerIdControl.setField(customer.getClass().getField("_customer_id"));
		customerIdControl.setEntity(customer);

		firstNameControl.setField(person.getClass().getField("_first_name"));
		firstNameControl.setEntity(person);

		lastNameControl.setField(person.getClass().getField("_last_name"));
		lastNameControl.setEntity(person);

		phoneNumberControl.setField(person.getClass().getField("_phone_number"));
		phoneNumberControl.setEntity(person);

		//grouping

	}

}
