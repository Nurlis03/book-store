package com.example.bookstore.controller;

import com.example.bookstore.dto.BookCreateDto;
import com.example.bookstore.dto.BookUpdateDto;
import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class BookController {
    BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/books")
    public ResponseEntity<String> addBook(@RequestBody BookCreateDto bookCreateDto) {
        return bookService.createBook(bookCreateDto);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") Long bookId, @RequestBody BookUpdateDto bookUpdateDto) {
        return bookService.updateBook(bookId, bookUpdateDto);
    }
}
