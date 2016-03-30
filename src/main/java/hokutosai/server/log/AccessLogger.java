package hokutosai.server.log;

import hokutosai.server.data.domain.AccessErrorLog;
import hokutosai.server.data.domain.AccessLog;

import org.springframework.stereotype.Component;

@Component
public class AccessLogger {

	public void access(AccessLog accessLog) {

	}

	public void error(AccessErrorLog errorLog) {

	}

}
