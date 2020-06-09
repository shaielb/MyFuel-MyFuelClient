package sceneswitch;

import java.util.HashMap;
import java.util.Map;

import javafx.stage.Stage;
import sceneswitch.SceneBase.ISceneSwitcher;
import wrapper.*;
import client.IClient;

public class ScenesSwitch {

	private Map<String, SceneBase> _scenesMap = new HashMap<String, SceneBase>();
	
	public ScenesSwitch(Stage stage, IClient client) throws Exception {
		ISceneSwitcher sceneSwitcher = (scenesName) -> { stage.setScene(_scenesMap.get(scenesName).getScene()); };
		
		_scenesMap.put("AboutUsScreen", new AboutUsScreen(sceneSwitcher, client));
		_scenesMap.put("CustomerCharacterizationReportScreen", new CustomerCharacterizationReportScreen(sceneSwitcher, client));
		_scenesMap.put("CustomerManagementScreen", new CustomerManagementScreen(sceneSwitcher, client));
		_scenesMap.put("CustomerRatingScreen", new CustomerRatingScreen(sceneSwitcher, client));
		_scenesMap.put("ExpenseReportScreen", new ExpenseReportScreen(sceneSwitcher, client));
		_scenesMap.put("FastFuelingScreen", new FastFuelingScreen(sceneSwitcher, client));
		_scenesMap.put("FuelThresholdLevelScreen", new FuelThresholdLevelScreen(sceneSwitcher, client));
		_scenesMap.put("HomeHeatingNewOrderScreen", new HomeHeatingNewOrderScreen(sceneSwitcher, client));
		_scenesMap.put("HomeHeatingOrderDetailsScreen", new HomeHeatingOrderDetailsScreen(sceneSwitcher, client));
		_scenesMap.put("IncomeReportScreen", new IncomeReportScreen(sceneSwitcher, client));
		_scenesMap.put("InventoryReportScreen", new InventoryReportScreen(sceneSwitcher, client));
		_scenesMap.put("LogInScreen", new LogInScreen(sceneSwitcher, client));
		_scenesMap.put("MainMenuCustomerScreen", new MainMenuCustomerScreen(sceneSwitcher, client));
		_scenesMap.put("MainMenuMarketingScreen", new MainMenuMarketingScreen(sceneSwitcher, client));
		_scenesMap.put("MainMenuMyFuelScreen", new MainMenuMyFuelScreen(sceneSwitcher, client));
		_scenesMap.put("MainMenuStationManagerScreen", new MainMenuStationManagerScreen(sceneSwitcher, client));
		_scenesMap.put("OrdersStationManagerScreen", new OrdersStationManagerScreen(sceneSwitcher, client));
		_scenesMap.put("PricingModelMatchingScreen", new PricingModelMatchingScreen(sceneSwitcher, client));
		_scenesMap.put("PurchasePlanMatchingScreen", new PurchasePlanMatchingScreen(sceneSwitcher, client));
		_scenesMap.put("SaleReportScreen", new SaleReportScreen(sceneSwitcher, client));
		_scenesMap.put("SalesManagementScreen", new SalesManagementScreen(sceneSwitcher, client));
		_scenesMap.put("SetPriceScreen", new SetPriceScreen(sceneSwitcher, client));
		_scenesMap.put("SignUpAddCarScreen", new SignUpAddCarScreen(sceneSwitcher, client));
		_scenesMap.put("SignUpPaymentDetailsScreen", new SignUpPaymentDetailsScreen(sceneSwitcher, client));
		_scenesMap.put("SignUpPersonalDetailsScreen", new SignUpPersonalDetailsScreen(sceneSwitcher, client));
		_scenesMap.put("SupplyOrderConfirmationPopScreen", new SupplyOrderConfirmationPopScreen(sceneSwitcher, client));
		_scenesMap.put("TrackMyOrderScreen", new TrackMyOrderScreen(sceneSwitcher, client));	}

	public SceneBase getScene(String key) {
		return _scenesMap.get(key);
	}
}

