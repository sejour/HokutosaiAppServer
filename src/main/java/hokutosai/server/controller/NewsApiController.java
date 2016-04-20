package hokutosai.server.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import hokutosai.server.data.entity.events.TopicEvent;
import hokutosai.server.data.entity.media.Media;
import hokutosai.server.data.entity.news.ConstantlyTopic;
import hokutosai.server.data.entity.news.InsertableNews;
import hokutosai.server.data.entity.news.NewsLike;
import hokutosai.server.data.entity.news.SelectableNews;
import hokutosai.server.data.entity.news.TopicNews;
import hokutosai.server.data.json.StatusResponse;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.data.json.news.NewsLikeResult;
import hokutosai.server.data.json.news.Topic;
import hokutosai.server.data.repository.events.TopicEventRepository;
import hokutosai.server.data.repository.media.MediaRepository;
import hokutosai.server.data.repository.news.ConstantlyTopicRepository;
import hokutosai.server.data.repository.news.InsertableNewsRepository;
import hokutosai.server.data.repository.news.NewsLikeRepository;
import hokutosai.server.data.repository.news.SelectableNewsRepository;
import hokutosai.server.data.repository.news.TopicNewsRepository;
import hokutosai.server.error.BadRequestException;
import hokutosai.server.error.InternalServerErrorException;
import hokutosai.server.error.NotFoundException;
import hokutosai.server.security.ParamValidator;
import hokutosai.server.util.DatetimeConverter;
import hokutosai.server.util.RequestAttribute;
import static hokutosai.server.data.specification.SelectableNewsSpecifications.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.http.HttpStatus;
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
	private InsertableNewsRepository insertableNewsRepository;

	@Autowired
	private SelectableNewsRepository selectableNewsRepository;

	@Autowired
	private NewsLikeRepository newsLikeRepository;

	@Autowired
	private MediaRepository mediaRepository;

	@Autowired
	private TopicNewsRepository topicNewsRepository;

	@Autowired
	private TopicEventRepository topicEventRepository;

	@Autowired
	private ConstantlyTopicRepository constantlyTopicRepository;

	@Autowired
	private DatetimeConverter datetimeConverter;

	private int TOPIC_NEWS_COUNT_MAX = 3;

	@RequestMapping(value = "/article", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public InsertableNews postArticle(@RequestBody @Valid InsertableNews news, Errors errors) throws Throwable {
		ParamValidator.checkErrors(errors);

		int relationCount = 0;
		if (news.getEventId() != null) ++relationCount;
		if (news.getShopId() != null) ++relationCount;
		if (news.getExhibitionId() != null) ++relationCount;
		if (relationCount > 1) throw new BadRequestException("It is not possible to register multiple relations.");

		news.setNewsId(null);
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

	@RequestMapping(value = "/{id:^[0-9]+$}", method = RequestMethod.DELETE)
	public StatusResponse deleteArticle(@PathVariable("id") Integer newsId) throws BadRequestException {
		if (!this.insertableNewsRepository.exists(newsId)) throw new BadRequestException("The news is not exit.");

		this.mediaRepository.deleteByNewsId(newsId);
		this.insertableNewsRepository.delete(newsId);

		return new StatusResponse(HttpStatus.OK);
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

		Specifications<SelectableNews> spec = Specifications
				.where(laterThanNewsId(sinceId))
				.and(earlierThanNewsId(lastId))
				.and(laterThanDatetime(sinceDatetime))
				.and(earlierThanDatetime(lastDatetime))
				.and(filterByEventId(filterEventId))
				.and(filterByShopId(filterShopId))
				.and(filterByExhibitionId(filterExhibitionId))
				.and(filterByTopic(filter))
				.and(filterByOnlyEvents(filter))
				.and(filterByExceptEvents(filter))
				.and(filterByOnlyShops(filter))
				.and(filterByExceptShops(filter))
				.and(filterByOnlyExhibitions(filter))
				.and(filterByExceptExhibitions(filter));

		List<SelectableNews> results = (count == null)
				? this.selectableNewsRepository.findAll(spec, new Sort(Sort.Direction.DESC, "datetime"))
				: this.selectableNewsRepository.findAll(spec, new PageRequest(0, count, Sort.Direction.DESC, "datetime")).getContent();

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

	@RequestMapping(value = "/topics", method = RequestMethod.GET)
	public List<Topic> getTopics() {
		// 常駐トピック
		List<ConstantlyTopic> constantly = this.constantlyTopicRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
		// 現在開催中イベント
		Long now = new Date().getTime();
		java.sql.Date currentDate = new java.sql.Date(now);
		java.sql.Time currentTime = new java.sql.Time(now);
		List<TopicEvent> activeEvent = this.topicEventRepository.findDateTimeActiveAll(currentDate, currentTime);
		// 注目イベント
		List<TopicEvent> featuredEvent = this.topicEventRepository.findFeaturedAll(currentDate, currentTime);
		// トピックニュース
		List<TopicNews> topicNews = this.topicNewsRepository.findAll(Specifications
				.where((root, query, cb) -> {
					return cb.isTrue(root.get("isTopic"));
				}),
				new PageRequest(0, TOPIC_NEWS_COUNT_MAX, Sort.Direction.DESC, "newsId")
		).getContent();

		List<Topic> results = new ArrayList<Topic>();
		results.addAll(constantly);
		results.addAll(activeEvent);
		results.addAll(featuredEvent);
		results.addAll(topicNews);

		return results;
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/likes", method = RequestMethod.POST)
	public NewsLikeResult postLikes(ServletRequest request, @PathVariable("id") Integer newsId) throws NotFoundException, InternalServerErrorException {
		SelectableNews news = this.selectableNewsRepository.findOne(newsId);
		if (news == null) throw new NotFoundException("The news is not exist.");

		String accountId = RequestAttribute.getRequiredAccount(request).getId();

		if (this.newsLikeRepository.findByNewsIdAndAccountId(newsId, accountId) == null) {
			this.newsLikeRepository.save(new NewsLike(newsId, accountId));
			this.selectableNewsRepository.incrementLikesCount(newsId);
			news.setLikesCount(news.getLikesCount() + 1);
		}

		NewsLikeResult result = new NewsLikeResult(news);
		result.setLiked(true);
		return result;
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/likes", method = RequestMethod.DELETE)
	public NewsLikeResult deleteLikes(ServletRequest request, @PathVariable("id") Integer newsId) throws NotFoundException, InternalServerErrorException {
		SelectableNews news = this.selectableNewsRepository.findOne(newsId);
		if (news == null) throw new NotFoundException("The news is not exist.");

		String accountId = RequestAttribute.getRequiredAccount(request).getId();

		NewsLike like = this.newsLikeRepository.findByNewsIdAndAccountId(newsId, accountId);
		if (like != null) {
			this.newsLikeRepository.delete(like.getId());
			this.selectableNewsRepository.decrementLikesCount(newsId);
			news.setLikesCount(news.getLikesCount() - 1);
		}

		NewsLikeResult result = new NewsLikeResult(news);
		result.setLiked(false);
		return result;
	}

}
