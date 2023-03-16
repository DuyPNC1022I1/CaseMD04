package code.gym.webmediaplayer.repository;

import code.gym.webmediaplayer.model.Account;
import code.gym.webmediaplayer.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Page<Account> findAllByNameContaining(String name, Pageable pageable);
}
