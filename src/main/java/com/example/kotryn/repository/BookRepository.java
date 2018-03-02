package com.example.kotryn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kotryn.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}