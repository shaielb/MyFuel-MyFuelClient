package wrapper;

import java.util.Arrays;

import annotations.AutoGenerated;
import application.Main;
import client.IClient;
import controls.MfImageView;
import controls.MfText;
import db.interfaces.IEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sceneswitch.Context;
import sceneswitch.ISceneSwitcher;
import sceneswitch.SceneBase;

@AutoGenerated
public class MainMenuMarketingNextNextScreen extends SceneBase {



	private MfImageView _analyticalRatingScreenControl;
	private MfImageView _mainMenuMyFuelScreenControl;
	private MfImageView _mainMenuMarketingScreenControl;
	private MfImageView _mainMenuMarketingNextScreenControl;
	private MfText _firstNameControl;

	public MainMenuMarketingNextNextScreen(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		super(sceneSwitcher, client, context);
	}

	@Override
	public void initialize() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("MainMenuMarketingNextNextScreen.fxml"));
		_scene = new Scene(root);

		//scene switchers
		_analyticalRatingScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$AnalyticalRatingScreen"));
		_analyticalRatingScreenControl.addEvent((event) -> { _switcher.switchScene("AnalyticalRatingScreen"); });
		_analyticalRatingScreenControl.getInstance().setDisable(true);

		_mainMenuMyFuelScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMyFuelScreen"));
		_mainMenuMyFuelScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMyFuelScreen"); });

		_mainMenuMarketingScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMarketingScreen"));
		_mainMenuMarketingScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMarketingScreen"); });

		_mainMenuMarketingNextScreenControl = new MfImageView((ImageView) _scene.lookup("#scene$MainMenuMarketingNextScreen"));
		_mainMenuMarketingNextScreenControl.addEvent((event) -> { _switcher.switchScene("MainMenuMarketingNextScreen"); });

		//controls instantiation
		_firstNameControl = new MfText((Text) _scene.lookup("#context$person$first_name"));

		//fields initializations
		_firstNameControl.setField(_context.getPerson().getClass().getDeclaredField("_first_name"));
		_firstNameControl.setEntity(_context.getPerson());

		if (Arrays.asList(new String[] { 
				"MyFuel Marketing Representative" , "MyFuel Manager" })
				.contains(_context.getSystemUser().getPermission())) {
			_analyticalRatingScreenControl.getInstance().setDisable(false);
		}
	}

	@Override
	protected void onLoad() {
		
	}

	@Override
	public void setParameters(IEntity[] entities) {
		super.setParameters(entities);
	}



}
