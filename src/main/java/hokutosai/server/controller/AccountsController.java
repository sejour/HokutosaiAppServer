package hokutosai.server.controller;

import javax.servlet.ServletRequest;

import hokutosai.server.data.entity.account.AccountMaster;
import hokutosai.server.data.entity.account.SecureAccount;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.data.repository.account.SecureAccountRepository;
import hokutosai.server.error.InternalServerErrorException;
import hokutosai.server.security.auth.AccountAuthorizer;
import hokutosai.server.security.auth.AccountForbiddenException;
import hokutosai.server.security.auth.AccountGenerator;
import hokutosai.server.security.auth.AccountUnauthorizedException;
import hokutosai.server.util.RequestAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/accounts")
public class AccountsController {

	@Autowired
	AccountAuthorizer accountAuthorizer;

	@Autowired
	SecureAccountRepository secureAccountRepository;

	@Autowired
	AccountGenerator accountGenerator;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public AccountMaster getNewAccount() throws InternalServerErrorException {
		return this.accountGenerator.issue();
	}

	@RequestMapping(value = "/authorization", method = RequestMethod.POST)
	public SecureAccount postAuth(ServletRequest request, @RequestParam("account_id") String id, @RequestParam("account_pass") String password) throws AccountUnauthorizedException, AccountForbiddenException, InternalServerErrorException {
		AuthorizedAccount account = this.accountAuthorizer.loginAuthorize(id, password);
		request.setAttribute(RequestAttribute.ACCOUNT, account);
		return new SecureAccount(account);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public SecureAccount getProfile(ServletRequest request, @RequestParam(value = "account_id", required = false) String accountId) throws InternalServerErrorException {
		if (accountId != null) {
			return this.secureAccountRepository.findOne(accountId);
		}

		AuthorizedAccount account = RequestAttribute.getRequiredAccount(request);

		return new SecureAccount(account);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.PUT)
	public SecureAccount putProfile(ServletRequest request, @RequestParam("user_name") String userName) throws InternalServerErrorException {
		AuthorizedAccount account = RequestAttribute.getRequiredAccount(request);

		if (!StringUtils.hasText(userName)) userName = null;
		this.secureAccountRepository.updateName(account.getId(), userName);

		SecureAccount secure = new SecureAccount(account);
		secure.setName(userName);

		return secure;
	}

}
