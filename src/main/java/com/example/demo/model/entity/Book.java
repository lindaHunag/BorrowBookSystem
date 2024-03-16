package com.example.demo.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "[Book]")
public class Book {
    @Id
    @Column(name = "ISBN", length = 13)
    private String isbn;
    @Column(name = "Name", length = 30)
    private String name;
    @Column(name = "Author", length = 100)
    private String author;
    @Column(name = "Introduction", length = 300)
    private String introduction;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    private Inventory inventory;
}
