package com.intcomexintegraciones.Service;

import com.intcomexintegraciones.Model.Category;
import org.springframework.stereotype.Service;
@Service
public interface CategoryService {
    Category createCategory(Category category);
}