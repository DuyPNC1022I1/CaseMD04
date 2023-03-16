package code.gym.webmediaplayer.service;

import code.gym.webmediaplayer.model.Album;
import code.gym.webmediaplayer.model.Song;
import code.gym.webmediaplayer.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumService implements Icrud<Album> {

    @Autowired
    private AlbumRepository albumRepository;
    @Override
    public Page<Album> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public Iterable<Album> findALl() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<Album> finById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Album album) {

    }
    @Override
    public void delete(Long id) {

    }
    @Override
    public Page<Album> findAllByNameContaining(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Iterable<Album> findAllOrderByLocalDateDesc() {
        return null;
    }
}
