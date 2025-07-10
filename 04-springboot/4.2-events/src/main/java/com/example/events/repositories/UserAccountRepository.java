package com.example.events.repositories;

import com.example.events.model.UserAccount;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Manoel Campos
 */
@Repository
public interface UserAccountRepository extends ListCrudRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
    Optional<UserAccount> findByUsername(String username);
}
