package wrappers.tmp;

import java.util.HashMap;
import java.util.Map;

import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfCheckBox;
import controls.MfImageView;
import controls.MfTextField;
import db.entity.Customer;
import db.entity.FuelingPurchase;
import db.entity.Payment;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sceneswitch.SceneBase;

@AutoGenerated
public class SignUpPaymentDetailsScreen extends SceneBase {

	public SignUpPaymentDetailsScreen(ISceneSwitcher sceneSwitcher, IClient client) throws Exception {
		super(sceneSwitcher, client);
		initialize();
	}

	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("SignUpPaymentDetailsScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		MfImageView signUpAddCarScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$SignUpAddCarScreen"));
		signUpAddCarScreenControl.addEvent((event) -> { _switcher.switchScene("SignUpAddCarScreen"); });

		MfImageView mainMenuMarketingScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMarketingScreen"));
		mainMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMarketingScreen"); });

		MfImageView signUpPersonalDetailsScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$SignUpPersonalDetailsScreen"));
		signUpPersonalDetailsScreenControl.addEvent((event) -> { _switcher.switchScene("SignUpPersonalDetailsScreen"); });

		//entities instantiation
		Customer customer = new Customer();
		Payment payment = new Payment();

		//entities assignments
		customer.setPayment(payment);

		//controls instantiation
		MfTextField creditCardNumberControl = new MfTextField((TextField) _scene.lookup("#table$customer$payment$credit_card_number"));
		MfTextField expirationDateMonthControl = new MfTextField((TextField) _scene.lookup("#table$customer$payment$expiration_date_month"));
		MfTextField expirationDateYearControl = new MfTextField((TextField) _scene.lookup("#table$customer$payment$expiration_date_year"));
		MfTextField cvvControl = new MfTextField((TextField) _scene.lookup("#table$customer$payment$cvv"));
		MfCheckBox cashControl = new MfCheckBox((CheckBox) _scene.lookup("#table$customer$payment$cash"));

		//fields initializations
		creditCardNumberControl.setField(payment.getClass().getDeclaredField("_credit_card_number"));
		creditCardNumberControl.setEntity(payment);

		expirationDateMonthControl.setField(payment.getClass().getDeclaredField("_expiration_date_month"));
		expirationDateMonthControl.setEntity(payment);

		expirationDateYearControl.setField(payment.getClass().getDeclaredField("_expiration_date_year"));
		expirationDateYearControl.setEntity(payment);

		cvvControl.setField(payment.getClass().getDeclaredField("_cvv"));
		cvvControl.setEntity(payment);

		cashControl.setField(payment.getClass().getDeclaredField("_cash"));
		cashControl.setEntity(payment);


	}
	private Map<IEntity, Map<String, String>> prepareQuery(FuelingPurchase fuelingPurchase) {
		Map<IEntity, Map<String, String>> map = new HashMap<IEntity, Map<String, String>>();
		
		Map<String, String> queryMap = new HashMap<String, String>();
		
		map.put(fuelingPurchase, queryMap);
		return map;
	}
}
