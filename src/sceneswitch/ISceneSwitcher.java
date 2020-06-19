package sceneswitch;

import db.interfaces.IEntity;

public interface ISceneSwitcher {

	/**
	 * @param sceneName
	 */
	public void switchScene(String sceneName, IEntity... entities);
}
