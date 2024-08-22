package com.example.bookstore.mapper;

import com.example.bookstore.dto.BookCreateRequestDto;
import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.BookStatus;
import com.example.bookstore.entity.Library;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public BookResponseDto fromBookToBookResponseDto(Book book) {
        return BookResponseDto
                .builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .cost(book.getCost())
                .pages(book.getPages())
                .binding(book.getBinding())
                .bookStatus(book.getBookStatus())
                .build();
    }

    public List<BookResponseDto> fromBookToBookResponseDto(List<Book> books) {
        return books.stream()
                .map(this::fromBookToBookResponseDto)
                .collect(Collectors.toList());
    }

    public Book fromBookCreateRequestDtoToBook(BookCreateRequestDto bookDto, Library library) {
        return Book.builder()
                .author(bookDto.getAuthor())
                .title(bookDto.getTitle())
                .pages(bookDto.getPages())
                .binding(bookDto.getBinding())
                .cost(bookDto.getCost())
                .library(library)
                .bookStatus(BookStatus.AVAILABLE)
                .build();
    }
}
