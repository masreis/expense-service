# Expenses

Esta é uma aplicação para controle de gastos. Para rodar a aplicação é necessário ter um MySQL instalado.

## Ports

|Application|Port|
|---|---|
|Expense Service API|8080|
|Expense Service Client API|8081|
|Spring Cloud Config Server|8888|
|Front End (Angular) |4200 (ng serve) ou 80 (Docker)|

## Expense JSON sample

```json
{
  "dateTime": "2020-11-02 02:50:12",
  "description": "Minha conta 1 updated",
  "personName": "Jose",
  "id": 1,
  "tags": "abc,def,ghi",
  "value": 101.99
}
```

## Build

mvn package
docker build -t exacta/config-server .
docker run --name exacta-config-server --network host --rm exacta/config-server

mvn clean compile package -DskipTests
docker build -t exacta/api .
docker run --name exacta-api --network host --rm exacta/api

mvn clean compile package
docker build -t exacta/client .
docker run --name exacta-client --network host --rm exacta/client

docker build -t exacta/frontend .
docker run --name exacta-frontend --network host --rm exacta/frontend
