package wrapper;

import java.util.HashSet;
import java.util.Set;

import action.ActionControl;
import action.UpdateCapability;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfImageView;
import db.entity.SpecialSalesEnum;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import sceneswitch.Context;
import sceneswitch.SceneBase;
import table.MfTable;
import widgets.table.MfAddremoveDecorator;
import widgets.table.Table;

@AutoGenerated
public class EditSalesScreen extends SceneBase {

	private SpecialSalesEnum _specialSalesEnum;

	private MfImageView _mainMenuMarketingScreenControl;
	private Table<SpecialSalesEnum> _specialSalesEnumTableWrapper;
	private MfTable<SpecialSalesEnum> _specialSalesEnumTable;
	private MfImageView _salesManagementScreenControl;
	private MfImageView _updateSpecialSalesEnumControl;
	private ActionControl _specialSalesEnumupdateAction;

	public EditSalesScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
		initialize();
	}

	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("EditSalesScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		_mainMenuMarketingScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMarketingScreen"));
		_mainMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMarketingScreen"); });

		_salesManagementScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$SalesManagementScreen"));
		_salesManagementScreenControl.addEvent((event) -> { _switcher.switchScene("SalesManagementScreen"); });

		//entities instantiation
		_specialSalesEnum = new SpecialSalesEnum();

		//tables instantiation
		BorderPane specialSalesEnumBp = (BorderPane) _scene.lookup("#uitable$editable$addremove$special_sales_enum");
		_specialSalesEnumTableWrapper = new Table<SpecialSalesEnum>();
		_specialSalesEnumTable = new MfTable<SpecialSalesEnum>(SpecialSalesEnum.class);
		_specialSalesEnumTable.setEditable(true);
		MfAddremoveDecorator<SpecialSalesEnum> specialSalesEnumAddremoveDecorator = new MfAddremoveDecorator<SpecialSalesEnum>();
		_specialSalesEnumTableWrapper.addDecorator(specialSalesEnumAddremoveDecorator);
		_specialSalesEnumTableWrapper.setTable(_specialSalesEnumTable);
		specialSalesEnumBp.setCenter(_specialSalesEnumTable);

		//initializations
		_updateSpecialSalesEnumControl = new MfImageView((ImageView) _scene.lookup("#action$update$special_sales_enum"));
		_specialSalesEnumupdateAction = new ActionControl();
		_specialSalesEnumupdateAction.setControl(_updateSpecialSalesEnumControl);
		UpdateCapability specialSalesEnumUpdateCapability = new UpdateCapability();
		Set<IEntity> specialSalesEnumUpdateEntities = new HashSet<IEntity>();
		specialSalesEnumUpdateCapability.setEntities(specialSalesEnumUpdateEntities);
		_specialSalesEnumupdateAction.addCapability(specialSalesEnumUpdateCapability);
		_specialSalesEnumupdateAction.setClient(_client);
		_specialSalesEnumupdateAction.setCallback((response) -> {
			
		});


	}



}
