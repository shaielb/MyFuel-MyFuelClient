package wrapper;

import adapter.base.ControlAdapter;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfCheckBox;
import controls.MfImageView;
import controls.MfNumberField;
import controls.MfText;
import db.entity.FuelEnum;
import db.entity.HomeHeatingDiscountEnum;
import db.entity.HomeHeatingOrder;
import db.entity.OrderUrgencyEnum;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sceneswitch.Context;
import sceneswitch.ISceneSwitcher;
import sceneswitch.SceneBase;

@AutoGenerated
public class HomeHeatingNewOrderScreen extends SceneBase {

	private static String HomeHeating = "HomeHeating";
	private static String Below600 = "Below 600";
	private static String In600800 = "600-800";
	private static String Above800 = "Above 800";

	private HomeHeatingOrder _homeHeatingOrder;

	private MfImageView _mainMenuCustomerScreenControl;
	private MfNumberField _amountControl;
	private MfImageView _homeHeatingOrderDetailsScreenControl;
	private MfCheckBox _orderUrgencyEnumControlRegular;
	private MfCheckBox _orderUrgencyEnumControlExpress;
	private MfText _pricePerLiterControl;
	private MfText _totalPriceControl;

	private HomeHeatingOrderDetailsScreen _homeHeatingOrderDetailsScreen;

	public HomeHeatingNewOrderScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
	}

	@Override
	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("HomeHeatingNewOrderScreen.fxml"));
		_scene = new Scene(root);

		_homeHeatingOrderDetailsScreen = new HomeHeatingOrderDetailsScreen(_switcher, _client, _context);

		//scene switchers
		_mainMenuCustomerScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuCustomerScreen"));
		_mainMenuCustomerScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuCustomerScreen"); });

		_homeHeatingOrderDetailsScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$HomeHeatingOrderDetailsScreen"));
		_homeHeatingOrderDetailsScreenControl.addEvent((event) -> { 

			/*Alert alert = new Alert(Alert.AlertType.WARNING, "I Warn You!", ButtonType.OK, ButtonType.CANCEL);
			DialogPane dp = alert.getDialogPane();
			Stage dialogStage = new Stage(StageStyle.UTILITY);
			for (ButtonType buttonType : dp.getButtonTypes()) {
			    ButtonBase button = (ButtonBase) dp.lookupButton(buttonType);
			    button.setOnAction(evt -> {
			    	dp.setUserData(buttonType);
			        dialogStage.close();
			    });
			}

			// replace old scene root with placeholder to allow using root in other Scene
			dp.getScene().setRoot(new Group());

			dp.setPadding(new Insets(10, 0, 10, 0));
			Scene scene = new Scene(dp);*/

			/*Stage dialogStage = new Stage(StageStyle.UTILITY);
			dialogStage.setScene(_homeHeatingOrderDetailsScreen.getScene());
			dialogStage.initModality(Modality.APPLICATION_MODAL);
			dialogStage.setAlwaysOnTop(true);
			dialogStage.setResizable(false);
			dialogStage.showAndWait();*/

			_switcher.switchScene("HomeHeatingOrderDetailsScreen", _homeHeatingOrder); 
		});

		//entities instantiation
		if (_homeHeatingOrder == null) {
			_homeHeatingOrder = new HomeHeatingOrder();
			_homeHeatingOrder.setCustomer(_context.getCustomer());
			FuelEnum fe = _client.getEnum(FuelEnum.class, HomeHeating);
			_homeHeatingOrder.setPricePerLiter(fe.getSupplierPricePerLiter());
			_homeHeatingOrder.setFuelEnum(fe);
		}

		//controls instantiation
		_amountControl = new MfNumberField((TextField) _scene.lookup("#table$home_heating_order$amount"), Double.class);
		_orderUrgencyEnumControlRegular = new MfCheckBox((CheckBox) _scene.lookup("#table$home_heating_order$order_urgency_enum$$regular"));
		_orderUrgencyEnumControlExpress = new MfCheckBox((CheckBox) _scene.lookup("#table$home_heating_order$order_urgency_enum$$express"));
		_pricePerLiterControl = new MfText((Text) _scene.lookup("#table$home_heating_order$price_per_liter"));
		_totalPriceControl = new MfText((Text) _scene.lookup("#table$home_heating_order$total_price"));

		// calculating the home heating fuel order total price
		_homeHeatingOrderDetailsScreenControl.getInstance().setDisable(true);

		// added start
		_amountControl.addEvent((event) -> {
			setTotalPrice();
		});
		_orderUrgencyEnumControlRegular.addEvent((event) -> {
			setTotalPrice();
		});
		_orderUrgencyEnumControlExpress.addEvent((event) -> {
			setTotalPrice();
		});
		// added end

		//fields initializations
		_amountControl.setField(_homeHeatingOrder.getClass().getDeclaredField("_amount"));
		_amountControl.setEntity(_homeHeatingOrder);

		_orderUrgencyEnumControlRegular.setField(_homeHeatingOrder.getClass().getDeclaredField("_order_urgency_enum"));
		_orderUrgencyEnumControlRegular.setEntity(_homeHeatingOrder);
		_orderUrgencyEnumControlRegular.setValidValue(_client.getEnum("order_urgency_enum", "regular"));

		_orderUrgencyEnumControlExpress.setField(_homeHeatingOrder.getClass().getDeclaredField("_order_urgency_enum"));
		_orderUrgencyEnumControlExpress.setEntity(_homeHeatingOrder);
		_orderUrgencyEnumControlExpress.setValidValue(_client.getEnum("order_urgency_enum", "express"));

		_pricePerLiterControl.setField(_homeHeatingOrder.getClass().getDeclaredField("_price_per_liter"));
		_pricePerLiterControl.setEntity(_homeHeatingOrder);

		_totalPriceControl.setField(_homeHeatingOrder.getClass().getDeclaredField("_total_price"));
		_totalPriceControl.setEntity(_homeHeatingOrder);

		//grouping
		groupControls(new ControlAdapter[] { _orderUrgencyEnumControlRegular, _orderUrgencyEnumControlExpress });

	}

	@Override
	protected void onLoad() {

	}

	private void setTotalPrice() {
		Double maxPrice = _client.getEnum(FuelEnum.class, HomeHeating).getMaxPricePerType();
		Double amount = _homeHeatingOrder.getAmount();
		HomeHeatingDiscountEnum hhe = _client.getEnum(HomeHeatingDiscountEnum.class, Below600);
		if (amount != null) {
			Double totalPrice = maxPrice * amount;
			Double discount = hhe.getDiscount();
			if (amount >= 600 && amount <= 800) {
				hhe = _client.getEnum(HomeHeatingDiscountEnum.class, In600800);
				discount = hhe.getDiscount();
			}
			else if (amount > 800) {
				hhe = _client.getEnum(HomeHeatingDiscountEnum.class, Above800);
				discount = hhe.getDiscount();
			}
			totalPrice *= discount;
			OrderUrgencyEnum oue = _homeHeatingOrder.getOrderUrgencyEnum();
			if (oue != null) {
				totalPrice *= oue.getExtraDeliveryPrice();
			}
			try {
				_totalPriceControl.setValue(totalPrice);
				_pricePerLiterControl.setValue(totalPrice / amount);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				_totalPriceControl.setValue(0);
				_pricePerLiterControl.setValue(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		_homeHeatingOrder.setHomeHeatingDiscountEnum(hhe);
		_homeHeatingOrderDetailsScreenControl.getInstance().setDisable(
				(_homeHeatingOrder.getAmount() == null || 
				_homeHeatingOrder.getAmount() == 0 || 
				_homeHeatingOrder.getOrderUrgencyEnum() == null));

	}

	@Override
	public void setParameters(IEntity[] entities) {
		super.setParameters(entities);
	}


	public HomeHeatingOrder getHomeHeatingOrder() {
		return _homeHeatingOrder;
	}

	public void setHomeHeatingOrder(HomeHeatingOrder homeHeatingOrder) {
		_homeHeatingOrder = homeHeatingOrder;
	}
}
