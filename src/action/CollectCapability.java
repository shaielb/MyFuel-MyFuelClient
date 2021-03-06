package action;

import java.io.IOException;

import adapter.base.ControlAdapter;
import messages.Header.RequestType;
import messages.request.ICollect;
import messages.response.ResponseEvent;

/**
 * @author shaielb
 *
 */
@SuppressWarnings("rawtypes")
public class CollectCapability extends CapabilityDecorator {

	/**
	 * 
	 */
	private String[] _tables;
	
	/**
	 * 
	 */
	public CollectCapability() {
		_type = RequestType.Collect;
	}

	/**
	 * @param tables
	 */
	public void setTables(String[] tables) {
		_tables = tables;
	}

	/**
	 *
	 */
	@Override
	protected void apply(ControlAdapter control) {
		control.addEvent((event) -> {
			collect();
		});
	}

	/**
	 * 
	 */
	public void collect() {
		ICollect request = _client.getCollectRequest();
		request.setTables(_tables);
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
