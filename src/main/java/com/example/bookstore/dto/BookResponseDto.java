package com.example.bookstore.dto;

import com.example.bookstore.entity.Binding;
import com.example.bookstore.entity.BookStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BookResponseDto {
    private Long id;

    private String title;

    private String author;

    private Long pages;

    private double cost;

    private Binding binding;

    private BookStatus bookStatus;
}
