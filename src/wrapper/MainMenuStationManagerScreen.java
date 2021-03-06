package wrapper;

import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfImageView;
import controls.MfText;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sceneswitch.Context;
import sceneswitch.ISceneSwitcher;
import sceneswitch.SceneBase;

@AutoGenerated
public class MainMenuStationManagerScreen extends SceneBase {



	private MfImageView _fuelThresholdLevelScreenControl;
	private MfImageView _ordersStationManagerScreenControl;
	private MfImageView _inventoryReportScreenControl;
	private MfImageView _incomeReportScreenControl;
	private MfImageView _expenseReportScreenControl;
	private MfImageView _mainMenuMyFuelScreenControl;
	private MfText _firstNameControl;

	public MainMenuStationManagerScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
	}

	@Override
	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("MainMenuStationManagerScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		_fuelThresholdLevelScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$FuelThresholdLevelScreen"));
		_fuelThresholdLevelScreenControl.addEvent((event) -> { _switcher.switchScene("FuelThresholdLevelScreen"); });

		_ordersStationManagerScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$OrdersStationManagerScreen"));
		_ordersStationManagerScreenControl.addEvent((event) -> { _switcher.switchScene("OrdersStationManagerScreen"); });

		_inventoryReportScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$InventoryReportScreen"));
		_inventoryReportScreenControl.addEvent((event) -> { _switcher.switchScene("InventoryReportScreen"); });

		_incomeReportScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$IncomeReportScreen"));
		_incomeReportScreenControl.addEvent((event) -> { _switcher.switchScene("IncomeReportScreen"); });

		_expenseReportScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$ExpenseReportScreen"));
		_expenseReportScreenControl.addEvent((event) -> { _switcher.switchScene("ExpenseReportScreen"); });

		_mainMenuMyFuelScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMyFuelScreen"));
		_mainMenuMyFuelScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMyFuelScreen"); });

		//controls instantiation
		_firstNameControl = new MfText((Text) _scene.lookup("#context$person$first_name"));

		//fields initializations
		_firstNameControl.setField(_context.getPerson().getClass().getDeclaredField("_first_name"));
		_firstNameControl.setEntity(_context.getPerson());


	}

	@Override
	protected void onLoad() {
		
	}

	@Override
	public void setParameters(IEntity[] entities) {
		super.setParameters(entities);
	}



}
