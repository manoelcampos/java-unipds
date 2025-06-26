package io.github.manoelcampos.jwt;

import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;

import java.util.Set;

/// Gera JWTs (JSON Web Tokens) para testes.
/// Para funcionar, a classe depende da configuração `smallrye.jwt.sign.key.location`
/// no arquivo `application.properties`.
///
/// A chave privada usada por esta classe e a pública usada
/// pelo backend para validar os JWT recebidas via header HTTP
/// foram copiadas do site do quarkus (mas podem ser geradas
/// com o comando openssl, como mostrado em https://quarkus.io/guides/security-jwt).
/// @author Manoel Campos
public class JwtGenerator {
    /** Se você alterar dados como o issuer ou groups,
     * precisará atualizar o application.properties e/ou
     * o {@link SecureResource}. */
    public static void main(String[] args) {
        final String username = "joao";
        String token = Jwt.issuer("https://example.com/issuer")
                          .upn(username + "@example.com")
                          .preferredUserName(username)
                          .groups(Set.of("User", "Admin", "Subscriber"))
                          .claim(Claims.birthdate, "2001-07-13")
                          .sign();
        System.out.println("JWT Gerado");
        System.out.println("Você pode copiar este conteúdo para um arquivo quarkus.jwt.token na pasta jwt.");
        System.out.println("Assim, tal arquivo pode ser usado pelo script request.sh para enviar requisições autenticadas para o backend.");
        System.out.println();
        System.out.println(token);
    }
}
