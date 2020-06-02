package wrapper;

import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import adapter.base.ControlAdapter;
import java.util.HashSet;
import widgets.table.Table;
import controls.*;
import annotations.AutoGenerated;
import java.util.HashMap;
import javafx.scene.Parent;
import sceneswitch.SceneBase;
import table.MfTable;
import widgets.table.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import action.*;
import db.interfaces.IEntity;
import javafx.scene.image.ImageView;
import java.util.Set;
import javafx.scene.text.Text;

@AutoGenerated
public class CustomerCharacterizationReportScreen extends SceneBase {

	public CustomerCharacterizationReportScreen(ISceneSwitcher sceneSwitcher) {
		super(sceneSwitcher);
	}

	public void initialize() {
		Parent root = FXMLLoader.load(getClass().getResource("CustomerCharacterizationReportScreen.fxml"));
		_scene = new Scene(root);

		//entities instantiation
		Customer customer = new Customer();
		Car car = new Car();
		FuelingPurchase fuelingPurchase = new FuelingPurchase();

		//controls instantiation
		MfDatePicker dateTimeControl = new MfDatePicker((DatePicker) _scene.lookup("#table$customer$car$fueling_purchase$date_time"));

		//initializations
		MfHyperlink mainMenuMarketingScreenControl = new MfHyperlink((Hyperlink) _scene.lookup("#scene$MainMenuMarketingScreen"));
		mainMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMarketingScreen"); });
		MfImageView mainMenuMarketingScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMarketingScreen"));
		mainMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMarketingScreen"); });
		MfHyperlink customersManagementScreenControl = new MfHyperlink((Hyperlink) _scene.lookup("#scene$CustomersManagementScreen"));
		customersManagementScreenControl.addEvent((event) -> { _switcher.switchScene("CustomersManagementScreen"); });
		MfImageView customersManagementScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$CustomersManagementScreen"));
		customersManagementScreenControl.addEvent((event) -> { _switcher.switchScene("CustomersManagementScreen"); });
		customer.setCar(car);
		car.setFuelingPurchase(fuelingPurchase);
		
		BorderPane fuelingPurchaseBp = (BorderPane) _scene.lookup("#uitable$noneditable$single$fueling_purchase");
		Table<FuelingPurchase> fuelingPurchaseTableWrapper = new Table<FuelingPurchase>();
		MfTable<FuelingPurchase> fuelingPurchaseTable = new MfTable<FuelingPurchase>(FuelingPurchase.class);
		fuelingPurchaseTable.setEditable(false);
		MfSingleCapability<FuelingPurchase> fuelingPurchaseSingleCapability = new MfSingleCapability<FuelingPurchase>();
		fuelingPurchaseTableWrapper.addCapability(fuelingPurchaseSingleCapability);
		fuelingPurchaseTableWrapper.setTable(fuelingPurchaseTable);
		fuelingPurchaseBp.setCenter(fuelingPurchaseTable.getInstance());
		
		MfImageView fuelingPurchaseControl = new MfImageView((ImageView) _scene.lookup("#action$collect$fueling_purchase"));
		ActionControl collectAction = new ActionControl();
		collectAction.setControl(fuelingPurchaseControl);
		CollectCapability fuelingPurchaseCollectCapability = new CollectCapability();
		collectAction.addCapability(fuelingPurchaseCollectCapability);
		collectAction.setCallback((response) -> {});

		//fields initializations
		dateTimeControl.setField(fuelingPurchase.getClass().getField("_date_time"));
		dateTimeControl.setEntity(fuelingPurchase);

		dateTimeControl.setField(fuelingPurchase.getClass().getField("_date_time"));
		dateTimeControl.setEntity(fuelingPurchase);

		//grouping
		groupControls(new ControlAdapter[] { dateTimeControl, dateTimeControl });
	}

}
