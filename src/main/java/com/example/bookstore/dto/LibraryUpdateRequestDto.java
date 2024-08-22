package com.example.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibraryUpdateRequestDto {
    private Long libraryId;
    private String title;
    private String address;
}
