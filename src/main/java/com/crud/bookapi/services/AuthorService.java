package com.crud.bookapi.services;

import com.crud.bookapi.entities.Author;
import com.crud.bookapi.models.RequestDTO;
import com.crud.bookapi.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public String createAuthor(RequestDTO<Author> request) {
        Author existingAuthor = authorRepository.findById(request.getData().getId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        if (Objects.nonNull(existingAuthor)) {
            return "Author already exists";
        }

        Author newAuthor= Author.builder()
                .firstName(request.getData().getFirstName())
                .lastName(request.getData().getLastName())
                .build();

        authorRepository.save(newAuthor);

        return "Author Created";
    }
}
