package mnewbold.liftoffproject.models.data;

import mnewbold.liftoffproject.models.Insect;
import mnewbold.liftoffproject.models.Plant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PlantRepository extends CrudRepository<Plant,Integer> {
    Plant findByFlowerName(String flowerName);

}

// To Make A Search page
// 1. Create a template (html) file that has a form with a text box on it, and a button to submit it
// 2. Create a Search Insect Controller to capture the POST request
// 3. Using wheat the user typed, use the DB reposoitory to query for that bug name
// 4. Using what the DB returns, show the result on the search page