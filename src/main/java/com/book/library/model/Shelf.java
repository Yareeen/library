package com.book.library.model;

import javax.persistence.*;
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
}