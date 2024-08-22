package com.example.bookstore.repository;

import com.example.bookstore.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
//    @Modifying
//    @Query("DELETE FROM Library l WHERE l.id = :libraryId")
//    Integer deleteLibraryById(@Param("libraryId") Long libraryId);
}
