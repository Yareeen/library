package com.book.library.view;

import java.util.List;

public interface ShelfView {

    Long getId();

    String getName();

    List<ShelfBookView> getBooks();

    interface ShelfBookView {
        Long getId();
        String getTitle();
        String getAuthor();
    }
}
