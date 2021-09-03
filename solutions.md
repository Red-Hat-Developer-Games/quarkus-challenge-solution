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

Trouble shooting:

2021-09-03 12:34:03,985 ERROR [org.jbo.res.res.i18n] (executor-thread-0) RESTEASY002005: Failed executing GET /books/Sapiens: org.jboss.resteasy.core.NoMessageBodyWriterFoundFailure: Could not find MessageBodyWriter for response object of type: com.santander.games.challenges.quarkus.Book of media type: text/html;charset=UTF-8
at org.jboss.resteasy.core.ServerResponseWriter.lambda$writeNomapResponse$3(ServerResponseWriter.java:125)
at org.jboss.resteasy.core.interception.jaxrs.ContainerResponseContextImpl.filter(ContainerResponseContextImpl.java:404)
at org.jboss.resteasy.core.ServerResponseWriter.executeFilters(ServerResponseWriter.java:252)
at org.jboss.resteasy.core.ServerResponseWriter.writeNomapResponse(ServerResponseWriter.java:101)

El problema era que faltaba el mediatype en el resource method: @Produces(MediaType.APPLICATION_JSON)
