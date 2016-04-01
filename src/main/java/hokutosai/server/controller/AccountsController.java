package hokutosai.server.controller;

import hokutosai.server.data.json.account.AuthorizedAccount;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/accounts")
public class AccountsController {

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public AuthorizedAccount postAuth() {
		return null;
	}

}
