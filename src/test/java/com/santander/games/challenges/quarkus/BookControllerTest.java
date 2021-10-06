package com.santander.games.challenges.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
public class BookControllerTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/books/hello")
          .then()
             .statusCode(200)
             .body(is("Hello Spring on Quarkus"));
    }

    @Test
    public void testBooksEndpoint() {
        given()
                .when().get("/books")
                .then()
                .statusCode(200)
                .body(containsString("Sapiens"))
                .body(containsString("Homo Deus"))
                .body(containsString("Enlightenment Now"))
                .body(containsString("Factfulness"))
                .body(containsString("Sleepwalkers"))
                .body(containsString("The Silk Roads"))
                .body(containsString("The culture map"))
                .body(containsString("Billy Summers"))
                .body(containsString("The Handmaids Tale"))
                .body(containsString("The Institue"));
    }

    @Test
    public void testBookByNameEndpoint() {
        given()
                .when().get("/books/name/Sapiens")
                .then()
                .statusCode(200)
                .body(containsString("Sapiens"));
    }

    @Test
    public void testBookByIdEndpoint() {
        given()
                .when().get("/books/id/1")
                .then()
                .statusCode(200)
                .body(containsString("Sapiens"));
//                .extract().body().jsonPath().getObject(".", Book.class);
//        assertThat(book).extracting(Book::getName).isEqualTo("Sapiens");

    }

    @Test
    public void testBooksByPublicationYearBetweenEndpoint() {
        given()
                .when().get("/books/2015/2018")
                .then()
                .statusCode(200)
                .body(containsString("Homo Deus"))
                .body(containsString("Enlightenment Now"))
                .body(containsString("Factfulness"))
                .body(containsString("The Silk Roads"));

    }

}