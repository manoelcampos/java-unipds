package com.example.events.controllers;

import com.example.events.dto.MyToken;
import com.example.events.model.UserAccount;
import com.example.events.services.UserAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Manoel Campos
 */
@RestController
@RequestMapping("/user")
public class UserAccountController {
    private final UserAccountService service;

    public UserAccountController(final UserAccountService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserAccount>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<UserAccount> save(@Valid @RequestBody UserAccount userAccount) {
        return ResponseEntity.ok(service.save(userAccount));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccount> findById(@Valid @PathVariable long id) {
        return service.findById(id)
                         .map(ResponseEntity::ok)
                         .orElseThrow(() -> newNotFoundException("id " + id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserAccount> findById(@Valid @PathVariable @Email String email) {
        return service.findByEmail(email)
                         .map(ResponseEntity::ok)
                         .orElseThrow(() -> newNotFoundException("email " + email));
    }

    @PostMapping("/login")
    public ResponseEntity<MyToken> login(@RequestBody UserAccount userAccount) {
        final var ex = new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        return ResponseEntity.ok(service.login(userAccount).orElseThrow(() -> ex));
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
