package hokutosai.server.security.auth;

import hokutosai.server.error.BadRequestException;
import hokutosai.server.error.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;

public class AuthorizationHeader {

	@Getter
	private ApiUserCredentials apiUser;

	@Getter
	private AccountCredentials account;

	public AuthorizationHeader(HttpServletRequest request) throws UnauthorizedException, BadRequestException {
		this(request.getHeader("Authorization"));
	}

	public AuthorizationHeader(String authorizationHeader) throws UnauthorizedException, BadRequestException {
		if (authorizationHeader == null) throw new UnauthorizedException("Authorization header does not exist.");

		String[] credentials = authorizationHeader.split(",");

		String userId = null, accessToken = null, accountId = null, accountPass = null;
		for (String credential: credentials) {
			String[] token = credential.split("=");
			if (token.length != 2) throw new BadRequestException("Bad format of credentials.");
			switch (token[0]) {
			case "user_id":
				userId = token[1];
				break;
			case "access_token":
				accessToken = token[1];
				break;
			case "account_id":
				accountId = token[1];
				break;
			case "account_pass":
				accountPass = token[1];
				break;
			default:
				throw new BadRequestException("Bad format of credentials.");
			}
		}

		if (userId == null || accessToken == null) {
			throw new BadRequestException("Bad format of credentials.");
		}

		this.apiUser = new ApiUserCredentials(userId, accessToken);

		if (accountId != null) {
			if (accountPass == null) throw new BadRequestException("Bad format of credentials.");
			this.account = new AccountCredentials(accountId, accountPass);
		}
	}

}
