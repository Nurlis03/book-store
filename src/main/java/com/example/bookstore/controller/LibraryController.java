package com.example.bookstore.controller;

import com.example.bookstore.dto.LibraryCreateRequestDto;
import com.example.bookstore.dto.LibraryResponseDto;
import com.example.bookstore.dto.LibraryUpdateRequestDto;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class LibraryController {
    private LibraryService libraryService;

    @GetMapping("/libraries")
    public List<LibraryResponseDto> getAllLibraries() {
        return libraryService.getAllLibraries();
    }

    @GetMapping("/libraries/{id}")
    public LibraryResponseDto getLibraryById(@PathVariable("id") Long id) {
        return libraryService.findLibraryById(id);
    }

    @PostMapping("/libraries")
    public LibraryResponseDto createLibrary(@RequestBody LibraryCreateRequestDto libraryCreateRequestDto) {
        return libraryService.createLibrary(libraryCreateRequestDto);
    }

    @PutMapping("/libraries")
    public LibraryResponseDto updateLibrary(@RequestBody LibraryUpdateRequestDto libraryUpdateRequestDto) {
        return libraryService.updateBook(libraryUpdateRequestDto);
    }

    @DeleteMapping("/libraries/{id}")
    public Map<String, String> deleteLibrary(@AuthenticationPrincipal User user, @PathVariable("id") Long libraryId) {
        return libraryService.deleteBookById(user, libraryId);
    }
}
