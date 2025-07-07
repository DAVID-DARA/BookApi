package com.crud.bookapi.repositories;

import com.crud.bookapi.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
