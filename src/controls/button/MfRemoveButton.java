package controls.button;

import java.util.Set;

import db.interfaces.IEntity;
import messages.request.IRemove;
import messages.request.IRequest;

public class MfRemoveButton extends MfActionButton {

	private Set<IEntity> _entities;

	public MfRemoveButton(String title) {
		super(title);
	}

	@Override
	protected IRequest createRequest() {
		IRemove request = _client.getRemoveRequest();
		request.setEntities(_entities);
		return request;
	}

	public void setEntities(Set<IEntity> entities) {
		_entities = entities;
	}
}
