package mnewbold.liftoffproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Insect {

    @Id
    @GeneratedValue
    private int id;

    private String bugName;

    @ManyToMany
    private final List<Plant> flower = new ArrayList<>();

    public Insect() {}

    public Insect(String bugName) {
        this.bugName = bugName;
    }

    public int getId() {
        return id;
    }

    public String getBugName() {
        return bugName;
    }

    public List<Plant> getFlower() {
        return flower;
    }

}
