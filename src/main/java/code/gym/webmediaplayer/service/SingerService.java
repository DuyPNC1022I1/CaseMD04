package code.gym.webmediaplayer.service;

import code.gym.webmediaplayer.model.Singer;
import code.gym.webmediaplayer.model.Song;
import code.gym.webmediaplayer.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SingerService implements Icrud<Singer>{
    @Autowired
    private SingerRepository singerRepository;

    @Override
    public Page<Singer> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public Iterable<Singer> findALl() {
        return singerRepository.findAll();
    }

    @Override
    public Optional<Singer> finById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Singer singer) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<Singer> findAllByNameContaining(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Iterable<Singer> findAllOrderByLocalDateDesc() {
        return null;
    }
}
