#!/bin/bash
set -eu

# REQUISITOS:
# 1. gere seu personal token no github com permissão de write:package 
#   documentação: https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens
# 2. com o token em mãos, autentique no github container registry
#    echo seu_novo_token | docker login ghcr.io -u flavioneubauer --password-stdin

IMAGE_NAME=ghcr.io/flavioneubauer/tc-s1-32
APP_VERSION="$1"

mv .env .env_bkp
echo "APP_VERSION=$APP_VERSION" > .env
docker compose build --push
mv .env_bkp .env