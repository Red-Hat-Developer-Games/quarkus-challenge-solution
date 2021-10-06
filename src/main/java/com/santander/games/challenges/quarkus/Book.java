package com.santander.games.challenges.quarkus;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    private Integer id;

   private String name;

   private Integer publicationYear;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }
}
