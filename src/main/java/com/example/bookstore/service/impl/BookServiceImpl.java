package com.example.bookstore.service.impl;

import com.example.bookstore.book.QBook;
import com.example.bookstore.dto.BookCreateRequestDto;
import com.example.bookstore.dto.BookPageResponseDto;
import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.dto.BookUpdateRequestDto;
import com.example.bookstore.entity.*;
import com.example.bookstore.entity.enums.ActionType;
import com.example.bookstore.entity.enums.Binding;
import com.example.bookstore.entity.enums.BookStatus;
import com.example.bookstore.exception.BookNotFoundException;
import com.example.bookstore.exception.LibraryNotFoundException;
import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.repository.BookHistoryRepository;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.LibraryRepository;
import com.example.bookstore.service.BookService;
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

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;
    private final BookMapper bookMapper;
    private final BookHistoryRepository bookHistoryRepository;

    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookMapper.fromBookToBookResponseDto(bookRepository.findAll());
    }

    @Override
    public BookResponseDto findBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        return bookMapper.fromBookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto createBook(User user, BookCreateRequestDto bookDto) {
        Library library = libraryRepository.findById(bookDto.getLibraryId())
                .orElseThrow(() -> new LibraryNotFoundException(bookDto.getLibraryId()));

        Book newBook = bookMapper.fromBookCreateRequestDtoToBook(bookDto, library);

        BookHistory bookHistory = BookHistory
                                        .builder()
                                        .actionDate(LocalDateTime.now())
                                        .actionType(ActionType.ADD)
                                        .performedByUser(user)
                                        .book(newBook)
                                        .build();
        bookRepository.save(newBook);
        bookHistoryRepository.save(bookHistory);
        return bookMapper.fromBookToBookResponseDto(newBook);
    }

    @Override
    public BookResponseDto updateBook(User user, BookUpdateRequestDto bookUpdateDto) {
        Book book = bookRepository.findById(bookUpdateDto.getBookId())
                .orElseThrow(() -> new BookNotFoundException(bookUpdateDto.getBookId()));

        book.setAuthor(bookUpdateDto.getAuthor());
        book.setTitle(bookUpdateDto.getTitle());
        book.setPages(bookUpdateDto.getPages());
        book.setCost(bookUpdateDto.getCost());
        book.setBinding(bookUpdateDto.getBinding());

        BookHistory bookHistory = BookHistory
                .builder()
                .actionDate(LocalDateTime.now())
                .actionType(ActionType.MOD)
                .book(book)
                .performedByUser(user)
                .build();

        bookRepository.save(book);
        bookHistoryRepository.save(bookHistory);
        return bookMapper.fromBookToBookResponseDto(book);
    }

    @Override
    public Map<String, String> deleteBookById(User user, Long bookId) {
        Book deletedBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        deletedBook.setDeletedBy(user);
        deletedBook.setDeletedAt(LocalDateTime.now());

        BookHistory bookHistory = BookHistory
                .builder()
                .actionDate(LocalDateTime.now())
                .actionType(ActionType.DEL)
                .book(deletedBook)
                .performedByUser(user)
                .build();

        bookRepository.save(deletedBook);
        bookHistoryRepository.save(bookHistory);
        return Map.of("message", "Deletion of book with ID=" + bookId + " was successful.");
    }

    @Override
    public BookPageResponseDto findByAuthorContaining(String authorSubstring, int page, int size, String sortBy, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));

        Page<Book> bookPage = bookRepository.findByAuthorContaining(authorSubstring, pageable);

        return bookMapper.fromBookPageToBookPageResponseDto(pageable, bookPage);
    }

    @Override
    public BookPageResponseDto findByPagesGreaterThan(Long minPages, int page, int size, String sortBy, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));

        Page<Book> bookPage = bookRepository.findByPagesGreaterThan(minPages, pageable);

        return bookMapper.fromBookPageToBookPageResponseDto(pageable, bookPage);
    }

    @Override
    public BookPageResponseDto findByCostLessThan(Double maxCost, int page, int size, String sortBy, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));

        Page<Book> bookPage = bookRepository.findByCostLessThan(maxCost, pageable);

        return bookMapper.fromBookPageToBookPageResponseDto(pageable, bookPage);
    }

    @Override
    public BookPageResponseDto findByBinding(Binding binding, int page, int size, String sortBy, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        Page<Book> bookPage = bookRepository.findByBinding(binding, pageable);

        return bookMapper.fromBookPageToBookPageResponseDto(pageable, bookPage);
    }

    @Override
    public BookPageResponseDto findByLibraryId(Long libraryId, int page, int size, String sortBy, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        Page<Book> bookPage = bookRepository.findByLibraryId(libraryId, pageable);

        return bookMapper.fromBookPageToBookPageResponseDto(pageable, bookPage);
    }

    @Override
    public BookResponseDto receiveBookFromLibrary(User user, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        book.setBookStatus(BookStatus.BORROWED);
        book.setReceivedByUser(user);

        BookHistory bookHistory = BookHistory
                .builder()
                .actionDate(LocalDateTime.now())
                .actionType(ActionType.RECEIVED)
                .book(book)
                .performedByUser(user)
                .build();

        bookRepository.save(book);
        bookHistoryRepository.save(bookHistory);
        return bookMapper.fromBookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto returnBookToLibrary(User user, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        book.setBookStatus(BookStatus.AVAILABLE);
        book.setReceivedByUser(null);

        BookHistory bookHistory = BookHistory
                .builder()
                .actionDate(LocalDateTime.now())
                .actionType(ActionType.RETURNED)
                .book(book)
                .performedByUser(user)
                .build();

        bookRepository.save(book);
        bookHistoryRepository.save(bookHistory);
        return bookMapper.fromBookToBookResponseDto(book);
    }

    @Override
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
        return bookMapper.fromBookPageToBookPageResponseDto(pageable, bookPage);
    }
}