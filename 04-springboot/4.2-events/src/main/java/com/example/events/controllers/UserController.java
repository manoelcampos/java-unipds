package com.example.events.controllers;

import com.example.events.model.UserAccount;
import com.example.events.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Manoel Campos
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository repository;

    public UserController(final UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<UserAccount>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }
}
