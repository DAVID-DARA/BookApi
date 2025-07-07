package com.crud.bookapi.services;

import com.crud.bookapi.dtos.BookDto;
import com.crud.bookapi.entities.Author;
import com.crud.bookapi.entities.Book;
import com.crud.bookapi.entities.Category;
import com.crud.bookapi.repositories.AuthorRepository;
import com.crud.bookapi.repositories.BookRepository;
import com.crud.bookapi.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;


    public Book addBook(BookDto dto) {
        Set<Author> authors = new HashSet<>(authorRepository.findAllById(dto.getAuthorIds()));
        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(dto.getCategoryIds()));
        Book book = Book.builder()
                .title(dto.getTitle())
                .authors(authors)
                .categories(categories)
                .build();
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookDto dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        book.setTitle(dto.getTitle());
        book.setAuthors(new HashSet<>(authorRepository.findAllById(dto.getAuthorIds())));
        book.setCategories(new HashSet<>(categoryRepository.findAllById(dto.getCategoryIds())));
        return bookRepository.save(book);
    }

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        book.getAuthors().forEach(a -> a.getBooks().remove(book));
        book.getCategories().forEach(c -> c.getBooks().remove(book));
        bookRepository.delete(book);
    }
}
