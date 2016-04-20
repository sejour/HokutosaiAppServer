package hokutosai.server.data.repository.news;

import java.util.List;

import hokutosai.server.data.entity.news.NewsLike;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsLikeRepository extends JpaRepository<NewsLike, Integer> {

	public List<NewsLike> findByAccountId(String accountId);

	public NewsLike findByNewsIdAndAccountId(Integer newsId, String accountId);

}
