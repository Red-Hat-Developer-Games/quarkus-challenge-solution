package com.santander.games.challenges.quarkus;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Parameters;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Book extends PanacheEntityBase {

    @Id
    public Integer id;
    public String name;
    public Integer publicationYear;

    public static Book findByName(String name){
        return find("name", name).firstResult();
    }

    public static List<Book> findByPublicationYearBetween(Integer lower, Integer higher){
        return find("SELECT book FROM Book book WHERE book.publicationYear >= ?1 and book.publicationYear <= ?2", lower,higher).list();
    }


}
