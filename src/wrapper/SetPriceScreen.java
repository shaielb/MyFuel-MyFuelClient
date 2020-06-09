package wrapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import action.ActionControl;
import action.UpdateCapability;
import adapter.base.ControlAdapter;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfComboBox;
import controls.MfImageView;
import controls.MfText;
import controls.MfTextField;
import db.entity.FuelCompanyEnum;
import db.entity.FuelEnum;
import db.entity.FuelInCompany;
import db.entity.FuelingPurchase;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sceneswitch.SceneBase;

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
		FuelInCompany fuelInCompany = new FuelInCompany();
		FuelEnum fuelEnum = new FuelEnum();

		//entities assignments
		fuelInCompany.setFuelCompanyEnum(fuelCompanyEnum);
		fuelInCompany.setFuelEnum(fuelEnum);

		//controls instantiation
		MfTextField companyPriceControlMotorcycleCompany_price = new MfTextField((TextField) _scene.lookup("#table$fuel_company_enum$fuel_in_company$$$motorcycle$company_price"));
		MfComboBox companyNameKeyControl = new MfComboBox((ComboBox) _scene.lookup("#table$fuel_company_enum$company_name_key"));
		MfText maxPricePerTypeControlMotorcycleMax_price_per_type = new MfText((Text) _scene.lookup("#table$fuel_company_enum$fuel_in_company$fuel_enum$$$motorcycle$max_price_per_type"));
		MfTextField companyPriceControlSolerCompany_price = new MfTextField((TextField) _scene.lookup("#table$fuel_company_enum$fuel_in_company$$$soler$company_price"));
		MfText maxPricePerTypeControlSolerMax_price_per_type = new MfText((Text) _scene.lookup("#table$fuel_company_enum$fuel_in_company$fuel_enum$$$soler$max_price_per_type"));
		MfTextField companyPriceControlBenzinCompany_price = new MfTextField((TextField) _scene.lookup("#table$fuel_company_enum$fuel_in_company$$$benzin$company_price"));
		MfText maxPricePerTypeControlBenzinMax_price_per_type = new MfText((Text) _scene.lookup("#table$fuel_company_enum$fuel_in_company$fuel_enum$$$benzin$max_price_per_type"));

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
		companyPriceControlMotorcycleCompany_price.setField(fuelInCompany.getClass().getDeclaredField("_company_price"));
		companyPriceControlMotorcycleCompany_price.setEntity(fuelInCompany);

		companyNameKeyControl.setField(fuelCompanyEnum.getClass().getDeclaredField("_company_name_key"));
		companyNameKeyControl.setEntity(fuelCompanyEnum);

		maxPricePerTypeControlMotorcycleMax_price_per_type.setField(fuelEnum.getClass().getDeclaredField("_max_price_per_type"));
		maxPricePerTypeControlMotorcycleMax_price_per_type.setEntity(fuelEnum);

		companyPriceControlSolerCompany_price.setField(fuelInCompany.getClass().getDeclaredField("_company_price"));
		companyPriceControlSolerCompany_price.setEntity(fuelInCompany);

		maxPricePerTypeControlSolerMax_price_per_type.setField(fuelEnum.getClass().getDeclaredField("_max_price_per_type"));
		maxPricePerTypeControlSolerMax_price_per_type.setEntity(fuelEnum);

		companyPriceControlBenzinCompany_price.setField(fuelInCompany.getClass().getDeclaredField("_company_price"));
		companyPriceControlBenzinCompany_price.setEntity(fuelInCompany);

		maxPricePerTypeControlBenzinMax_price_per_type.setField(fuelEnum.getClass().getDeclaredField("_max_price_per_type"));
		maxPricePerTypeControlBenzinMax_price_per_type.setEntity(fuelEnum);

		//grouping
		groupControls(new ControlAdapter[] { maxPricePerTypeControlMotorcycleMax_price_per_type, maxPricePerTypeControlSolerMax_price_per_type, maxPricePerTypeControlBenzinMax_price_per_type });
		groupControls(new ControlAdapter[] { companyPriceControlMotorcycleCompany_price, companyPriceControlSolerCompany_price, companyPriceControlBenzinCompany_price });

	}
	private Map<IEntity, Map<String, String>> prepareQuery(FuelingPurchase fuelingPurchase) {
		Map<IEntity, Map<String, String>> map = new HashMap<IEntity, Map<String, String>>();
		
		Map<String, String> queryMap = new HashMap<String, String>();
		
		map.put(fuelingPurchase, queryMap);
		return map;
	}
}