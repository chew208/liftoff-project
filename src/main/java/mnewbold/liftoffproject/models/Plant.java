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
public class Plant {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @NotBlank
    public String flowerName;

    @ManyToMany(mappedBy = "flower")
    private final List<Insect> bug = new ArrayList<>();

    public Plant() {}

    public Plant(String flowerName) {
        this.flowerName = flowerName;
    }

    public int getId() {
        return id;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public List<Insect> getBug() {
        return bug;
    }
}
