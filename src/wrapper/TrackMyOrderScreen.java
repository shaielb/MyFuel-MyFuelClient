package wrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.ActionControl;
import action.FilterCapability;
import action.RemoveCapability;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfHyperlink;
import controls.MfImageView;
import controls.MfNumberField;
import controls.MfText;
import db.entity.HomeHeatingOrder;
import db.interfaces.IEntity;
import handler.UiHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import messages.QueryContainer;
import messages.request.IFilter;
import messages.response.ResponseEvent;
import sceneswitch.Context;
import sceneswitch.ISceneSwitcher;
import sceneswitch.SceneBase;
import table.MfTable;
import widgets.table.Table;

@AutoGenerated
public class TrackMyOrderScreen extends SceneBase {
	
	private QueryContainer _filterQueryContainer;

	private HomeHeatingOrder _homeHeatingOrder;

	private MfImageView _mainMenuCustomerScreenControl;
	private MfNumberField _orderIdControl;
	private MfImageView _filterHomeHeatingOrderControlBtn;
	private ActionControl _homeHeatingOrderfilterActionBtn;
	private MfHyperlink _filterHomeHeatingOrderControlHl;
	private ActionControl _homeHeatingOrderfilterActionHl;
	private MfHyperlink _removeHomeHeatingOrderControl;
	private ActionControl _homeHeatingOrderremoveAction;
	private Table<HomeHeatingOrder> _homeHeatingOrderTableWrapper;
	private MfTable<HomeHeatingOrder> _homeHeatingOrderTable;
	private MfText _orderStatusControl;

