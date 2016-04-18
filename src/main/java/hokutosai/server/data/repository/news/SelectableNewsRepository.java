package hokutosai.server.data.repository.news;

import hokutosai.server.data.entity.news.InsertableNews;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectableNewsRepository extends JpaRepository<InsertableNews, Integer> {

}
