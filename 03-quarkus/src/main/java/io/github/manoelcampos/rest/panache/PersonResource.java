package io.github.manoelcampos.rest.panache;

import io.micrometer.core.annotation.Counted;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import java.util.List;

/**
 * Fornece operações CRUD via API REST para gerenciar o cadastro de pessoas.
 * Cada método na classe é um endpoint que pode ser acessado via requisições HTTP.
 * @author Manoel Campos
 */
@Path("/person")
public class PersonResource {
    @GET
    @Counted("counted.person.all") // requer quarkus-micrometer
    public List<Person> all(){
        return Person.listAll();
    }

    @GET
    @Path("/{id}")
    public Person findById(@PathParam("id") long id) {
        return Person.findById(id);
    }

    @GET
    @Path("/findByBirthYear/{year}")
    public List<Person> findByBirthYear(@PathParam("year") int year) {
        return Person.findByBirthYear(year);
    }

    @POST @Transactional
    public Person add(Person person){
        // O Panache vai chamar o setter no lugar do acesso direto ao atributo (durante o build)
        // Ver a documentação da classe Person para mais detalhes.
        person.name = person.name.toUpperCase();
        Person.persist(person);
        return person;
    }

    @PUT @Transactional
    public Person update(Person person){
        /* Você pode fazer o update com esta única linha de código,
         * mas existem alguns problemas que não foram resolvidos no Panache,
         * relatados por mim aqui: https://github.com/quarkusio/quarkus/issues/7193
        */
        return Person.getEntityManager().merge(person);
    }

    @DELETE @Transactional
    public void add(long id){
        Person.deleteById(id);
    }
}
