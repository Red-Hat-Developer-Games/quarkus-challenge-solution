package com.santander.games.challenges.quarkus;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/books")
public class BookResource {

    @Inject
    private BookRepository bookRepository;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> books() {
        return Book.listAll();
    }

    @GET
    @Path("/{lower}/{higher}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> findByPublicationYear(@PathParam("lower")Integer lower, @PathParam("higher") Integer higher){
        return bookRepository.findByPublicationYearBetween(lower,higher);
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book findByName(@PathParam("name")String name){
        return bookRepository.findByName(name);
    }
}