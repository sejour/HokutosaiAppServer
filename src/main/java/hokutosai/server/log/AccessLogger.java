package hokutosai.server.log;

import hokutosai.server.data.domain.log.AccessErrorLog;
import hokutosai.server.data.domain.log.AccessLog;

import org.springframework.stereotype.Component;

@Component
public class AccessLogger {

	public void access(AccessLog accessLog) {

	}

	public void error(AccessErrorLog errorLog) {

	}

}
