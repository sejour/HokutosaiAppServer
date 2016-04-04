package hokutosai.server.controller;

import javax.servlet.ServletRequest;

import hokutosai.server.data.entity.shops.DetailedShop;
import hokutosai.server.data.entity.shops.ShopAssess;
import hokutosai.server.data.entity.shops.ShopScore;
import hokutosai.server.data.entity.shops.SimpleShop;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.data.repository.shops.DetailedShopRepository;
import hokutosai.server.data.repository.shops.ShopAssessRepository;
import hokutosai.server.data.repository.shops.ShopScoreRepository;
import hokutosai.server.data.repository.shops.SimpleShopRepository;
import hokutosai.server.error.InternalServerErrorException;
import hokutosai.server.error.InvalidParameterValueException;
import hokutosai.server.error.NotFoundException;
import hokutosai.server.security.ParamValidator;
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

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public Iterable<DetailedShop> getDetails() {
	    return this.detailedShopRepository.findAll();
	}

	@RequestMapping(value = "/details/{id:^[0-9]+$}", method = RequestMethod.GET)
	public DetailedShop getDetailsById(@PathVariable Integer id) throws NotFoundException {
		DetailedShop result = this.detailedShopRepository.findByShopId(id);
		if (result == null) throw new NotFoundException("The id is not used.");
		return result;
	}

	@RequestMapping(value = "/assess", method = RequestMethod.GET)
	public Iterable<ShopScore> getAssess() throws NotFoundException {
		return this.shopScoreRepository.findAll();
	}

	@RequestMapping(value = "/assess/{id:^[0-9]+$}", method = RequestMethod.GET)
	public ShopScore getAssessById(@PathVariable Integer id) throws NotFoundException {
		ShopScore result = this.shopScoreRepository.findByShopId(id);
		if (result == null) throw new NotFoundException("The id is not used.");
		return result;
	}

	private static final int SCORE_MIN = 1;
	private static final int SCORE_MAX = 5;

	@RequestMapping(value = "/assess/{id:^[0-9]+$}", method = RequestMethod.POST)
	public ShopScore postAssessWithId(
			ServletRequest request,
			@PathVariable("id") Integer shopId,
			@RequestParam("score") Integer score
		) throws NotFoundException, InvalidParameterValueException, InternalServerErrorException
	{
		ParamValidator.range("score", score, SCORE_MIN, SCORE_MAX);
		AuthorizedAccount account = RequestAttribute.getRequiredAccount(request);
		String accountId = account.getId();

		ShopAssess myAssess = this.shopAssessRepository.findByShopIdAndAccountId(shopId, accountId);

		if (myAssess == null) {
			this.shopAssessRepository.save(new ShopAssess(shopId, accountId, score));
			this.shopScoreRepository.assess(shopId, score);
		} else {
			this.shopAssessRepository.updateAssess(accountId, shopId, score);
			this.shopScoreRepository.reassess(shopId, score, myAssess.getScore());
		}

		ShopScore result = this.shopScoreRepository.findByShopId(shopId);
		if (result == null) throw new NotFoundException("The id is not used.");
		return result;
	}

}
