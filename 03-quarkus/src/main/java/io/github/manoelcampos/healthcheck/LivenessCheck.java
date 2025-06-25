package io.github.manoelcampos.healthcheck;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

/// Precisa da extensão [quarkus-smallrye-health](https://quarkus.io/guides/smallrye-health)
/// @author Manoel Campos
@Liveness
public class LivenessCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.up("Liveness check");
    }
}
