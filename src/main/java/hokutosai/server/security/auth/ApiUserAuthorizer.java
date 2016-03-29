package hokutosai.server.security.auth;

import javax.servlet.http.HttpServletRequest;

import hokutosai.server.data.domain.AuthorizationApiUser;
import hokutosai.server.data.entity.auth.ApiUser;
import hokutosai.server.data.entity.auth.ApiUserRole;
import hokutosai.server.data.entity.auth.EndpointPermission;
import hokutosai.server.data.repository.auth.ApiUserRepository;
import hokutosai.server.data.repository.auth.EndpointPermissionRepository;
import hokutosai.server.error.BadRequestException;
import hokutosai.server.error.NotFoundException;
import hokutosai.server.error.UnauthorizedException;
import hokutosai.server.util.EndpointPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiUserAuthorizer {

	private static final String PERMISSION_ALLOW = "allow";

	@Autowired
	private ApiUserRepository apiUserRepository;

	@Autowired
	private EndpointPermissionRepository endpointPermissionRepository;

	public AuthorizationApiUser authorize(HttpServletRequest request) throws UnauthorizedException, BadRequestException, ApiUserForbiddenException, NotFoundException {

		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader == null) {
			throw new UnauthorizedException("Authorization header does not exist.");
		}

		String[] credentials = authorizationHeader.split(",");
		if (credentials.length != 2) {
			throw new BadRequestException("Bad format of credentials.");
		}

		String[] userIdValues = credentials[0].split("=");
		String[] accessTokenValues = credentials[1].split("=");
		if (userIdValues.length != 2 || accessTokenValues.length != 2 || !userIdValues[0].equals("user_id") || !accessTokenValues[0].equals("access_token")) {
			throw new BadRequestException("Bad format of credentials.");
		}

		String userId = userIdValues[1];
		String accessToken = accessTokenValues[1];

		ApiUser apiUser = apiUserRepository.findByUserId(userId);
		if (apiUser == null) {
			throw new ApiUserUnauthorizedException(new AuthorizationApiUser(userId));
		}

		ApiUserRole role = apiUser.getRole();

		String uri = request.getRequestURI();
		String method = request.getMethod();
		String roleName = role.getRole();
		EndpointPath path = new EndpointPath(uri);
		EndpointPermission endpoint = this.endpointPermissionRepository.findByPathAndMethodAndRole(path.toString(), method, roleName);
		if (endpoint == null) {
			throw new NotFoundException("The endpoint does not exist.");
		}

		if (userId.equals(apiUser.getUserId()) && accessToken.equals(apiUser.getAccessToken())) {
			if (role.getPermission().equals(PERMISSION_ALLOW) && apiUser.getPermission().equals(PERMISSION_ALLOW) && endpoint.getCategory().getPermission().equals(PERMISSION_ALLOW) && endpoint.getPermission().equals(PERMISSION_ALLOW)) {
				return new AuthorizationApiUser(userId, roleName);
			}
			throw new ApiUserForbiddenException(new AuthorizationApiUser(userId, roleName));
		}

		throw new ApiUserUnauthorizedException(new AuthorizationApiUser(userId, roleName));
	}

}
