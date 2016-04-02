package hokutosai.server.data.repository.auth;

import hokutosai.server.data.entity.auth.EndpointApiUserPermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface EndpointApiUserPermissionRepository extends JpaRepository<EndpointApiUserPermission, Integer> {

	public EndpointApiUserPermission findByPathAndMethodAndRole(String path, String method, @Param("api_user_role") String role);

}
