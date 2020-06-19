package action;

import java.util.HashMap;
import java.util.Map;

import adapter.base.ControlAdapter;
import client.IClient;
import messages.Header.RequestType;
import messages.response.IPreSendRequest;
import messages.response.IResponseCallBack;

/**
 * @author shaielb
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ActionControl {

	/**
	 * 
	 */
	private Map<RequestType, CapabilityDecorator> _capabilities = new HashMap<RequestType, CapabilityDecorator>();

	/**
	 * 
	 */
	private IClient _client;

	/**
	 * 
	 */
	private IResponseCallBack _callback;

	/**
	 * 
	 */
	private IPreSendRequest _preSendRequest;

	/**
	 * 
	 */
	private ControlAdapter _control;

	/**
	 * @param capability
	 */
	public void addCapability(CapabilityDecorator capability) {
		capability.set(_client);
		capability.set(_callback);
		capability.set(_control);
		_capabilities.put(capability.getType(), capability);
	}

	/**
	 * @param client
	 */
	public void setClient(IClient client) {
		_client = client;
		for (CapabilityDecorator capability: _capabilities.values()) {
			capability.set(client);
		}
	}

	/**
	 * @param callback
	 */
	public void setCallback(IResponseCallBack callback) {
		_callback = callback;
		for (CapabilityDecorator capability: _capabilities.values()) {
			capability.set(callback);
		}
	}

	public void setPreSend(IPreSendRequest preSendRequest) {
		_preSendRequest = preSendRequest;
		for (CapabilityDecorator capability: _capabilities.values()) {
			capability.set(preSendRequest);
		}
	}

	/**
	 * @param control
	 */
	public void setControl(ControlAdapter control) {
		_control = control;
		for (CapabilityDecorator capability: _capabilities.values()) {
			capability.set(control);
		}
	}

	/**
	 * @return
	 */
	public ControlAdapter getControl() {
		return _control;
	}

	/**
	 * @param <TCapability>
	 * @param type
	 * @return
	 */
	public <TCapability extends CapabilityDecorator> TCapability getCapability(RequestType type) {
		return (TCapability) _capabilities.get(type);
	}
}
