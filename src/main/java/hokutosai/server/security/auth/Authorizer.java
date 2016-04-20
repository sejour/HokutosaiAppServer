package hokutosai.server.security.auth;

import hokutosai.server.data.entity.auth.Permission;
import hokutosai.server.data.type.PermissionEnum;

public abstract class Authorizer {

	public static boolean isAllow(Permission permission) {
		return permission.getPermission() == PermissionEnum.allow;
	}

}
