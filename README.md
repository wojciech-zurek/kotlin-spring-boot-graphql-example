# kotlin-spring-boot-graphql-example
kotlin-spring-boot-graphql-example

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