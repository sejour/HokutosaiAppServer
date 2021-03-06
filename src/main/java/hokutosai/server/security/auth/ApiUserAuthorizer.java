package hokutosai.server.security.auth;

import hokutosai.server.data.document.auth.AuthorizationTarget;
import hokutosai.server.data.entity.Endpoint;
import hokutosai.server.data.entity.auth.ApiUser;
import hokutosai.server.data.entity.auth.ApiUserRole;
import hokutosai.server.data.entity.auth.EndpointApiUserPermission;
import hokutosai.server.data.repository.auth.ApiUserRepository;
import hokutosai.server.data.repository.auth.EndpointApiUserPermissionRepository;
import hokutosai.server.error.InternalServerErrorException;
import hokutosai.server.error.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiUserAuthorizer extends Authorizer {

	@Autowired
	private ApiUserRepository apiUserRepository;

	@Autowired
	private EndpointApiUserPermissionRepository endpointPermissionRepository;

	public AuthorizationTarget authorize(ApiUserCredentials credentials, Endpoint endpoint) throws InternalServerErrorException, ApiUserUnauthorizedException, NotFoundException, ApiUserForbiddenException {
		if (credentials == null || endpoint == null) throw new InternalServerErrorException("Credentials is null");

		String userId = credentials.getId();

		ApiUser apiUser = apiUserRepository.findByUserId(userId);
		if (apiUser == null) {
			throw new ApiUserUnauthorizedException(new AuthorizationTarget(userId));
		}

		ApiUserRole role = apiUser.getRole();
		String roleName = role.getRole();

		EndpointApiUserPermission endpointPermission = this.endpointPermissionRepository.findByPathAndMethodAndRole(endpoint.getPath(), endpoint.getMethod(), roleName);
		if (endpointPermission == null) {
			throw new NotFoundException("The endpoint does not exist.");
		}

		if (apiUser.getUserId().equals(userId) && apiUser.getAccessToken().equals(credentials.getAccessToken())) {
			if (isAllow(role) && isAllow(apiUser) && isAllow(endpointPermission)) {
				return new AuthorizationTarget(userId, roleName);
			}
			throw new ApiUserForbiddenException(new AuthorizationTarget(userId, roleName));
		}

		throw new ApiUserUnauthorizedException(new AuthorizationTarget(userId, roleName));
	}

}
