package com.example.bookstore.entity;

import com.example.bookstore.entity.base.BaseEntity;
import com.example.bookstore.entity.enums.ActionType;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookHistory extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User performedByUser;

    private LocalDateTime actionDate;

    private String comment;
}
