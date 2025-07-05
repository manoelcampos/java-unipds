package com.example.events.repositories;

import com.example.events.model.Conference;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manoel Campos
 */
@Repository
public interface ConferenceRepository extends ListCrudRepository<Conference, Long> {
}
