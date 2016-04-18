package hokutosai.server.data.repository.exhibitions;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hokutosai.server.data.entity.exhibitions.ExhibitionScore;

public interface ExhibitionScoreRepository extends JpaRepository<ExhibitionScore, Integer>{

	public ExhibitionScore findByExhibitionId(Integer exhibitionId);

	@Modifying
	@Transactional
	@Query("UPDATE ExhibitionScore s SET s.assessedCount = s.assessedCount + 1, s.totalScore = s.totalScore + :assessedScore WHERE s.exhibitionId = :exhibitionId")
	public void assess(@Param("exhibitionId") Integer exhibitionId, @Param("assessedScore") Integer score);


	@Modifying
	@Transactional
	@Query("UPDATE ExhibitionScore s SET s.totalScore = s.totalScore - :previousScore + :assessedScore WHERE s.exhibitionId = :exhibitionId")
	public void reassess(@Param("exhibitionId") Integer exhibitionId, @Param("assessedScore") Integer score, @Param("previousScore") Integer previousScore);

	@Modifying
	@Transactional
	@Query("UPDATE ExhibitionScore s SET s.assessedCount = s.assessedCount - 1, s.totalScore = s.totalScore - :cancelScore WHERE s.exhibitionId = :exhibitionId")
	public void cancelAssess(@Param("exhibitionId") Integer exhibitionId, @Param("cancelScore") Integer cancelScore);
}
