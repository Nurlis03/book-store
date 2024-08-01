package com.example.bookstore.dto;

import com.example.bookstore.entity.Binding;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class BookCreateDto {
    private String author;

    private int pages;

    private double cost;

    private Binding binding;
}
