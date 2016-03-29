package hokutosai.server.data.repository.auth;

import hokutosai.server.data.entity.auth.EndpointPermission;
import hokutosai.server.data.entity.auth.EndpointPermissionId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EndpointPermissionRepository extends JpaRepository<EndpointPermission, EndpointPermissionId> {

	public EndpointPermission findByPathAndMethodAndRole(String path, String method, String role);

}
