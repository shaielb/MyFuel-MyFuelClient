package sceneswitch;

import adapter.base.ControlAdapter;
import client.IClient;
import db.interfaces.IEntity;
import javafx.scene.Scene;

@SuppressWarnings("rawtypes")
public class SceneBase {

	public interface ISceneSwitcher {
		public void switchScene(String sceneName);
	}
	
	protected Scene _scene;
	
	protected IEntity _entity;
	
	protected ISceneSwitcher _switcher;
	
	protected IClient _client;
	
	protected SceneBase(ISceneSwitcher sceneSwitcher, IClient client) throws Exception {
		_switcher = sceneSwitcher;
		_client = client;
		initialize();
	}
	
	public void initialize() throws Exception {}
	
	protected void groupControls(ControlAdapter[] adapters) {
		for (ControlAdapter adapter1 : adapters) {
			for (ControlAdapter adapter2 : adapters) {
				if (adapter1 != adapter2) {
					adapter1.addEvent(newVal -> {
						try {
							adapter2.update();
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
				}
			}
		}
	}
	
	public Scene getScene() {
		return _scene;
	}
	
	public IEntity getEntity() {
		return _entity;
	}
	
	public void setSceneSwitcher(ISceneSwitcher switcher) {
		_switcher = switcher;
	}
}
