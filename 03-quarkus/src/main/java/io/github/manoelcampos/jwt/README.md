Mostra como usar o JWT (JSON Web Token) para autenticação e autorização de endpoints REST no Quarkus. 

As configurações para que a autenticação/autorização com JWT funcionem estão definidas no arquivo `application.properties`.

O arquivo `quarkus.jwt.token` contém um token JWT que pode ser usado para acessar os endpoints protegidos.
Ele foi gerado pela classe `JwtGenerator`.

As chaves públicas e privadas usadas pela geração/validação dos tokens estão na pasta `resources`.
Você pode gerar seu próprio par de chaves usando ferramentas como openssl, mas tais chaves foram obtidas diretamente da documentação do Quarkus no link abaixo.

Docs: [JWT Guide](https://quarkus.io/guides/security-jwt).
