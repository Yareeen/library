package com.book.library.model;

import jakarta.persistence.*;

@Entity
@Table(name = "librarians")
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}