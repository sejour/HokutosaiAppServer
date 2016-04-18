package hokutosai.server.data.repository.exhibitions;

import org.springframework.data.jpa.repository.JpaRepository;

import hokutosai.server.data.entity.exhibitions.SimpleExhibition;

public interface SimpleExhibitionRepository  extends JpaRepository<SimpleExhibition, Integer> {

	public SimpleExhibition findByExhibitionId(Integer exhibitionId);
}
