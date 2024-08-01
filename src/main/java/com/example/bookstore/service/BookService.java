package com.example.bookstore.service;

import com.example.bookstore.dto.BookCreateDto;
import com.example.bookstore.dto.BookUpdateDto;
import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public ResponseEntity<String> createBook(BookCreateDto bookDto) {
        Book newBook = Book.builder()
                .author(bookDto.getAuthor())
                .pages(bookDto.getPages())
                .binding(bookDto.getBinding())
                .cost(bookDto.getCost())
                .build();
        bookRepository.save(newBook);
        return new ResponseEntity<>("Book saved successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> updateBook(Long bookId, BookUpdateDto bookUpdateDto) {
        Book book = getBookById(bookId);
        if (book != null) {
            book.setAuthor(bookUpdateDto.getAuthor());
            bookRepository.save(book);
            return new ResponseEntity<>("Book updated successfully with id: " + bookId, HttpStatus.OK);
        }
        return new ResponseEntity<>("Book with id " + bookId + " not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteBookById(Long bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return new ResponseEntity<>("Book with id " + bookId + " successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Book with id " + bookId + " not found", HttpStatus.NOT_FOUND);
    }
}
