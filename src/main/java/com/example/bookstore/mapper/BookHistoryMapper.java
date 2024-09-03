package com.example.bookstore.mapper;

import com.example.bookstore.dto.BookHistoryResponseDto;
import com.example.bookstore.entity.BookHistory;

import java.util.List;

public interface BookHistoryMapper {
    List<BookHistoryResponseDto> fromBookHistoryToBookHistoryResponseDto(List<BookHistory> bookHistories);
    BookHistoryResponseDto fromBookHistoryToBookHistoryResponseDto(BookHistory bookHistory);
}
