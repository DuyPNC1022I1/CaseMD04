package code.gym.webmediaplayer.service;

import code.gym.webmediaplayer.model.Song;
import code.gym.webmediaplayer.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongService implements Icrud<Song>{
    @Autowired
    private SongRepository songRepository;

    @Override
    public Page<Song> findALl(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    @Override
    public Iterable<Song> findALl() {
        return songRepository.findAll();
    }

    @Override
    public Optional<Song> finById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public void save(Song song) {
        songRepository.save(song);
    }

    @Override
    public void delete(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Page<Song> findAllByNameContaining(String name, Pageable pageable) {
        return songRepository.findAllByNameContaining(name, pageable) ;
    }
    @Override
    public Iterable<Song> getSongNewest() {
        return songRepository.getSongNewest();
    }

    
}
