package hokutosai.server.data.repository.news;

import hokutosai.server.data.entity.news.SelectableNews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SelectableNewsRepository extends JpaRepository<SelectableNews, Integer>, JpaSpecificationExecutor<SelectableNews> {

}
