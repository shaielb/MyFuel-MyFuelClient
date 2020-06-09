package action;

import java.io.IOException;
import java.util.Map;

import adapter.base.ControlAdapter;
import db.interfaces.IEntity;
import messages.Header.RequestType;
import messages.request.IFilter;
import messages.response.ResponseEvent;

@SuppressWarnings("rawtypes")
public class FilterCapability extends CapabilityDecorator {

	private Map<IEntity, Map<String, String>> _queryEntitiesMap;
	
	public FilterCapability() {
		_type = RequestType.Filter;
	}

	public void setQueryEntities(Map<IEntity, Map<String, String>> queryEntitiesMap) {
		_queryEntitiesMap = queryEntitiesMap;
	}

	@Override
	protected void apply(ControlAdapter control) {
		control.addEvent((event) -> {
			filter();
		});
	}

	public void filter() {
		IFilter request = _client.getFilterRequest();
		request.setQueryEntities(_queryEntitiesMap);
		try {
			ResponseEvent responseEvent = _client.sendRequest(request);
			responseEvent.continueWith(_callback);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
