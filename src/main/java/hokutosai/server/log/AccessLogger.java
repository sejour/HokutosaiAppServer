package hokutosai.server.log;

import hokutosai.server.data.domain.log.AccessErrorLog;
import hokutosai.server.data.domain.log.AccessLog;
import hokutosai.server.data.repository.log.AccessErrorLogRepository;
import hokutosai.server.data.repository.log.AccessLogRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessLogger {

	@Autowired
	private AccessLogRepository accessLogRepository;

	@Autowired
	private AccessErrorLogRepository accessErrorLogRepository;

	private static final Logger logger = LoggerFactory.getLogger(AccessLogger.class);

	public void access(AccessLog accessLog) {
		this.accessLogRepository.save(accessLog);
	}

	public void error(AccessErrorLog errorLog) {
		this.accessErrorLogRepository.save(errorLog);

		if (errorLog.getStatus().getCode() == 500) {
			logger.error(errorLog.getException().getMessage());
		}
	}

}
