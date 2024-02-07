package com.book.library.service;

import com.book.library.model.Book;
import com.book.library.model.BorrowingBook;
import com.book.library.model.User;
import com.book.library.repository.BookRepository;
import com.book.library.repository.BorrowingBookRepository;
import com.book.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowingBookService {

    private final BorrowingBookRepository borrowingBookRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;


    public BorrowingBookService(BorrowingBookRepository borrowingBookRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.borrowingBookRepository = borrowingBookRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public BorrowingBook borrowBook(Long userId, Long bookId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("Kullanıcı bulunamadı."));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new Exception("Kitap bulunamadı."));

        // Kullanıcının halihazırda ödünç aldığı ve henüz geri vermediği kitapları kontrol et
        if (borrowingBookRepository.existsByUserIdAndReturnDateIsNull(userId)) {
            throw new Exception("Kullanıcı zaten bir kitabı ödünç almış ve henüz geri vermemiş.");
        }

        // Eğer aldığı kitapları geri vermişse kitabı ödünç al
        BorrowingBook borrowing = new BorrowingBook();
        borrowing.setUser(user);
        borrowing.setBook(book);
        borrowing.setBorrowDate(LocalDate.now());
        borrowing.setDueDate(LocalDate.now().plusDays(14)); // 14 gün sonrasını teslim tarihi olarak ayarla

        return borrowingBookRepository.save(borrowing);
    }

    public BorrowingBook deliverBook(Long borrowingId) throws Exception {
        BorrowingBook borrowing = borrowingBookRepository.findById(borrowingId).orElseThrow(() -> new Exception("Ödünç alma kaydı bulunamadı."));

        if (borrowing.getReturnDate() != null) {
            throw new Exception("Bu kitap zaten iade edilmiş.");
        }

        borrowing.setReturnDate(LocalDate.now());
        //long daysLate = ChronoUnit.DAYS.between(borrowing.getDueDate(), LocalDate.now());
/*
        if (daysLate > 0) {
            double fine = calculateFine(daysLate); // Gecikme cezasını hesapla
            borrowing.setFine(fine);
        }
*/
        return borrowingBookRepository.save(borrowing);
    }

    private double calculateFine(long daysLate) {
        final double dailyFineRate = 1.5; // Günlük ceza oranı, daha esnek bir yapı için bir yapılandırma dosyasından alınabilir
        return daysLate * dailyFineRate;
    }
}
