package hokutosai.server.data.repository.news;

import hokutosai.server.data.entity.news.SelectableNews;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectableNewsRepository extends JpaRepository<SelectableNews, Integer> {

}
