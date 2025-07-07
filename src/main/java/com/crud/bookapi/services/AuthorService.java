package com.crud.bookapi.services;

import com.crud.bookapi.dtos.AuthorDto;
import com.crud.bookapi.entities.Author;
import com.crud.bookapi.models.RequestDTO;
import com.crud.bookapi.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

   public Author addAuthor(AuthorDto dto) {
       Author author = Author.builder()
               .firstName(dto.getFirstName())
               .lastName(dto.getLastName())
               .build();

       return authorRepository.save(author);
   }

   public Author updateAuthor(Long id, AuthorDto dto) {
       Author author = authorRepository.findById(id)
               .orElseThrow(EntityNotFoundException::new);

       author.setFirstName(dto.getFirstName());
       author.setLastName(dto.getLastName());
       return authorRepository.save(author);
   }

   public List<Author> listAuthors () {
       return authorRepository.findAll();
   }

    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
        author.getBooks().forEach(b -> b.getAuthors().remove(author));
        authorRepository.delete(author);
    }
}
