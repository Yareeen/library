package com.book.library.view;

public interface BookView {

    Long getId();

    String getTitle();

    String getAuthor();

    ShelfView getShelf();

    interface ShelfView {
        Long getId();
        String getName();
    }
}
