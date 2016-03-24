package hokutosai.server.controller;

import hokutosai.server.data.entity.shops.DetailedShop;
import hokutosai.server.data.entity.shops.SimpleShop;
import hokutosai.server.data.repository.shops.DetailedShopRepository;
import hokutosai.server.data.repository.shops.SimpleShopRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/shops")
public class ShopsApiController {

	@Autowired
    private SimpleShopRepository shopRepository;

	@Autowired
    private DetailedShopRepository detailedShopRepository;

	 @RequestMapping(value = "/list", method = RequestMethod.GET)
	 public Iterable<SimpleShop> list() {
	     return this.shopRepository.findAll();
	 }

	 @RequestMapping(value = "/list/detail", method = RequestMethod.GET)
	 public Iterable<DetailedShop> listDetail() {
	     return this.detailedShopRepository.findAll();
	 }

}
