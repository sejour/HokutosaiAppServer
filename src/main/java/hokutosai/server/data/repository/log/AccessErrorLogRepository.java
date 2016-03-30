package hokutosai.server.data.repository.log;

import hokutosai.server.data.domain.log.AccessErrorLog;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccessErrorLogRepository extends MongoRepository<AccessErrorLog, String> {

}
