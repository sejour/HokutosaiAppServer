package hokutosai.server.controller;

import static hokutosai.server.data.specification.EventItemSpecifications.*;
import static hokutosai.server.data.specification.SimpleEventSpecifications.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hokutosai.server.data.entity.events.DetailedEvent;
import hokutosai.server.data.entity.events.EventItem;
import hokutosai.server.data.entity.events.EventLike;
import hokutosai.server.data.entity.events.SimpleEvent;
import hokutosai.server.data.json.events.EventLikeResult;
import hokutosai.server.data.repository.events.DetailedEventRepository;
import hokutosai.server.data.repository.events.EventItemRepository;
import hokutosai.server.data.repository.events.EventLikeRepository;
import hokutosai.server.data.repository.events.SimpleEventRepository;
import hokutosai.server.error.InternalServerErrorException;
import hokutosai.server.error.NotFoundException;
import hokutosai.server.util.DatetimeConverter;
import hokutosai.server.util.RequestAttribute;

@RestController
@EnableAutoConfiguration
@RequestMapping("/events")
public class EventsApiController {

	@Autowired
	private EventItemRepository eventItemRepository;

	@Autowired
	private DatetimeConverter datetimeConverter;

	@Autowired
	private DetailedEventRepository detailedeventRepository;

	@Autowired
	private SimpleEventRepository simpleEventRepository;

	@Autowired
	private EventLikeRepository eventLikeRepository;

	@RequestMapping(value = "/enumeration", method = RequestMethod.GET)
	public List<EventItem> getEnumeration(ServletRequest request,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "place_id", required = false) Integer placeId
	) throws ParseException {

		Date datetime = date == null ? null : this.datetimeConverter.stringToDate(date);

		Specifications<EventItem> spec = Specifications
				.where(equalDate(datetime))
				.and(filterByPlaceId(placeId));

		List<EventItem> results =this.eventItemRepository.findAll(spec, new Sort(Sort.Direction.ASC, "date"));

		return results;
	}

	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public List<SimpleEvent> getSchedule(ServletRequest request,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "place_id", required = false) Integer placeId
	) throws ParseException {

		Date datetime = date == null ? null : this.datetimeConverter.stringToDate(date);

		Specifications<SimpleEvent> spec = Specifications
				.where(equalSimpleEventDate(datetime))
				.and(filterBySimpleEventPlaceId(placeId));

		List<SimpleEvent> results =this.simpleEventRepository.findAll(spec, new Sort(Sort.Direction.ASC, "date"));

		return results;
	}

	@RequestMapping(value = "/now", method = RequestMethod.GET)
	public List<SimpleEvent> getEnumeration() {

		Long now = new Date().getTime();
		java.sql.Date currentDate = new java.sql.Date(now);
		java.sql.Time currentTime = new java.sql.Time(now);

		Specifications<SimpleEvent> spec = Specifications
				.where(equalSimpleEventDate(currentDate))
				.and(laterThanEndtime(currentTime))
				.and(earlierThanStarttime(currentTime));

		return this.simpleEventRepository.findAll(spec, new Sort(Sort.Direction.ASC, "date"));
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/details", method = RequestMethod.GET)
	public DetailedEvent getDetailed(@PathVariable Integer id) throws NotFoundException {
		DetailedEvent result = this.detailedeventRepository.findByEventId(id);
		if (result == null) throw new NotFoundException("The id is not used.");
		return result;
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/likes", method = RequestMethod.POST)
	public EventLikeResult postLikes(ServletRequest request, @PathVariable("id") Integer eventId) throws NotFoundException, InternalServerErrorException {
		SimpleEvent event = this.simpleEventRepository.findOne(eventId);
		if (event == null) throw new NotFoundException("The event is not exist.");

		String accountId = RequestAttribute.getRequiredAccount(request).getId();

		if (this.eventLikeRepository.findByEventIdAndAccountId(eventId, accountId) == null) {
			this.eventLikeRepository.save(new EventLike(eventId, accountId));
			this.simpleEventRepository.incrementLikesCount(eventId);
			event.setLikesCount(event.getLikesCount() + 1);
		}

		EventLikeResult result = new EventLikeResult(event);
		result.setLiked(true);
		return result;
	}

}