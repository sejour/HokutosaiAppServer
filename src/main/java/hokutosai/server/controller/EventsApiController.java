package hokutosai.server.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hokutosai.server.data.entity.events.DetailedEvent;
import hokutosai.server.data.entity.events.EventItem;
import hokutosai.server.data.entity.events.EventLike;
import hokutosai.server.data.entity.events.Schedule;
import hokutosai.server.data.entity.events.SimpleEvent;
import hokutosai.server.data.entity.events.TopicEvent;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.data.json.events.EventLikeResult;
import hokutosai.server.data.repository.events.DetailedEventRepository;
import hokutosai.server.data.repository.events.EventItemRepository;
import hokutosai.server.data.repository.events.EventLikeRepository;
import hokutosai.server.data.repository.events.ScheduleRepository;
import hokutosai.server.data.repository.events.SimpleEventRepository;
import hokutosai.server.data.repository.events.TopicEventRepository;
import hokutosai.server.data.specification.EventItemSpecifications;
import hokutosai.server.data.specification.EventSpecifications;
import hokutosai.server.error.BadRequestException;
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
	private ScheduleRepository scheduleRepository;

	@Autowired
	private TopicEventRepository topicEventRepository;

	@Autowired
	private EventLikeRepository eventLikeRepository;

	@RequestMapping(value = "/enumeration", method = RequestMethod.GET)
	public List<EventItem> getEnumeration(ServletRequest request, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "place_id", required = false) Integer placeId) throws ParseException {
		Date datetime = date == null ? null : Date.valueOf(date);

		Specifications<EventItem> spec = Specifications
				.where(EventItemSpecifications.filterByDate(datetime))
				.and(EventItemSpecifications.filterByPlaceId(placeId));

		return this.eventItemRepository.findAll(spec, new Sort(new Order(Sort.Direction.ASC, "date"), new Order(Sort.Direction.ASC, "startTime")));
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<SimpleEvent> get(ServletRequest request, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "place_id", required = false) Integer placeId) throws ParseException {
		Date datetime = date == null ? null : Date.valueOf(date);

		Specifications<SimpleEvent> spec = Specifications
				.where(EventSpecifications.filterByDate(datetime))
				.and(EventSpecifications.filterByPlaceId(placeId));

		List<SimpleEvent> results = this.simpleEventRepository.findAll(spec, new Sort(new Order(Sort.Direction.ASC, "date"), new Order(Sort.Direction.ASC, "startTime")));

		AuthorizedAccount account = RequestAttribute.getAccount(request);
		if (account != null) {
			Map<Integer, EventLike> likesMap = this.getEventLikesMap(account.getId());
			for (SimpleEvent result: results) {
				result.setLiked(likesMap.containsKey(result.getEventId()));
			}
		}

		return results;
	}

	@RequestMapping(value = "/schedules", method = RequestMethod.GET)
	public List<Schedule> getSchedules(ServletRequest request) {
		List<Schedule> results = this.scheduleRepository.findAll(new Sort(Sort.Direction.ASC, "date"));

		for (Schedule schedule: results) {
			schedule.setTimetable(this.simpleEventRepository.timetableAt(schedule.getDate()));
		}

		AuthorizedAccount account = RequestAttribute.getAccount(request);
		if (account != null) {
			Map<Integer, EventLike> likesMap = this.getEventLikesMap(account.getId());
			for (Schedule schedule: results) {
				for (SimpleEvent event: schedule.getTimetable()) {
					event.setLiked(likesMap.containsKey(event.getEventId()));
				}
			}
		}

		return results;
	}

	private Map<Integer, EventLike> getEventLikesMap(String accountId) {
		List<EventLike> likes = this.eventLikeRepository.findByAccountId(accountId);
	    Map<Integer, EventLike> likesMap = new HashMap<Integer, EventLike>();

	    for (EventLike like: likes) {
	    	likesMap.put(like.getEventId(), like);
	    }

	    return likesMap;
	}

	@RequestMapping(value = "/topics", method = RequestMethod.GET)
	public List<TopicEvent> getTopics(ServletRequest request) {
		Long now = new java.util.Date().getTime();
		java.sql.Date currentDate = new java.sql.Date(now);
		java.sql.Time currentTime = new java.sql.Time(now);
		List<TopicEvent> activeEvents = this.topicEventRepository.findDateTimeActiveAll(currentDate, currentTime);
		List<TopicEvent> featuredEvents = this.topicEventRepository.findFeaturedAll();

		List<TopicEvent> results = new ArrayList<TopicEvent>();
		if (activeEvents != null) results.addAll(activeEvents);
		if (featuredEvents != null) results.addAll(featuredEvents);

		AuthorizedAccount account = RequestAttribute.getAccount(request);
		if (account != null) {
			Map<Integer, EventLike> likesMap = this.getEventLikesMap(account.getId());
			for (TopicEvent result: results) {
				result.setLiked(likesMap.containsKey(result.getEventId()));
			}
		}

		return results;
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/details", method = RequestMethod.GET)
	public DetailedEvent getDetails(ServletRequest request, @PathVariable Integer eventId) throws NotFoundException {
		DetailedEvent result = this.detailedeventRepository.findOne(eventId);
		if (result == null) throw new NotFoundException("The id is not used.");

		AuthorizedAccount account = RequestAttribute.getAccount(request);
		if (account != null) {
			EventLike like = this.eventLikeRepository.findByEventIdAndAccountId(eventId, account.getId());
			result.setLiked(like != null);
		}

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

	@RequestMapping(value = "/{id:^[0-9]+$}/likes", method = RequestMethod.DELETE)
	public EventLikeResult deleteLikes(ServletRequest request, @PathVariable("id") Integer eventId) throws NotFoundException, InternalServerErrorException {
		SimpleEvent event = this.simpleEventRepository.findOne(eventId);
		if (event == null) throw new NotFoundException("The event is not exist.");

		String accountId = RequestAttribute.getRequiredAccount(request).getId();

		EventLike like = this.eventLikeRepository.findByEventIdAndAccountId(eventId, accountId);
		if (like != null) {
			this.eventLikeRepository.delete(like.getId());
			this.simpleEventRepository.decrementLikesCount(eventId);
			event.setLikesCount(event.getLikesCount() - 1);
		}

		EventLikeResult result = new EventLikeResult(event);
		result.setLiked(false);
		return result;
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/times", method = RequestMethod.PUT)
	public SimpleEvent putTimes(ServletRequest request,
			@PathVariable("id") Integer eventId,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "start_time", required = false) String startTime,
			@RequestParam(value = "end_time", required = false) String endTime) throws BadRequestException, NotFoundException {
		if (date == null && startTime == null && endTime == null) throw new BadRequestException("all parameters are null");

		SimpleEvent event = this.simpleEventRepository.findOne(eventId);
		if (event == null) throw new NotFoundException("The event is not exist.");

		Date sqlDate = date == null ? event.getDate() : Date.valueOf(date);
		Time sqlStartTime = startTime == null ? event.getStartTime() :  Time.valueOf(startTime);
		Time sqlEndTime = endTime == null ? event.getEndTime() :  Time.valueOf(endTime);

		this.simpleEventRepository.updateTimes(eventId, sqlDate, sqlStartTime, sqlEndTime);

		event.setDate(sqlDate);
		event.setStartTime(sqlStartTime);
		event.setEndTime(sqlEndTime);

		return event;
	}

	@RequestMapping(value = "/{id:^[0-9]+$}/feature", method = RequestMethod.PUT)
	public SimpleEvent putFeature(ServletRequest request, @PathVariable("id") Integer eventId, @RequestParam(value = "featured") Boolean featured) throws NotFoundException {
		SimpleEvent event = this.simpleEventRepository.findOne(eventId);
		if (event == null) throw new NotFoundException("The event is not exist.");

		this.simpleEventRepository.updateFeatured(eventId, featured);
		event.setFeatured(featured);

		return event;
	}

}
