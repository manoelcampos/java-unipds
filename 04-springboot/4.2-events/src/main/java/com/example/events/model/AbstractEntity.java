package com.example.events.model;

import jakarta.persistence.*;

/**
 * Define uma classe abstrata para não ter que repetir
 * a definição do ID em todas as entidades.
 * @author Manoel Campos
 */
@MappedSuperclass
abstract class AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
