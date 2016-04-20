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
	@Query("UPDATE Media m SET m.newsId = :newsId WHERE m.mediaId = :mediaId")
	public void link(@Param("mediaId") String mediaId, @Param("newsId") Integer newsId);

	@Modifying
    @Transactional
    @Query("DELETE FROM Media m WHERE m.newsId = :newsId")
	public void deleteByNewsId(@Param("newsId") Integer newsId);
	
}
