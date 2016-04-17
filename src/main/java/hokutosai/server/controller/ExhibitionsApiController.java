package hokutosai.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hokutosai.server.data.entity.exhibitions.ExhibitionItem;
import hokutosai.server.data.entity.exhibitions.ExhibitionLike;
import hokutosai.server.data.entity.exhibitions.SimpleExhibition;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.data.repository.exhibitions.ExhibitionItemRepository;
import hokutosai.server.data.repository.exhibitions.ExhibitionLikeRepository;
import hokutosai.server.data.repository.exhibitions.SimpleExhibitionRepository;
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
	    for (SimpleExhibition shop: results) {
	    	shop.setLiked(likesMap.containsKey(shop.getExhibitionId()));
	    }

	    return results;
	}
}
