package hokutosai.server.security.auth;

import javax.servlet.http.HttpServletRequest;

import hokutosai.server.data.repository.auth.ApiUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiUserAuthorizer {

	@Autowired
	private ApiUserRepository apiUserRepository;

	public void authorize(HttpServletRequest request) {

	}

}
