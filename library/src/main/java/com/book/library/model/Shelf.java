package com.book.library.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shelves")
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private Book books; //kitaptan gelecek
}