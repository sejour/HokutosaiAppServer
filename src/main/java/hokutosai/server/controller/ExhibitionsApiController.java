package hokutosai.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hokutosai.server.data.entity.exhibitions.ExhibitionItem;
import hokutosai.server.data.repository.exhibitions.ExhibitionItemRepository;

@RestController
@EnableAutoConfiguration
@RequestMapping("/exhibitions")
public class ExhibitionsApiController {

	@Autowired
	private ExhibitionItemRepository exhibitionItemRepository;

	@RequestMapping(value = "/enumeration", method = RequestMethod.GET)
	public List<ExhibitionItem> getEnumeration() {
		return this.exhibitionItemRepository.findAll();
	}
}
