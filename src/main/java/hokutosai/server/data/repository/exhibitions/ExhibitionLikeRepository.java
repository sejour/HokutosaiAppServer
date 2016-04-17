package hokutosai.server.data.repository.exhibitions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hokutosai.server.data.entity.exhibitions.ExhibitionLike;

public interface ExhibitionLikeRepository extends JpaRepository<ExhibitionLike, Integer> {

	public List<ExhibitionLike> findByAccountId(String accountId);

	public ExhibitionLike findByExhibitionIdAndAccountId(Integer shopId, String accountId);
}
