#!/bin/bash

## Envia uma requisição autenticada (passando o JWT) para um endpoint protegido no backend
## Você precisa executar o script estando dentro da pasta dele

token=$(cat quarkus.jwt.token)
curl -v -w '\n' -H "Authorization: Bearer $token" localhost:8080/api/secure/claim
