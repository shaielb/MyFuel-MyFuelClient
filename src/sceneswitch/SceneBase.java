package sceneswitch;

import adapter.base.ControlAdapter;
import client.IClient;
import db.interfaces.IEntity;
import javafx.scene.Scene;

@SuppressWarnings("rawtypes")
public class SceneBase {
	
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
	
	protected IEntity[] _entitiesParameters;
	
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
	}
	
	protected void onLoad() throws Exception {}
	
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
	
	public void setParameters(IEntity[] entities) {
		_entitiesParameters = entities;
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
