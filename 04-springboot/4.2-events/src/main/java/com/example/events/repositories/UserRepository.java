package com.example.events.repositories;

import com.example.events.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manoel Campos
 */
@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {
}
