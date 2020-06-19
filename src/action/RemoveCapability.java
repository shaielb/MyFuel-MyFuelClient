package action;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import adapter.base.ControlAdapter;
import db.interfaces.IEntity;
import messages.Header.RequestType;
import messages.QueryContainer;
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
	private List<QueryContainer> _queryContainers;

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
	 * @param queryEntitiesMap
	 */
	public void setQueryContainers(List<QueryContainer> queryContainers) {
		_queryContainers = queryContainers;
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
		if (_entities != null && _entities.size() > 0) {
			request.setEntities(_entities);
		}
		if (_queryContainers != null && _queryContainers.size() > 0) {
			request.setQueryContainers(_queryContainers);
		}
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
