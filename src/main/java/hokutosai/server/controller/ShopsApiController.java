package hokutosai.server.controller;

import java.util.Date;

import javax.servlet.ServletRequest;

import hokutosai.server.data.entity.AssessedScore;
import hokutosai.server.data.entity.shops.DetailedShop;
import hokutosai.server.data.entity.shops.ShopAssess;
import hokutosai.server.data.entity.shops.ShopScore;
import hokutosai.server.data.entity.shops.SimpleShop;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.data.json.shops.ShopAssessmentResponse;
import hokutosai.server.data.repository.shops.DetailedShopRepository;
import hokutosai.server.data.repository.shops.ShopAssessRepository;
import hokutosai.server.data.repository.shops.ShopScoreRepository;
import hokutosai.server.data.repository.shops.SimpleShopRepository;
import hokutosai.server.error.InternalServerErrorException;
import hokutosai.server.error.InvalidParameterValueException;
import hokutosai.server.error.NotFoundException;
import hokutosai.server.util.RequestAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
	private ShopScoreRepository shopScoreRepository;

	@Autowired
	private ShopAssessRepository shopAssessRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<SimpleShop> get() {
	    return this.simpleShopRepository.findAll();
	}

	@RequestMapping(value = "{id:^[0-9]+$}", method = RequestMethod.GET)
	public SimpleShop getById(@PathVariable Integer id) throws NotFoundException {
		SimpleShop result = this.simpleShopRepository.findByShopId(id);
		if (result == null) throw new NotFoundException("The id is not used.");
		return result;
	}

	@RequestMapping(value = "/details/{id:^[0-9]+$}", method = RequestMethod.GET)
	public DetailedShop getDetailsById(ServletRequest request, @PathVariable("id") Integer shopId) throws NotFoundException {
		DetailedShop result = this.detailedShopRepository.findByShopId(shopId);
		if (result == null) throw new NotFoundException("The id is not used.");

		AuthorizedAccount account = RequestAttribute.getAccount(request);
		if (account != null) {
			ShopAssess myAssessment = this.shopAssessRepository.findByShopIdAndAccountId(shopId, account.getId());
			result.setMyAssessment(myAssessment);
		}

		return result;
	}

	@RequestMapping(value = "/assess/{id:^[0-9]+$}", method = RequestMethod.GET)
	public ShopScore getAssessById(@PathVariable Integer id) throws NotFoundException {
		ShopScore result = this.shopScoreRepository.findByShopId(id);
		if (result == null) throw new NotFoundException("The id is not used.");
		return result;
	}

	@RequestMapping(value = "/assess/{id:^[0-9]+$}", method = RequestMethod.POST)
	public ShopAssessmentResponse postAssessWithId(
			ServletRequest request,
			@PathVariable("id") Integer shopId,
			@RequestParam("score") Integer score,
			@RequestParam("comment") String comment
		) throws NotFoundException, InvalidParameterValueException, InternalServerErrorException
	{
		if (!this.simpleShopRepository.exists(shopId)) throw new NotFoundException("The id is not used.");

		AuthorizedAccount account = RequestAttribute.getRequiredAccount(request);
		String accountId = account.getId();

		ShopAssess newAssessment = new ShopAssess(shopId, accountId, new Date(), score, comment);
		ShopAssess oldAssessment = this.shopAssessRepository.findByShopIdAndAccountId(shopId, accountId);

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

}
