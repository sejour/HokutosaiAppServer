package hokutosai.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hokutosai.server.data.entity.account.SecureAccount;
import hokutosai.server.data.entity.assessments.AssessedScore;
import hokutosai.server.data.entity.exhibitions.DetailedExhibition;
import hokutosai.server.data.entity.exhibitions.ExhibitionAssess;
import hokutosai.server.data.entity.exhibitions.ExhibitionAssessmentReport;
import hokutosai.server.data.entity.exhibitions.ExhibitionItem;
import hokutosai.server.data.entity.exhibitions.ExhibitionLike;
import hokutosai.server.data.entity.exhibitions.SimpleExhibition;
import hokutosai.server.data.json.StatusResponse;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.data.json.exhibitions.ExhibitionAssessmentResponse;
import hokutosai.server.data.json.exhibitions.ExhibitionLikeResult;
import hokutosai.server.data.repository.exhibitions.DetailedExhibitionRepository;
import hokutosai.server.data.repository.exhibitions.ExhibitionAssessRepository;
import hokutosai.server.data.repository.exhibitions.ExhibitionAssessmentReportRepository;
import hokutosai.server.data.repository.exhibitions.ExhibitionItemRepository;
import hokutosai.server.data.repository.exhibitions.ExhibitionLikeRepository;
import hokutosai.server.data.repository.exhibitions.ExhibitionScoreRepository;
import hokutosai.server.data.repository.exhibitions.SimpleExhibitionRepository;
import hokutosai.server.error.InternalServerErrorException;
import hokutosai.server.error.InvalidParameterValueException;
import hokutosai.server.error.NotFoundException;
import hokutosai.server.security.ParamValidator;
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
	private ExhibitionScoreRepository exhibitionScoreRepository;

	@Autowired
	private ExhibitionAssessmentReportRepository exhibitionAssessmentReportRepository;

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

	@RequestMapping(value = "/{id:^[0-9]+$}/likes", method = RequestMethod.POST)
	public ExhibitionLikeResult postLikes(ServletRequest request, @PathVariable("id") Integer exhibitionId) throws NotFoundException, InternalServerErrorException {
		SimpleExhibition exhibition = this.simpleExhibitionRepository.findByExhibitionId(exhibitionId);
		if (exhibition == null) throw new NotFoundException("The id is not used.");

		String accountId = RequestAttribute.getRequiredAccount(request).getId();

		if (this.exhibitionLikeRepository.findByExhibitionIdAndAccountId(exhibitionId, accountId) == null) {
			this.exhibitionLikeRepository.save(new ExhibitionLike(exhibitionId, accountId));
			this.detailedExhibitionRepository.incrementLikesCount(exhibitionId);
			exhibition.setLikesCount(exhibition.getLikesCount() + 1);
		}

		ExhibitionLikeResult result = new ExhibitionLikeResult(exhibition);
		result.setLiked(true);
		return result;
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/likes", method = RequestMethod.DELETE)
	public ExhibitionLikeResult deleteLikes(ServletRequest request, @PathVariable("id") Integer exhibitionId) throws NotFoundException, InternalServerErrorException {
		SimpleExhibition exhibition = this.simpleExhibitionRepository.findByExhibitionId(exhibitionId);
		if (exhibition == null) throw new NotFoundException("The id is not used.");

		String accountId = RequestAttribute.getRequiredAccount(request).getId();

		ExhibitionLike like = this.exhibitionLikeRepository.findByExhibitionIdAndAccountId(exhibitionId, accountId);
		if (like != null) {
			this.exhibitionLikeRepository.delete(like.getId());
			this.detailedExhibitionRepository.decrementLikesCount(exhibitionId);
			exhibition.setLikesCount(exhibition.getLikesCount() - 1);
		}

		ExhibitionLikeResult result = new ExhibitionLikeResult(exhibition);
		result.setLiked(false);
		return result;
	}

	@RequestMapping(value = "/assessment/{id:^[0-9]+$}/report", method = RequestMethod.GET)
	public StatusResponse postAssessmentReport(ServletRequest request, @PathVariable("id") Integer assessmentId, @RequestParam("cause") String cause) throws NotFoundException, InvalidParameterValueException {
		String causeId = ParamValidator.text("cause", cause);

		AuthorizedAccount account = RequestAttribute.getAccount(request);
		String accountId = account == null ? null : account.getId();

		ExhibitionAssessmentReport report = new ExhibitionAssessmentReport(assessmentId, causeId, accountId);
		this.exhibitionAssessmentReportRepository.save(report);

		return new StatusResponse(HttpStatus.OK);
	}

}
