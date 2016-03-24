package hokutosai.server.controller;

import hokutosai.server.data.entity.Shop;
import hokutosai.server.data.repository.ShopRepository;

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
    private ShopRepository shopRepository;

	 @RequestMapping(value = "/list", method = RequestMethod.GET)
	 public Iterable<Shop> list() {
	     return shopRepository.findAll();
	 }

}
