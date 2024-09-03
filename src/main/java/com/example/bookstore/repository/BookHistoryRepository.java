package com.example.bookstore.repository;

import com.example.bookstore.entity.BookHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookHistoryRepository extends JpaRepository<BookHistory, Long> {
}
