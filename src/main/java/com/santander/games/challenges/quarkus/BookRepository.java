package com.santander.games.challenges.quarkus;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

    public Book findByName(String name){
        return find("name", name).firstResult();
    }

    public List<Book> findByPublicationYearBetween(Integer lower, Integer higher){
        return Book.find("publicationYear >= ?1 and publicationYear <= ?2", lower,higher).list();
    }
}
