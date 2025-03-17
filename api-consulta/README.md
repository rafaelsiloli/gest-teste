# API Consulta de Créditos

API RESTful desenvolvida com Spring Boot para consulta de créditos constituídos.

## Funcionalidades

- Consulta de créditos por número da NFS-e
- Consulta de crédito pelo número do crédito
- Auditoria das consultas realizadas via Kafka

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.4.3
- Spring Data JPA
- PostgreSQL
- Flyway para migrações do banco
- Kafka para mensageria
- Lombok e MapStruct para produtividade
- SpringDoc OpenAPI para documentação interativa da API

## Boas Práticas Aplicadas

- SOLID, DRY, KISS principles
- Código limpo e organizado
- Cobertura de testes unitários e de integração

## Execução do Projeto

### Pré-requisitos
- Docker e Docker Compose instalados.
- JDK 17
- Maven

### Executando com Docker Compose

```bash
cd docker
docker-compose up --build -d
```

### Rodando a aplicação Spring Boot

```bash
./mvnw clean spring-boot:run
```

Acesse Swagger UI:

```
http://localhost:8080/swagger-ui/index.html
```

## Testes

Executar testes unitários e de integração:

```bash
./mvnw clean test
```

## Estrutura do Projeto

```
src/
|-- main/java/br/com/gestiona/apiconsulta/
│   ├-- aop (camada que faz a interceptação da consulta realiza e manda métricas para o kafka)
│   ├-- controller (camada de controller que chama a camada de serviço)
|   |-- entity (camada de entidade)
│   ├-- integration (faz a integração com o kafka)
│   ├-- repo (camada do banco de dados)
│   ├-- service (camada de serviço que faz a busca no repositório e realiza o mapeamento para o dto de saída)
│   ├-- util (classe que contém o mapeamento)
└-- test/java/br/com/gestiona/apiconsulta/
    ├-- unit (pacote com os testes unitários que não foram cobertos pelo teste de integração e mais algum outro cenário)
    └-- ApiConsultaApplicationTests.java (faz os testes de integração)
```