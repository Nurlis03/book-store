package com.example.bookstore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class BookPageResponseDto {
    private List<BookResponseDto> books;
    private Long totalRecords;
    private Integer currentPage;
    private Integer totalPages;
    private Integer nextPage;
    private Integer prevPage;
}
