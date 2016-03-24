package hokutosai.server.controller;

import hokutosai.server.data.entity.places.Place;
import hokutosai.server.data.repository.places.PlaceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/places")
public class PlacesApiController {

	@Autowired
    private PlaceRepository placeRepository;

	 @RequestMapping(value = "/list", method = RequestMethod.GET)
	 public Iterable<Place> list() {
	     return placeRepository.findAll();
	 }

}
