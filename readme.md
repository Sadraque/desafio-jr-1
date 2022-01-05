# API Desafio Backend JR - Blog
## Funcionalidades
- Implementado um CRUD básico para a classe de domínio Posts.
- As deleções de Posts são feitas logicamente (column deleted_at: DATE e column deleted: BOOLEAN), sendo que apos a deleção o Post ainda continua disponivel na base de dados.
- Para recuperar os Posts deletados, foi criado o endpoint /posts/restore{id}: GET
- Implementado scheduler (job) para remover fisicamente os Posts que estão deletados (logicamente). 
- Este job possui dois parametros configuraveis via arquivo de configuracap (application.yml), sendo eles: maxPeriodInDays (periodo maximo, em dias, de permanecia dos Posts excluidos na base de dados, inicialmente a cada 30 dias) e cronTimer (timer para execução do job, inicialemnte configurado de hora em hora)

## Enpoints
### POST
- /posts (save Post)

### PUT
- /posts (Update Post)

### DELETE
- /posts (Delete Post)

### GET
- /posts/{id} (Find Post by id)
- /posts/restore/{id} (Restore a deleted Post)
- /posts (Find all Post)
- /posts/date?startDate&endDate (Find all Post by startDate and endDate)

### Documentação
Documentação gerada pelo Swagger:
- endpoint: /api/docs

## Como executar? 
API desenvolvida utilizando Java 17 + Spring Boot + PostgreSQL e H2

### Collection Postman
- diretório: java/resources/postman

#### Requisitos para ambiente de desenvolvimento:
- Java 17
- PostgreSQL
- Maven
- Docker
- Docker Compose

#### subindo a aplicação:
A API configurada para rodar através de containers docker, utilizando docker-compose.
- Para iniciar a API execute o script start.sh
- Para parar a API execute o script stop.sh

#### Testando a API
Foi realizado implementação de testes unitários utilizando JUnit 4 e Mockito
- rodar o comando: mvn test
