package com.example.events.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/// Aqui a classe foi nomeada como `UserAccount` no lugar de `User`,
/// pois em muitos bancos, o nome "user"
/// é uma palavra reservada (como é no caso do Postgres).
/// Não quis usar a anotação [Table] para definir um nome
/// diferente para a tabela, para garantir que as tabelas tem o mesmo
/// nome das classes.
/// @author Manoel Campos
@Entity
public class UserAccount extends AbstractEntity {
    @NotNull @NotBlank
    private String name;

    @NotNull @NotBlank @Email @Column(unique = true)
    private String email;

    @ManyToMany(mappedBy = "users") @NotNull
    private List<Session> sessions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
