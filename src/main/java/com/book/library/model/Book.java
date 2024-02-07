package com.book.library.model;


import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @ManyToOne //her kitabın bir adet rafı vardır. Bir rafın birden çok kitabı vardır.
    @JoinColumn(name = "shelf_id", nullable = false)// shelf tablosundaki ilgili kolon adı
    private Shelf shelf; //raf bilgisi, raf modelinden gelecek.

    //TODO: isbn numarası eklenebilir.

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }
}
