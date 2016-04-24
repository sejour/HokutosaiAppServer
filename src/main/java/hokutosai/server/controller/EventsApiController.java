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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hokutosai.server.data.entity.events.EventItem;
import hokutosai.server.data.entity.events.SimpleEvent;
import hokutosai.server.data.repository.events.EventItemRepository;
import hokutosai.server.data.repository.events.SimpleEventRepository;
import hokutosai.server.util.DatetimeConverter;

@RestController
@EnableAutoConfiguration
@RequestMapping("/events")
public class EventsApiController {

	@Autowired
	private EventItemRepository eventItemRepository;

	@Autowired
	private DatetimeConverter datetimeConverter;

	@Autowired
	private SimpleEventRepository simpleEventRepository;

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

}
