# TC SOAT 1 - TURMA 32

## Fase 2

### Teste da aplicação em k8s

Para subida do projeto em um cluster kubernetes, mais espeficamente no minikube, se faz necessário executar os seguintes passos:

1. Instalar o minikube
2. Rodar o minikube com `minikube start --driver=docker`
3. Verificar que o kubectl está configurado para o cluster do minikube em um terminal
4. Aplicar as mudanças as seguir, a partir do diretório `k8s` do repositório

```bash
kubectl apply -f namespace.yaml
kubectl apply -f secret.yaml 
kubectl apply -f github-registry.yaml
kubectl apply -f db/pv.yaml
kubectl apply -f db/pvc.yaml
kubectl apply -f db/svc.yaml 
kubectl apply -f db/statefulset.yaml
kubectl apply -f backend/svc.yaml
kubectl apply -f backend/deployment.yaml
kubectl apply -f nlb.yaml
```

5. Para validar o NLB, executar o comando `minikube tunnel`
6. Em outro terminal, executar o comando `minikube dashboard` e na seção services do dashboard irá aparecer o link para o nlb que direciona para o backend

Se por algum acaso precisar refazer esses passos, recomendo que rode o `minikube delete` e volte ao passo 2.

Todos os pods, services e volumes estão contidos no namespace tc-s1-32.

### Build e push no container registry

O build e push da imagem da aplicação pode ser feito pelo comando `./push2registry.sh identificador-da-versão`.
Nele são executados os passos de build e push para o container registry do Github.

É importante se atentar aos pré-requisitos: 
1. gere seu personal token no github com permissão de `write:package`. Dúvidas verifique a [documentação](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens)
2. com o token em mãos, autentique no github container registry utilizando o comando `echo seu_novo_token | docker login ghcr.io -u flavioneubauer --password-stdin`

### Configuração do Github container registry no cluster kubernetes

Para configurar o acesso ao container registry do Github pelo cluster kubernetes existe o arquivo de configuração github-registry.yaml. Dentro dele existe um conteúdo em formato base64 do seguinte json:

```json
{"auths":{"ghcr.io":{"auth":"<AUTH-TOKEN>"}}}
```

A chave `AUTH-TOKEN` é um base64 de `usuario:token_de_acesso`. Esse token de acesso precisa ser gerado no Github e ter apenas a permissão `read:package`.
Exemplo de geração: 
1. token usuario = `echo 'flavioneubauer:meu_token_somente_leitura' | base64 -w0`
2. .dockerconfigjson = `echo -n '{"auths":{"ghcr.io":{"auth":"ZmxhdmlvbmV1YmF1ZXI6Z2hwX2dRWHVnTHdxakxFTm9tUERmSVpwM0Noc0RwSTI3ajJHS0V4Yw=="}}}' | base64 -w0`

Já foi gerada uma chave de acesso somente leitura, portanto esse passo é apenas caso haja o desejo de configurar uma nova chave no futuro.

## Fase 1

A responsabilidade deste software é controlar o fluxo de pedidos feitos pelos clientes, e com manutenção de produtos e checkout de pagamento.

O projeto está estruturado em 3 módulos sendo: 

- adapters: driven e drivers 
- core: domain. ports e use cases
- application: inicialização da aplicação

Existe um módulo principal chamado `tc` que agrupa os 3 módulos.

Documentação de apoio: https://spring.io/guides/gs/multi-module/ 

Para executar:

```
./mvnw install -DskipTests && ./mvnw spring-boot:run -pl application
```

Para executar usando docker compose: 

1. Primeiro realize o build do container

```
docker compose build
```

2. Suba a aplicação e o banco de dados

```
docker compose up
```

Para escalar a aplicação, utilzar `docker compose up --scale app=3` e serão instanciadas 3 aplicações de backend nas portas 8080, 8081 e 8082.

Caso não suba mais de uma instância, utilize o comando `docker compose up` e verifique a porta em que a aplicação subiu utilizando o comando `docker ps`. A porta estará no range de 8080 a 8083.

Durante os testes foi utilizada a versão 24.0.0 do docker engine e v2.17.3 do docker compose.
