package mnewbold.liftoffproject.models;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @NotNull
    private String type;

    public User() {}

    public User(@NotNull String username, @NotNull String pwHash, @NotNull String type) {
        this.id = id;
        this.username = username;
        this.pwHash = encoder.encode(pwHash);
        this.type = type;
    }


    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getType() { return type;}

    public boolean isGuest() { return type == "guest"; }


}
