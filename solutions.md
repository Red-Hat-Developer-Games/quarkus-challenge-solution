## Solutions

## Solution 2: using the repository pattern

Guide: https://quarkus.io/guides/hibernate-orm-panache

branch: solution-2-repository-pattern

1 - Project generation
```shell
mvn io.quarkus:quarkus-maven-plugin:2.2.1.Final:create \
    -DprojectGroupId=com.santander.games.challenges.quarkus \
    -DprojectArtifactId=quarkus-challenge \
    -DclassName="com.santander.games.challenges.quarkus.BookResource" \
    -Dpath="/books" \
    -Dextensions="hibernate-orm-panache, jdbc-postgresql, resteasy-jackson"
```
Database:

```shell
#%prod.quarkus.datasource.jdbc.url=jdbc:postgresql://library-database.user150-quarkus-challenge:5432/library_database
#locally
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/library_database
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=book
quarkus.datasource.password=book
quarkus.datasource.max-size=8
quarkus.datasource.min-size=2
quarkus.hibernate-orm.sql-load-script=import.sql

quarkus.hibernate-orm.database.generation=drop-and-create
```


Trouble shooting:

2021-09-03 12:34:03,985 ERROR [org.jbo.res.res.i18n] (executor-thread-0) RESTEASY002005: Failed executing GET /books/Sapiens: org.jboss.resteasy.core.NoMessageBodyWriterFoundFailure: Could not find MessageBodyWriter for response object of type: com.santander.games.challenges.quarkus.Book of media type: text/html;charset=UTF-8
at org.jboss.resteasy.core.ServerResponseWriter.lambda$writeNomapResponse$3(ServerResponseWriter.java:125)
at org.jboss.resteasy.core.interception.jaxrs.ContainerResponseContextImpl.filter(ContainerResponseContextImpl.java:404)
at org.jboss.resteasy.core.ServerResponseWriter.executeFilters(ServerResponseWriter.java:252)
at org.jboss.resteasy.core.ServerResponseWriter.writeNomapResponse(ServerResponseWriter.java:101)

El problema era que faltaba el mediatype en el resource method: @Produces(MediaType.APPLICATION_JSON)

== Running DBs locally with Docker
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name library-database -e POSTGRES_USER=book -e POSTGRES_PASSWORD=book -e POSTGRES_DB=library_database -p 5432:5432 postgres:11.5


Deploy

Si es desde la maquina local, saltar los tests pq da error de no conexion a la Database, no la ve porque esta en openshift
./mvnw clean package -Dquarkus.kubernetes.deploy=true

