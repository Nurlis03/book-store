package com.example.bookstore.exception;

public class BookHistoryNotFoundException extends RuntimeException {
    public BookHistoryNotFoundException(Long id) {
        super("Book history not found with Id: " + id);
    }
}
