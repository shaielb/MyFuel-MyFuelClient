package action;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import adapter.base.ControlAdapter;
import db.interfaces.IEntity;
import messages.Header.RequestType;
import messages.request.IRemove;
import messages.response.ResponseEvent;

/**
 * @author shaielb
 *
 */
@SuppressWarnings("rawtypes")
public class RemoveCapability extends CapabilityDecorator {

	/**
	 * 
	 */
	private Set<IEntity> _entities = new HashSet<IEntity>();

	/**
	 * 
	 */
	public RemoveCapability() {
		_type = RequestType.Remove;
	}
	
	/**
	 * @param entity
	 */
	public void addEntity(IEntity entity) {
		_entities.add(entity);
	}

	/**
	 *
	 */
	@Override
	protected void apply(ControlAdapter control) {
		control.addEvent((event) -> {
			remove();
		});
	}

	/**
	 * 
	 */
	public void remove() {
		IRemove request = _client.getRemoveRequest();
		request.setEntities(_entities);
		try {
			ResponseEvent responseEvent = _client.sendRequest(request);
			responseEvent.continueWith(_callback);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
