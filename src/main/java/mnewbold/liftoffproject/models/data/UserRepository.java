package mnewbold.liftoffproject.models.data;

import mnewbold.liftoffproject.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
