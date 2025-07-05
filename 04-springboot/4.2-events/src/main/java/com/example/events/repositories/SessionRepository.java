package com.example.events.repositories;

import com.example.events.model.Session;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manoel Campos
 */
@Repository
public interface SessionRepository extends ListCrudRepository<Session, Long> {
}
