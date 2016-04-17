package hokutosai.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hokutosai.server.data.entity.AssessedScore;
import hokutosai.server.data.entity.account.SecureAccount;
import hokutosai.server.data.entity.exhibitions.DetailedExhibition;
import hokutosai.server.data.entity.exhibitions.ExhibitionAssess;
import hokutosai.server.data.entity.exhibitions.ExhibitionItem;
import hokutosai.server.data.entity.exhibitions.ExhibitionLike;
import hokutosai.server.data.entity.exhibitions.SimpleExhibition;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.data.json.exhibitions.ExhibitionAssessmentResponse;
import hokutosai.server.data.repository.exhibitions.DetailedExhibitionRepository;
import hokutosai.server.data.repository.exhibitions.ExhibitionAssessRepository;
import hokutosai.server.data.repository.exhibitions.ExhibitionItemRepository;
import hokutosai.server.data.repository.exhibitions.ExhibitionLikeRepository;
import hokutosai.server.data.repository.exhibitions.ExhibitionScoreRepository;
import hokutosai.server.data.repository.exhibitions.SimpleExhibitionRepository;
import hokutosai.server.error.InternalServerErrorException;
import hokutosai.server.error.InvalidParameterValueException;
import hokutosai.server.error.NotFoundException;
import hokutosai.server.util.RequestAttribute;

@RestController
@EnableAutoConfiguration
@RequestMapping("/exhibitions")
public class ExhibitionsApiController {

	@Autowired
	private ExhibitionItemRepository exhibitionItemRepository;

	@Autowired
    private SimpleExhibitionRepository simpleExhibitionRepository;

	@Autowired
	private ExhibitionLikeRepository exhibitionLikeRepository;

	@Autowired
	private DetailedExhibitionRepository detailedExhibitionRepository;

	@Autowired
	private ExhibitionAssessRepository exhibitionAssessRepository;

	@Autowired
	ExhibitionAssessmentResponse exhibitionAssessmentResponse;

	@Autowired
	ExhibitionScoreRepository exhibitionScoreRepository;

	@RequestMapping(value = "/enumeration", method = RequestMethod.GET)
	public List<ExhibitionItem> getEnumeration() {
		return this.exhibitionItemRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<SimpleExhibition> get(ServletRequest request) {
	    List<SimpleExhibition> results = this.simpleExhibitionRepository.findAll();

	    AuthorizedAccount account = RequestAttribute.getAccount(request);
		if (account == null) return results;

	    List<ExhibitionLike> likes = this.exhibitionLikeRepository.findByAccountId(account.getId());
	    Map<Integer,ExhibitionLike> likesMap = new HashMap<Integer, ExhibitionLike>();

	    for (ExhibitionLike like: likes) {
	    	likesMap.put(like.getExhibitionId(), like);
	    }
	    for (SimpleExhibition exhibition: results) {
	    	exhibition.setLiked(likesMap.containsKey(exhibition.getExhibitionId()));
	    }

	    return results;
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/details", method = RequestMethod.GET)
	public DetailedExhibition getDetails(ServletRequest request, @PathVariable("id") Integer exhibitionId) throws NotFoundException {
		DetailedExhibition result = this.detailedExhibitionRepository.findByExhibitionId(exhibitionId);
		if (result == null) throw new NotFoundException("The id is not used.");

		AuthorizedAccount account = RequestAttribute.getAccount(request);
		if (account != null) {
			ExhibitionAssess myAssessment = this.exhibitionAssessRepository.findByExhibitionIdAndAccountId(exhibitionId, account.getId());
			result.setMyAssessment(myAssessment);
			ExhibitionLike like = this.exhibitionLikeRepository.findByExhibitionIdAndAccountId(exhibitionId, account.getId());
			result.setLiked(like != null);
		}

		return result;
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/assessment", method = RequestMethod.POST)
	public ExhibitionAssessmentResponse postAssessment(
			ServletRequest request,
			@PathVariable("id") Integer exhibitionId,
			@RequestParam("score") Integer score,
			@RequestParam("comment") String comment
		) throws NotFoundException, InvalidParameterValueException, InternalServerErrorException
	{
		if (!this.simpleExhibitionRepository.exists(exhibitionId)) throw new NotFoundException("The id is not used.");

		AuthorizedAccount account = RequestAttribute.getRequiredAccount(request);

		ExhibitionAssess newAssessment = new ExhibitionAssess(exhibitionId, new SecureAccount(account), new Date(), score, comment);
		ExhibitionAssess oldAssessment = this.exhibitionAssessRepository.findByExhibitionIdAndAccountId(exhibitionId, account.getId());

		if (oldAssessment == null) {
			this.exhibitionAssessRepository.save(newAssessment);
			this.exhibitionScoreRepository.assess(exhibitionId, score);
		} else {
			this.exhibitionAssessRepository.updateAssess(newAssessment.getAccountId(), newAssessment.getExhibitionId(), newAssessment.getDatetime(), newAssessment.getScore(), newAssessment.getComment());
			this.exhibitionScoreRepository.reassess(exhibitionId, score, oldAssessment.getScore());
		}

		AssessedScore aggregate = this.exhibitionScoreRepository.findByExhibitionId(exhibitionId);

		return new ExhibitionAssessmentResponse(exhibitionId, newAssessment, aggregate);
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/assessment", method = RequestMethod.DELETE)
	public ExhibitionAssessmentResponse deleteAssessment(ServletRequest request, @PathVariable("id") Integer exhibitionId) throws NotFoundException, InternalServerErrorException {
		if (!this.simpleExhibitionRepository.exists(exhibitionId)) throw new NotFoundException("The id is not used.");

		AuthorizedAccount account = RequestAttribute.getRequiredAccount(request);

		ExhibitionAssess assessment = this.exhibitionAssessRepository.findByExhibitionIdAndAccountId(exhibitionId, account.getId());

		if (assessment != null) {
			this.exhibitionScoreRepository.cancelAssess(exhibitionId, assessment.getScore());
			this.exhibitionAssessRepository.delete(assessment.getId());
		}

		AssessedScore aggregate = this.exhibitionScoreRepository.findByExhibitionId(exhibitionId);

		return new ExhibitionAssessmentResponse(exhibitionId, null, aggregate);
	}
}
