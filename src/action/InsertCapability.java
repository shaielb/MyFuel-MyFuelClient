package action;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import adapter.base.ControlAdapter;
import db.interfaces.IEntity;
import messages.Header.RequestType;
import messages.request.IInsert;
import messages.response.ResponseEvent;

@SuppressWarnings("rawtypes")
public class InsertCapability extends CapabilityDecorator {

	private Set<IEntity> _entities = new HashSet<IEntity>();

	public InsertCapability() {
		_type = RequestType.Insert;
	}
	
	public void addEntity(IEntity entity) {
		_entities.add(entity);
	}

	@Override
	protected void apply(ControlAdapter control) {
		control.addEvent((event) -> {
			insert();
		});
	}
	
	public void insert() {
		IInsert request = _client.getInsertRequest();
		request.setEntities(_entities);
		try {
			ResponseEvent responseEvent = _client.sendRequest(request);
			responseEvent.continueWith(_callback);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
