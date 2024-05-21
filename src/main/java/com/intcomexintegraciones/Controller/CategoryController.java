package com.intcomexintegraciones.Controller;

import com.intcomexintegraciones.Exception.CategoryAlreadyExistsException;
import com.intcomexintegraciones.Model.Category;
import com.intcomexintegraciones.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        try {
            Category newCategory = categoryService.createCategory(category);
            return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
        } catch (CategoryAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
