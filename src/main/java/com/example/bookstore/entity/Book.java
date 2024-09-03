package com.example.bookstore.entity;

import com.example.bookstore.entity.base.SoftDeletable;
import com.example.bookstore.entity.enums.Binding;
import com.example.bookstore.entity.enums.BookStatus;
import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Where(clause = "deleted_at IS NULL")
public class Book extends SoftDeletable {

    private String title;

    private String author;

    private Long pages;

    private Double cost;

    @Enumerated(EnumType.STRING)
    private Binding binding;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @ManyToOne
    private Library library;

    @ManyToOne
    private User receivedByUser;
}
