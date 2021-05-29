package mnewbold.liftoffproject.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddBugFormDTO  {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 20, message = "Invalid bugname. Must be between 3 and 20 characters.")
    private String bugname;

    public String getBugName() {
        return bugname;
    }

    public void setBugName(String bugName) {
        this.bugname = bugName;
    }
}


