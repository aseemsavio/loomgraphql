The GraphQL endpoint can be reached through this cURL command.

```
curl --location --request POST 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"query {\n    people {\n        name\n    }\n}","variables":{}}'
```