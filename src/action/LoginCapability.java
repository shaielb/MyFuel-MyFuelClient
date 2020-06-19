package action;

import messages.Header.RequestType;

/**
 * @author shaielb
 *
 */
public class LoginCapability extends FilterCapability {

	/**
	 * 
	 */
	public LoginCapability() {
		_type = RequestType.Login;
	}
}
