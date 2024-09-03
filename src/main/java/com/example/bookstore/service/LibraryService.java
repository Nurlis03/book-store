package com.example.bookstore.service;

import com.example.bookstore.dto.LibraryCreateRequestDto;
import com.example.bookstore.dto.LibraryResponseDto;
import com.example.bookstore.dto.LibraryUpdateRequestDto;
import com.example.bookstore.entity.User;

import java.util.List;
import java.util.Map;

public interface LibraryService {
    List<LibraryResponseDto> getAllLibraries();
    LibraryResponseDto findLibraryById(Long libraryId);
    LibraryResponseDto createLibrary(LibraryCreateRequestDto libraryDto);
    LibraryResponseDto updateBook(LibraryUpdateRequestDto libraryDto);
    Map<String, String> deleteBookById(User user, Long libraryId);
}
