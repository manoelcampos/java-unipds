#!/bin/bash

echo "Enviando requisições para o servidor local, que encaminhará para o servidor remoto da StarWars API"
while true; do
  curl http://localhost:8080/api/starwars/starships
  sleep 0.8
done
