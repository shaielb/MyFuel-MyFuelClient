package controls.button;

import java.util.Set;

import db.interfaces.IEntity;
import messages.request.IRequest;
import messages.request.IUpdate;

public class MfUpdateButton extends MfActionButton {

	private Set<IEntity> _entities;

	public MfUpdateButton(String title) {
		super(title);
	}

	@Override
	protected IRequest createRequest() {
		IUpdate request = _client.getUpdateRequest();
		request.setEntities(_entities);
		return request;
	}

	public void setEntities(Set<IEntity> entities) {
		_entities = entities;
	}
}
