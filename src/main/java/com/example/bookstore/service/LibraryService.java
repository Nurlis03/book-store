package com.example.bookstore.service;

import com.example.bookstore.dto.LibraryCreateRequestDto;
import com.example.bookstore.dto.LibraryResponseDto;
import com.example.bookstore.dto.LibraryUpdateRequestDto;
import com.example.bookstore.entity.Library;
import com.example.bookstore.entity.User;
import com.example.bookstore.exception.LibraryNotFoundException;
import com.example.bookstore.mapper.LibraryMapper;
import com.example.bookstore.repository.LibraryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class LibraryService {
    private LibraryRepository libraryRepository;
    private LibraryMapper libraryMapper;

    public List<LibraryResponseDto> getAllLibraries() {
        return libraryMapper.fromLibraryToLibraryResponseDto(libraryRepository.findAll());
    }

    public LibraryResponseDto findLibraryById(Long libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new LibraryNotFoundException(libraryId));
        return libraryMapper.fromLibraryToLibraryResponseDto(library);
    }

    public LibraryResponseDto createLibrary(LibraryCreateRequestDto libraryDto) {
        Library newLibrary = libraryMapper.fromLibraryCreateRequestDtoToLibrary(libraryDto);
        libraryRepository.save(newLibrary);
        return libraryMapper.fromLibraryToLibraryResponseDto(newLibrary);
    }

    public LibraryResponseDto updateBook(LibraryUpdateRequestDto libraryDto) {
        Library library = libraryRepository.findById(libraryDto.getLibraryId())
                .orElseThrow(() -> new LibraryNotFoundException(libraryDto.getLibraryId()));

        library.setAddress(libraryDto.getAddress());
        library.setTitle(libraryDto.getTitle());
        libraryRepository.save(library);

        return libraryMapper.fromLibraryToLibraryResponseDto(library);
    }


    public Map<String, String> deleteBookById(User user, Long libraryId) {
        Library deletedLibrary = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new LibraryNotFoundException(libraryId));

        deletedLibrary.setDeletedAt(LocalDateTime.now());
        deletedLibrary.setDeletedBy(user);
        libraryRepository.save(deletedLibrary);
        return Map.of("message", "Deletion of library with ID=" + libraryId + " was successful.");
    }
}
