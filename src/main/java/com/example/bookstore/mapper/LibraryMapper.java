package com.example.bookstore.mapper;

import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.dto.LibraryCreateRequestDto;
import com.example.bookstore.dto.LibraryResponseDto;
import com.example.bookstore.entity.Library;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryMapper {
    public LibraryResponseDto fromLibraryToLibraryResponseDto(Library library) {
        List<BookResponseDto> bookResponseDtos = library.getBooks().stream()
                .map(book -> BookResponseDto
                        .builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .pages(book.getPages())
                        .cost(book.getCost())
                        .binding(book.getBinding())
                        .bookStatus(book.getBookStatus())
                        .build())
                .collect(Collectors.toList());

        return LibraryResponseDto
                .builder()
                .id(library.getId())
                .title(library.getTitle())
                .address(library.getAddress())
                .books(bookResponseDtos)
                .build();
    }

    public List<LibraryResponseDto> fromLibraryToLibraryResponseDto(List<Library> libraries) {
        return libraries.stream()
                .map(this::fromLibraryToLibraryResponseDto)
                .collect(Collectors.toList());
    }

    public Library fromLibraryCreateRequestDtoToLibrary(LibraryCreateRequestDto libraryDto) {
        return Library
                .builder()
                .title(libraryDto.getTitle())
                .address(libraryDto.getAddress())
                .books(new ArrayList<>())
                .build();
    }
}
