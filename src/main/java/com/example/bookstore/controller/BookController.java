package com.example.bookstore.controller;

import com.example.bookstore.dto.BookCreateRequestDto;
import com.example.bookstore.dto.BookPageResponseDto;
import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.dto.BookUpdateRequestDto;
import com.example.bookstore.entity.Binding;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping("/books")
    public List<BookResponseDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public BookResponseDto getBookById(@PathVariable("id") Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping("/books")
    public BookResponseDto createBook(@RequestBody BookCreateRequestDto bookCreateDto) {
        return bookService.createBook(bookCreateDto);
    }

    @PutMapping("/books")
    public BookResponseDto updateBook(@RequestBody BookUpdateRequestDto bookUpdateDto) {
        return bookService.updateBook(bookUpdateDto);
    }

    @DeleteMapping("/books/{id}")
    public Map<String, String> deleteBook(@AuthenticationPrincipal User user, @PathVariable("id") Long bookId) {
        return bookService.deleteBookById(user, bookId);
    }

    @GetMapping("/books/search/author")
    public BookPageResponseDto findByAuthorContaining(
            @RequestParam String author,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder ) {
        return bookService.findByAuthorContaining(author, page, size, sortBy, sortOrder);
    }

    @GetMapping("/books/search/minpages")
    public BookPageResponseDto findByPagesGreaterThan(
            @RequestParam Long minPages,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder ) {
        return bookService.findByPagesGreaterThan(minPages, page, size, sortBy, sortOrder);
    }

    @GetMapping("/books/search/cost")
    public BookPageResponseDto findByCostLessThan(
            @RequestParam Double maxCost,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder ) {
        return bookService.findByCostLessThan(maxCost, page, size, sortBy, sortOrder);
    }

    @GetMapping("/books/search/binding")
    public BookPageResponseDto findByBinding(
            @RequestParam Binding binding,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder ) {
        return bookService.findByBinding(binding, page, size, sortBy, sortOrder);
    }

    // Page -> PageResponseDto
    @GetMapping("/books/search/library")
    public BookPageResponseDto findByLibraryId(
            @RequestParam Long libraryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder ) {
        return bookService.findByLibraryId(libraryId, page, size, sortBy, sortOrder);
    }

    @GetMapping("/books/search/")
    public BookPageResponseDto searchBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Long minPages,
            @RequestParam(required = false) Double maxCost,
            @RequestParam(required = false) Binding binding,
            @RequestParam(required = false) Long libraryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        return bookService.searchBooks(author, minPages, maxCost, binding, libraryId, page, size, sortBy, sortOrder);
    }

    @PostMapping("/books/retrieve/{id}")
    public BookResponseDto retrieveBookFromLibrary(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long bookId) {
        return bookService.retrieveBookFromLibrary(user, bookId);
    }

    @PostMapping("/books/return/{id}")
    public BookResponseDto returnBookToLibrary(
            @PathVariable("id") Long bookId) {
        return bookService.returnBookToLibrary(bookId);
    }
}
