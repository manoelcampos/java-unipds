package com.example.events.controllers;

import com.example.events.model.UserAccount;
import com.example.events.repositories.UserAccountRepository;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Manoel Campos
 */
@RestController
@RequestMapping("/user")
public class UserAccountController {
    private final UserAccountRepository repository;

    public UserAccountController(final UserAccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<UserAccount>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccount> findById(@PathVariable long id) {
        return repository.findById(id)
                         .map(ResponseEntity::ok)
                         .orElseThrow(() -> newNotFoundException("id " + id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserAccount> findById(@PathVariable @Email String email) {
        return repository.findByEmail(email)
                         .map(ResponseEntity::ok)
                         .orElseThrow(() -> newNotFoundException("email " + email));
    }

    /**
     *
     * @param usedFilter filtro usado para tentar localizar a conta de usuário, que não foi encontrado.
     *                   Por exemplo "id 123" ou "email usuario@gmail.com".
     * @return
     */
    private static ResponseStatusException newNotFoundException(final String usedFilter) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "User account not found with " + usedFilter);
    }
}
