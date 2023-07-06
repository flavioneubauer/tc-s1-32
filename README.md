# TC SOAT 1 - TURMA 32

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

Durante os testes foi utilizada a versão 24.0.0 do docker engine e v2.17.3 do docker compose.
