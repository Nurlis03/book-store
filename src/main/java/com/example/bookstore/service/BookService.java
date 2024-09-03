package com.example.bookstore.service;

import com.example.bookstore.dto.BookCreateRequestDto;
import com.example.bookstore.dto.BookPageResponseDto;
import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.dto.BookUpdateRequestDto;
import com.example.bookstore.entity.enums.Binding;
import com.example.bookstore.entity.User;

import java.util.List;
import java.util.Map;

public interface BookService {

    List<BookResponseDto> getAllBooks();

    BookResponseDto findBookById(Long bookId);

    BookResponseDto createBook(User user, BookCreateRequestDto bookDto);
    BookResponseDto updateBook(User user, BookUpdateRequestDto bookUpdateDto);
    Map<String, String> deleteBookById(User user, Long bookId);

    BookPageResponseDto findByAuthorContaining(
            String authorSubstring,
            int page,
            int size,
            String sortBy,
            String sortOrder
    );

    BookPageResponseDto findByPagesGreaterThan(Long minPages, int page, int size, String sortBy, String sortOrder);
    BookPageResponseDto findByCostLessThan(Double maxCost, int page, int size, String sortBy, String sortOrder);
    BookPageResponseDto findByBinding(Binding binding, int page, int size, String sortBy, String sortOrder);
    BookPageResponseDto findByLibraryId(Long libraryId, int page, int size, String sortBy, String sortOrder);
    BookResponseDto receiveBookFromLibrary(User user, Long bookId);
    BookResponseDto returnBookToLibrary(User user, Long bookId);
    BookPageResponseDto searchBooks(String author, Long minPages,
                                           Double maxCost, Binding binding,
                                           Long libraryId, int page,
                                           int size, String sortBy, String sortOrder);
}
