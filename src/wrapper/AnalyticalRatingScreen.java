package wrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.ActionControl;
import action.FilterCapability;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfImageView;
import db.entity.AnalyticalRatings;
import db.entity.CustomersRatingsView;
import db.interfaces.IEntity;
import handler.UiHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import messages.QueryContainer;
import messages.request.IFilter;
import messages.response.ResponseEvent;
import panels.SearchPanel;
import sceneswitch.Context;
import sceneswitch.ISceneSwitcher;
import sceneswitch.SceneBase;
import table.MfTable;
import widgets.table.MfSingleDecorator;
import widgets.table.Table;

@AutoGenerated
@SuppressWarnings("unchecked")
public class AnalyticalRatingScreen extends SceneBase {

	private AnalyticalRatings _analyticalRatings;
	private CustomersRatingsView _customersRatings;

	private MfImageView _mainMenuMarketingScreenControl;
	private SearchPanel<AnalyticalRatings> _analyticalRatingsSearchPnl;
	private Table<CustomersRatingsView> _customersRatingsTableWrapper;
	private MfTable<CustomersRatingsView> _customersRatingsTable;
	private MfImageView _filterAnalyticalRatingsControl;
	private ActionControl _analyticalRatingsfilterAction;

	public AnalyticalRatingScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
	}

	@Override
	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("AnalyticalRatingScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		_mainMenuMarketingScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMarketingScreen"));
		_mainMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMarketingScreen"); });

		//entities instantiation
		if (_analyticalRatings == null) {
			_analyticalRatings = new AnalyticalRatings();
		}
		if (_customersRatings == null) {
			_customersRatings = new CustomersRatingsView();
		}

		//tables instantiation
		BorderPane analyticalRatingsBp = (BorderPane) _scene.lookup("#uisearch$analytical_ratings");
		_analyticalRatingsSearchPnl = new SearchPanel(AnalyticalRatings.class, _client, (response) -> {
			_customersRatingsTable.setRows(response.getEntitiesAsType());
		});
		analyticalRatingsBp.setCenter(_analyticalRatingsSearchPnl);

		BorderPane customersRatingsBp = (BorderPane) _scene.lookup("#uitable$noneditable$single$customers_ratings");
		_customersRatingsTableWrapper = new Table<CustomersRatingsView>();
		_customersRatingsTable = new MfTable<CustomersRatingsView>(CustomersRatingsView.class);
		_customersRatingsTable.setEditable(false);
		MfSingleDecorator<CustomersRatingsView> customersRatingsSingleDecorator = new MfSingleDecorator<CustomersRatingsView>();
		_customersRatingsTableWrapper.addDecorator(customersRatingsSingleDecorator);
		_customersRatingsTableWrapper.setTable(_customersRatingsTable);
		customersRatingsBp.setCenter(_customersRatingsTable);

		//initializations
		_filterAnalyticalRatingsControl = new MfImageView((ImageView) _scene.lookup("#action$collect$analytical_ratings"));
		_filterAnalyticalRatingsControl.
		setMouseImages("@resource/images/Search_btn.png", "@resource/images/Search_overbtn.png", "@resource/images/Search_clickbtn.png");
		_analyticalRatingsfilterAction = new ActionControl();
		_analyticalRatingsfilterAction.setControl(_filterAnalyticalRatingsControl);
		FilterCapability analyticalRatingsFilterCapability = new FilterCapability();
		analyticalRatingsFilterCapability.setQueryContainers(prepareQuery(_analyticalRatings));
		_analyticalRatingsfilterAction.addCapability(analyticalRatingsFilterCapability);
		_analyticalRatingsfilterAction.setClient(_client);
		_analyticalRatingsfilterAction.setPreSend((request) -> {

			return true;
		});
		_analyticalRatingsfilterAction.setCallback((response) -> {
			Collection<IEntity> entities = response.getEntities();
			for (IEntity ientity : entities) {
				AnalyticalRatings entity = (AnalyticalRatings) ientity;
			}
		});


	}

	@Override
	protected void onLoad() throws IOException {
		IFilter filterRequest = _client.getFilterRequest();
		QueryContainer queryContainer = new QueryContainer();
		queryContainer.setQueryEntity(_customersRatings);
		filterRequest.addQueryContainer(queryContainer);
		ResponseEvent responseEvent = _client.sendRequest(filterRequest);
		responseEvent.continueWith((response) -> {
			if (response.isPassed()) {
				try {
					Collection<CustomersRatingsView> entities = response.getEntitiesAsType();
					_customersRatingsTable.setRows(entities);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				UiHandler.showAlert(AlertType.ERROR, "Customer Ratings Collection", "", response.getDescription());
			}
		});
	}

	@Override
	public void setParameters(IEntity[] entities) {
		super.setParameters(entities);
	}

	private List<QueryContainer> prepareQuery(AnalyticalRatings analyticalRatings) {
		List<QueryContainer> containers = new ArrayList<QueryContainer>();

		Map<String, String> queryMap = new HashMap<String, String>();

		QueryContainer container = new QueryContainer();
		container.setQueryEntity(analyticalRatings);
		container.setQueryMap(queryMap);
		containers.add(container);
		return containers;
	}


}