package code.gym.webmediaplayer.repository;

import code.gym.webmediaplayer.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Page<Song> findAllByNameContaining(String name, Pageable pageable);

    @Query("SELECT s FROM Song s ORDER BY s.date DESC")
    Iterable<Song> findAllOrderByLocalDateDesc();

}
