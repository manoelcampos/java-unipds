package com.example.events.repositories;

import com.example.events.model.Subscription;
import com.example.events.model.SubscriptionID;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Manoel Campos
 */
@Repository
public interface SubscriptionRepository extends ListCrudRepository<Subscription, SubscriptionID> {
    List<Subscription> findByIdUserAccountId(long userAccountId);
    List<Subscription> findByIdSessionId(long sessionId);
}
