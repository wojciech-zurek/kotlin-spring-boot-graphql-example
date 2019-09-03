# kotlin-spring-boot-graphql-example
kotlin-spring-boot-graphql-example

## GraphQL Schema
book.graphqls
```graphql
type Book {
    publicId: ID!,
    title: String,
    author: String
}
type Query {
    books(size: Int!):[Book]
    book(publicId: ID!):Book
}
type Mutation {
    createBook(title: String!, author: String!):Book
    updateBook(publicId: ID!, title: String!, author: String!):Book
    deleteBook(publicId: ID!): ID
}
```

## GraphQL Endpoint
```
/graphql
```
Example: http://localhost:8080/graphql

## GraphiQL Endpoint
```
/graphiql
```
Example: http://localhost:8080/graphiql

More info: https://github.com/graphql/graphiql

## Altair Endpoint
```
/altair
```
Example: http://localhost:8080/altair

More info: https://github.com/imolorhe/altair

## Voyager Endpoint
```
/voyager
```
Example: http://localhost:8080/voyager

More info: https://github.com/APIs-guru/graphql-voyager

## Download

```bash
    git clone https://github.com/wojciech-zurek/kotlin-spring-boot-graphql-example.git
```

## Run with gradle

```bash
    cd kotlin-spring-boot-graphql-example/
    ./gradlew bootRun
```

## Run as jar file

```bash
    cd kotlin-spring-boot-graphql-example/
    ./gradlew bootJar
    java -jar build/libs/kotlin-spring-boot-graphql-example-0.0.1-SNAPSHOT.jar
```

## Test

```bash
    cd kotlin-spring-boot-graphql-example/
    ./gradlew cleanTest test
`````