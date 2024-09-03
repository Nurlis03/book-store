package com.example.bookstore.service;

import com.example.bookstore.dto.BookHistoryResponseDto;

import java.util.List;

public interface BookHistoryService {
    List<BookHistoryResponseDto> getAllBooksHistories();

    BookHistoryResponseDto findBookHistoryById(Long id);
}
