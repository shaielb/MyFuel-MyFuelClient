package wrappers.tmp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import action.ActionControl;
import action.FilterCapability;
import adapter.base.ControlAdapter;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfDatePicker;
import controls.MfImageView;
import controls.MfText;
import db.entity.FuelingPurchase;
import db.entity.SpecialSalesEnum;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import sceneswitch.SceneBase;
import table.MfTable;
import widgets.table.MfSingleDecorator;
import widgets.table.Table;

@AutoGenerated
public class SaleReportScreen extends SceneBase {

	public SaleReportScreen(ISceneSwitcher sceneSwitcher, IClient client) throws Exception {
		super(sceneSwitcher, client);
		initialize();
	}

	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("SaleReportScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		MfImageView maimMenuMarketingScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MaimMenuMarketingScreen"));
		maimMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MaimMenuMarketingScreen"); });

		MfImageView salesManagmentScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$SalesManagmentScreen"));
		salesManagmentScreenControl.addEvent((event) -> { _switcher.switchScene("SalesManagmentScreen"); });

		//entities instantiation
		SpecialSalesEnum specialSalesEnum = new SpecialSalesEnum();
		FuelingPurchase fuelingPurchase = new FuelingPurchase();

		//entities assignments
		fuelingPurchase.setSpecialSalesEnum(specialSalesEnum);

		//controls instantiation
		MfDatePicker dateTimeControlDt = new MfDatePicker((DatePicker) _scene.lookup("#table$special_sales_enum$fueling_purchase$date_time$$$dt"));
		MfText specialSaleKeyControl = new MfText((Text) _scene.lookup("#table$special_sales_enum$special_sale_key"));

		//tables instantiation
		BorderPane fuelingPurchaseBp = (BorderPane) _scene.lookup("#uitable$noneditable$single$fueling_purchase");
		Table<FuelingPurchase> fuelingPurchaseTableWrapper = new Table<FuelingPurchase>();
		MfTable<FuelingPurchase> fuelingPurchaseTable = new MfTable<FuelingPurchase>(FuelingPurchase.class);
		fuelingPurchaseTable.setEditable(false);
		MfSingleDecorator<FuelingPurchase> fuelingPurchaseSingleDecorator = new MfSingleDecorator<FuelingPurchase>();
		fuelingPurchaseTableWrapper.addDecorator(fuelingPurchaseSingleDecorator);
		fuelingPurchaseTableWrapper.setTable(fuelingPurchaseTable);
		fuelingPurchaseBp.setCenter(fuelingPurchaseTable);

		//initializations
		MfImageView filterFuelingPurchaseControl = new MfImageView((ImageView) _scene.lookup("#action$collect$fueling_purchase"));
		ActionControl fuelingPurchasefilterAction = new ActionControl();
		fuelingPurchasefilterAction.setControl(filterFuelingPurchaseControl);
		FilterCapability fuelingPurchaseFilterCapability = new FilterCapability();
		fuelingPurchaseFilterCapability.setQueryEntities(prepareQuery(fuelingPurchase));
		fuelingPurchasefilterAction.addCapability(fuelingPurchaseFilterCapability);
		fuelingPurchasefilterAction.setClient(_client);
		fuelingPurchasefilterAction.setCallback((response) -> {
			Collection<IEntity> entities = response.getEntities();
			for (IEntity ientity : entities) {
				FuelingPurchase entity = (FuelingPurchase) ientity;
			}
		});

		//fields initializations
		dateTimeControlDt.setField(fuelingPurchase.getClass().getDeclaredField("_date_time"));
		dateTimeControlDt.setEntity(fuelingPurchase);

		dateTimeControlDt.setField(fuelingPurchase.getClass().getDeclaredField("_date_time"));
		dateTimeControlDt.setEntity(fuelingPurchase);

		specialSaleKeyControl.setField(specialSalesEnum.getClass().getDeclaredField("_special_sale_key"));
		specialSaleKeyControl.setEntity(specialSalesEnum);

		//grouping
		groupControls(new ControlAdapter[] { dateTimeControlDt, dateTimeControlDt });

	}
	private Map<IEntity, Map<String, String>> prepareQuery(FuelingPurchase fuelingPurchase) {
		Map<IEntity, Map<String, String>> map = new HashMap<IEntity, Map<String, String>>();
		
		Map<String, String> queryMap = new HashMap<String, String>();
		
		map.put(fuelingPurchase, queryMap);
		return map;
	}
}
