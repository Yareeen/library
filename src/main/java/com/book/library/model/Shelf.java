package com.book.library.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "shelves")
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    @OneToMany(mappedBy = "shelf")
    private List<Book> books;

    public Shelf(Long id, String location, List<Book> books) {
        this.id = id;
        this.location = location;
        this.books = books;
    }

    public Shelf() {
    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}