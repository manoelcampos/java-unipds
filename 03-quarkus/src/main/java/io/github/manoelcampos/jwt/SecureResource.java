package io.github.manoelcampos.jwt;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

/// Uma classe de resource que contém métodos
/// que exigem autenticação via JWT para serem acessados.
/// Depende da extensão [quarkus-smallrye-jwt](https://quarkus.io/guides/security-jwt).
/// @author Manoel Campos
@Path("/secure")
@RequestScoped
public class SecureResource {
    /**
     * Obtém o nome de usuário de um possível JWT recebido
     * via header HTTP.
     */
    @Claim(standard = Claims.preferred_username)
    String username;

    @GET
    @Path("/claim")
    @RolesAllowed("Subscriber")
    public String getClaim() {
        return username;
    }
}
