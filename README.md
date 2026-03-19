# training-cqrs

Projeto de estudo para praticar o padrao **CQRS (Command Query Responsibility Segregation)** com **Java 17**, **Maven** e **Spring Boot**.

No estado atual, o repositorio ja esta organizado como um projeto **multi-modulo**, separando responsabilidades entre escrita (`command`) e leitura (`query`), mas ainda esta em fase inicial de implementacao.

## Objetivo

A ideia deste projeto e servir como base de treinamento para uma arquitetura em que:

- o modulo `command` concentra operacoes de escrita e regras de negocio;
- o modulo `query` concentra operacoes de leitura e consultas otimizadas;
- tecnologias diferentes podem ser usadas para cada lado da aplicacao, de acordo com a necessidade.

## Estrutura do projeto

```text
training-cqrs/
|- command/
|  |- src/main/java/br/com/cqrstraining/
|  |  |- CommandApplication.java
|  |  `- domain/
|  |     |- Product.java
|  |     `- Review.java
|  `- pom.xml
|- query/
|  |- src/main/java/br/com/cqrstraining/
|  |  `- QueryApplication.java
|  `- pom.xml
|- pom.xml
|- mvnw
`- mvnw.cmd
```

## Modulos

### `command`

Responsavel pela parte de **escrita** da aplicacao.

Dependencias ja adicionadas:

- Spring Boot
- Spring Data JPA
- MySQL Driver
- Kafka
- Validation
- Web MVC
- MapStruct
- Lombok

Entidades iniciais ja criadas:

- `Product`
- `Review`

### `query`

Responsavel pela parte de **leitura** da aplicacao.

Atualmente herda do projeto principal dependencias como:

- Spring Data MongoDB
- Kafka
- Validation
- Web MVC

## Tecnologias utilizadas

- Java 17
- Maven Wrapper
- Spring Boot 4.0.3
- Spring Data JPA
- MySQL
- Spring Data MongoDB
- Apache Kafka
- MapStruct
- Lombok

## Como compilar

### Windows

```bash
.\mvnw.cmd clean install
```

### Linux / macOS

```bash
./mvnw clean install
```

## Como executar os módulos

No momento, as classes principais `CommandApplication` e `QueryApplication` ainda estao como classes Java simples, sem a configuracao completa de inicializacao do Spring Boot.

Ou seja, o projeto **ainda nao esta pronto para subir como duas APIs funcionais**, mas ja pode ser evoluido a partir da estrutura existente.

## Estado atual

Hoje o projeto contem:

- agregacao Maven com os modulos `command` e `query`;
- dependencias principais para uma arquitetura CQRS;
- modelo inicial de dominio com `Product` e `Review`;
- classes de entrada para cada modulo.

Ainda faltam, por exemplo:

- anotacoes Spring Boot nas classes principais;
- controllers, services e repositories;
- configuracao de banco relacional no `command`;
- configuracao de leitura no `query`;
- integracao entre escrita e leitura via eventos;
- testes automatizados;
- arquivos `application.yml` com propriedades preenchidas.

## Proximos passos sugeridos

Uma evolucao natural para este projeto seria:

1. transformar `command` e `query` em aplicacoes Spring Boot executaveis;
2. modelar os casos de uso de escrita e leitura;
3. persistir escrita com MySQL no modulo `command`;
4. publicar eventos com Kafka apos comandos;
5. materializar projecoes de leitura no modulo `query`;
6. expor endpoints REST para comandos e consultas.

## Observacao

Este repositorio esta com cara de **base de treinamento/estudo**, nao de aplicacao finalizada. A README foi escrita refletindo o codigo atual, para evitar documentar funcionalidades que ainda nao existem.
