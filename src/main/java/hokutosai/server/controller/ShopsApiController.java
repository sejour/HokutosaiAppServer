package hokutosai.server.controller;

import hokutosai.server.data.entity.shops.DetailedShop;
import hokutosai.server.data.entity.shops.SimpleShop;
import hokutosai.server.data.repository.shops.DetailedShopRepository;
import hokutosai.server.data.repository.shops.SimpleShopRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/shops")
public class ShopsApiController {

	@Autowired
    private SimpleShopRepository simpleShopRepository;

	@Autowired
    private DetailedShopRepository detailedShopRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<SimpleShop> enumerate() {
	    return this.simpleShopRepository.findAll();
	}

	@RequestMapping(value = "{id:^[0-9]+$}", method = RequestMethod.GET)
	public SimpleShop byId(@PathVariable Integer id) {
		return this.simpleShopRepository.findByShopId(id);
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public Iterable<DetailedShop> detailsEnumerate() {
	    return this.detailedShopRepository.findAll();
	}

	@RequestMapping(value = "/details/{id:^[0-9]+$}", method = RequestMethod.GET)
	public DetailedShop detailsById(@PathVariable Integer id) {
		return this.detailedShopRepository.findByShopId(id);
	}

}
