package com.book.library.repository;


import com.book.library.model.Book;
import com.book.library.view.BookView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<BookView> findBy(Pageable pageable);

    Optional<BookView> getBookById(Long id);
}