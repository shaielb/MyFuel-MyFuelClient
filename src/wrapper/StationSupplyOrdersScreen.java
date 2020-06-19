package wrapper;

import java.util.HashSet;
import java.util.Set;

import action.ActionControl;
import action.UpdateCapability;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfImageView;
import db.entity.StationSupplyOrder;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import sceneswitch.Context;
import sceneswitch.ISceneSwitcher;
import sceneswitch.SceneBase;
import table.MfTable;
import widgets.table.MfSingleDecorator;
import widgets.table.Table;

@AutoGenerated
public class StationSupplyOrdersScreen extends SceneBase {

	private StationSupplyOrder _stationSupplyOrder;

	private MfImageView _mainMenuSupplierScreenControl;
	private Table<StationSupplyOrder> _stationSupplyOrderTableWrapper;
	private MfTable<StationSupplyOrder> _stationSupplyOrderTable;
	private MfImageView _updateStationSupplyOrderControl;
	private ActionControl _stationSupplyOrderupdateAction;

	public StationSupplyOrdersScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
	}

	@Override
	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("StationSupplyOrdersScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		_mainMenuSupplierScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuSupplierScreen"));
		_mainMenuSupplierScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuSupplierScreen"); });

		//entities instantiation
		if (_stationSupplyOrder == null) {
			_stationSupplyOrder = new StationSupplyOrder();
		}

		//tables instantiation
		BorderPane stationSupplyOrderBp = (BorderPane) _scene.lookup("#uitable$editable$single$station_supply_order");
		_stationSupplyOrderTableWrapper = new Table<StationSupplyOrder>();
		_stationSupplyOrderTable = new MfTable<StationSupplyOrder>(StationSupplyOrder.class);
		_stationSupplyOrderTable.setEditable(true);
		MfSingleDecorator<StationSupplyOrder> stationSupplyOrderSingleDecorator = new MfSingleDecorator<StationSupplyOrder>();
		_stationSupplyOrderTableWrapper.addDecorator(stationSupplyOrderSingleDecorator);
		_stationSupplyOrderTableWrapper.setTable(_stationSupplyOrderTable);
		stationSupplyOrderBp.setCenter(_stationSupplyOrderTable);

		//initializations
		_updateStationSupplyOrderControl = new MfImageView((ImageView) _scene.lookup("#action$update$station_supply_order"));
		_updateStationSupplyOrderControl.
			setMouseImages("@resource/images/Done_btn.png", "@resource/images/Done_overbtn.png", "@resource/images/Done_clickbtn.png");
		_stationSupplyOrderupdateAction = new ActionControl();
		_stationSupplyOrderupdateAction.setControl(_updateStationSupplyOrderControl);
		UpdateCapability stationSupplyOrderUpdateCapability = new UpdateCapability();
		Set<IEntity> stationSupplyOrderUpdateEntities = new HashSet<IEntity>();
		stationSupplyOrderUpdateCapability.setEntities(stationSupplyOrderUpdateEntities);
		_stationSupplyOrderupdateAction.addCapability(stationSupplyOrderUpdateCapability);
		_stationSupplyOrderupdateAction.setClient(_client);
		_stationSupplyOrderupdateAction.setPreSend((request) -> {

			return true;
		});
		_stationSupplyOrderupdateAction.setCallback((response) -> {
			
		});


	}

	@Override
	protected void onLoad() {
		
	}

	@Override
	public void setParameters(IEntity[] entities) {
		super.setParameters(entities);
	}



}