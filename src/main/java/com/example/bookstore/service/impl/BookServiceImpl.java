package com.example.bookstore.service;

import com.example.bookstore.book.QBook;
import com.example.bookstore.dto.BookCreateRequestDto;
import com.example.bookstore.dto.BookPageResponseDto;
import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.dto.BookUpdateRequestDto;
import com.example.bookstore.entity.*;
import com.example.bookstore.exception.BookNotFoundException;
import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.mapper.impl.BookMapperImpl;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.LibraryRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;
    private LibraryRepository libraryRepository;
    private BookMapper bookMapper;

    public List<BookResponseDto> getAllBooks() {
        return bookMapper.fromBookToBookResponseDto(bookRepository.findAll());
    }

    public BookResponseDto findBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        return bookMapper.fromBookToBookResponseDto(book);
    }

    public BookResponseDto createBook(BookCreateRequestDto bookDto) {
        Library library = libraryRepository.findById(bookDto.getLibraryId())
                .orElseThrow(() -> new BookNotFoundException(bookDto.getLibraryId()));

        Book newBook = bookMapper.fromBookCreateRequestDtoToBook(bookDto, library);
        bookRepository.save(newBook);
        return bookMapper.fromBookToBookResponseDto(newBook);
    }

    public BookResponseDto updateBook(BookUpdateRequestDto bookUpdateDto) {
        Book book = bookRepository.findById(bookUpdateDto.getBookId())
                .orElseThrow(() -> new BookNotFoundException(bookUpdateDto.getBookId()));

        book.setAuthor(bookUpdateDto.getAuthor());
        book.setTitle(bookUpdateDto.getTitle());
        book.setPages(bookUpdateDto.getPages());
        book.setCost(bookUpdateDto.getCost());
        book.setBinding(bookUpdateDto.getBinding());

        bookRepository.save(book);
        return bookMapper.fromBookToBookResponseDto(book);
    }

    public Map<String, String> deleteBookById(User user, Long bookId) {
        Book deletedBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        deletedBook.setDeletedBy(user);
        deletedBook.setDeletedAt(LocalDateTime.now());
        bookRepository.save(deletedBook);
        return Map.of("message", "Deletion of book with ID=" + bookId + " was successful.");
    }

    public BookPageResponseDto findByAuthorContaining(String authorSubstring, int page, int size, String sortBy, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));

        Page<Book> bookPage = bookRepository.findByAuthorContaining(authorSubstring, pageable);

        return fromBookPageToBooksResponseList(pageable, bookPage);
    }

    public BookPageResponseDto findByPagesGreaterThan(Long minPages, int page, int size, String sortBy, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));

        Page<Book> bookPage = bookRepository.findByPagesGreaterThan(minPages, pageable);

        return fromBookPageToBooksResponseList(pageable, bookPage);
    }

    public BookPageResponseDto findByCostLessThan(Double maxCost, int page, int size, String sortBy, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));

        Page<Book> bookPage = bookRepository.findByCostLessThan(maxCost, pageable);

        return fromBookPageToBooksResponseList(pageable, bookPage);
    }

    public BookPageResponseDto findByBinding(Binding binding, int page, int size, String sortBy, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        Page<Book> bookPage = bookRepository.findByBinding(binding, pageable);

        return fromBookPageToBooksResponseList(pageable, bookPage);
    }

    public BookPageResponseDto findByLibraryId(Long libraryId, int page, int size, String sortBy, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        Page<Book> bookPage = bookRepository.findByLibraryId(libraryId, pageable);

        return fromBookPageToBooksResponseList(pageable, bookPage);
    }

    public BookResponseDto retrieveBookFromLibrary(User user, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        book.setBookStatus(BookStatus.BORROWED);
        book.setUser(user);
        bookRepository.save(book);
        return bookMapper.fromBookToBookResponseDto(book);
    }

    public BookResponseDto returnBookToLibrary(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setUser(null);
        bookRepository.save(book);
        return bookMapper.fromBookToBookResponseDto(book);
    }

    private BookPageResponseDto fromBookPageToBooksResponseList(Pageable pageable, Page<Book> bookPage) {
//        Integer result = doSmth(
//                (arg) -> arg++,
//                1
//        );

        List<BookResponseDto> bookResponseDtoList = bookPage.getContent()
                .stream()
                .map(bookMapper::fromBookToBookResponseDto)
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

    public BookPageResponseDto searchBooks(String author, Long minPages,
                                             Double maxCost, Binding binding,
                                             Long libraryId, int page,
                                             int size, String sortBy, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));

        QBook book = QBook.book;

        BooleanBuilder builder = new BooleanBuilder();

        if (author != null) {
            builder.and(book.author.containsIgnoreCase(author));
        }
        if (minPages != null) {
            builder.and(book.pages.gt(minPages));
        }
        if (maxCost != null) {
            builder.and(book.cost.lt(maxCost));
        }
        if (binding != null) {
            builder.and(book.binding.eq(binding));
        }
        if (libraryId != null) {
            builder.and(book.library.id.eq(libraryId));
        }

        Page<Book> bookPage = bookRepository.findAll(builder, pageable);
        return fromBookPageToBooksResponseList(pageable, bookPage);
    }
}