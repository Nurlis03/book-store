package com.example.bookstore.dto;

import com.example.bookstore.entity.enums.ActionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BookHistoryResponseDto {

    private ActionType actionType;

    private Long bookId;

    private Long userId;

    private LocalDateTime actionDate;

    private String comment;
}
