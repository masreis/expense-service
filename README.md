#

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
