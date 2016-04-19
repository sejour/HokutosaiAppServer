package hokutosai.server.data.repository.news;

import org.springframework.data.jpa.repository.JpaRepository;

import hokutosai.server.data.entity.news.TopicLink;

public interface TopicLinkRepository extends JpaRepository<TopicLink, Integer> {

}
