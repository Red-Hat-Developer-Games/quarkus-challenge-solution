package com.santander.games.challenges.quarkus;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/books")
public class BookResource {

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
        return Book.findByPublicationYearBetween(lower,higher);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteById(@PathParam("id")Integer id){
        Book.deleteById(1);
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book findById(@PathParam("id")Integer id){
        return Book.findById(1);
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book findByName(@PathParam("name")String name){
        return Book.findByName(name);
    }
}