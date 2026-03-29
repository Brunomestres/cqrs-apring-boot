# training-cqrs

Projeto de estudo para praticar **CQRS (Command Query Responsibility Segregation)** com **Java 17**, **Spring Boot**, **MySQL**, **MongoDB** e **Kafka**.

Hoje o fluxo principal do projeto funciona assim:

- o modulo `command` recebe comandos e persiste no MySQL;
- depois publica eventos no Kafka;
- o modulo `query` consome esses eventos;
- por fim materializa a leitura no MongoDB.

## Arquitetura

```text
Client
  |
  v
command API -> MySQL -> Kafka -> query API -> MongoDB
```

## Modulos

### `command`

Responsavel pela escrita.

- persiste produtos e reviews no MySQL
- publica eventos no topico `tp-query`
- endpoints de escrita para criar, atualizar e remover produtos
- endpoint para criar review

### `query`

Responsavel pela leitura.

- consome eventos do Kafka
- monta a projecao de produtos no MongoDB
- expõe endpoints de consulta

## Tecnologias

- Java 17
- Spring Boot 4.0.3
- Spring Data JPA
- Spring Data MongoDB
- MySQL
- MongoDB
- Apache Kafka
- Kafdrop
- phpMyAdmin
- Mongo Express
- Lombok
- MapStruct

## Infra com Docker

O arquivo [`docker-compose.yml`](c:\Users\BrunoMestres\Desktop\pessoal\java\training-cqrs\docker-compose.yml) sobe:

- Zookeeper
- Kafka
- Kafdrop
- MySQL
- phpMyAdmin
- MongoDB
- Mongo Express

### Subir a infra

```bash
docker compose up -d
```

### Portas

- Kafka: `9092`
- Kafdrop: `http://localhost:9000`
- MySQL: `3306`
- phpMyAdmin: `http://localhost:8081`
- MongoDB: `27018`
- Mongo Express: `http://localhost:8082`

### Credenciais

#### MySQL

- usuario: `root`
- senha: `example`
- database: `command`

#### MongoDB

- usuario: `root`
- senha: `example`
- authSource: `admin`
- database de leitura: `query`

#### Mongo Express

- login web: `admin`
- senha web: `pass`

## Configuracao das aplicacoes

### `command`

Configurado em [`command/src/main/resources/application.yml`](c:\Users\BrunoMestres\Desktop\pessoal\java\training-cqrs\command\src\main\resources\application.yml):

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/command
    username: root
    password: example
```

### `query`

Configurado em [`query/src/main/resources/application.yml`](c:\Users\BrunoMestres\Desktop\pessoal\java\training-cqrs\query\src\main\resources\application.yml):

```yml
server:
  port: 8083
spring:
  data:
    mongodb:
      uri: mongodb://root:example@localhost:27018/query?authSource=admin
```

## Endpoints

### Command API

Base path: `http://localhost:8080`

- `POST /api/v1/products`
- `PUT /api/v1/products/{id}`
- `DELETE /api/v1/products/{id}`
- `POST /api/v1/reviews`

### Query API

Base path: `http://localhost:8083`

- `GET /api/v1/products`
- `GET /api/v1/products/{id}`

## Exemplo de fluxo

### 1. Criar produto

```http
POST /api/v1/products
Content-Type: application/json

{
  "name": "Notebook",
  "description": "Notebook para estudo",
  "imageUrl": "https://exemplo.com/notebook.png",
  "value": 3500
}
```

### 2. Consultar no modulo de leitura

```http
GET /api/v1/products
```

### 3. Criar review

```http
POST /api/v1/reviews
Content-Type: application/json

{
  "productId": 1,
  "userName": "Bruno",
  "description": "Produto muito bom",
  "rating": 5
}
```

Depois disso, a review passa a ficar dentro do produto salvo no Mongo.

## Estrutura resumida

```text
training-cqrs/
|- command/
|- query/
|- docker-compose.yml
|- pom.xml
|- mvnw
`- mvnw.cmd
```

## Observacoes

- o modulo `command` usa MySQL como fonte de escrita
- o modulo `query` usa MongoDB como base de leitura
- o topico Kafka utilizado atualmente e `tp-query`
- para visualizar eventos, use o Kafdrop
- para visualizar o MySQL, use o phpMyAdmin
- para visualizar o MongoDB, use o Mongo Express
