package hokutosai.server.security.auth;

import hokutosai.server.data.entity.auth.Permission;

public abstract class Authorizer {

	public static final String PERMISSION_ALLOW = "allow";

	public static boolean isAllow(Permission permission) {
		return permission.getPermission().equals(PERMISSION_ALLOW);
	}

}
