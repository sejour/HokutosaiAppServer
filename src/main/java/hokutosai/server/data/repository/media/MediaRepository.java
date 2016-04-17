package hokutosai.server.data.repository.media;

import javax.transaction.Transactional;

import hokutosai.server.data.entity.media.Media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MediaRepository extends JpaRepository<Media, String> {

	@Modifying
	@Transactional
	@Query("UPDATE Media m SET m.url = :url WHERE m.mediaId = :mediaId")
	public void registUrl(@Param("mediaId") Integer mediaId, @Param("url") String url);

}
