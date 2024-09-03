package com.example.bookstore.service.impl;

import com.example.bookstore.dto.BookHistoryResponseDto;
import com.example.bookstore.entity.BookHistory;
import com.example.bookstore.exception.BookHistoryNotFoundException;
import com.example.bookstore.mapper.BookHistoryMapper;
import com.example.bookstore.repository.BookHistoryRepository;
import com.example.bookstore.service.BookHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookHistoryServiceImpl implements BookHistoryService {

    private final BookHistoryRepository bookHistoryRepository;
    private final BookHistoryMapper bookHistoryMapper;

    @Override
    public List<BookHistoryResponseDto> getAllBooksHistories() {
        return bookHistoryMapper.fromBookHistoryToBookHistoryResponseDto(
                bookHistoryRepository.findAll()
        );
    }

    @Override
    public BookHistoryResponseDto findBookHistoryById(Long id) {
        BookHistory bookHistory = bookHistoryRepository.findById(id)
                .orElseThrow(() -> new BookHistoryNotFoundException(id));
        return bookHistoryMapper.fromBookHistoryToBookHistoryResponseDto(bookHistory);
    }
}
