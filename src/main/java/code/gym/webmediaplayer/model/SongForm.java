package code.gym.webmediaplayer.model;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Set;

public class SongForm {
    private Long id;
    private String name;
    private Double price;
    private LocalDate date;
    private String description;
    private Set<Singer> singer;
    private Set<Album> album;
    private MultipartFile avatar;
    private MultipartFile song;

    public SongForm() {
    }

    public SongForm(String name, Double price, LocalDate date, String description, Set<Singer> singer, Set<Album> album, MultipartFile avatar, MultipartFile song) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.description = description;
        this.singer = singer;
        this.album = album;
        this.avatar = avatar;
        this.song = song;
    }
    public SongForm(Long id, String name, Double price, String description, Set<Singer> singer, Set<Album> album) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.description = description;
        this.singer = singer;
        this.album = album;
    }
    public SongForm(Long id, String name, Double price, LocalDate date, String description, Set<Singer> singer, Set<Album> album, MultipartFile avatar, MultipartFile song) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.description = description;
        this.singer = singer;
        this.album = album;
        this.avatar = avatar;
        this.song = song;
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

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatarPath) {
        this.avatar = avatarPath;
    }

    public MultipartFile getSong() {
        return song;
    }

    public void setSong(MultipartFile songPath) {
        this.song = songPath;
    }
}
