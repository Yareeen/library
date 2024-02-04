package com.book.library.controller;

import com.book.library.model.Shelf;
import com.book.library.service.ShelfService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelves")
public class ShelfController {

    private final ShelfService shelfService;

    public ShelfController(ShelfService shelfService) {
        this.shelfService = shelfService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Shelf> createShelf(@RequestBody Shelf shelf) {
        Shelf createdShelf = shelfService.saveShelf(shelf);
        return new ResponseEntity<>(createdShelf, HttpStatus.CREATED);
    }

    @GetMapping("/{shelfId}")
    public ResponseEntity<Shelf> getShelf(@PathVariable Long shelfId) {
        Shelf shelf = shelfService.getShelfById(shelfId);
        return shelf != null ? new ResponseEntity<>(shelf, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Shelf>> getAllShelves() {
        List<Shelf> shelves = shelfService.getAllShelves();
        return new ResponseEntity<>(shelves, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{shelfId}")
    public ResponseEntity<Void> deleteShelf(@PathVariable Long shelfId) {
        shelfService.deleteShelf(shelfId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}