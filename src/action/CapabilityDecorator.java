package action;

import adapter.base.ControlAdapter;
import client.IClient;
import messages.Header.RequestType;
import messages.response.IResponseCallBack;

@SuppressWarnings("rawtypes")
public abstract class CapabilityDecorator {
	
	protected RequestType _type;
	
	protected IClient _client;
	
	protected IResponseCallBack _callback;
	
	protected ControlAdapter _control;
	
	public void set(IClient client) {
		_client = client;
	}
	
	public void set(IResponseCallBack callback) {
		_callback = callback;
	}
	
	public void set(ControlAdapter control) {
		_control = control;
		apply(control);
	}
	
	public RequestType getType() {
		return _type;
	}
	
	protected abstract void apply(ControlAdapter control);
}
