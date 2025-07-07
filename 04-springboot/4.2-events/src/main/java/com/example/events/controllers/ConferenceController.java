package com.example.events.controllers;

import com.example.events.model.Conference;
import com.example.events.repositories.ConferenceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

/**
 * @author Manoel Campos
 */
@RestController
@RequestMapping("/conference")
public class ConferenceController {
    private final ConferenceRepository repository;

    public ConferenceController(final ConferenceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conference> findById(@PathVariable Long id) {
        final var ex = new ResponseStatusException(HttpStatus.NOT_FOUND, "Conference not found with id " + id);
        return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> ex));
    }

    @GetMapping
    public ResponseEntity<List<Conference>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Conference> insert(@RequestBody Conference conference) {
        final var newConference = repository.save(conference);
        return ResponseEntity.created(URI.create("/conference/" + newConference.getId())).body(newConference);
    }

}
