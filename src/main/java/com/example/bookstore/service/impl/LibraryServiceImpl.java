package com.example.bookstore.service.impl;

import com.example.bookstore.dto.LibraryCreateRequestDto;
import com.example.bookstore.dto.LibraryResponseDto;
import com.example.bookstore.dto.LibraryUpdateRequestDto;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Library;
import com.example.bookstore.entity.User;
import com.example.bookstore.exception.LibraryNotFoundException;
import com.example.bookstore.mapper.LibraryMapper;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.LibraryRepository;
import com.example.bookstore.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;
    private final LibraryMapper libraryMapper;

    @Override
    public List<LibraryResponseDto> getAllLibraries() {
        return libraryMapper.fromLibraryToLibraryResponseDto(libraryRepository.findAll());
    }

    @Override
    public LibraryResponseDto findLibraryById(Long libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new LibraryNotFoundException(libraryId));
        return libraryMapper.fromLibraryToLibraryResponseDto(library);
    }

    @Override
    public LibraryResponseDto createLibrary(LibraryCreateRequestDto libraryDto) {
        Library newLibrary = libraryMapper.fromLibraryCreateRequestDtoToLibrary(libraryDto);
        libraryRepository.save(newLibrary);
        return libraryMapper.fromLibraryToLibraryResponseDto(newLibrary);
    }

    @Override
    public LibraryResponseDto updateBook(LibraryUpdateRequestDto libraryDto) {
        Library library = libraryRepository.findById(libraryDto.getLibraryId())
                .orElseThrow(() -> new LibraryNotFoundException(libraryDto.getLibraryId()));

        library.setAddress(libraryDto.getAddress());
        library.setTitle(libraryDto.getTitle());
        libraryRepository.save(library);

        return libraryMapper.fromLibraryToLibraryResponseDto(library);
    }

    @Override
    public Map<String, String> deleteBookById(User user, Long libraryId) {
        Library deletedLibrary = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new LibraryNotFoundException(libraryId));

        deletedLibrary.setDeletedAt(LocalDateTime.now());
        deletedLibrary.setDeletedBy(user);

        List<Book> books = deletedLibrary.getBooks();
        for (Book book: books) {
            book.setLibrary(null);
            bookRepository.save(book);
        }

        libraryRepository.save(deletedLibrary);
        return Map.of("message", "Deletion of library with ID=" + libraryId + " was successful.");
    }
}
