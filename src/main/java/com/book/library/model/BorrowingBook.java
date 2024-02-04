package com.book.library.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BorrowingBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //bir kullanıcının aynı anda birden fazla kitap almaması için service kısmında kontrol sağlanacaktır.
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


    @Column(nullable = false)
    private LocalDate borrowDate; //LocalDatede gün ay yıl bulunmaktadır.

    @Column(nullable = false)
    private LocalDate dueDate; //teslim edilmesi gereken tarih

    @Column(nullable = true)
    private LocalDate returnDate; // Geri verme tarihi

    // Ceza ücreti, kitap geri verildiğinde hesaplanıp buraya kaydedilebilir
    @Column(nullable = true)
    private Double fine;

    public BorrowingBook(Long id, User user, Book book, LocalDate borrowDate, LocalDate dueDate, LocalDate returnDate, Double fine) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fine = fine;
    }

    public BorrowingBook() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public Double getFine() {
        return fine;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }
}