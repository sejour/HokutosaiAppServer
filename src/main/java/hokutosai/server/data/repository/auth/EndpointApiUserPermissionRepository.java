package hokutosai.server.data.repository.auth;

import hokutosai.server.data.entity.auth.EndpointApiUserPermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EndpointApiUserPermissionRepository extends JpaRepository<EndpointApiUserPermission, Integer> {

	@Query("SELECT e FROM EndpointApiUserPermission e WHERE e.path = :path AND e.method = :method AND e.role = :role")
	public EndpointApiUserPermission findByPathAndMethodAndRole(@Param("path") String path, @Param("method") String method, @Param("role") String role);

}
