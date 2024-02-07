package com.book.library.controller;

import com.book.library.DTO.SaveBookRequestDto;
import com.book.library.service.BookService;
import com.book.library.utils.ResponseCreator;
import com.book.library.view.BookView;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<ResponseCreator<Page<BookView>>> getAllBooks(@RequestParam int page,
                                                                       @RequestParam int size) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseCreator<>(bookService.getAllBooks(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCreator<BookView>> getBookById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseCreator<>(bookService.getBookById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseCreator<Long>> addBook(@RequestBody SaveBookRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseCreator<>(bookService.addBook(requestDto).getId()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseCreator<Long>> updateBook(@PathVariable Long id,
                                                            @RequestBody SaveBookRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseCreator<>(bookService.updateBook(id, requestDto).getId()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCreator<Long>> deleteBook(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseCreator<>(bookService.deleteBook(id).getId()));
    }
}
