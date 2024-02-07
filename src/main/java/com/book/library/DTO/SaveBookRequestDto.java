package com.book.library.DTO;

public class SaveBookRequestDto {

    private String title;
    private String author;
    private Long shelfId;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Long getShelfId() {
        return shelfId;
    }

}