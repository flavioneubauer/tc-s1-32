# TC SOAT 1 - TURMA 32

## Fase 2

### Teste da aplicação

Para subida do projeto em um cluster kubernetes, mais espeficamente no minikube, se faz necessário executar os seguintes passos:

1. Instalar o minikube
2. Rodar o minikube com `minikube start --driver=docker`
3. Verificar que o kubectl está configurado para o cluster do minikube
4. Aplicar as mudanças as seguir, a partir do diretório `k8s`

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
6. Executar o comando `minikube dashboard` e na seção services do dashboard irá aparecer o link para o nlb que direciona para o backend

### Build e push no container registry

O build e deploy pode ser feito pelo comando `push2registry.sh`. 
Nele são executados os passos de build e push para o container registry. 

É importante se atentar aos pré-requisitos: 
1. gere seu personal token no github com permissão de `write:package`. Dúvidas verifique a [documentação](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens)
2. com o token em mãos, autentique no github container registry utilizando o comando `echo seu_novo_token | docker login ghcr.io -u flavioneubauer --password-stdin`


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
