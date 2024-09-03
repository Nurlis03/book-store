package com.example.bookstore.mapper;

import com.example.bookstore.dto.LibraryCreateRequestDto;
import com.example.bookstore.dto.LibraryResponseDto;
import com.example.bookstore.entity.Library;

import java.util.List;

public interface LibraryMapper {
    LibraryResponseDto fromLibraryToLibraryResponseDto(Library library);
    List<LibraryResponseDto> fromLibraryToLibraryResponseDto(List<Library> libraries);
    Library fromLibraryCreateRequestDtoToLibrary(LibraryCreateRequestDto libraryDto);
}
