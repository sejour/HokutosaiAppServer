package hokutosai.server.data.repository.exhibitions;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hokutosai.server.data.entity.exhibitions.DetailedExhibition;

public interface DetailedExhibitionRepository extends JpaRepository<DetailedExhibition, Integer>{

	public DetailedExhibition findByExhibitionId(Integer exhibitionId);

	@Modifying
	@Transactional
	@Query("UPDATE DetailedExhibition e SET e.likesCount = e.likesCount + 1 WHERE e.exhibitionId = :exhibitionId")
	public void incrementLikesCount(@Param("exhibitionId") Integer exhibitionId);

	@Modifying
	@Transactional
	@Query("UPDATE DetailedExhibition e SET e.likesCount = e.likesCount - 1 WHERE e.exhibitionId = :exhibitionId")
	public void decrementLikesCount(@Param("exhibitionId") Integer exhibitionId);
}
