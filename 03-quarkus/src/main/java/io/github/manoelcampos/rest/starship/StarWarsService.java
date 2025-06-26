package io.github.manoelcampos.rest.starship;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/// Interface do REST Client para acessar a API REST do Star Wars.
/// Ela é implementada automaticamente pelo Quarkus.
///
/// Precisa das extensões [quarkus-rest-client](https://quarkus.io/guides/rest-client)
/// e quarkus-rest-client-jsonb (para serialização JSON)
/// @author Manoel Campos
@RegisterRestClient(baseUri = "https://swapi.info/api/")
public interface StarWarsService {
    /**
     * Usado apenas para contar quantas vezes o método de fallback é chamado.
     * Como precisamos alterar este contador e estamos em uma interface, não
     * dá pra alterar seu valor se declararmos o atributo como int, por exemplo.
     * O tipo {@link AtomicInteger} é um objeto que armazena um valor inteiro
     * internamente e permite alterá-lo e de modo thread-safe.
     * O que estamos declarando aqui é uma constante
     * (static final). Assim, se o serviço estiver
     * sendo usado em outras partes da aplicação,
     * não teremos um contador único para cada instância do serviço.
     * Mas como o objeto aqui de debug, não há problema.
     */
    AtomicInteger fallbackCounter = new AtomicInteger();

    @GET
    @Path("/starships")
    List<Starship> getStarshipsOriginal();

    /// Requer [quarkus-smallrye-fault-tolerance](https://quarkus.io/guides/smallrye-fault-tolerance),
    /// recurso do [SmallRye](https://smallrye.io), implementação da especificação
    /// [MicroProfile](http://microprofile.io).
    /// Para ver o circuit breaker em ação, basta colocar o URI errado no topo da classe.
    /// Como este método só chama o original (que será implementado pelo SmallRye),
    /// se o original falhar, este método não será mais chamado quando
    /// o circuit breaker estiver aberto.
    /// Para ver o circuit breaker sendo desabilitado,
    /// inverta o nome dos métodos [#getStarships()] e [#getStarshipsOriginal()].
    @GET
    @Path("/starships")
    @Timeout(value = 3, unit = ChronoUnit.SECONDS)
    @Fallback(fallbackMethod = "getStarshipsFallback")
    @CircuitBreaker(failureRatio = 0.5, delay = 5000, requestVolumeThreshold = 4)
    default List<Starship> getStarships(){
        System.out.println("Chamando o método getStarships");
        fallbackCounter.set(0);
        return getStarshipsOriginal();
    }

    /**
     * Para fazer este método ser chamado, basta colocar um URI inválido no topo da classe.
     * @return uma lista vazia para saber que o fallback foi chamado.
     */
    default List<Starship> getStarshipsFallback(){
        System.out.printf("%d: Fallback chamado para getStarships%n", fallbackCounter.incrementAndGet());
        return List.of();
    }
}
