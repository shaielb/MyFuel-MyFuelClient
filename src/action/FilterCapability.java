package action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import adapter.base.ControlAdapter;
import db.interfaces.IEntity;
import messages.Header.RequestType;
import messages.QueryContainer;
import messages.request.IFilter;
import messages.response.ResponseEvent;

/**
 * @author shaielb
 *
 */
@SuppressWarnings("rawtypes")
public class FilterCapability extends CapabilityDecorator {

	/**
	 * 
	 */
	private List<QueryContainer> _queryContainers;
	
	/**
	 * 
	 */
	public FilterCapability() {
		_type = RequestType.Filter;
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
			filter();
		});
	}

	/**
	 * 
	 */
	public void filter() {
		IFilter request = _client.getFilterRequest();
		request.setQueryContainers(_queryContainers);
		try {
			ResponseEvent responseEvent = _client.sendRequest(request);
			responseEvent.continueWith(_callback);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
