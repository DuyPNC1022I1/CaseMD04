package code.gym.webmediaplayer.model;

import javax.persistence.*;

@Entity
@Table(name = "singers")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String avatarPath;

    public Singer() {
    }

    public Singer(Long id, String name, String avatarPath) {
        this.id = id;
        this.name = name;
        this.avatarPath = avatarPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
