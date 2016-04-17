package hokutosai.server.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import hokutosai.server.config.MediaConfiguration;
import hokutosai.server.data.entity.media.Media;
import hokutosai.server.data.entity.news.InsertableNews;
import hokutosai.server.data.entity.news.NewsLike;
import hokutosai.server.data.entity.news.SelectableNews;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.data.repository.media.MediaRepository;
import hokutosai.server.data.repository.news.InsertableNewsRepository;
import hokutosai.server.data.repository.news.NewsLikeRepository;
import hokutosai.server.data.repository.news.SelectableNewsRepository;
import hokutosai.server.error.BadRequestException;
import hokutosai.server.error.NotFoundException;
import hokutosai.server.security.ParamValidator;
import hokutosai.server.util.RequestAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/news")
public class NewsApiController {

	@Autowired
	private MediaConfiguration mediaConfig;

	@Autowired
	private InsertableNewsRepository insertableNewsRepository;

	@Autowired
	private SelectableNewsRepository selectableNewsRepository;

	@Autowired
	private NewsLikeRepository newsLikeRepository;

	@Autowired
	private MediaRepository mediaRepository;

	@RequestMapping(value = "/article", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public InsertableNews postArticle(@RequestBody @Valid InsertableNews news, Errors errors) throws BadRequestException {
		ParamValidator.checkErrors(errors);
		news.setDatetime(new Date());

		InsertableNews result = this.insertableNewsRepository.save(news);
		Integer newsId = result.getNewsId();

		List<Media> medias = news.getMedias();
		if (medias != null) {
			for (Media media: medias) {
				this.mediaRepository.link(media.getMediaId(), newsId);
			}
		}

		return result;
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/details", method = RequestMethod.GET)
	public SelectableNews getDetails(ServletRequest request, @PathVariable("id") Integer newsId) throws NotFoundException {
		SelectableNews news = this.selectableNewsRepository.findOne(newsId);
		if (news == null) throw new NotFoundException("The news is not exist.");

		AuthorizedAccount account = RequestAttribute.getAccount(request);

		if (account != null) {
			NewsLike like = this.newsLikeRepository.findByNewsIdAndAccountId(newsId, account.getId());
			news.setLiked(like != null);
		}

		return news;
	}

}
