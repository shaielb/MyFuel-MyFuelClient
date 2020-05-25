package controls.button;

import java.util.Map;

import messages.request.IFilter;
import messages.request.IRequest;

public class MfFilterButton extends MfActionButton {

	private Map<String, String> _querySigns;

	public MfFilterButton(String title) {
		super(title);
	}

	@Override
	protected IRequest createRequest() {
		IFilter request = _client.getFilterRequest();
		request.setQueryEntity(_entity, _querySigns);
		return request;
	}

	public void setQuerySigns(Map<String, String> querySigns) {
		_querySigns = querySigns;
	}
}
