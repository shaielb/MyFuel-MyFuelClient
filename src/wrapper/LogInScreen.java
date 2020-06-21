package wrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.ActionControl;
import action.LoginCapability;
import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfImageView;
import controls.MfPasswordField;
import controls.MfTextField;
import db.entity.Customer;
import db.entity.Employee;
import db.entity.Person;
import db.entity.SystemUser;
import db.interfaces.IEntity;
import handler.UiHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import messages.QueryContainer;
import sceneswitch.Context;
import sceneswitch.ISceneSwitcher;
import sceneswitch.SceneBase;

@AutoGenerated
public class LogInScreen extends SceneBase {

	private SystemUser _systemUser;

	private MfTextField _userNameControl;
	private MfPasswordField _userPasswordControl;
	private MfImageView _loginSystemUserControl;
	private ActionControl _systemUserloginAction;

	// preserved members start
	private Map<String, String> _screensMap = new HashMap<String, String>() {{
		put("Station Manager", "MainMenuStationManagerScreen");
		put("Customer", "MainMenuCustomerScreen");
		put("MyFuel Manager", "MainMenuMarketingScreen");
		put("Company Marketing Representative", "MainMenuMarketingScreen");
		put("MyFuel Marketing Representative", "MainMenuMarketingScreen");
		put("Company Marketing Manager", "MainMenuMarketingScreen");
		put("Company Manager", "MainMenuCompanyManagerScreen");
		put("Supplier", "MainMenuSupplierScreen");

	}};
	// preserved members end

	public LogInScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
	}

	@Override
	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("LogInScreen.fxml"));
		_scene = new Scene(root);

		//entities instantiation
		if (_systemUser == null) {
			_systemUser = new SystemUser();
		}

		//controls instantiation
		_userNameControl = new MfTextField((TextField) _scene.lookup("#table$system_user$user_name"));
		_userPasswordControl = new MfPasswordField((PasswordField) _scene.lookup("#table$system_user$user_password"));

		//initializations
		_loginSystemUserControl = new MfImageView((ImageView) _scene.lookup("#action$login$system_user"));
		_loginSystemUserControl.
		setMouseImages("@resource/images/Login_btn.png", "@resource/images/Login_overbtn.png", "@resource/images/Login_clickbtn.png");
		_systemUserloginAction = new ActionControl();
		_systemUserloginAction.setControl(_loginSystemUserControl);
		LoginCapability systemUserLoginCapability = new LoginCapability();
		systemUserLoginCapability.setQueryContainers(prepareQuery(_systemUser));
		_systemUserloginAction.addCapability(systemUserLoginCapability);
		_systemUserloginAction.setClient(_client);
		_systemUserloginAction.setPreSend((request) -> {

			return true;
		});
		_systemUserloginAction.setCallback((response) -> {
			// #action$collect$system_user callback start
			if (!response.isPassed()) {
				UiHandler.showAlert(AlertType.ERROR, "Login Error", "", response.getDescription());
			}
			else {
				Collection<IEntity> entities = response.getEntities();
				SystemUser sysUser = null;
				if (entities == null || entities.size() == 0) {
					UiHandler.showAlert(AlertType.ERROR, "Login Error", "", "User Was Not Found");
				}
				else {
					for (IEntity ientity : entities) {
						if (ientity instanceof SystemUser) {
							_context.setSystemUser(sysUser = (SystemUser) ientity);
						}
						else if (ientity instanceof Customer) {
							_context.setCustomer((Customer) ientity);
						}
						else if (ientity instanceof Employee) {
							_context.setEmployee((Employee) ientity);
						}
						else if (ientity instanceof Person) {
							_context.setPerson((Person) ientity);
						}
					}
					String permissions = sysUser.getPermission();
					UiHandler.RunUi(() -> {
						_switcher.switchScene(_screensMap.get(permissions));
					});
				}
			}
			// #action$collect$system_user callback end
		});

		//fields initializations
		_userNameControl.setField(_systemUser.getClass().getDeclaredField("_user_name"));
		_userNameControl.setEntity(_systemUser);

		_userPasswordControl.setField(_systemUser.getClass().getDeclaredField("_user_password"));
		_userPasswordControl.setEntity(_systemUser);


	}

	@Override
	protected void onLoad() {

	}

	@Override
	public void setParameters(IEntity[] entities) {
		super.setParameters(entities);
	}

	// #action$collect$system_user prepareQuery start
	private List<QueryContainer> prepareQuery(SystemUser systemUser) {
		List<QueryContainer> containers = new ArrayList<QueryContainer>();

		Map<String, String> userQueryMap = new HashMap<String, String>();
		userQueryMap.put("user_name", "=");
		userQueryMap.put("user_password", "=");
		QueryContainer userContainer = new QueryContainer();
		userContainer.setQueryEntity(systemUser);
		userContainer.setQueryMap(userQueryMap);

		containers.add(userContainer);
		return containers;
	}
	// #action$collect$system_user prepareQuery end

	public SystemUser getSystemUser() {
		return _systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		_systemUser = systemUser;
	}
}
