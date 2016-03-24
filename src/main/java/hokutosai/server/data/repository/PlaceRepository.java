package hokutosai.server.data.repository;

import hokutosai.server.data.entity.Place;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Integer>  {

}
