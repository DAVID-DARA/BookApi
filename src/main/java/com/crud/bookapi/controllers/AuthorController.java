package com.crud.bookapi.controllers;

import com.crud.bookapi.entities.Author;
import com.crud.bookapi.models.RequestDTO;
import com.crud.bookapi.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody RequestDTO<Author> requestDTO) {
        try {
            String result = authorService.createAuthor(requestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
