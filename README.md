# training-cqrs

Projeto de estudo para praticar o padrão **CQRS (Command Query Responsibility Segregation)** com **Java 17**, **Maven** e **Spring Boot**.

No estado atual, o repositório já está organizado como um projeto **multi-módulo**, separando responsabilidades entre escrita (`command`) e leitura (`query`), mas ainda está em fase inicial de implementação.

## Objetivo

A ideia deste projeto é servir como base de treinamento para uma arquitetura em que:

- o módulo `command` concentra operações de escrita e regras de negócio;
- o módulo `query` concentra operações de leitura e consultas otimizadas;
- tecnologias diferentes podem ser usadas para cada lado da aplicação, de acordo com a necessidade.

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

## Módulos

### `command`

Responsável pela parte de **escrita** da aplicação.

Dependências já adicionadas:

- Spring Boot
- Spring Data JPA
- MySQL Driver
- Kafka
- Validation
- Web MVC
- MapStruct
- Lombok

Entidades iniciais já criadas:

- `Product`
- `Review`

### `query`

Responsável pela parte de **leitura** da aplicação.

Atualmente herda do projeto principal dependências como:

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

No momento, as classes principais `CommandApplication` e `QueryApplication` ainda estão como classes Java simples, sem a configuração completa de inicialização do Spring Boot.

Ou seja, o projeto **ainda não está pronto para subir como duas APIs funcionais**, mas já pode ser evoluído a partir da estrutura existente.

## Estado atual

Hoje o projeto contém:

- agregação Maven com os módulos `command` e `query`;
- dependências principais para uma arquitetura CQRS;
- modelo inicial de domínio com `Product` e `Review`;
- classes de entrada para cada módulo.

Ainda faltam, por exemplo:

- anotações Spring Boot nas classes principais;
- controllers, services e repositories;
- configuração de banco relacional no `command`;
- configuração de leitura no `query`;
- integração entre escrita e leitura via eventos;
- testes automatizados;
- arquivos `application.yml` com propriedades preenchidas.

## Próximos passos sugeridos

Uma evolução natural para este projeto seria:

1. transformar `command` e `query` em aplicações Spring Boot executáveis;
2. modelar os casos de uso de escrita e leitura;
3. persistir escrita com MySQL no módulo `command`;
4. publicar eventos com Kafka após comandos;
5. materializar projeções de leitura no módulo `query`;
6. expor endpoints REST para comandos e consultas.

## Observação

Este repositório está com cara de **base de treinamento/estudo**, não de aplicação finalizada. A README foi escrita refletindo o código atual, para evitar documentar funcionalidades que ainda não existem.
