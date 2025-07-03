package io.github.manoelcampos.rest.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.requireNonNullElse;

/**
 * Classe que representa uma Entidade JPA/Hibernate e que usa a extensão
 * Panache do Quarkus que automaticamente implementa métodos para operações CRUD
 * diretamente na classe da entidade.
 *
 * Usa {@link PanacheEntityBase} no lugar de {@link PanacheEntity}
 * para ter mais controle sobre as configurações do atributo ID.
 * @author Manoel Campos
 */
@Entity
public class Person extends PanacheEntityBase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public LocalDate birthDate;

    public static List<Person> findByBirthYear(int year) {
        return find("year(birthDate)", year).list();
    }

    /**
     * Com os atributos públicos, o Panache troca os acessos diretos
     * a cada atributo pela chamada do respectivo getter ou setter (se houver).
     * @param name
     */
    public void setName(String name) {
        name = requireNonNullElse(name, "").trim();
        System.out.println("Chamando setName: " + name);
        if(name.matches("\\d+")) {
            throw new IllegalArgumentException("O nome não pode conte números");
        }

        this.name = name;
    }
}
