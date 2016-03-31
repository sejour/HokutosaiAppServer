package hokutosai.server.controller;

import hokutosai.server.data.entity.shops.DetailedShop;
import hokutosai.server.data.entity.shops.ShopScore;
import hokutosai.server.data.entity.shops.SimpleShop;
import hokutosai.server.data.repository.shops.DetailedShopRepository;
import hokutosai.server.data.repository.shops.ShopScoreRepository;
import hokutosai.server.data.repository.shops.SimpleShopRepository;
import hokutosai.server.error.BadRequestException;
import hokutosai.server.error.NotFoundException;

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

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<SimpleShop> enumerate() {
	    return this.simpleShopRepository.findAll();
	}

	@RequestMapping(value = "{id:^[0-9]+$}", method = RequestMethod.GET)
	public SimpleShop byId(@PathVariable Integer id) throws NotFoundException {
		SimpleShop result = this.simpleShopRepository.findByShopId(id);
		if (result == null) throw new NotFoundException("The id is not used.");
		return result;
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public Iterable<DetailedShop> detailsEnumerate() {
	    return this.detailedShopRepository.findAll();
	}

	@RequestMapping(value = "/details/{id:^[0-9]+$}", method = RequestMethod.GET)
	public DetailedShop detailsById(@PathVariable Integer id) throws NotFoundException {
		DetailedShop result = this.detailedShopRepository.findByShopId(id);
		if (result == null) throw new NotFoundException("The id is not used.");
		return result;
	}

	@RequestMapping(value = "/assess/{id:^[0-9]+$}", method = RequestMethod.POST)
	public ShopScore assess(
			@PathVariable Integer id,
			@RequestParam("score") Integer score,
			@RequestParam(value = "previous_score", required = false) Integer previousScore
		) throws NotFoundException
	{
		if (previousScore == null) {
			this.shopScoreRepository.assess(id, score);
		} else {
			this.shopScoreRepository.reassess(id, score, previousScore);
		}

		ShopScore result = this.shopScoreRepository.findByShopId(id);
		if (result == null) throw new NotFoundException("The id is not used.");
		return result;
	}

}
