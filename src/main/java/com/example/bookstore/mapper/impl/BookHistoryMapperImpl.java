package com.example.bookstore.mapper.impl;

import com.example.bookstore.dto.BookHistoryResponseDto;
import com.example.bookstore.entity.BookHistory;
import com.example.bookstore.mapper.BookHistoryMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookHistoryMapperImpl implements BookHistoryMapper {
    @Override
    public List<BookHistoryResponseDto> fromBookHistoryToBookHistoryResponseDto(List<BookHistory> bookHistories) {
        return bookHistories
                .stream()
                .map(this::fromBookHistoryToBookHistoryResponseDto)
                .toList();
    }

    @Override
    public BookHistoryResponseDto fromBookHistoryToBookHistoryResponseDto(BookHistory bookHistory) {
        return BookHistoryResponseDto
                .builder()
                .bookId(bookHistory.getBook().getId())
                .comment(bookHistory.getComment())
                .actionType(bookHistory.getActionType())
                .userId(bookHistory.getPerformedByUser().getId())
                .build();
    }
}
