package panels;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import action.ActionControl;
import action.FilterCapability;
import adapter.base.ControlAdapter;
import client.IClient;
import comperators.Comperators;
import controls.MfButton;
import controls.MfComboBox;
import db.interfaces.IEntity;
import handler.UiHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import messages.Header.RequestType;
import messages.QueryContainer;
import messages.response.IResponseCallBack;
import utilities.StringUtil;

/**
 * @author shaielb
 *
 * @param <TEntity>
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SearchPanel<TEntity extends IEntity> extends BorderPane {

	/**
	 * 
	 */
	@SuppressWarnings("serial")
	private Map<Class<?>, String[]> _signsMap = new HashMap<Class<?>, String[]>() {{
		put(Number.class, new String[] {Comperators.NoFilter, Comperators.E, Comperators.Ne, 
				Comperators.Ge, Comperators.G, Comperators.Le, Comperators.L});
		put(Boolean.class, new String[] {Comperators.NoFilter, Comperators.E});
		put(String.class, new String[] {Comperators.NoFilter, Comperators.Equals, 
				Comperators.NotEqual, Comperators.StartsWith, Comperators.Containes, 
				Comperators.EndsWith});
	}};

	/**
	 * 
	 */
	protected ActionControl _searchButton;

	/**
	 * 
	 */
	protected TEntity _searchEntity;

	/**
	 * 
	 */
	protected Map<String, String> _searchSigns = new HashMap<String, String>();

	/**
	 * @param entityClass
	 * @param client
	 * @param callback
	 * @throws Exception
	 */
	public SearchPanel(Class<TEntity> entityClass, IClient client, IResponseCallBack callback) throws Exception {
		initialize(entityClass, client, callback);
	}

	/**
	 * @param entityClass
	 * @param client
	 * @param callback
	 * @throws Exception
	 */
	public void initialize(Class<TEntity> entityClass, IClient client, IResponseCallBack callback) throws Exception {
		_searchEntity = entityClass.newInstance();

		_searchButton = new ActionControl();
		_searchButton.setControl(new MfButton("Search"));

		_searchButton.setClient(client);

		FilterCapability filterCapability = new FilterCapability();
		List<QueryContainer> queryContainers = new ArrayList<QueryContainer>();
		QueryContainer qc = new QueryContainer();
		qc.setQueryEntity(_searchEntity);
		qc.setQueryMap(_searchSigns);
		queryContainers.add(qc);
		filterCapability.setQueryContainers(queryContainers);

		_searchButton.addCapability(filterCapability);
		_searchButton.setCallback(callback);

		placeControls();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	private GridPane createGridPane() throws Exception {
		Map<String, ControlAdapter> map = UiHandler.createEntityControls(_searchEntity.getClass());

		GridPane gridPane = new GridPane();
		gridPane.setVgap(5); 
		gridPane.setHgap(5);
		gridPane.setAlignment(Pos.CENTER);

		int i = 0;
		for (Entry<String, ControlAdapter> entry : map.entrySet()) {
			ControlAdapter control = entry.getValue();
			control.setEntity(_searchEntity);
			Field field = control.getField();
			String title = StringUtil.getTitle(control.getColumnName());
			ComboBox<String> signControl = createSignControl(field, control);

			// setting the entity attributes to their default values
			control.clear();

			Label lbl = new Label(title);
			lbl.setAlignment(Pos.BASELINE_CENTER);

			int col = i * 3;

			gridPane.add(lbl, col, 0);
			gridPane.add(signControl, col, 1);
			gridPane.add(entry.getValue().getInstance(), col + 1, 1);

			Separator separator = new Separator(Orientation.VERTICAL);
			separator.setMaxWidth(40);
			separator.setMaxHeight(40);

			gridPane.add(separator, col + 2, 1);
			++i;
		}
		return gridPane;
	}

	/**
	 * @throws Exception
	 */
	private void placeControls() throws Exception {
		setPadding(new Insets(5, 5, 5, 5));

		HBox hbSearch = new HBox();
		hbSearch.getChildren().add(createGridPane());
		hbSearch.setSpacing(3);
		BorderPane.setAlignment(hbSearch, Pos.CENTER_LEFT);
		setLeft(hbSearch);
		setRight(_searchButton.getControl().getInstance());
	}

	/**
	 * @param field
	 * @param control
	 * @return
	 */
	private ComboBox<String> createSignControl(Field field, ControlAdapter control) {
		Class<?> type = field.getType();
		if (Number.class.isAssignableFrom(type)) {
			type = Number.class;
		}
		String[] signs = _signsMap.get(type);
		if (signs == null && control instanceof MfComboBox) {
			signs = new String[] {Comperators.NoFilter, Comperators.E};
		}

		ComboBox<String> signControl = new ComboBox<String>(
				FXCollections.observableArrayList(signs));
		signControl.setOnAction((new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String value = ((ComboBox<String>) event.getSource()).getSelectionModel().getSelectedItem();
				_searchSigns.put(control.getColumnName(), value);
			}
		}));
		signControl.setValue(Comperators.NoFilter);
		return signControl;
	}

	/**
	 * 
	 */
	public void search() {
		FilterCapability fc = _searchButton.getCapability(RequestType.Filter);
		fc.filter();
	}
}
