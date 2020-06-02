package panels;

import java.util.Map;
import java.util.Map.Entry;

import action.ActionControl;
import action.InsertCapability;
import adapter.base.ControlAdapter;
import controls.MfButton;
import db.interfaces.IEntity;
import handler.ControlsHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import messages.response.IResponseCallBack;
import utilities.StringUtil;

@SuppressWarnings("rawtypes")
public class InsertPanel<TEntity extends IEntity> extends BorderPane{

	protected ActionControl _insertButton;

	//protected MfButton _insertButton;

	protected TEntity _insertEntity;

	public InsertPanel(Class<TEntity> entityClass, IResponseCallBack callback) throws Exception {
		initialize(entityClass, callback);
	}

	public void initialize(Class<TEntity> entityClass, IResponseCallBack callback) throws Exception {
		_insertEntity = entityClass.newInstance();

		_insertButton = new ActionControl();
		_insertButton.setControl(new MfButton("Submit"));

		InsertCapability insertCapability = new InsertCapability();
		insertCapability.addEntity(_insertEntity);

		_insertButton.addCapability(new InsertCapability());
		_insertButton.setCallback(callback);

		placeControls();
	}

	private GridPane createGridPane() throws Exception {
		Map<String, ControlAdapter> map = ControlsHandler.createEntityControls(_insertEntity.getClass());

		GridPane gridPane = new GridPane();
		gridPane.setVgap(5); 
		gridPane.setHgap(5);
		gridPane.setAlignment(Pos.CENTER);

		int i = 0;
		for (Entry<String, ControlAdapter> entry : map.entrySet()) {
			// setting the entity attributes to their default values
			entry.getValue().clear();
			String name = StringUtil.swithToUpperCase(entry.getKey(), "_");
			String title = String.join(" ", StringUtil.separateByUpperCase(name));
			Label lbl = new Label(title);
			lbl.setAlignment(Pos.BASELINE_CENTER);

			int col = i * 2;

			gridPane.add(lbl, col, 0);
			gridPane.add(entry.getValue().getInstance(), col, 1);

			Separator separator = new Separator(Orientation.VERTICAL);
			separator.setMaxWidth(40);
			separator.setMaxHeight(40);

			gridPane.add(separator, col + 1, 1);
			++i;
		}
		return gridPane;
	}

	private void placeControls() throws Exception {
		setPadding(new Insets(5, 5, 5, 5));

		HBox hbInsert = new HBox();
		hbInsert.getChildren().add(createGridPane());
		hbInsert.getChildren().add(_insertButton.getControl().getInstance());
		hbInsert.setSpacing(3);
		BorderPane.setAlignment(hbInsert, Pos.CENTER_LEFT);
		BorderPane.setAlignment(_insertButton.getControl().getInstance(), Pos.CENTER_RIGHT);
		setLeft(hbInsert);
	}
}
