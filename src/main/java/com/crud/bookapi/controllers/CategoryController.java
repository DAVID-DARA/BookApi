package com.crud.bookapi.controllers;

import com.crud.bookapi.dtos.CategoryDto;
import com.crud.bookapi.entities.Category;
import com.crud.bookapi.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> add(@RequestBody CategoryDto dto) {
        return ResponseEntity.ok(categoryService.addCategory(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody CategoryDto dto) {
        return ResponseEntity.ok(categoryService.updateCategory(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<Category>> list() {
        return ResponseEntity.ok(categoryService.listCategories());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/add-books")
    public ResponseEntity<Category> addBooks(@PathVariable Long id, @RequestBody Set<Long> bookIds) {
        return ResponseEntity.ok(categoryService.addBooksToCategory(id, bookIds));
    }
}
