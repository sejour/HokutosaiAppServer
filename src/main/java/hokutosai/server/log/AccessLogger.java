package hokutosai.server.log;

import hokutosai.server.data.domain.AccessErrorLog;
import hokutosai.server.data.domain.AccessLog;
import hokutosai.server.filter.HokutosaiApiFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AccessLogger {

	private static final Logger logger = LoggerFactory.getLogger(HokutosaiApiFilter.class);


	public void access(AccessLog accessLog) {

	}

	public void error(AccessErrorLog errorLog) {

	}

}
