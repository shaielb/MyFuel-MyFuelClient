package wrapper;

import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import db.entity.*;
import adapter.base.ControlAdapter;
import java.util.HashSet;
import widgets.table.Table;
import application.Main;
import controls.*;
import annotations.AutoGenerated;
import java.util.HashMap;
import javafx.scene.Parent;
import sceneswitch.SceneBase;
import java.util.Map;
import java.util.Collection;
import table.MfTable;
import widgets.table.*;
import client.IClient;

import javafx.scene.Scene;
import javafx.scene.control.*;
import action.*;
import db.interfaces.IEntity;
import javafx.scene.image.ImageView;
import java.util.Set;
import javafx.scene.text.Text;

@AutoGenerated
public class SignUpAddCarScreen extends SceneBase {

	public SignUpAddCarScreen(ISceneSwitcher sceneSwitcher, IClient client) throws Exception {
		super(sceneSwitcher, client);
		initialize();
	}

	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("SignUpAddCarScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		MfImageView mainMenuMarketingScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMarketingScreen"));
		mainMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMarketingScreen"); });

		MfImageView signUpPaymentDetailsScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$SignUpPaymentDetailsScreen"));
		signUpPaymentDetailsScreenControl.addEvent((event) -> { _switcher.switchScene("SignUpPaymentDetailsScreen"); });

		//entities instantiation
		Customer customer = new Customer();
		Car car = new Car();

		//tables instantiation
		BorderPane carBp = (BorderPane) _scene.lookup("#uitable$editable$addremove$car");
		Table<Car> carTableWrapper = new Table<Car>();
		MfTable<Car> carTable = new MfTable<Car>(Car.class);
		carTable.setEditable(true);
		MfAddremoveDecorator<Car> carAddremoveDecorator = new MfAddremoveDecorator<Car>();
		carTableWrapper.addDecorator(carAddremoveDecorator);
		carTableWrapper.setTable(carTable);
		carBp.setCenter(carTable);

		//initializations
		MfImageView insertCustomerControl = new MfImageView((ImageView) _scene.lookup("#action$insert$customer"));
		ActionControl customerinsertAction = new ActionControl();
		customerinsertAction.setControl(insertCustomerControl);
		InsertCapability customerInsertCapability = new InsertCapability();
		customerInsertCapability.addEntity(customer);
		customerinsertAction.addCapability(customerInsertCapability);
		customerinsertAction.setClient(_client);
		customerinsertAction.setCallback((response) -> {
			
		});


	}
	private Map<IEntity, Map<String, String>> prepareQuery(FuelingPurchase fuelingPurchase) {
		Map<IEntity, Map<String, String>> map = new HashMap<IEntity, Map<String, String>>();
		
		Map<String, String> queryMap = new HashMap<String, String>();
		
		map.put(fuelingPurchase, queryMap);
		return map;
	}
}
