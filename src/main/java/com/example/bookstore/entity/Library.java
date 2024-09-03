package com.example.bookstore.entity;

import com.example.bookstore.entity.base.SoftDeletable;
import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted_at IS NULL")
public class Library extends SoftDeletable {

    private String title;

    private String address;

    @OneToMany(
            mappedBy = "library",
            cascade = {
                    CascadeType.PERSIST
            }
    )
    private List<Book> books;
}
