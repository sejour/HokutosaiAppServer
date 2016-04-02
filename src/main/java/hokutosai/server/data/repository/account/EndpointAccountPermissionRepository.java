package hokutosai.server.data.repository.account;

import hokutosai.server.data.entity.account.EndpointAccountPermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface EndpointAccountPermissionRepository extends JpaRepository<EndpointAccountPermission, Integer> {

	public EndpointAccountPermission findByPathAndMethodAndRole(String path, String method, @Param("account_role") String role);

}
