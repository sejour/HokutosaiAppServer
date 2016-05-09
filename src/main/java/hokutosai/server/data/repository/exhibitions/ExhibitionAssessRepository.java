package hokutosai.server.data.repository.exhibitions;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hokutosai.server.data.entity.exhibitions.ExhibitionAssess;

public interface ExhibitionAssessRepository extends JpaRepository<ExhibitionAssess, Integer>{

	public ExhibitionAssess findByExhibitionIdAndAccountId(Integer exhibitionId, String accountId);

	@Query("SELECT a FROM ExhibitionAssess a WHERE a.exhibitionId = :exhibitionId ORDER BY a.id DESC")
	public List<ExhibitionAssess> findByExhibitionId(@Param("exhibitionId") Integer exhibitionId);

	@Modifying
	@Transactional
	@Query("UPDATE ExhibitionAssess a SET a.datetime = :datetime, a.score = :score, a.comment = :comment WHERE a.accountId = :accountId AND a.exhibitionId = :exhibitionId")
	public void updateAssess(@Param("accountId") String accountId, @Param("exhibitionId") Integer exhibitionId, @Param("datetime") Date datetime, @Param("score") Integer score, @Param("comment") String comment);
}
