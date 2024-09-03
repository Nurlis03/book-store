package com.example.bookstore.mapper.impl;

import com.example.bookstore.dto.BookCreateRequestDto;
import com.example.bookstore.dto.BookPageResponseDto;
import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.enums.BookStatus;
import com.example.bookstore.entity.Library;
import com.example.bookstore.mapper.BookMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapperImpl implements BookMapper {

    @Override
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

    @Override
    public List<BookResponseDto> fromBookToBookResponseDto(List<Book> books) {
        return books.stream()
                .map(this::fromBookToBookResponseDto)
                .toList();
    }

    @Override
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

    @Override
    public BookPageResponseDto fromBookPageToBookPageResponseDto(Pageable pageable, Page<Book> bookPage) {
//        Integer result = doSmth(
//                (arg) -> arg++,
//                1
//        );

        List<BookResponseDto> bookResponseDtoList = bookPage.getContent()
                .stream()
                .map(this::fromBookToBookResponseDto)
                .collect(Collectors.toList());

        Integer nextPage = null;
        Integer previousPage = null;

        if (bookPage.hasNext()) {
            nextPage = bookPage.nextPageable().getPageNumber();
        }
        if (bookPage.hasPrevious()) {
            previousPage = bookPage.previousPageable().getPageNumber();
        }
        return BookPageResponseDto
                .builder()
                .books(bookResponseDtoList)
                .totalRecords(bookPage.getTotalElements())
                .currentPage(bookPage.getNumber())
                .totalPages(bookPage.getTotalPages())
                .nextPage(nextPage)
                .prevPage(previousPage)
                .build();
    }

    //    private Integer doSmth(MyLambda lambda, Integer a) {
//        return lambda.execute(a);
//    }

}
