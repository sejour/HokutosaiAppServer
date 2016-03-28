package hokutosai.server.data.repository.auth;

import hokutosai.server.data.entity.auth.ApiUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiUserRepository extends JpaRepository<ApiUser, String> {

	public ApiUser findByUserId(String userId);

}
