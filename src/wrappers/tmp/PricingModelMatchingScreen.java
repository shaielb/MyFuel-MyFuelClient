package wrappers.tmp;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import action.ActionControl;
import action.FilterCapability;
import action.UpdateCapability;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfImageView;
import controls.MfListView;
import controls.MfMenuButton;
import controls.MfText;
import controls.MfTextField;
import db.entity.Car;
import db.entity.Customer;
import db.entity.Person;
import db.entity.PricingModelEnum;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import sceneswitch.SceneBase;
import table.MfTable;
import widgets.table.MfSingleDecorator;
import widgets.table.Table;

@AutoGenerated
public class PricingModelMatchingScreen extends SceneBase {

	public PricingModelMatchingScreen(ISceneSwitcher sceneSwitcher, IClient client) throws Exception {
		super(sceneSwitcher, client);
		initialize();
	}

	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("PricingModelMatchingScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		MfImageView mainMenuMarketingScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMarketingScreen"));
		mainMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMarketingScreen"); });

		//entities instantiation
		Customer customer = new Customer();
		PricingModelEnum pricingModelEnum = new PricingModelEnum();
		Car car = new Car();
		Person person = new Person();

		//entities assignments
		car.setCustomer(customer);
		customer.setPerson(person);

		//controls instantiation
		MfTextField customerIdControl = new MfTextField((TextField) _scene.lookup("#table$customer$customer_id"));
		MfListView plateNumberControl = new MfListView((ListView) _scene.lookup("#table$customer$car$plate_number"));
		MfMenuButton modelTypeKeyControl = new MfMenuButton((MenuButton) _scene.lookup("#table$pricing_model_enum$model_type_key"));
		MfText firstNameControl = new MfText((Text) _scene.lookup("#table$customer$person$first_name"));
		MfText emailControl = new MfText((Text) _scene.lookup("#table$customer$person$email"));
		MfText phoneNumberControl = new MfText((Text) _scene.lookup("#table$customer$person$phone_number"));
		MfText lastNameControl = new MfText((Text) _scene.lookup("#table$customer$person$last_name"));

		//tables instantiation
		BorderPane pricingModelEnumBp = (BorderPane) _scene.lookup("#uitable$noneditable$single$pricing_model_enum");
		Table<PricingModelEnum> pricingModelEnumTableWrapper = new Table<PricingModelEnum>();
		MfTable<PricingModelEnum> pricingModelEnumTable = new MfTable<PricingModelEnum>(PricingModelEnum.class);
		pricingModelEnumTable.setEditable(false);
		MfSingleDecorator<PricingModelEnum> pricingModelEnumSingleDecorator = new MfSingleDecorator<PricingModelEnum>();
		pricingModelEnumTableWrapper.addDecorator(pricingModelEnumSingleDecorator);
		pricingModelEnumTableWrapper.setTable(pricingModelEnumTable);
		pricingModelEnumBp.setCenter(pricingModelEnumTable);

		//initializations
		MfImageView filterCustomerControl = new MfImageView((ImageView) _scene.lookup("#action$collect$customer"));
		ActionControl customerfilterAction = new ActionControl();
		customerfilterAction.setControl(filterCustomerControl);
		FilterCapability customerFilterCapability = new FilterCapability();
		customerFilterCapability.setQueryEntities(prepareQuery(customer));
		customerfilterAction.addCapability(customerFilterCapability);
		customerfilterAction.setClient(_client);
		customerfilterAction.setCallback((response) -> {
			Collection<IEntity> entities = response.getEntities();
			for (IEntity ientity : entities) {
				Customer entity = (Customer) ientity;
			}
		});

		MfImageView updatePricingModelEnumControl = new MfImageView((ImageView) _scene.lookup("#action$update$customer$pricing_model_enum"));
		ActionControl pricingModelEnumupdateAction = new ActionControl();
		pricingModelEnumupdateAction.setControl(updatePricingModelEnumControl);
		UpdateCapability pricingModelEnumUpdateCapability = new UpdateCapability();
		Set<IEntity> pricingModelEnumUpdateEntities = new HashSet<IEntity>();
		pricingModelEnumUpdateCapability.setEntities(pricingModelEnumUpdateEntities);
		pricingModelEnumupdateAction.addCapability(pricingModelEnumUpdateCapability);
		pricingModelEnumupdateAction.setClient(_client);
		pricingModelEnumupdateAction.setCallback((response) -> {
			
		});

		//fields initializations
		customerIdControl.setField(customer.getClass().getDeclaredField("_customer_id"));
		customerIdControl.setEntity(customer);

		plateNumberControl.setField(car.getClass().getDeclaredField("_plate_number"));
		plateNumberControl.setEntity(car);

		modelTypeKeyControl.setField(pricingModelEnum.getClass().getDeclaredField("_model_type_key"));
		modelTypeKeyControl.setEntity(pricingModelEnum);

		firstNameControl.setField(person.getClass().getDeclaredField("_first_name"));
		firstNameControl.setEntity(person);

		emailControl.setField(person.getClass().getDeclaredField("_email"));
		emailControl.setEntity(person);

		phoneNumberControl.setField(person.getClass().getDeclaredField("_phone_number"));
		phoneNumberControl.setEntity(person);

		lastNameControl.setField(person.getClass().getDeclaredField("_last_name"));
		lastNameControl.setEntity(person);


	}
	private Map<IEntity, Map<String, String>> prepareQuery(Customer customer) {
		Map<IEntity, Map<String, String>> map = new HashMap<IEntity, Map<String, String>>();
		
		Map<String, String> queryMap = new HashMap<String, String>();
		
		map.put(customer, queryMap);
		return map;
	}
}
