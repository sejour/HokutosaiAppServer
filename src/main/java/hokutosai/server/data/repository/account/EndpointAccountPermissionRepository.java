package hokutosai.server.data.repository.account;

import hokutosai.server.data.entity.account.EndpointAccountPermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EndpointAccountPermissionRepository extends JpaRepository<EndpointAccountPermission, Integer> {

	@Query("SELECT e FROM EndpointAccountPermission e WHERE e.path = :path AND e.method = :method AND e.role = :role")
	public EndpointAccountPermission findByPathAndMethodAndRole(@Param("path") String path, @Param("method") String method, @Param("role") String role);

}
