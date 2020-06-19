package action;

import java.io.IOException;
import java.util.Set;

import adapter.base.ControlAdapter;
import db.interfaces.IEntity;
import messages.Header.RequestType;
import messages.request.IUpdate;
import messages.response.ResponseEvent;

/**
 * @author shaielb
 *
 */
@SuppressWarnings("rawtypes")
public class UpdateCapability extends CapabilityDecorator {

	/**
	 * 
	 */
	private Set<IEntity> _entities;

	/**
	 * 
	 */
	public UpdateCapability() {
		_type = RequestType.Update;
	}
	
	/**
	 * @param entities
	 */
	public void setEntities(Set<IEntity> entities) {
		_entities = entities;
	}

	/**
	 *
	 */
	@Override
	protected void apply(ControlAdapter control) {
		control.addEvent((event) -> {
			update();
		});
	}

	/**
	 * 
	 */
	public void update() {
		IUpdate request = _client.getUpdateRequest();
		request.setEntities(_entities);
		try {
			if (_preSendRequest.execute(request)) {
				ResponseEvent responseEvent = _client.sendRequest(request);
				responseEvent.continueWith(_callback);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
