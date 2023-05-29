package com.acciojob.bookManager;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String name) {
        super(name);
    }
}
