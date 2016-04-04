package hokutosai.server.util;

import hokutosai.server.data.document.auth.AuthorizationTarget;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.error.InternalServerErrorException;

import javax.servlet.ServletRequest;

public final class RequestAttribute {

	public static final String API_USER = "ApiUser";

	public static final String ACCOUNT = "Account";

	public static final String ERROR = "Error";

	public static AuthorizationTarget getApiUser(ServletRequest request) {
		Object apiUser = request.getAttribute(API_USER);
		if (apiUser == null) return null;
		return (AuthorizationTarget)apiUser;
	}

	public static AuthorizationTarget getRequiredApiUser(ServletRequest request) throws InternalServerErrorException {
		Object apiUser = request.getAttribute(API_USER);
		if (apiUser == null) throw new InternalServerErrorException("Required account is not exist.");
		return (AuthorizationTarget)apiUser;
	}

	public static AuthorizedAccount getAccount(ServletRequest request) {
		Object account = request.getAttribute(ACCOUNT);
		if (account == null) return null;
		return (AuthorizedAccount)account;
	}

	public static AuthorizedAccount getRequiredAccount(ServletRequest request) throws InternalServerErrorException {
		Object account = request.getAttribute(ACCOUNT);
		if (account == null) throw new InternalServerErrorException("Required account is not exist.");
		return (AuthorizedAccount)account;
	}

}
