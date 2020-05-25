package controls.button;

import java.util.Set;

import db.interfaces.IEntity;
import messages.request.IInsert;
import messages.request.IRequest;

public class MfInsertButton extends MfActionButton {

	private Set<IEntity> _entities;

	public MfInsertButton(String title) {
		super(title);
	}

	@Override
	protected IRequest createRequest() {
		IInsert request = _client.getInsertRequest();
		request.setEntities(_entities);
		return request;
	}

	public void setEntities(Set<IEntity> entities) {
		_entities = entities;
	}
}