	public TrackMyOrderScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
	}

	@Override
	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("TrackMyOrderScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		_mainMenuCustomerScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuCustomerScreen"));
		_mainMenuCustomerScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuCustomerScreen"); });

		//entities instantiation
		if (_homeHeatingOrder == null) {
			_homeHeatingOrder = new HomeHeatingOrder();
		}
		_homeHeatingOrder.setCustomer(_context.getCustomer());

		//controls instantiation
		_orderIdControl = new MfNumberField((TextField) _scene.lookup("#table$home_heating_order$order_id"), Integer.class);
		_orderStatusControl = new MfText((Text) _scene.lookup("#table$home_heating_order$order_status"));

		//tables instantiation
		BorderPane homeHeatingOrderBp = (BorderPane) _scene.lookup("#uitable$noneditable$plain$home_heating_order");
		_homeHeatingOrderTableWrapper = new Table<HomeHeatingOrder>();
		_homeHeatingOrderTable = new MfTable<HomeHeatingOrder>(HomeHeatingOrder.class);
		_homeHeatingOrderTable.setEditable(false);
		_homeHeatingOrderTableWrapper.setTable(_homeHeatingOrderTable);
		homeHeatingOrderBp.setCenter(_homeHeatingOrderTable);

		//initializations
		_filterHomeHeatingOrderControlBtn = new MfImageView((ImageView) _scene.lookup("#action$collect$home_heating_order$$$btn"));
		_filterHomeHeatingOrderControlBtn.
		setMouseImages("@resource/images/SmallSearch_btn.png", "@resource/images/SmallSearch_overbtn.png", "@resource/images/SmallSearch_clickbtn.png");
		_homeHeatingOrderfilterActionBtn = new ActionControl();
		_homeHeatingOrderfilterActionBtn.setControl(_filterHomeHeatingOrderControlBtn);
		FilterCapability homeHeatingOrderFilterCapabilityBtn = new FilterCapability();
		homeHeatingOrderFilterCapabilityBtn.setQueryContainers(prepareQuery(_homeHeatingOrder));
		_homeHeatingOrderfilterActionBtn.addCapability(homeHeatingOrderFilterCapabilityBtn);
		_homeHeatingOrderfilterActionBtn.setClient(_client);
		_homeHeatingOrderfilterActionBtn.setPreSend((request) -> {
			_filterQueryContainer.getQueryMap().put("order_id", "=");
			_filterQueryContainer.getQueryMap().put("customer_fk", "=");
			return true;
		});
		_homeHeatingOrderfilterActionBtn.setCallback((response) -> {
			Collection<IEntity> entities = response.getEntities();
			List<HomeHeatingOrder> list = new ArrayList<HomeHeatingOrder>();
			for (IEntity ientity : entities) {
				list.add((HomeHeatingOrder) ientity);
			}
			_homeHeatingOrderTable.setRows(list);
		});

		_filterHomeHeatingOrderControlHl = new MfHyperlink((Hyperlink) _scene.lookup("#action$collect$home_heating_order$$$hl"));
		_homeHeatingOrderfilterActionHl = new ActionControl();
		_homeHeatingOrderfilterActionHl.setControl(_filterHomeHeatingOrderControlHl);
		FilterCapability homeHeatingOrderFilterCapabilityHl = new FilterCapability();
		homeHeatingOrderFilterCapabilityHl.setQueryContainers(prepareQuery(_homeHeatingOrder));
		_homeHeatingOrderfilterActionHl.addCapability(homeHeatingOrderFilterCapabilityHl);
		_homeHeatingOrderfilterActionHl.setClient(_client);
		_homeHeatingOrderfilterActionHl.setPreSend((request) -> {
			try {
				_orderIdControl.setValue(null);
				_filterQueryContainer.getQueryMap().clear();
				_filterQueryContainer.getQueryMap().put("customer_fk", "=");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		});
		_homeHeatingOrderfilterActionHl.setCallback((response) -> {
			Collection<IEntity> entities = response.getEntities();
			List<HomeHeatingOrder> list = new ArrayList<HomeHeatingOrder>();
			for (IEntity ientity : entities) {
				list.add((HomeHeatingOrder) ientity);
			}
			_homeHeatingOrderTable.setRows(list);
		});

		_removeHomeHeatingOrderControl = new MfHyperlink((Hyperlink) _scene.lookup("#action$remove$home_heating_order"));
		_homeHeatingOrderremoveAction = new ActionControl();
		_homeHeatingOrderremoveAction.setControl(_removeHomeHeatingOrderControl);
		RemoveCapability homeHeatingOrderRemoveCapability = new RemoveCapability();
		homeHeatingOrderRemoveCapability.setQueryContainers(prepareRemoveQuery());
		_homeHeatingOrderremoveAction.addCapability(homeHeatingOrderRemoveCapability);
		_homeHeatingOrderremoveAction.setClient(_client);
		_homeHeatingOrderremoveAction.setPreSend((request) -> {
			if (!UiHandler.showAlert(AlertType.WARNING, "About To Delete Completed Orders", "", 
					"You Are About To Delete All Completed Orders, Continue?")) {
				return false;
			}
			// add remove allot functionality
			return true;
		});
		_homeHeatingOrderremoveAction.setCallback((response) -> {
			if (!response.isPassed()) {
				UiHandler.showAlert(AlertType.ERROR, "Orders Deletion", "", response.getDescription());
			}
			else {
				_filterQueryContainer.getQueryMap().clear();
				_filterQueryContainer.getQueryMap().put("customer_fk", "=");
				IFilter filterQuery = _client.getFilterRequest();
				filterQuery.addQueryContainer(_filterQueryContainer);
				try {
					ResponseEvent responseEvent = _client.sendRequest(filterQuery);
					responseEvent.continueWith((responseAfterDeletion) -> {
						if (!responseAfterDeletion.isPassed()) {
							UiHandler.showAlert(AlertType.ERROR, "Orders Deletion", "", responseAfterDeletion.getDescription());
						}
						else {
							_homeHeatingOrderTable.setEntities(responseAfterDeletion.getEntities());
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		//fields initializations
		_orderIdControl.setField(_homeHeatingOrder.getClass().getDeclaredField("_order_id"));
		_orderIdControl.setEntity(_homeHeatingOrder);

		_orderStatusControl.setField(_homeHeatingOrder.getClass().getDeclaredField("_order_status"));
		_orderStatusControl.setEntity(_homeHeatingOrder);
	}

	@Override
	protected void onLoad() {

	}

	@Override
	public void setParameters(IEntity[] entities) {
		super.setParameters(entities);
	}

	private List<QueryContainer> prepareQuery(HomeHeatingOrder homeHeatingOrder) {
		List<QueryContainer> containers = new ArrayList<QueryContainer>();

		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("order_id", "=");
		queryMap.put("customer_fk", "=");

		QueryContainer container = _filterQueryContainer = new QueryContainer();
		container.setQueryEntity(homeHeatingOrder);
		container.setQueryMap(queryMap);
		containers.add(container);
		return containers;
	}

	private List<QueryContainer> prepareRemoveQuery() {
		HomeHeatingOrder homeHeatingOrder = new HomeHeatingOrder();
		homeHeatingOrder.setOrderStatus("Done");
		homeHeatingOrder.setCustomer(_context.getCustomer());
		List<QueryContainer> containers = new ArrayList<QueryContainer>();

		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("order_status", "=");
		queryMap.put("customer_fk", "=");

		QueryContainer container = new QueryContainer();
		container.setQueryEntity(homeHeatingOrder);
		container.setQueryMap(queryMap);
		containers.add(container);
		return containers;
	}

	public HomeHeatingOrder getHomeHeatingOrder() {
		return _homeHeatingOrder;
	}

	public void setHomeHeatingOrder(HomeHeatingOrder homeHeatingOrder) {
		_homeHeatingOrder = homeHeatingOrder;
	}
}
