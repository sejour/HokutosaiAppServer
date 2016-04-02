package hokutosai.server.security.auth;

import hokutosai.server.data.entity.Endpoint;
import hokutosai.server.data.entity.account.Account;
import hokutosai.server.data.entity.account.AccountRole;
import hokutosai.server.data.entity.account.EndpointAccountPermission;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.data.repository.account.AccountRepository;
import hokutosai.server.data.repository.account.EndpointAccountPermissionRepository;
import hokutosai.server.error.InternalServerErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountAuthorizer extends Authorizer {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private EndpointAccountPermissionRepository endpointPermissionRepository;

	public AuthorizedAccount authorize(AccountCredentials credentials, Endpoint endpoint) throws AccountUnauthorizedException, AccountForbiddenException, InternalServerErrorException {
		if (credentials == null || endpoint == null) throw new InternalServerErrorException("Credentials is null");

		String accountId = credentials.getId();

		Account account = this.accountRepository.findByAccountId(accountId);
		if (account == null) {
			throw new AccountUnauthorizedException(accountId);
		}

		AccountRole role = account.getRole();
		String roleName = role.getRole();

		EndpointAccountPermission endpointPermission = this.endpointPermissionRepository.findByPathAndMethodAndRole(endpoint.getPath(), endpoint.getMethod(), roleName);
		if (endpointPermission == null) {
			throw new InternalServerErrorException("The endpoint does not regist.");
		}

		if (account.getAccountId().equals(accountId) && account.getPassword().equals(credentials.getPassword())) {
			if (isAllow(role) && isAllow(account) && isAllow(endpointPermission)) {
				return new AuthorizedAccount(account);
			}
			throw new AccountForbiddenException(account);
		}

		throw new AccountUnauthorizedException(accountId);
	}

}
