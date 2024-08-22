package com.example.bookstore.repository;

import com.example.bookstore.entity.Binding;
import com.example.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookRepository extends JpaRepository<Book, Long>, QuerydslPredicateExecutor<Book> {

//    @Modifying
//    @Query("DELETE FROM Book b WHERE b.id = :bookId")
//    Integer deleteBookById(@Param("bookId") Long bookId);

    Page<Book> findByAuthorContaining(String authorSubstring, Pageable pageable);

    Page<Book> findByPagesGreaterThan(Long minPages, Pageable pageable);

    Page<Book> findByCostLessThan(Double maxCost, Pageable pageable);

    Page<Book> findByBinding(Binding binding, Pageable pageable);

    Page<Book> findByLibraryId(Long libraryId, Pageable pageable);
}
