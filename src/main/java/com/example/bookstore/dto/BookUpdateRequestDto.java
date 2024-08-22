package com.example.bookstore.dto;

import com.example.bookstore.entity.Binding;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateRequestDto {
    private Long bookId;
    private String author;
    private String title;
    private Long pages;
    private Double cost;
    private Binding binding;
}