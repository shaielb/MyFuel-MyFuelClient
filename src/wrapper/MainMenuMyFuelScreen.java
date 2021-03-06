package wrapper;

import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfImageView;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import sceneswitch.Context;
import sceneswitch.ISceneSwitcher;
import sceneswitch.SceneBase;

@AutoGenerated
public class MainMenuMyFuelScreen extends SceneBase {



	private MfImageView _aboutUsScreenControl;
	private MfImageView _signUpPersonalDetailsScreenControl;
	private MfImageView _logInScreenControl;

	public MainMenuMyFuelScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
	}

	@Override
	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("MainMenuMyFuelScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		_aboutUsScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$AboutUsScreen"));
		_aboutUsScreenControl.addEvent((event) -> { _switcher.switchScene("AboutUsScreen"); });

		_signUpPersonalDetailsScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$SignUpPersonalDetailsScreen"));
		_signUpPersonalDetailsScreenControl.addEvent((event) -> { _switcher.switchScene("SignUpPersonalDetailsScreen"); });

		_logInScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$LogInScreen"));
		_logInScreenControl.addEvent((event) -> { _switcher.switchScene("LogInScreen"); });


	}

	@Override
	protected void onLoad() {
		
	}

	@Override
	public void setParameters(IEntity[] entities) {
		super.setParameters(entities);
	}



}
