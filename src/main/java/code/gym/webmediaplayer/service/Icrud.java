package code.gym.webmediaplayer.service;

import code.gym.webmediaplayer.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface Icrud<T>{
    Page<T> findALl(Pageable pageable);
    Iterable<T> findALl();
    Optional<T> finById(Long id);
    void save(T t);
    void delete(Long id);
    Page<T> findAllByNameContaining(String name, Pageable pageable);
    Iterable<T> findAllOrderByLocalDateDesc();
}