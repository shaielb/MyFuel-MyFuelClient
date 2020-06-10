package wrapper;

import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import db.entity.*;
import adapter.base.ControlAdapter;
import java.util.HashSet;
import widgets.table.Table;
import application.Main;
import controls.*;
import annotations.AutoGenerated;
import java.util.HashMap;
import javafx.scene.Parent;
import sceneswitch.SceneBase;
import java.util.Map;
import java.util.Collection;
import table.MfTable;
import widgets.table.*;
import client.IClient;

import javafx.scene.Scene;
import javafx.scene.control.*;
import action.*;
import db.interfaces.IEntity;
import javafx.scene.image.ImageView;
import java.util.Set;
import javafx.scene.text.Text;

@AutoGenerated
public class SetPriceScreen extends SceneBase {

	public SetPriceScreen(ISceneSwitcher sceneSwitcher, IClient client) throws Exception {
		super(sceneSwitcher, client);
		initialize();
	}

	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("SetPriceScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		MfImageView mainMenuMarketingScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMarketingScreen"));
		mainMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMarketingScreen"); });

		//entities instantiation
		FuelCompanyEnum fuelCompanyEnum = new FuelCompanyEnum();
		FuelInCompany fuelInCompanyMotorcycleCompanyPrice = new FuelInCompany();
		FuelInCompany fuelInCompany = new FuelInCompany();
		FuelEnum fuelEnumMotorcycleMaxPricePerType = new FuelEnum();
		FuelInCompany fuelInCompanySolerCompanyPrice = new FuelInCompany();
		FuelEnum fuelEnumSolerMaxPricePerType = new FuelEnum();
		FuelInCompany fuelInCompanyBenzinCompanyPrice = new FuelInCompany();
		FuelEnum fuelEnumBenzinMaxPricePerType = new FuelEnum();

		//entities assignments
		fuelInCompanyMotorcycleCompanyPrice.setFuelCompanyEnum(fuelCompanyEnum);
		fuelInCompany.setFuelCompanyEnum(fuelCompanyEnum);
		fuelInCompany.setFuelEnum(fuelEnumMotorcycleMaxPricePerType);
		fuelInCompanySolerCompanyPrice.setFuelCompanyEnum(fuelCompanyEnum);
		fuelInCompany.setFuelEnum(fuelEnumSolerMaxPricePerType);
		fuelInCompanyBenzinCompanyPrice.setFuelCompanyEnum(fuelCompanyEnum);
		fuelInCompany.setFuelEnum(fuelEnumBenzinMaxPricePerType);

		//controls instantiation
		MfTextField companyPriceControlMotorcycleCompanyPrice = new MfTextField((TextField) _scene.lookup("#table$fuel_company_enum$fuel_in_company$$$motorcycle$company_price"));
		MfComboBox companyNameKeyControl = new MfComboBox((ComboBox) _scene.lookup("#table$fuel_company_enum$company_name_key"));
		MfText maxPricePerTypeControlMotorcycleMaxPricePerType = new MfText((Text) _scene.lookup("#table$fuel_company_enum$fuel_in_company$fuel_enum$$$motorcycle$max_price_per_type"));
		MfTextField companyPriceControlSolerCompanyPrice = new MfTextField((TextField) _scene.lookup("#table$fuel_company_enum$fuel_in_company$$$soler$company_price"));
		MfText maxPricePerTypeControlSolerMaxPricePerType = new MfText((Text) _scene.lookup("#table$fuel_company_enum$fuel_in_company$fuel_enum$$$soler$max_price_per_type"));
		MfTextField companyPriceControlBenzinCompanyPrice = new MfTextField((TextField) _scene.lookup("#table$fuel_company_enum$fuel_in_company$$$benzin$company_price"));
		MfText maxPricePerTypeControlBenzinMaxPricePerType = new MfText((Text) _scene.lookup("#table$fuel_company_enum$fuel_in_company$fuel_enum$$$benzin$max_price_per_type"));

		//initializations
		MfImageView updateFuelInCompanyControl = new MfImageView((ImageView) _scene.lookup("#action$update$fuel_in_company"));
		ActionControl fuelInCompanyupdateAction = new ActionControl();
		fuelInCompanyupdateAction.setControl(updateFuelInCompanyControl);
		UpdateCapability fuelInCompanyUpdateCapability = new UpdateCapability();
		Set<IEntity> fuelInCompanyUpdateEntities = new HashSet<IEntity>();
		fuelInCompanyUpdateCapability.setEntities(fuelInCompanyUpdateEntities);
		fuelInCompanyupdateAction.addCapability(fuelInCompanyUpdateCapability);
		fuelInCompanyupdateAction.setClient(_client);
		fuelInCompanyupdateAction.setCallback((response) -> {
			
		});

		//fields initializations
		companyPriceControlMotorcycleCompanyPrice.setField(fuelInCompanyMotorcycleCompanyPrice.getClass().getDeclaredField("_company_price"));
		companyPriceControlMotorcycleCompanyPrice.setEntity(fuelInCompanyMotorcycleCompanyPrice);

		companyNameKeyControl.setField(fuelCompanyEnum.getClass().getDeclaredField("_company_name_key"));
		companyNameKeyControl.setEntity(fuelCompanyEnum);

		maxPricePerTypeControlMotorcycleMaxPricePerType.setField(fuelEnumMotorcycleMaxPricePerType.getClass().getDeclaredField("_max_price_per_type"));
		maxPricePerTypeControlMotorcycleMaxPricePerType.setEntity(fuelEnumMotorcycleMaxPricePerType);

		companyPriceControlSolerCompanyPrice.setField(fuelInCompanySolerCompanyPrice.getClass().getDeclaredField("_company_price"));
		companyPriceControlSolerCompanyPrice.setEntity(fuelInCompanySolerCompanyPrice);

		maxPricePerTypeControlSolerMaxPricePerType.setField(fuelEnumSolerMaxPricePerType.getClass().getDeclaredField("_max_price_per_type"));
		maxPricePerTypeControlSolerMaxPricePerType.setEntity(fuelEnumSolerMaxPricePerType);

		companyPriceControlBenzinCompanyPrice.setField(fuelInCompanyBenzinCompanyPrice.getClass().getDeclaredField("_company_price"));
		companyPriceControlBenzinCompanyPrice.setEntity(fuelInCompanyBenzinCompanyPrice);

		maxPricePerTypeControlBenzinMaxPricePerType.setField(fuelEnumBenzinMaxPricePerType.getClass().getDeclaredField("_max_price_per_type"));
		maxPricePerTypeControlBenzinMaxPricePerType.setEntity(fuelEnumBenzinMaxPricePerType);

		//grouping
		groupControls(new ControlAdapter[] { maxPricePerTypeControlMotorcycleMaxPricePerType, maxPricePerTypeControlSolerMaxPricePerType, maxPricePerTypeControlBenzinMaxPricePerType });
		groupControls(new ControlAdapter[] { companyPriceControlMotorcycleCompanyPrice, companyPriceControlSolerCompanyPrice, companyPriceControlBenzinCompanyPrice });

	}
	private Map<IEntity, Map<String, String>> prepareQuery(FuelingPurchase fuelingPurchase) {
		Map<IEntity, Map<String, String>> map = new HashMap<IEntity, Map<String, String>>();
		
		Map<String, String> queryMap = new HashMap<String, String>();
		
		map.put(fuelingPurchase, queryMap);
		return map;
	}
}
