package hokutosai.server.data.repository.exhibitions;

import org.springframework.data.jpa.repository.JpaRepository;

import hokutosai.server.data.entity.exhibitions.ExhibitionItem;

public interface ExhibitionItemRepository extends JpaRepository<ExhibitionItem, Integer>{

}
