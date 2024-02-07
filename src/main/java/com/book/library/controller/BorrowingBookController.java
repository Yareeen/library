package com.book.library.controller;

import com.book.library.model.BorrowingBook;
import com.book.library.service.BorrowingBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class BorrowingBookController {

    private final BorrowingBookService borrowingBookService;

    public BorrowingBookController(BorrowingBookService borrowingBookService) {
        this.borrowingBookService = borrowingBookService;
    }
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@PathVariable Long userId, @PathVariable Long bookId) {
        try {
            BorrowingBook borrowing = borrowingBookService.borrowBook(userId, bookId);
            return ResponseEntity.ok(borrowing);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping("/deliver/{borrowingId}")
    public ResponseEntity<?> deliverBook(@PathVariable Long borrowingId) {
        try {
            BorrowingBook borrowing = borrowingBookService.deliverBook(borrowingId);
            return ResponseEntity.ok(borrowing);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}