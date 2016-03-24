package hokutosai.server.data.repository.places;

import hokutosai.server.data.entity.places.Place;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

}
