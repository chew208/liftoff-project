package mnewbold.liftoffproject.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddPlantFormDTO {

    @NotNull
    @NotBlank
    public String flowerName;

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }
}
