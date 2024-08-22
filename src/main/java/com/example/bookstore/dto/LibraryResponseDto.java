package com.example.bookstore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class LibraryResponseDto {
    private Long id;
    private String title;
    private String address;
    private List<BookResponseDto> books;
}
