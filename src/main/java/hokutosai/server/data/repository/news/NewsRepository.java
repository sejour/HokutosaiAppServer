package hokutosai.server.data.repository.news;

import hokutosai.server.data.entity.news.News;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Integer> {

}
