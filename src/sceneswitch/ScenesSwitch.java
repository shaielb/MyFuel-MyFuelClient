package sceneswitch;

import java.util.HashMap;
import java.util.Map;

import javafx.stage.Stage;
import sceneswitch.SceneBase.ISceneSwitcher;
import wrapper.*;

public class ScenesSwitch {

	private Map<String, SceneBase> _scenesMap = new HashMap<String, SceneBase>();
	
	public ScenesSwitch(Stage stage) {
		ISceneSwitcher sceneSwitcher = (scenesName) -> { stage.setScene(_scenesMap.get(scenesName).getScene()); };
		
		_scenesMap.put("CustomerCharacterizationReportScreen", new CustomerCharacterizationReportScreen(sceneSwitcher));
		_scenesMap.put("CustomerManagmentScreen", new CustomerManagmentScreen(sceneSwitcher));
		_scenesMap.put("HomeHeatingNewOrderScreen", new HomeHeatingNewOrderScreen(sceneSwitcher));
		_scenesMap.put("HomeHeatingOrderDetailsScreen", new HomeHeatingOrderDetailsScreen(sceneSwitcher));
		_scenesMap.put("NewUserPopScreen", new NewUserPopScreen(sceneSwitcher));
		_scenesMap.put("OrdersStationManagerScreen", new OrdersStationManagerScreen(sceneSwitcher));
		_scenesMap.put("PurchasePlanMatchingScreen", new PurchasePlanMatchingScreen(sceneSwitcher));
		_scenesMap.put("SaleReportScreen", new SaleReportScreen(sceneSwitcher));
		_scenesMap.put("SalesManagementScreen", new SalesManagementScreen(sceneSwitcher));
		_scenesMap.put("SignUpAddCarScreen", new SignUpAddCarScreen(sceneSwitcher));
		_scenesMap.put("SignUpPaymentDetailsScreen", new SignUpPaymentDetailsScreen(sceneSwitcher));
		_scenesMap.put("SignUpPersonalDetailsScreen", new SignUpPersonalDetailsScreen(sceneSwitcher));
		_scenesMap.put("SupplyOrderConfirmationPopScreen", new SupplyOrderConfirmationPopScreen(sceneSwitcher));
		_scenesMap.put("TrackMyOrderScreen", new TrackMyOrderScreen(sceneSwitcher));
	}
}

