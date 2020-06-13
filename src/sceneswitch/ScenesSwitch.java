package sceneswitch;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import db.entity.SystemUser;
import java.util.Set;

import javafx.stage.Stage;
import messages.request.IUpdate;
import sceneswitch.SceneBase.ISceneSwitcher;
import wrapper.*;
import client.IClient;
import db.interfaces.IEntity;

public class ScenesSwitch {

	private IClient _client;

	private Context _context = new Context();

	private Map<String, SceneBase> _scenesMap = new HashMap<String, SceneBase>();

	private ISceneSwitcher _sceneSwitcher;
	
	public ScenesSwitch(Stage stage, IClient client) throws Exception {
		_client = client;
		_sceneSwitcher = (scenesName) -> {
			stage.setScene(getScene(scenesName).getScene());
		};
	}

	public void onClose() throws IOException {
		SystemUser su = _context.getSystemUser();
		if (su != null) {
			su.setOnlineStatus(false);
			IUpdate updateRequest = _client.getUpdateRequest();
			Set<IEntity> set = new HashSet<IEntity>();
			set.add(su);
			updateRequest.setEntities(set);
			_client.sendRequest(updateRequest);
		}
	}

	public SceneBase getScene(String scenesName) {
			SceneBase sb = _scenesMap.get(scenesName);
			if (sb == null) {
				try {
					switch(scenesName) {
					case "AboutUsScreen" : 
						_scenesMap.put("AboutUsScreen", sb = new AboutUsScreen(_sceneSwitcher, _client, _context));
						break;

					case "CustomerCharacterizationReportScreen" : 
						_scenesMap.put("CustomerCharacterizationReportScreen", sb = new CustomerCharacterizationReportScreen(_sceneSwitcher, _client, _context));
						break;

					case "CustomerManagementScreen" : 
						_scenesMap.put("CustomerManagementScreen", sb = new CustomerManagementScreen(_sceneSwitcher, _client, _context));
						break;

					case "CustomerRatingScreen" : 
						_scenesMap.put("CustomerRatingScreen", sb = new CustomerRatingScreen(_sceneSwitcher, _client, _context));
						break;

					case "EditSalesScreen" : 
						_scenesMap.put("EditSalesScreen", sb = new EditSalesScreen(_sceneSwitcher, _client, _context));
						break;

					case "ExpenseReportScreen" : 
						_scenesMap.put("ExpenseReportScreen", sb = new ExpenseReportScreen(_sceneSwitcher, _client, _context));
						break;

					case "FastFuelingScreen" : 
						_scenesMap.put("FastFuelingScreen", sb = new FastFuelingScreen(_sceneSwitcher, _client, _context));
						break;

					case "FuelThresholdLevelScreen" : 
						_scenesMap.put("FuelThresholdLevelScreen", sb = new FuelThresholdLevelScreen(_sceneSwitcher, _client, _context));
						break;

					case "HomeHeatingNewOrderScreen" : 
						_scenesMap.put("HomeHeatingNewOrderScreen", sb = new HomeHeatingNewOrderScreen(_sceneSwitcher, _client, _context));
						break;

					case "HomeHeatingOrderDetailsScreen" : 
						_scenesMap.put("HomeHeatingOrderDetailsScreen", sb = new HomeHeatingOrderDetailsScreen(_sceneSwitcher, _client, _context));
						break;

					case "IncomeReportScreen" : 
						_scenesMap.put("IncomeReportScreen", sb = new IncomeReportScreen(_sceneSwitcher, _client, _context));
						break;

					case "InventoryReportScreen" : 
						_scenesMap.put("InventoryReportScreen", sb = new InventoryReportScreen(_sceneSwitcher, _client, _context));
						break;

					case "LogInScreen" : 
						_scenesMap.put("LogInScreen", sb = new LogInScreen(_sceneSwitcher, _client, _context));
						break;

					case "MainMenuCustomerScreen" : 
						_scenesMap.put("MainMenuCustomerScreen", sb = new MainMenuCustomerScreen(_sceneSwitcher, _client, _context));
						break;

					case "MainMenuMarketingScreen" : 
						_scenesMap.put("MainMenuMarketingScreen", sb = new MainMenuMarketingScreen(_sceneSwitcher, _client, _context));
						break;

					case "MainMenuMyFuelScreen" : 
						_scenesMap.put("MainMenuMyFuelScreen", sb = new MainMenuMyFuelScreen(_sceneSwitcher, _client, _context));
						break;

					case "MainMenuStationManagerScreen" : 
						_scenesMap.put("MainMenuStationManagerScreen", sb = new MainMenuStationManagerScreen(_sceneSwitcher, _client, _context));
						break;

					case "OrdersStationManagerScreen" : 
						_scenesMap.put("OrdersStationManagerScreen", sb = new OrdersStationManagerScreen(_sceneSwitcher, _client, _context));
						break;

					case "PricingModelMatchingScreen" : 
						_scenesMap.put("PricingModelMatchingScreen", sb = new PricingModelMatchingScreen(_sceneSwitcher, _client, _context));
						break;

					case "PurchasePlanMatchingScreen" : 
						_scenesMap.put("PurchasePlanMatchingScreen", sb = new PurchasePlanMatchingScreen(_sceneSwitcher, _client, _context));
						break;

					case "SaleReportScreen" : 
						_scenesMap.put("SaleReportScreen", sb = new SaleReportScreen(_sceneSwitcher, _client, _context));
						break;

					case "SalesManagementScreen" : 
						_scenesMap.put("SalesManagementScreen", sb = new SalesManagementScreen(_sceneSwitcher, _client, _context));
						break;

					case "SetPriceScreen" : 
						_scenesMap.put("SetPriceScreen", sb = new SetPriceScreen(_sceneSwitcher, _client, _context));
						break;

					case "SignUpAddCarScreen" : 
						_scenesMap.put("SignUpAddCarScreen", sb = new SignUpAddCarScreen(_sceneSwitcher, _client, _context));
						break;

					case "SignUpPaymentDetailsScreen" : 
						_scenesMap.put("SignUpPaymentDetailsScreen", sb = new SignUpPaymentDetailsScreen(_sceneSwitcher, _client, _context));
						break;

					case "SignUpPersonalDetailsScreen" : 
						_scenesMap.put("SignUpPersonalDetailsScreen", sb = new SignUpPersonalDetailsScreen(_sceneSwitcher, _client, _context));
						break;

					case "SupplyOrderConfirmationPopScreen" : 
						_scenesMap.put("SupplyOrderConfirmationPopScreen", sb = new SupplyOrderConfirmationPopScreen(_sceneSwitcher, _client, _context));
						break;

					case "TrackMyOrderScreen" : 
						_scenesMap.put("TrackMyOrderScreen", sb = new TrackMyOrderScreen(_sceneSwitcher, _client, _context));
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		return sb;
	}
}

