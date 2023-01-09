# Virtual Threads with Spring Boot GraphQL in Kotlin

This is a demo project exhibiting virtual threads in Kotlin.

To run the application, run the main function in the `LoomgraphqlApplication.kt` class.

To build a native image:
```
./gradlew nativeCompile
```

To run the native executable:
```
build/native/nativeCompile/loomgraphql
```

The GraphQL endpoint can be reached through this cURL command.

```
curl --location --request POST 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"query {\n    people {\n        name\n    }\n}","variables":{}}'
```
