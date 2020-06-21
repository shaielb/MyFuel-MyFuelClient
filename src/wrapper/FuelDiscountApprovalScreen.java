package wrapper;

import java.util.HashSet;
import java.util.Set;

import action.ActionControl;
import action.UpdateCapability;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfImageView;
import controls.MfText;
import db.entity.Employee;
import db.entity.FuelCompanyEnum;
import db.interfaces.IEntity;
import handler.UiHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sceneswitch.Context;
import sceneswitch.ISceneSwitcher;
import sceneswitch.SceneBase;

@AutoGenerated
public class FuelDiscountApprovalScreen extends SceneBase {

	private FuelCompanyEnum _fuelCompanyEnum;

	private MfImageView _mainMenuCompanyManagerScreenControl;
	private MfText _companyDiscountControl;
	private MfText _companyDiscountRequestControl;
	private MfImageView _updateFuelCompanyEnumControl;
	private ActionControl _fuelCompanyEnumupdateAction;
	private MfImageView _updateFuelCompanyEnumControlBtn;
	private ActionControl _fuelCompanyEnumupdateActionBtn;
	
	private Set<IEntity> _fuelCompanyEnumUpdateEntitiesBtn;
	private Set<IEntity> _fuelCompanyEnumUpdateEntities;

	public FuelDiscountApprovalScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
	}

	@Override
	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("FuelDiscountApprovalScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		_mainMenuCompanyManagerScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuCompanyManagerScreen"));
		_mainMenuCompanyManagerScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuCompanyManagerScreen"); });

		//entities instantiation
		if (_fuelCompanyEnum == null) {
			_fuelCompanyEnum = _client.getEnum(FuelCompanyEnum.class, _context.getEmployee().getFuelCompanyEnum().getId());
		}

		//controls instantiation
		_companyDiscountControl = new MfText((Text) _scene.lookup("#table$fuel_company_enum$company_discount"));
		_companyDiscountRequestControl = new MfText((Text) _scene.lookup("#table$fuel_company_enum$company_discount_request"));

		//initializations
		_updateFuelCompanyEnumControl = new MfImageView((ImageView) _scene.lookup("#action$update$fuel_company_enum"));
		_updateFuelCompanyEnumControl.
		setMouseImages("@resource/images/Decline_btn.jpg", "@resource/images/Decline_overbtn.png", "@resource/images/Decline_clickbtn.png");
		_fuelCompanyEnumupdateAction = new ActionControl();
		_fuelCompanyEnumupdateAction.setControl(_updateFuelCompanyEnumControl);
		UpdateCapability fuelCompanyEnumUpdateCapability = new UpdateCapability();
		_fuelCompanyEnumUpdateEntities = new HashSet<IEntity>();
		_fuelCompanyEnumUpdateEntities.add(_fuelCompanyEnum);
		fuelCompanyEnumUpdateCapability.setEntities(_fuelCompanyEnumUpdateEntities);
		_fuelCompanyEnumupdateAction.addCapability(fuelCompanyEnumUpdateCapability);
		_fuelCompanyEnumupdateAction.setClient(_client);
		_fuelCompanyEnumupdateAction.setPreSend((request) -> {
			_fuelCompanyEnum.setRequestHandled(true);
			_fuelCompanyEnum.setCompanyDiscountRequest(null);
			return true;
		});
		_fuelCompanyEnumupdateAction.setCallback((response) -> {
			if (response.isPassed()) {
				UiHandler.showAlert(AlertType.INFORMATION, "Fuel Company Update Request", "", "Request Was Declined");
				try {
					discountToPresentage(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				UiHandler.showAlert(AlertType.ERROR, "Fuel Company Update Request", "", response.getDescription());
			}
		});

		_updateFuelCompanyEnumControlBtn = new MfImageView((ImageView) _scene.lookup("#action$update$fuel_company_enum$$$btn"));
		_updateFuelCompanyEnumControlBtn.
		setMouseImages("@resource/images/Confirm_btn.jpg", "@resource/images/Confirm_overbtn.png", "@resource/images/Confirm_clickbtn.png");
		_fuelCompanyEnumupdateActionBtn = new ActionControl();
		_fuelCompanyEnumupdateActionBtn.setControl(_updateFuelCompanyEnumControlBtn);
		UpdateCapability fuelCompanyEnumUpdateCapabilityBtn = new UpdateCapability();
		_fuelCompanyEnumUpdateEntitiesBtn = new HashSet<IEntity>();
		_fuelCompanyEnumUpdateEntitiesBtn.add(_fuelCompanyEnum);
		fuelCompanyEnumUpdateCapabilityBtn.setEntities(_fuelCompanyEnumUpdateEntitiesBtn);
		_fuelCompanyEnumupdateActionBtn.addCapability(fuelCompanyEnumUpdateCapabilityBtn);
		_fuelCompanyEnumupdateActionBtn.setClient(_client);
		_fuelCompanyEnumupdateActionBtn.setPreSend((request) -> {
			_fuelCompanyEnum.setRequestHandled(true);
			_fuelCompanyEnum.setCompanyDiscount(_fuelCompanyEnum.getCompanyDiscountRequest());
			_fuelCompanyEnum.setCompanyDiscountRequest(null);
			return true;
		});
		_fuelCompanyEnumupdateActionBtn.setCallback((response) -> {
			if (response.isPassed()) {
				UiHandler.showAlert(AlertType.INFORMATION, "Fuel Company Update Request", "", "Request Was Confirmed");
				try {
					discountToPresentage(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				UiHandler.showAlert(AlertType.ERROR, "Fuel Company Update Request", "", response.getDescription());
			}
		});

		//fields initializations
		_companyDiscountControl.setField(_fuelCompanyEnum.getClass().getDeclaredField("_company_discount"));
		_companyDiscountControl.setEntity(_fuelCompanyEnum);

		_companyDiscountRequestControl.setField(_fuelCompanyEnum.getClass().getDeclaredField("_company_discount_request"));
		_companyDiscountRequestControl.setEntity(_fuelCompanyEnum);
	}

	@Override
	protected void onLoad() {
		discountToPresentage(null);
	}
	
	private void discountToPresentage(Boolean declined) {
		Double discount = (1 - _fuelCompanyEnum.getCompanyDiscount()) * 100;
		String val = discount.toString();
		((Text) _companyDiscountControl.getInstance()).setText(String.format(
				"%s.%s", val.substring(0, val.indexOf(".")), val.substring(val.indexOf(".") + 1, val.indexOf(".") + 3)));
		
		String declinedStr = "";
		if (declined != null) {
			declinedStr = (declined) ? "(X)" : "(V)";
		}
		if (_fuelCompanyEnum.getCompanyDiscountRequest() != null) {
			Double discount1 = (1 - _fuelCompanyEnum.getCompanyDiscountRequest()) * 100;
			String val1 = discount1.toString();
			((Text) _companyDiscountRequestControl.getInstance()).setText(String.format(
					"%s.%s %s", val1.substring(0, val1.indexOf(".")), 
					val1.substring(val1.indexOf(".") + 1, val1.indexOf(".") + 3), declinedStr));
		}
		else {
			((Text) _companyDiscountRequestControl.getInstance()).setText(declinedStr);
		}
	}

	@Override
	public void setParameters(IEntity[] entities) {
		super.setParameters(entities);
		if (_context.getEmployee() == null) {
			Employee employee = new Employee();
			FuelCompanyEnum fuelCompany = new FuelCompanyEnum();
			employee.setFuelCompanyEnum(fuelCompany);
			fuelCompany.setId(2);
			_context.setEmployee(employee);
		}
	}


	public FuelCompanyEnum getFuelCompanyEnum() {
		return _fuelCompanyEnum;
	}

	public void setFuelCompanyEnum(FuelCompanyEnum fuelCompanyEnum) {
		_fuelCompanyEnum = fuelCompanyEnum;
	}
}
