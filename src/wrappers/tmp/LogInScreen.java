package wrappers.tmp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import action.ActionControl;
import action.FilterCapability;
import application.Main;
import client.IClient;
import controls.MfImageView;
import controls.MfPasswordField;
import controls.MfTextField;
import db.entity.SystemUser;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sceneswitch.SceneBase;

@SuppressWarnings("serial")
public class LogInScreen extends SceneBase {

	private Map<String, String> _mainMenuMapping = new HashMap<String, String>() {{
		put("Station Manager", "MainMenuStationManagerScreen");
		put("Customer", "MainMenuCustomerScreen");
		put("Marketing manager", "MainMenuMarketingScreen");
		put("Marketing representative", "MainMenuMarketingScreen");
	}};

	public LogInScreen(ISceneSwitcher sceneSwitcher, IClient client) throws Exception {
		super(sceneSwitcher, client);
		initialize();
	}

	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("LogInScreen.fxml"));
		_scene = new Scene(root);

		//entities instantiation
		SystemUser systemUser = new SystemUser();

		//controls instantiation
		MfTextField userNameControl = new MfTextField((TextField) _scene.lookup("#table$system_user$user_name"));
		MfPasswordField userPasswordControl = new MfPasswordField((PasswordField) _scene.lookup("#table$system_user$user_password"));

		//initializations
		MfImageView filterSystemUserControl = new MfImageView((ImageView) _scene.lookup("#action$collect$system_user"));
		ActionControl systemUserfilterAction = new ActionControl();
		systemUserfilterAction.setControl(filterSystemUserControl);
		FilterCapability systemUserFilterCapability = new FilterCapability();
		systemUserFilterCapability.setQueryEntities(prepareQuery(systemUser));
		systemUserfilterAction.addCapability(systemUserFilterCapability);
		systemUserfilterAction.setClient(_client);
		systemUserfilterAction.setCallback((response) -> {
			Collection<IEntity> entities = response.getEntities();
			for (IEntity ientity : entities) {
				SystemUser entity = (SystemUser) ientity;
				_switcher.switchScene(_mainMenuMapping.get(entity.getPermission()));
				break;
			}
		});

		//fields initializations
		userNameControl.setField(systemUser.getClass().getDeclaredField("_user_name"));
		userNameControl.setEntity(systemUser);

		userPasswordControl.setField(systemUser.getClass().getDeclaredField("_user_password"));
		userPasswordControl.setEntity(systemUser);


	}

	private Map<IEntity, Map<String, String>> prepareQuery(SystemUser systemUser) {
		Map<IEntity, Map<String, String>> map = new HashMap<IEntity, Map<String, String>>();

		Map<String, String> queryMap = new HashMap<String, String>();

		map.put(systemUser, queryMap);
		return map;
	}
}
