package com.santander.games.challenges.quarkus;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {


    List<Book> findByPublicationYearBetween(Integer lower, Integer higher);

    Optional<Book> findByName(String name);
}
