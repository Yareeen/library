package com.book.library.repository;


import com.book.library.model.Book;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page findAll(Pageable pageable); //sayfalandırma için
}