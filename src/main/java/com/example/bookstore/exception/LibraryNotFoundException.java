package com.example.bookstore.exception;

public class LibraryNotFoundException extends RuntimeException {
    public LibraryNotFoundException(Long id) {
        super("Library not found with Id: " + id);
    }
}
