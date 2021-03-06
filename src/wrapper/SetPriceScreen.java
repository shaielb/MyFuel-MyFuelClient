package wrapper;

import java.util.HashSet;
import java.util.Set;

import action.ActionControl;
import action.UpdateCapability;
import adapter.base.ControlAdapter;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfImageView;
import controls.MfNumberField;
import controls.MfText;
import db.entity.FuelCompanyEnum;
import db.entity.FuelEnum;
import db.interfaces.IEntity;
import handler.UiHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sceneswitch.Context;
import sceneswitch.ISceneSwitcher;
import sceneswitch.SceneBase;

@AutoGenerated
public class SetPriceScreen extends SceneBase {

	private FuelEnum _fuelEnumMotorcycleMaxPricePerType;
	private FuelCompanyEnum _fuelCompanyEnum;
	private FuelEnum _fuelEnumSolerMaxPricePerType;
	private FuelEnum _fuelEnumBenzinMaxPricePerType;

	private MfImageView _mainMenuMarketingScreenControl;
	private MfText _maxPricePerTypeControlMotorcycleMaxPricePerType;
	private MfNumberField _companyDiscountRequestControl;
	private MfText _maxPricePerTypeControlSolerMaxPricePerType;
	private MfText _maxPricePerTypeControlBenzinMaxPricePerType;
	private MfText _companyNameKeyControl;
	private MfImageView _updateFuelCompanyEnumControl;
	private ActionControl _fuelCompanyEnumupdateAction;
	private MfText _companyDiscountControl;

