package action;

import adapter.base.ControlAdapter;
import client.IClient;
import messages.Header.RequestType;
import messages.response.IResponseCallBack;

/**
 * @author shaielb
 *
 */
@SuppressWarnings("rawtypes")
public abstract class CapabilityDecorator {
	
	/**
	 * 
	 */
	protected RequestType _type;
	
	/**
	 * 
	 */
	protected IClient _client;
	
	/**
	 * 
	 */
	protected IResponseCallBack _callback;
	
	/**
	 * 
	 */
	protected ControlAdapter _control;
	
	/**
	 * @param client
	 */
	public void set(IClient client) {
		_client = client;
	}
	
	/**
	 * @param callback
	 */
	public void set(IResponseCallBack callback) {
		_callback = callback;
	}
	
	/**
	 * @param control
	 */
	public void set(ControlAdapter control) {
		_control = control;
		apply(control);
	}
	
	/**
	 * @return
	 */
	public RequestType getType() {
		return _type;
	}
	
	/**
	 * @param control
	 */
	protected abstract void apply(ControlAdapter control);
}
