package io.github.manoelcampos.healthcheck;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

/// Adiciona um endpoint de verificação de liveness da aplicação,
/// para saber se a aplicação está viva (rodando).
/// Com isto, uma box na DevUI do Quarkus é adicionada para mostrar o status da aplicação.
///
/// Precisa da extensão [quarkus-smallrye-health](https://quarkus.io/guides/smallrye-health)
/// @author Manoel Campos
@Liveness
public class LivenessCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.up("Liveness check");
    }
}
