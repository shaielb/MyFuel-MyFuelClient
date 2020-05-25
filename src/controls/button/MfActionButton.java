package controls.button;

import java.io.IOException;

import client.IClient;
import controls.MfButton;
import messages.Response;
import messages.request.IRequest;
import messages.response.IResponseCallBack;
import messages.response.ResponseEvent;

public abstract class MfActionButton extends MfButton {

	public interface IResponseEntities {
		public void response(Response response);
	}

	protected IClient _client;

	protected IResponseCallBack _callback;

	public MfActionButton(String title) {
		super(title);
	}

	@Override
	protected void initialize() {
		super.initialize();
		addEvent((event) -> {
			search();
		});
	}

	public void search() {
		IRequest request = createRequest();
		try {
			ResponseEvent responseEvent = _client.sendRequest(request);
			responseEvent.continueWith(_callback);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected abstract IRequest createRequest();

	public void setClient(IClient client) {
		_client = client;
	}

	public void setResponseCallBack(IResponseCallBack callback) {
		_callback = callback;
	}
}
