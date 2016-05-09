package hokutosai.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import hokutosai.server.data.entity.account.SecureAccount;
import hokutosai.server.data.entity.assessments.Assess;
import hokutosai.server.data.entity.assessments.AssessedScore;
import hokutosai.server.data.entity.shops.DetailedShop;
import hokutosai.server.data.entity.shops.ShopAssess;
import hokutosai.server.data.entity.shops.ShopAssessmentReport;
import hokutosai.server.data.entity.shops.ShopItem;
import hokutosai.server.data.entity.shops.ShopLike;
import hokutosai.server.data.entity.shops.SimpleShop;
import hokutosai.server.data.json.StatusResponse;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.data.json.shops.ShopAssessmentList;
import hokutosai.server.data.json.shops.ShopAssessmentResponse;
import hokutosai.server.data.json.shops.ShopLikeResult;
import hokutosai.server.data.repository.shops.DetailedShopRepository;
import hokutosai.server.data.repository.shops.ShopAssessRepository;
import hokutosai.server.data.repository.shops.ShopAssessmentReportRepository;
import hokutosai.server.data.repository.shops.ShopItemRepository;
import hokutosai.server.data.repository.shops.ShopLikeRepository;
import hokutosai.server.data.repository.shops.ShopScoreRepository;
import hokutosai.server.data.repository.shops.SimpleShopRepository;
import hokutosai.server.error.InternalServerErrorException;
import hokutosai.server.error.InvalidParameterValueException;
import hokutosai.server.error.NotFoundException;
import hokutosai.server.security.ParamValidator;
import hokutosai.server.util.RequestAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/shops")
public class ShopsApiController {

	@Autowired
    private SimpleShopRepository simpleShopRepository;

	@Autowired
    private DetailedShopRepository detailedShopRepository;

	@Autowired
	private ShopItemRepository shopItemRepository;

	@Autowired
	private ShopScoreRepository shopScoreRepository;

	@Autowired
	private ShopAssessRepository shopAssessRepository;

	@Autowired
	private ShopLikeRepository shopLikeRepository;

	@Autowired
	private ShopAssessmentReportRepository shopAssessmentReportRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<SimpleShop> get(ServletRequest request) {
	    List<SimpleShop> results = this.simpleShopRepository.findAll();

	    AuthorizedAccount account = RequestAttribute.getAccount(request);
		if (account == null) return results;

	    List<ShopLike> likes = this.shopLikeRepository.findByAccountId(account.getId());
	    Map<Integer, ShopLike> likesMap = new HashMap<Integer, ShopLike>();

	    for (ShopLike like: likes) {
	    	likesMap.put(like.getShopId(), like);
	    }
	    for (SimpleShop shop: results) {
	    	shop.setLiked(likesMap.containsKey(shop.getShopId()));
	    }

	    return results;
	}

	@RequestMapping(value = "/enumeration", method = RequestMethod.GET)
	public List<ShopItem> getEnumeration() {
		return this.shopItemRepository.findAll();
	}

