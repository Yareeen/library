package com.book.library.utils;

import java.time.LocalDateTime;

public class ResponseCreator<T> {

    private final T data;
    private final LocalDateTime timestamp;

    public ResponseCreator(T data) {
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
