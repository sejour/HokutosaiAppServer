package hokutosai.server.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hokutosai.server.data.entity.Endpoint;
import hokutosai.server.data.entity.EndpointId;

public interface EndpointRepository extends JpaRepository<Endpoint, EndpointId> {

	public Endpoint findByPathAndMethod(String path, String method);

}
