package hokutosai.server.data.repository.log;

import hokutosai.server.data.domain.log.AccessLog;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccessLogRepository extends MongoRepository<AccessLog, String> {

}
