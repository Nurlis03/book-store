package com.example.bookstore.controller;

import com.example.bookstore.dto.BookHistoryResponseDto;
import com.example.bookstore.service.BookHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class BookHistoryController {
    public final BookHistoryService bookHistoryService;

    @GetMapping("/books/histories")
    public List<BookHistoryResponseDto> getAllBooks() {
        return bookHistoryService.getAllBooksHistories();
    }

    @GetMapping("/books/histories/{id}")
    public BookHistoryResponseDto getBookById(@PathVariable("id") Long id) {
        return bookHistoryService.findBookHistoryById(id);
    }
}
