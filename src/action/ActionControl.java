package action;

import java.util.HashMap;
import java.util.Map;

import adapter.base.ControlAdapter;
import client.IClient;
import messages.Header.RequestType;
import messages.response.IResponseCallBack;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ActionControl {

	private Map<RequestType, CapabilityDecorator> _capabilities = new HashMap<RequestType, CapabilityDecorator>();

	private IClient _client;

	private IResponseCallBack _callback;

	private ControlAdapter _control;

	public void addCapability(CapabilityDecorator capability) {
		capability.set(_client);
		capability.set(_callback);
		capability.set(_control);
		_capabilities.put(capability.getType(), capability);
	}

	public void setClient(IClient client) {
		_client = client;
		for (CapabilityDecorator capability: _capabilities.values()) {
			capability.set(client);
		}
	}

	public void setCallback(IResponseCallBack callback) {
		_callback = callback;
		for (CapabilityDecorator capability: _capabilities.values()) {
			capability.set(callback);
		}
	}

	public void setControl(ControlAdapter control) {
		_control = control;
		for (CapabilityDecorator capability: _capabilities.values()) {
			capability.set(control);
		}
	}

	public ControlAdapter getControl() {
		return _control;
	}
	
	public <TCapability extends CapabilityDecorator> TCapability getCapability(RequestType type) {
		return (TCapability) _capabilities.get(type);
	}
}
