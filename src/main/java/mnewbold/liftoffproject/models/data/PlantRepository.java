package mnewbold.liftoffproject.models.data;

import mnewbold.liftoffproject.models.Plant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends CrudRepository<Plant,Integer> {
}