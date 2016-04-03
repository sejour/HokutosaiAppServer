package hokutosai.server.security.auth;

import hokutosai.server.data.document.auth.AuthorizationTarget;

public interface AuthorizationApiUserException {

	public AuthorizationTarget getApiUser();

}
