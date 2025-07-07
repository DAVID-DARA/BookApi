package com.crud.bookapi.controllers;

import com.crud.bookapi.dtos.AuthorDto;
import com.crud.bookapi.entities.Author;
import com.crud.bookapi.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> add(@RequestBody AuthorDto dto) {
        return ResponseEntity.ok(authorService.addAuthor(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody AuthorDto dto) {
        return ResponseEntity.ok(authorService.updateAuthor(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<Author>> list() {
        return ResponseEntity.ok(authorService.listAuthors());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}