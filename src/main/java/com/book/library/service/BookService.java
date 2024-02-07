package com.book.library.service;

import com.book.library.DTO.SaveBookRequestDto;
import com.book.library.exception.EntityNotFountException;
import com.book.library.model.Book;
import com.book.library.repository.BookRepository;
import com.book.library.view.BookView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ShelfService shelfService;


    public BookService(BookRepository bookRepository, ShelfService shelfService) {
        this.bookRepository = bookRepository;
        this.shelfService = shelfService;
    }

    public Page<BookView> getAllBooks(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return bookRepository.findBy(pageRequest);
    }

    public BookView getBookById(Long id) {
        return bookRepository.getBookById(id).orElseThrow(() -> new EntityNotFountException("Book not found"));
    }

    public Book addBook(SaveBookRequestDto requestDto) {
        Book book = new Book();

        book.setTitle(requestDto.getTitle());
        book.setAuthor(requestDto.getAuthor());
        book.setShelf(shelfService.findById(requestDto.getShelfId()));

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, SaveBookRequestDto requestDto) {
        Book book = findById(id);

        book.setTitle(requestDto.getTitle());
        book.setAuthor(requestDto.getAuthor());
        book.setShelf(shelfService.findById(requestDto.getShelfId()));

        return bookRepository.save(book);
    }


    public Book deleteBook(Long id) {
        Book book = findById(id);
        bookRepository.delete(book);
        return book;
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFountException("Book not found"));
    }
}