package com.example.bookstore.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Book not found with Id: " + id);
    }
}
