package io.github.manoelcampos;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Permite configurar a aplicação Quarkus,
 * como, por exemplo, qual o caminho base (raiz) da API REST.
 * @author Manoel Campos
 */
@ApplicationPath("/api") // Define raiz da API REST
public class RestApplication extends Application {
}
