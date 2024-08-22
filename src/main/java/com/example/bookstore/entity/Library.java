package com.example.bookstore.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted_at IS NULL")
public class Library implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String address;

    @OneToMany(
            mappedBy = "library",
            cascade = {
                    CascadeType.PERSIST
            }
    )
    private List<Book> books;

    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "deleted_by")
    private User deletedBy;
}
