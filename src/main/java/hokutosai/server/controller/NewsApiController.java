package hokutosai.server.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import hokutosai.server.config.MediaConfiguration;
import hokutosai.server.data.entity.media.Media;
import hokutosai.server.data.entity.news.InsertableNews;
import hokutosai.server.data.entity.news.NewsWithMedia;
import hokutosai.server.data.entity.news.SelectableNews;
import hokutosai.server.data.repository.news.InsertableNewsRepository;
import hokutosai.server.data.repository.news.NewsWithMediaRepository;
import hokutosai.server.error.BadRequestException;
import hokutosai.server.security.ParamValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
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
	private NewsWithMediaRepository newsWithMediaRepository;

	@RequestMapping(value = "/article", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public InsertableNews postArticle(@RequestBody @Valid InsertableNews news, Errors errors) throws BadRequestException {
		ParamValidator.checkErrors(errors);
		news.setDatetime(new Date());

		InsertableNews result = this.insertableNewsRepository.save(news);
		Integer newsId = result.getNewsId();

		List<Media> medias = news.getMedias();
		if (medias != null) {
			int sequence = 0;
			for (Media media: medias) {
				this.newsWithMediaRepository.save(new NewsWithMedia(newsId, media.getMediaId(), ++sequence));
			}
		}

		return result;
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/details", method = RequestMethod.GET)
	public List<SelectableNews> getDetails() {
		return null;
	}

}
