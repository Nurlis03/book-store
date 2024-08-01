package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private int pages;

    private double cost;

    @Enumerated(EnumType.STRING)
    private Binding binding;
}
