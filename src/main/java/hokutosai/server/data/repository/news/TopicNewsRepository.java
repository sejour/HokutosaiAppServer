package hokutosai.server.data.repository.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hokutosai.server.data.entity.news.TopicNews;

public interface TopicNewsRepository extends JpaRepository<TopicNews, Integer>, JpaSpecificationExecutor<TopicNews> {

}
