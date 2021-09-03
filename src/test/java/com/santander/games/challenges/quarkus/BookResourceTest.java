package com.santander.games.challenges.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BookResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/books/hello")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

    @Test
    public void testBooksEndpoint() {
        given()
                .when().get("/books")
                .then()
                .statusCode(200)
                .body(Matchers.containsString("Homo Deus"))
                .body(Matchers.containsString("Enlightenment Now"))
                .body(Matchers.containsString("Factfulness"))
                .body(Matchers.containsString("Sleepwalkers"))
                .body(Matchers.containsString("Sapiens"))
                .body(Matchers.containsString("The Silk Roads"));
    }

    @Test
    public void testBookByNameEndpoint() {
        given()
                .when().get("/books/Sapiens")
                .then()
                .statusCode(200)
                .body(Matchers.containsString("Sapiens"));
    }

    @Test
    public void testBooksByPublicationYearBetweenEndpoint() {
        given()
                .when().get("/books/2015/2018")
                .then()
                .statusCode(200)
                .body(Matchers.containsString("Homo Deus"))
                .body(Matchers.containsString("Enlightenment Now"))
                .body(Matchers.containsString("Factfulness"))
                .body(Matchers.containsString("The Silk Roads"));

    }

}