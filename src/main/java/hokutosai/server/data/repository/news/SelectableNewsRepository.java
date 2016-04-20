package hokutosai.server.data.repository.news;

import javax.transaction.Transactional;

import hokutosai.server.data.entity.news.SelectableNews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SelectableNewsRepository extends JpaRepository<SelectableNews, Integer>, JpaSpecificationExecutor<SelectableNews> {

	@Modifying
	@Transactional
	@Query("UPDATE SelectableNews n SET n.likesCount = n.likesCount + 1 WHERE n.newsId = :newsId")
	public void incrementLikesCount(@Param("newsId") Integer newsId);

	@Modifying
	@Transactional
	@Query("UPDATE SelectableNews n SET n.likesCount = n.likesCount - 1 WHERE n.newsId = :newsId")
	public void decrementLikesCount(@Param("newsId") Integer newsId);

}
