package com.crud.bookapi.repositories;

import com.crud.bookapi.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
