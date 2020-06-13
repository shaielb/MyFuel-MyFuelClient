package wrapper;

import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import sceneswitch.Context;
import sceneswitch.SceneBase;

@AutoGenerated
public class MainMenuCustomerScreen extends SceneBase {



	private MfImageView _homeHeatingNewOrderScreenControl;
	private MfImageView _trackMyOrderScreenControl;
	private MfImageView _mainMenuMyFuelScreenControl;

	public MainMenuCustomerScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
		initialize();
	}

	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("MainMenuCustomerScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		_homeHeatingNewOrderScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$HomeHeatingNewOrderScreen"));
		_homeHeatingNewOrderScreenControl.addEvent((event) -> { _switcher.switchScene("HomeHeatingNewOrderScreen"); });

		_trackMyOrderScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$TrackMyOrderScreen"));
		_trackMyOrderScreenControl.addEvent((event) -> { _switcher.switchScene("TrackMyOrderScreen"); });

		_mainMenuMyFuelScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMyFuelScreen"));
		_mainMenuMyFuelScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMyFuelScreen"); });


	}



}
