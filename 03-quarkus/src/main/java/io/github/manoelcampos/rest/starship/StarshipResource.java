package io.github.manoelcampos.rest.starship;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

/**
 * @author Manoel Campos
 */
@Path("/starwars")
public class StarshipResource {
    @RestClient
    StarWarsService client;

    @GET
    @Path("/starships")
    public List<Starship> getStarships(){
        return client.getStarships();
    }
}
