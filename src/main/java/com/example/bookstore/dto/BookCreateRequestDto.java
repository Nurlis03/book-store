package com.example.bookstore.dto;

import com.example.bookstore.entity.enums.Binding;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class BookCreateRequestDto {
    private String author;
    private String title;
    private Long pages;
    private Double cost;
    private Binding binding;
    private Long libraryId;
}
