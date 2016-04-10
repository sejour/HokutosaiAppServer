package hokutosai.server.data.repository.news;

import hokutosai.server.data.entity.news.NewsWithMedia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsWithMediasRepository extends JpaRepository<NewsWithMedia, Integer> {

}
