package com.book.library.service;

import com.book.library.exception.EntityNotFountException;
import com.book.library.model.Shelf;
import com.book.library.repository.ShelfRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelfService {

    private final ShelfRepository shelfRepository;

    public ShelfService(ShelfRepository shelfRepository) {
        this.shelfRepository = shelfRepository;
    }

    public Shelf saveShelf(Shelf shelf) {
        return shelfRepository.save(shelf);
    }

    public Shelf getShelfById(Long shelfId) {
        return shelfRepository.findById(shelfId).orElse(null);
    }

    public List<Shelf> getAllShelves() {
        return shelfRepository.findAll();
    }


    public Shelf updateShelf(Long shelfId, Shelf updatedShelf) {
        Shelf existingShelf = shelfRepository.findById(shelfId).orElse(null);

        if (existingShelf != null) {

            return shelfRepository.save(updatedShelf);
        }
        else
            // Raf bulunamadı, hata işleme yapılabilir.
            return null;

    } //TODO: shelfin updateini controllera ekle.

    public void deleteShelf(Long shelfId) {
        shelfRepository.deleteById(shelfId);
    }


    public Shelf findById(Long id) {
        return shelfRepository.findById(id).orElseThrow(() -> new EntityNotFountException("Shelf not found"));
    }
}