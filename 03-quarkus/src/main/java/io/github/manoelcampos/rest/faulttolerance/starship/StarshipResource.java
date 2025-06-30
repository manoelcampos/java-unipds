package io.github.manoelcampos.rest.faulttolerance.starship;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

/**
 * Classe que implementa endpoints REST no nosso backend
 * para receber requisições e encaminhar para a API REST externa
 * do Star Wars usando o {@link StarWarsService}
 * que é implementado automaticamente pela extensão quarkus-rest-client.
 *
 * @author Manoel Campos
 */
@Path("/starwars")
public class StarshipResource {
    /**
     * Instância do cliente REST para a API externa do Star Wars.
     */
    @RestClient
    StarWarsService client;

    @GET
    @Path("/starships")
    public List<Starship> getStarships(){
        return client.getStarships();
    }
}
