package mnewbold.liftoffproject.models.data;


import mnewbold.liftoffproject.models.Insect;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsectRepository extends CrudRepository<Insect,Integer> {
}
