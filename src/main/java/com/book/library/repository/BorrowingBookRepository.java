package com.book.library.repository;

import com.book.library.model.BorrowingBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingBookRepository extends JpaRepository<BorrowingBook, Long> {
    boolean existsByUserIdAndReturnDateIsNull(Long userId);
}