package hokutosai.server.data.repository.news;

import java.util.List;

import hokutosai.server.data.entity.news.NewsWithMedia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsWithMediaRepository extends JpaRepository<NewsWithMedia, Integer> {

	public List<NewsWithMedia> findByNewsIdOrderBySequenceAsc(Integer newsId);

}
