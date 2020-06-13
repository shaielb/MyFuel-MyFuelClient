package sceneswitch;

import adapter.base.ControlAdapter;
import client.IClient;
import db.interfaces.IEntity;
import javafx.scene.Scene;

@SuppressWarnings("rawtypes")
public class SceneBase {
	
	/**
	 * @author shaielb
	 *
	 */
	public interface ISceneSwitcher {
		public void switchScene(String sceneName);
	}
	
	/**
	 * 
	 */
	protected Scene _scene;
	
	/**
	 * 
	 */
	protected IEntity _entity;
	
	/**
	 * 
	 */
	protected ISceneSwitcher _switcher;
	
	/**
	 * 
	 */
	protected IClient _client;
	
	/**
	 * 
	 */
	protected Context _context;
	
	/**
	 * @param sceneSwitcher
	 * @param client
	 * @param context
	 * @throws Exception
	 */
	protected SceneBase(ISceneSwitcher sceneSwitcher, IClient client, Context context) throws Exception {
		_switcher = sceneSwitcher;
		_client = client;
		_context = context;
		initialize();
	}
	
	/**
	 * @throws Exception
	 */
	public void initialize() throws Exception {}
	
	/**
	 * @param adapters
	 */
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
	
	/**
	 * @return
	 */
	public Scene getScene() {
		return _scene;
	}
	
	/**
	 * @return
	 */
	public IEntity getEntity() {
		return _entity;
	}
	
	/**
	 * @param switcher
	 */
	public void setSceneSwitcher(ISceneSwitcher switcher) {
		_switcher = switcher;
	}
}
