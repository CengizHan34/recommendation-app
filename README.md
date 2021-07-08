# recommendation-app

It is a project that allows users to make recommendations to users by looking at their browser history and products they have purchased in the past.

Used technologies
  - Spring Boot
  - Java 11
  - Kafka
  - Docker
  - Postgresql
  - JMockit

Port numbers where applications run

> api - localhost:9090

> etl-process-app - localhost:9100

> stream-reader-app - localhost:8080

> view-producer-app - localhost:8090

After running kafka and postgresql using docker-compose yml, run the 'mvn clean install' script from the terminal and run 
the projects as view-producer-app, stream-reader-app, etl-process-app and api respectively.

Note: Postman collection under doc folder

