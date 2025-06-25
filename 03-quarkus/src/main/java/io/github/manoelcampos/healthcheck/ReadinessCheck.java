package io.github.manoelcampos.healthcheck;

import io.github.manoelcampos.rest.starship.StarWarsService;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/// Precisa da extensão [quarkus-smallrye-health](https://quarkus.io/guides/smallrye-health)
/// @author Manoel Campos
@Readiness
public class ReadinessCheck implements HealthCheck {
    @RestClient
    StarWarsService client;

    @Override
    public HealthCheckResponse call() {
        final boolean fallBackCalled = client.getStarships().isEmpty();
        return fallBackCalled ?
                HealthCheckResponse.down("Star Wars Service down") :
                HealthCheckResponse.up("Star Wars Service up");
    }
}
