package com.intcomexintegraciones.Service.Impl;

import com.intcomexintegraciones.Exception.CategoryAlreadyExistsException;
import com.intcomexintegraciones.Model.Category;
import com.intcomexintegraciones.Repository.CategoryRepository;
import com.intcomexintegraciones.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        // Verificar si ya existe una categoría con el mismo nombre
        if (categoryRepository.existsByCategoryName(category.getCategoryName())) {
            throw new CategoryAlreadyExistsException("Ya existe una categoría con el nombre proporcionado.");
        }
        // Guardar la categoría si no existe una con el mismo nombre
        return categoryRepository.save(category);
    }
}