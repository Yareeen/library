package com.book.library.service;

import com.book.library.model.Book;
import com.book.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    //TODO: pagination eklenecek
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void updateBook(Long id, Book updatedBook) {
        if (bookRepository.existsById(id)) {
            updatedBook.setId(id);
            bookRepository.save(updatedBook);
        }
        // else: Kitap bulunamadı, hata işleme yapılabilir.
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}