	@RequestMapping(value = "/{id:^[0-9]+$}", method = RequestMethod.GET)
	public SimpleShop getById(ServletRequest request, @PathVariable("id") Integer shopId) throws NotFoundException {
		SimpleShop result = this.simpleShopRepository.findOne(shopId);
		if (result == null) throw new NotFoundException("The id is not used.");

		AuthorizedAccount account = RequestAttribute.getAccount(request);
		if (account != null) {
			ShopLike like = this.shopLikeRepository.findByShopIdAndAccountId(shopId, account.getId());
			result.setLiked(like != null);
		}

		return result;
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/details", method = RequestMethod.GET)
	public DetailedShop getDetails(ServletRequest request, @PathVariable("id") Integer shopId) throws NotFoundException {
		DetailedShop result = this.detailedShopRepository.findByShopId(shopId);
		if (result == null) throw new NotFoundException("The id is not used.");

		AuthorizedAccount account = RequestAttribute.getAccount(request);
		if (account != null) {
			ShopAssess myAssessment = this.shopAssessRepository.findByShopIdAndAccountId(shopId, account.getId());
			result.setMyAssessment(myAssessment);
			ShopLike like = this.shopLikeRepository.findByShopIdAndAccountId(shopId, account.getId());
			result.setLiked(like != null);
		}

		return result;
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/assessments", method = RequestMethod.GET)
	public ShopAssessmentList getAssessments(ServletRequest request, @PathVariable("id") Integer shopId) throws NotFoundException {
		if (!this.simpleShopRepository.exists(shopId)) throw new NotFoundException("The id is not used.");

		List<ShopAssess> assessments = this.shopAssessRepository.findByShopId(shopId);

		Assess myAssessment = null;
		AuthorizedAccount account = RequestAttribute.getAccount(request);
		if (account != null) {
			myAssessment = this.shopAssessRepository.findByShopIdAndAccountId(shopId, account.getId());
		}

		return new ShopAssessmentList(shopId, assessments, myAssessment);
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/assessment", method = RequestMethod.POST)
	public ShopAssessmentResponse postAssessment(
			ServletRequest request,
			@PathVariable("id") Integer shopId,
			@RequestParam("score") Integer score,
			@RequestParam("comment") String comment
		) throws NotFoundException, InvalidParameterValueException, InternalServerErrorException
	{
		if (!this.simpleShopRepository.exists(shopId)) throw new NotFoundException("The id is not used.");

		AuthorizedAccount account = RequestAttribute.getRequiredAccount(request);

		ShopAssess newAssessment = new ShopAssess(shopId, new SecureAccount(account), new Date(), score, comment);
		ShopAssess oldAssessment = this.shopAssessRepository.findByShopIdAndAccountId(shopId, account.getId());

		if (oldAssessment == null) {
			this.shopAssessRepository.save(newAssessment);
			this.shopScoreRepository.assess(shopId, score);
		} else {
			this.shopAssessRepository.updateAssess(newAssessment.getAccountId(), newAssessment.getShopId(), newAssessment.getDatetime(), newAssessment.getScore(), newAssessment.getComment());
			this.shopScoreRepository.reassess(shopId, score, oldAssessment.getScore());
		}

		AssessedScore aggregate = this.shopScoreRepository.findByShopId(shopId);

		return new ShopAssessmentResponse(shopId, newAssessment, aggregate);
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/assessment", method = RequestMethod.DELETE)
	public ShopAssessmentResponse deleteAssessment(ServletRequest request, @PathVariable("id") Integer shopId) throws NotFoundException, InternalServerErrorException {
		if (!this.simpleShopRepository.exists(shopId)) throw new NotFoundException("The id is not used.");

		AuthorizedAccount account = RequestAttribute.getRequiredAccount(request);

		ShopAssess assessment = this.shopAssessRepository.findByShopIdAndAccountId(shopId, account.getId());

		if (assessment != null) {
			this.shopScoreRepository.cancelAssess(shopId, assessment.getScore());
			this.shopAssessRepository.delete(assessment.getId());
		}

		AssessedScore aggregate = this.shopScoreRepository.findByShopId(shopId);

		return new ShopAssessmentResponse(shopId, null, aggregate);
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/likes", method = RequestMethod.POST)
	public ShopLikeResult postLikes(ServletRequest request, @PathVariable("id") Integer shopId) throws NotFoundException, InternalServerErrorException {
		SimpleShop shop = this.simpleShopRepository.findByShopId(shopId);
		if (shop == null) throw new NotFoundException("The id is not used.");

		String accountId = RequestAttribute.getRequiredAccount(request).getId();

		if (this.shopLikeRepository.findByShopIdAndAccountId(shopId, accountId) == null) {
			this.shopLikeRepository.save(new ShopLike(shopId, accountId));
			this.detailedShopRepository.incrementLikesCount(shopId);
			shop.setLikesCount(shop.getLikesCount() + 1);
		}

		ShopLikeResult result = new ShopLikeResult(shop);
		result.setLiked(true);
		return result;
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/likes", method = RequestMethod.DELETE)
	public ShopLikeResult deleteLikes(ServletRequest request, @PathVariable("id") Integer shopId) throws NotFoundException, InternalServerErrorException {
		SimpleShop shop = this.simpleShopRepository.findByShopId(shopId);
		if (shop == null) throw new NotFoundException("The id is not used.");

		String accountId = RequestAttribute.getRequiredAccount(request).getId();

		ShopLike like = this.shopLikeRepository.findByShopIdAndAccountId(shopId, accountId);
		if (like != null) {
			this.shopLikeRepository.delete(like.getId());
			this.detailedShopRepository.decrementLikesCount(shopId);
			shop.setLikesCount(shop.getLikesCount() - 1);
		}

		ShopLikeResult result = new ShopLikeResult(shop);
		result.setLiked(false);
		return result;
	}

	@RequestMapping(value = "/assessment/{id:^[0-9]+$}/report", method = RequestMethod.POST)
	public StatusResponse postAssessmentReport(ServletRequest request, @PathVariable("id") Integer assessmentId, @RequestParam("cause") String cause) throws NotFoundException, InvalidParameterValueException {
		String causeId = ParamValidator.text("cause", cause);

		AuthorizedAccount account = RequestAttribute.getAccount(request);
		String accountId = account == null ? null : account.getId();

		ShopAssessmentReport report = new ShopAssessmentReport(assessmentId, causeId, accountId);
		this.shopAssessmentReportRepository.save(report);

		return new StatusResponse(HttpStatus.OK);
	}

}
