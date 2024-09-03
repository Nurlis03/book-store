package com.example.bookstore.mapper;

import com.example.bookstore.dto.BookCreateRequestDto;
import com.example.bookstore.dto.BookPageResponseDto;
import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookMapper {
    BookResponseDto fromBookToBookResponseDto(Book book);
    List<BookResponseDto> fromBookToBookResponseDto(List<Book> books);
    Book fromBookCreateRequestDtoToBook(BookCreateRequestDto bookDto, Library library);
    BookPageResponseDto fromBookPageToBookPageResponseDto(Pageable pageable, Page<Book> bookPage);
}
