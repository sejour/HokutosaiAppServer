package hokutosai.server.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import hokutosai.server.util.DatetimeConverter;
import hokutosai.server.util.RequestAttribute;
import static hokutosai.server.data.specification.SelectableNewsSpecifications.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@Autowired
	private DatetimeConverter datetimeConverter;

	@RequestMapping(value = "/article", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public InsertableNews postArticle(@RequestBody @Valid InsertableNews news, Errors errors) throws Throwable {
		ParamValidator.checkErrors(errors);
		news.setDatetime(new Date());

		InsertableNews result = this.insertableNewsRepository.save(news);
		Integer newsId = result.getNewsId();

		try {
			List<Media> medias = news.getMedias();
			if (medias != null) {
				for (Media media: medias) {
					String mediaId = media.getMediaId();
					Media target = this.mediaRepository.findOne(mediaId);
					if (target == null) throw new BadRequestException(String.format("The media has not been uploaded. [media_id=%s]", mediaId));
					if (target.getNewsId() != null) throw new BadRequestException(String.format("The media is already linked another news. [media_id=%s]", mediaId));
					this.mediaRepository.link(mediaId, newsId);
				}
			}
		} catch (Throwable e) {
			this.insertableNewsRepository.delete(newsId);
			throw new Throwable(e);
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

	@RequestMapping(value = "/timeline", method = RequestMethod.GET)
	public List<SelectableNews> getTimeline(ServletRequest request,
			@RequestParam(value = "since_id", required = false) Integer sinceId,
			@RequestParam(value = "last_id", required = false) Integer lastId,
			@RequestParam(value = "since_datetime", required = false) String sinceDatetimeStr,
			@RequestParam(value = "last_datetime", required = false) String lastDatetimeStr,
			@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "filter_event_id", required = false) Integer filterEventId,
			@RequestParam(value = "filter_shop_id", required = false) Integer filterShopId,
			@RequestParam(value = "filter_exhibition_id", required = false) Integer filterExhibitionId
	) throws ParseException {
		Date sinceDatetime = sinceDatetimeStr == null ? null : this.datetimeConverter.stringToDate(sinceDatetimeStr);
		Date lastDatetime = lastDatetimeStr == null ? null : this.datetimeConverter.stringToDate(lastDatetimeStr);

		List<SelectableNews> results = this.selectableNewsRepository.findAll(Specifications
					.where(laterThanNewsId(sinceId))
					.and(earlierThanNewsId(lastId))
					.and(filterByEventId(filterEventId))
					.and(filterByShopId(filterShopId))
					.and(filterByExhibitionId(filterExhibitionId))
				);

		AuthorizedAccount account = RequestAttribute.getAccount(request);
		if (account != null) {
			this.setNewsLiked(account.getId(), results);
		}

		return results;
	}

	private void setNewsLiked(String accountId, List<SelectableNews> results) {
		List<NewsLike> likes = this.newsLikeRepository.findByAccountId(accountId);
	    Map<Integer, NewsLike> likesMap = new HashMap<Integer, NewsLike>();

	    for (NewsLike like: likes) {
	    	likesMap.put(like.getNewsId(), like);
	    }
	    for (SelectableNews result: results) {
	    	result.setLiked(likesMap.containsKey(result.getNewsId()));
	    }
	}

}
