package com.santander.games.challenges.quarkus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring on Quarkus";
    }

    @GetMapping
    public Iterable<Book> findAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/{lower}/{higher}")
    public List<Book> findByPublicationYear(@PathVariable Integer lower, @PathVariable Integer higher){
        return bookRepository.findByPublicationYearBetween(lower,higher);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id){
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            throw new MissingBookException();
        }
    }

    @GetMapping("/id/{id}")
    public Book findById(@PathVariable Integer id){
        return bookRepository.findById(id).orElse(null);
    }

    @GetMapping("/name/{name}")
    public Book findByName(@PathVariable String name){
        return bookRepository.findByName(name).orElse(null);
    }
}