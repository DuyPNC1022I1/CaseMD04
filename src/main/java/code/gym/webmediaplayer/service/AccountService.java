package code.gym.webmediaplayer.service;

import code.gym.webmediaplayer.model.Account;
import code.gym.webmediaplayer.model.Song;
import code.gym.webmediaplayer.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements Icrud<Account> {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Page<Account> findALl(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Iterable<Account> findALl() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> finById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Account account) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<Account> findAllByNameContaining(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Iterable<Song> getSongNewest() {
        return null;
    }
}
