package hokutosai.server.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hokutosai.server.data.document.auth.AuthorizationTarget;
import hokutosai.server.data.document.log.AccessErrorLog;
import hokutosai.server.data.document.log.AccessLog;
import hokutosai.server.data.repository.log.AccessErrorLogRepository;
import hokutosai.server.data.repository.log.AccessLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AccessLogger {

	@Autowired
	private AccessLogRepository accessLogRepository;

	@Autowired
	private AccessErrorLogRepository accessErrorLogRepository;

	public void log(HttpServletRequest request, HttpServletResponse response) {
		AuthorizationTarget apiUser = null;
		AuthorizationTarget account = null;
		HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus());

		Object apiUserAttribute = request.getAttribute("apiUser");
		if (apiUserAttribute != null) apiUser = (AuthorizationTarget)apiUserAttribute;

		Object accountAttribute = request.getAttribute("account");
		if (accountAttribute != null) account = (AuthorizationTarget)accountAttribute;

		Object error = request.getAttribute("Error");
		if (error == null) {
			this.accessLogRepository.save(new AccessLog(request, apiUser, account, httpStatus));
		}
		else {
			this.accessErrorLogRepository.save(new AccessErrorLog(request, apiUser, account, httpStatus, (Throwable)error));
		}
	}

}
