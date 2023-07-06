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
