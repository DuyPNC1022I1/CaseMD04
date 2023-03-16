package code.gym.webmediaplayer.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String name;

    @Column(columnDefinition = "double default 0.0")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater than or equal to 0")
    @NotNull
    private Double price;

    @DateTimeFormat
    private LocalDate date;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "singer_id")
    private Set<Singer> singer;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Album> album;

    private String avatarPath;

    private String songPath;

    public Song() {
    }


    public Song(String name, Double price, LocalDate date, String description, Set<Singer> singer, Set<Album> album, String avatarPath, String songPath) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.description = description;
        this.singer = singer;
        this.album = album;
        this.avatarPath = avatarPath;
        this.songPath = songPath;
    }

    public Song(Long id, String name, Double price, LocalDate date, String description, Set<Singer> singer, Set<Album> album, String avatarPath, String songPath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.description = description;
        this.singer = singer;
        this.album = album;
        this.avatarPath = avatarPath;
        this.songPath = songPath;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Singer> getSinger() {
        return singer;
    }

    public void setSinger(Set<Singer> singer) {
        this.singer = singer;
    }

    public Set<Album> getAlbum() {
        return album;
    }

    public void setAlbum(Set<Album> album) {
        this.album = album;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }
}
