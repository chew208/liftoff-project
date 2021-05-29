package mnewbold.liftoffproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Insect {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @NotBlank
    private String bugname;

    @ManyToMany
    private final List<Plant> flower = new ArrayList<>();

    public Insect() {}

    public Insect(String bugName) {
        this.bugname = bugName;
    }

    public int getId() {
        return id;
    }

    public String getBugName() { //Java program outside of Insect.java: bugName; MYSQL: bugname
        if (bugname == null) {
            return "";
        } else
            return bugname;
    }
    public void setBugName(String bugName) {
        this.bugname = bugName;
    }

    public List<Plant> getFlower() {
        return flower;
    }

}
