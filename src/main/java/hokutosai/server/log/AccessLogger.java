package hokutosai.server.log;

import hokutosai.server.data.domain.log.AccessErrorLog;
import hokutosai.server.data.domain.log.AccessLog;
import hokutosai.server.data.repository.log.AccessErrorLogRepository;
import hokutosai.server.data.repository.log.AccessLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessLogger {

	@Autowired
	private AccessLogRepository accessLogRepository;

	@Autowired
	private AccessErrorLogRepository accessErrorLogRepository;

	public void access(AccessLog accessLog) {
		this.accessLogRepository.save(accessLog);
	}

	public void error(AccessErrorLog errorLog) {
		this.accessErrorLogRepository.save(errorLog);
	}

}
