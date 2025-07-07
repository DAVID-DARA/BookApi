package com.crud.bookapi.services;

import com.crud.bookapi.dtos.CategoryDto;
import com.crud.bookapi.entities.Book;
import com.crud.bookapi.entities.Category;
import com.crud.bookapi.repositories.BookRepository;
import com.crud.bookapi.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    public Category addCategory(CategoryDto dto) {
        Category category = Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, CategoryDto dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return categoryRepository.save(category);
    }

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        category.getBooks().forEach(b -> b.getCategories().remove(category));
        categoryRepository.delete(category);
    }

    public Category addBooksToCategory(Long id, Set<Long> bookIds) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        List<Book> books = bookRepository.findAllById(bookIds);
        for (Book book : books) {
            category.getBooks().add(book);
            book.getCategories().add(category);
        }
        return categoryRepository.save(category);
    }
}
