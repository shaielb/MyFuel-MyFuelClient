package wrappers.tmp;

import java.util.HashMap;
import java.util.Map;

import adapter.base.ControlAdapter;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfCheckBox;
import controls.MfImageView;
import controls.MfText;
import controls.MfTextField;
import db.entity.HomeHeatingOrder;
import db.entity.StationManagerReports;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sceneswitch.SceneBase;

@AutoGenerated
public class HomeHeatingNewOrderScreen extends SceneBase {

	public HomeHeatingNewOrderScreen(ISceneSwitcher sceneSwitcher, IClient client) throws Exception {
		super(sceneSwitcher, client);
		initialize();
	}

	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("HomeHeatingNewOrderScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		MfImageView maimMenuCustomerScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MaimMenuCustomerScreen"));
		maimMenuCustomerScreenControl.addEvent((event) -> { _switcher.switchScene("MaimMenuCustomerScreen"); });

		MfImageView homeHeatingOrderDetailsScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$HomeHeatingOrderDetailsScreen"));
		homeHeatingOrderDetailsScreenControl.addEvent((event) -> { _switcher.switchScene("HomeHeatingOrderDetailsScreen"); });

		//entities instantiation
		HomeHeatingOrder homeHeatingOrder = new HomeHeatingOrder();

		//controls instantiation
		MfTextField amountControl = new MfTextField((TextField) _scene.lookup("#table$home_heating_order$amount"));
		MfCheckBox orderUrgencyControlRegular = new MfCheckBox((CheckBox) _scene.lookup("#table$home_heating_order$order_urgency$$regular"));
		MfCheckBox orderUrgencyControlExpress = new MfCheckBox((CheckBox) _scene.lookup("#table$home_heating_order$order_urgency$$express"));
		MfText pricePerLiterControl = new MfText((Text) _scene.lookup("#table$home_heating_order$price_per_liter"));
		MfText totalPriceControl = new MfText((Text) _scene.lookup("#table$home_heating_order$total_price"));

		//fields initializations
		amountControl.setField(homeHeatingOrder.getClass().getDeclaredField("_amount"));
		amountControl.setEntity(homeHeatingOrder);

		orderUrgencyControlRegular.setField(homeHeatingOrder.getClass().getDeclaredField("_order_urgency"));
		orderUrgencyControlRegular.setEntity(homeHeatingOrder);

		orderUrgencyControlExpress.setField(homeHeatingOrder.getClass().getDeclaredField("_order_urgency"));
		orderUrgencyControlExpress.setEntity(homeHeatingOrder);

		pricePerLiterControl.setField(homeHeatingOrder.getClass().getDeclaredField("_price_per_liter"));
		pricePerLiterControl.setEntity(homeHeatingOrder);

		totalPriceControl.setField(homeHeatingOrder.getClass().getDeclaredField("_total_price"));
		totalPriceControl.setEntity(homeHeatingOrder);

		//grouping
		groupControls(new ControlAdapter[] { orderUrgencyControlRegular, orderUrgencyControlExpress });

	}
	private Map<IEntity, Map<String, String>> prepareQuery(StationManagerReports stationManagerReports) {
		Map<IEntity, Map<String, String>> map = new HashMap<IEntity, Map<String, String>>();
		
		Map<String, String> queryMap = new HashMap<String, String>();
		
		map.put(stationManagerReports, queryMap);
		return map;
	}
}
