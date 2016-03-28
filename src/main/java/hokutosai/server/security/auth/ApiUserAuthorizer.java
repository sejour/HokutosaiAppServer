package hokutosai.server.security.auth;

import javax.servlet.http.HttpServletRequest;

import hokutosai.server.data.entity.auth.ApiUser;
import hokutosai.server.data.repository.auth.ApiUserRepository;
import hokutosai.server.error.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiUserAuthorizer {

	@Autowired
	private ApiUserRepository apiUserRepository;

	public String authorize(HttpServletRequest request) throws BadRequestException, ApiUserUnauthorizedException, ApiUserForbiddenException {

		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader == null) {
			throw new BadRequestException(request.getMethod(), request.getRequestURI(), "Authorization header does not exist.");
		}

		String[] credentials = authorizationHeader.split(",");
		if (credentials.length != 2) {
			throw new BadRequestException(request.getMethod(), request.getRequestURI(), "Bad format of credentials.");
		}

		String[] userIdValues = credentials[0].split("=");
		String[] accessTokenValues = credentials[1].split("=");
		if (userIdValues.length != 2 || accessTokenValues.length != 2 || !userIdValues[0].equals("user_id") || !accessTokenValues[0].equals("access_token")) {
			throw new BadRequestException(request.getMethod(), request.getRequestURI(), "Bad format of credentials.");
		}

		String userId = userIdValues[1];
		String accessToken = accessTokenValues[1];

		ApiUser apiUser = apiUserRepository.findByUserId(userId);

		if (apiUser == null) {
			throw new ApiUserUnauthorizedException(request, userId);
		}

		String resultUserId = apiUser.getUserId();
		String resultAccessToken = apiUser.getAccessToken();

		if (resultUserId.equals(userId) && resultAccessToken.equals(accessToken)) {
			return userId;
		}

		throw new ApiUserUnauthorizedException(request, userId);
	}

}
