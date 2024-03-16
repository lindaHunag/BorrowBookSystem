package com.example.demo.repository;

import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findAll();

    Book findByIsbn(String isbn);
}