	public SetPriceScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
	}

	@Override
	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("SetPriceScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		_mainMenuMarketingScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMarketingScreen"));
		_mainMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMarketingScreen"); });

		//entities instantiation
		if (_fuelEnumMotorcycleMaxPricePerType == null) {
			_fuelEnumMotorcycleMaxPricePerType = _client.getEnum(FuelEnum.class, "max_price_per_type");
		}
		if (_fuelCompanyEnum == null) {
			_fuelCompanyEnum = _context.getEmployee().getFuelCompanyEnum();
		}
		if (_fuelEnumSolerMaxPricePerType == null) {
			_fuelEnumSolerMaxPricePerType = _client.getEnum(FuelEnum.class, "max_price_per_type");
		}
		if (_fuelEnumBenzinMaxPricePerType == null) {
			_fuelEnumBenzinMaxPricePerType = _client.getEnum(FuelEnum.class, "max_price_per_type");
		}

		//controls instantiation
		_maxPricePerTypeControlMotorcycleMaxPricePerType = new MfText((Text) _scene.lookup("#table$fuel_enum$$$motorcycle$max_price_per_type"));
		_companyDiscountRequestControl = new MfNumberField((TextField) _scene.lookup("#table$fuel_company_enum$company_discount_request"), Double.class);
		_maxPricePerTypeControlSolerMaxPricePerType = new MfText((Text) _scene.lookup("#table$fuel_enum$$$soler$max_price_per_type"));
		_maxPricePerTypeControlBenzinMaxPricePerType = new MfText((Text) _scene.lookup("#table$fuel_enum$$$benzin$max_price_per_type"));
		_companyNameKeyControl = new MfText((Text) _scene.lookup("#table$fuel_company_enum$company_name_key"));
		_companyDiscountControl = new MfText((Text) _scene.lookup("#table$fuel_company_enum$company_discount"));

		//initializations
		_updateFuelCompanyEnumControl = new MfImageView((ImageView) _scene.lookup("#action$update$fuel_company_enum"));
		_updateFuelCompanyEnumControl.
			setMouseImages("@resource/images/SendRequest_btn.png", "@resource/images/SendRequest_overbtn.png", "@resource/images/SendRequest_clickbtn.png");
		_fuelCompanyEnumupdateAction = new ActionControl();
		_fuelCompanyEnumupdateAction.setControl(_updateFuelCompanyEnumControl);
		UpdateCapability fuelCompanyEnumUpdateCapability = new UpdateCapability();
		Set<IEntity> fuelCompanyEnumUpdateEntities = new HashSet<IEntity>();
		fuelCompanyEnumUpdateCapability.setEntities(fuelCompanyEnumUpdateEntities);
		_fuelCompanyEnumupdateAction.addCapability(fuelCompanyEnumUpdateCapability);
		_fuelCompanyEnumupdateAction.setClient(_client);
		_fuelCompanyEnumupdateAction.setPreSend((request) -> {
			try {
				_companyDiscountRequestControl.setValue((100 - _fuelCompanyEnum.getCompanyDiscountRequest()) / 100);
				_fuelCompanyEnum.setRequestHandled(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		});
		_fuelCompanyEnumupdateAction.setCallback((response) -> {
			if (response.isPassed()) {
				UiHandler.showAlert(AlertType.INFORMATION, "Request Submition", "", "Request Submited");
			}
			else {
				UiHandler.showAlert(AlertType.ERROR, "Request Submition", "", response.getDescription());
			}
		});

		//fields initializations
		_maxPricePerTypeControlMotorcycleMaxPricePerType.setField(_fuelEnumMotorcycleMaxPricePerType.getClass().getDeclaredField("_max_price_per_type"));
		_maxPricePerTypeControlMotorcycleMaxPricePerType.setEntity(_fuelEnumMotorcycleMaxPricePerType);

		_companyDiscountRequestControl.setField(_fuelCompanyEnum.getClass().getDeclaredField("_company_discount_request"));
		_companyDiscountRequestControl.setEntity(_fuelCompanyEnum);

		_maxPricePerTypeControlSolerMaxPricePerType.setField(_fuelEnumSolerMaxPricePerType.getClass().getDeclaredField("_max_price_per_type"));
		_maxPricePerTypeControlSolerMaxPricePerType.setEntity(_fuelEnumSolerMaxPricePerType);

		_maxPricePerTypeControlBenzinMaxPricePerType.setField(_fuelEnumBenzinMaxPricePerType.getClass().getDeclaredField("_max_price_per_type"));
		_maxPricePerTypeControlBenzinMaxPricePerType.setEntity(_fuelEnumBenzinMaxPricePerType);

		_companyNameKeyControl.setField(_fuelCompanyEnum.getClass().getDeclaredField("_company_name_key"));
		_companyNameKeyControl.setEntity(_fuelCompanyEnum);

		_companyDiscountControl.setField(_fuelCompanyEnum.getClass().getDeclaredField("_company_discount"));
		_companyDiscountControl.setEntity(_fuelCompanyEnum);

		//grouping
		groupControls(new ControlAdapter[] { _maxPricePerTypeControlMotorcycleMaxPricePerType, _maxPricePerTypeControlSolerMaxPricePerType, _maxPricePerTypeControlBenzinMaxPricePerType });
		
		_companyDiscountRequestControl.addEvent((event) -> {
			Double valueM = _fuelEnumMotorcycleMaxPricePerType.getMaxPricePerType() * 
					((100 - _companyDiscountRequestControl.getValue().doubleValue()) / 100);
			((Text) _scene.lookup("#calculate_motorcycles")).setText(valueM.toString());
			
			Double valueS = _fuelEnumSolerMaxPricePerType.getMaxPricePerType() * 
					((100 - _companyDiscountRequestControl.getValue().doubleValue()) / 100);
			((Text) _scene.lookup("#calculate_soler")).setText(valueS.toString());
			
			Double valueB = _fuelEnumBenzinMaxPricePerType.getMaxPricePerType() * 
					((100 - _companyDiscountRequestControl.getValue().doubleValue()) / 100);
			((Text) _scene.lookup("#calculate_benzin")).setText(valueB.toString());
		});

	}

	@Override
	protected void onLoad() {
		
	}

	@Override
	public void setParameters(IEntity[] entities) {
		super.setParameters(entities);
	}


	public FuelEnum getFuelEnumMotorcycleMaxPricePerType() {
		 return _fuelEnumMotorcycleMaxPricePerType;
	}

	public void setFuelEnumMotorcycleMaxPricePerType(FuelEnum fuelEnumMotorcycleMaxPricePerType) {
		 _fuelEnumMotorcycleMaxPricePerType = fuelEnumMotorcycleMaxPricePerType;
	}

	public FuelCompanyEnum getFuelCompanyEnum() {
		 return _fuelCompanyEnum;
	}

	public void setFuelCompanyEnum(FuelCompanyEnum fuelCompanyEnum) {
		 _fuelCompanyEnum = fuelCompanyEnum;
	}

	public FuelEnum getFuelEnumSolerMaxPricePerType() {
		 return _fuelEnumSolerMaxPricePerType;
	}

	public void setFuelEnumSolerMaxPricePerType(FuelEnum fuelEnumSolerMaxPricePerType) {
		 _fuelEnumSolerMaxPricePerType = fuelEnumSolerMaxPricePerType;
	}

	public FuelEnum getFuelEnumBenzinMaxPricePerType() {
		 return _fuelEnumBenzinMaxPricePerType;
	}

	public void setFuelEnumBenzinMaxPricePerType(FuelEnum fuelEnumBenzinMaxPricePerType) {
		 _fuelEnumBenzinMaxPricePerType = fuelEnumBenzinMaxPricePerType;
	}
}
