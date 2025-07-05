package com.example.events.controllers;

import com.example.events.model.Subscription;
import com.example.events.repositories.SubscriptionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * @author Manoel Campos
 */
@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    private final SubscriptionRepository repository;

    public SubscriptionController(final SubscriptionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Subscription> insert(@RequestBody Subscription subscription) {
        final var newSubscription = repository.save(subscription);
        return ResponseEntity.created(URI.create("/subscription/" + newSubscription.getId())).body(newSubscription);
    }

    @GetMapping("/user/{userAccountId}")
    public ResponseEntity<List<Subscription>> findByUserAccountId(@PathVariable long userAccountId) {
        return ResponseEntity.ok(repository.findByIdUserAccountId(userAccountId));
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<Subscription>> findBySessionId(@PathVariable long sessionId) {
        return ResponseEntity.ok(repository.findByIdSessionId(sessionId));
    }

}
