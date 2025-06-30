package io.github.manoelcampos.healthcheck;

import io.github.manoelcampos.rest.faulttolerance.starship.StarWarsService;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/// Adiciona um endpoint de verificação de readiness da aplicação,
/// para saber se a aplicação pronta (ready).
/// Com isto, uma box na DevUI do Quarkus é adicionada para mostrar o status da aplicação.
///
/// Precisa da extensão [quarkus-smallrye-health](https://quarkus.io/guides/smallrye-health)
/// @author Manoel Campos
@Readiness
public class ReadinessCheck implements HealthCheck {
    @RestClient
    StarWarsService client;

    /**
     * A aplicação só será identificada como pronta para
     * atender requisições se o serviço de Star Wars estiver ativo.
     * Para isso, enviamos uma requisição para um dos endpoints
     * de tal API e verificamos se a resposta obtida corresponde
     * a uma resposta de um método de fallback (que neste exemplo,
     * tal método retorna uma lista vazia de starships).
     * @return
     */
    @Override
    public HealthCheckResponse call() {
        final boolean fallBackCalled = client.getStarships().isEmpty();
        return fallBackCalled ?
                HealthCheckResponse.down("Star Wars Service down") :
                HealthCheckResponse.up("Star Wars Service up");
    }
}
