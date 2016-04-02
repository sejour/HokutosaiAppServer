package hokutosai.server.controller;

import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.security.auth.AccountAuthorizer;
import hokutosai.server.security.auth.AccountForbiddenException;
import hokutosai.server.security.auth.AccountUnauthorizedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public AuthorizedAccount postAuth(@RequestParam("account_id") String id, @RequestParam("account_pass") String password) throws AccountUnauthorizedException, AccountForbiddenException {
		return this.accountAuthorizer.loginAuthorize(id, password);
	}

}
