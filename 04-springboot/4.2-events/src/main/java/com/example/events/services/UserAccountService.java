package com.example.events.services;

import com.example.events.dto.MyToken;
import com.example.events.model.UserAccount;
import com.example.events.repositories.UserAccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Manoel Campos
 */
@Service
public class UserAccountService {
    private final UserAccountRepository repository;

    public UserAccountService(UserAccountRepository repository) {
        this.repository = repository;
    }

    public Optional<UserAccount> findById(long id) {
        return repository.findById(id);
    }

    public UserAccount save(UserAccount userAccount) {
        final var encoder = new BCryptPasswordEncoder();
        if(userAccount.getPassword() != null)
            userAccount.setPassword(encoder.encode(userAccount.getPassword()));
        System.out.printf("Encrypted Password for user %s: %s%n", userAccount.getUsername(), userAccount.getPassword());
        return repository.save(userAccount);
    }

    public List<UserAccount> findAll() {
        return repository.findAll();
    }

    public Optional<UserAccount> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<MyToken> login(UserAccount userAccount) {
        final var encoder = new BCryptPasswordEncoder();
        return repository
                .findByUsername(userAccount.getUsername())
                .filter(foundUser -> encoder.matches(userAccount.getPassword(), foundUser.getPassword()))
                .map(foundUser -> new MyToken("security123"));
    }
}
