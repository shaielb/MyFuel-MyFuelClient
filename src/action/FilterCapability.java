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

	private IEntity _queryEntity;

	private Map<String, String> _querySigns;
	
	public FilterCapability() {
		_type = RequestType.Filter;
	}

	public void setQueryEntity(IEntity queryEntity, Map<String, String> querySigns) {
		_queryEntity = queryEntity;
		_querySigns = querySigns;
	}

	@Override
	protected void apply(ControlAdapter control) {
		control.addEvent((event) -> {
			filter();
		});
	}

	public void filter() {
		IFilter request = _client.getFilterRequest();
		request.setQueryEntity(_queryEntity, _querySigns);
		try {
			ResponseEvent responseEvent = _client.sendRequest(request);
			responseEvent.continueWith(_callback);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
